/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : PdmFileUpload.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 4. 16.  jongjung.kwon   Initial
 * ===========================================
 */
package com.rap.omc.framework.file.upload;

import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.constants.ResponseConstants;
import com.rap.omc.foundation.file.model.FCSFileVO;
import com.rap.omc.framework.file.upload.exception.FileUploadException;
import com.rap.omc.framework.file.upload.model.UploadFileInfo;
import com.rap.omc.framework.file.upload.policy.DefaultFileUploadPolicy;
import com.rap.omc.framework.file.upload.policy.FileUploadPolicy;
import com.rap.omc.framework.file.upload.policy.PdmFileUploadPolicy;
import com.rap.omc.framework.refresh.BeanRefreshSupport;
import com.rap.omc.util.NullUtil;

/**
 * <pre>
 * Class : PdmFileUpload
 * Description : TODO
 * </pre>
 * 
 * @author jongjung.kwon
 */
public class PdmFileUpload  implements InitializingBean, ApplicationContextAware, BeanRefreshSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(PdmFileUpload.class);

    public static final String GLOBAL_POLICY = null;
    private ApplicationContext applicationContext;
    protected FileUploadPolicy globalPolicy;
    protected Map<String, FileUploadPolicy> optionalPolicyMap;
    
    private PdmFileUploadPolicy appliedFilePolicy;
    private String appliedFilePolicyName;
    
    
    public PdmFileUploadPolicy getAppliedFilePolicy(){
        return appliedFilePolicy;
    }
    public void setAppliedFilePolicy(PdmFileUploadPolicy appliedFilePolicy, String appliedFilePolicyName){
        this.appliedFilePolicy = appliedFilePolicy;
        this.appliedFilePolicyName = appliedFilePolicyName;
    }
    public String getAppliedFilePolicyName(){
        return appliedFilePolicyName;
    }
    public PdmFileUpload()
    {
        super();
        this.globalPolicy      = new DefaultFileUploadPolicy();
        this.optionalPolicyMap = new HashMap<String, FileUploadPolicy>();
    }
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        this.applicationContext = applicationContext;
    }
    public void setGlobalPolicy(FileUploadPolicy globalPolicy)
    {
        this.globalPolicy = globalPolicy;
    }

    public void setOptionalPolicyMap(Map<String, FileUploadPolicy> optionalPolicyMap)
    {
        this.optionalPolicyMap = optionalPolicyMap;
    }
    public boolean validatePolicy(Map<String, Object> resultData){
        return true;
    }
    public void afterPropertiesSet()
    {
        CommonsMultipartResolver multipartResolver = (CommonsMultipartResolver)this.applicationContext.getBean("multipartResolver");
        if (multipartResolver != null){
            return; 
        }
        throw new FileUploadException("DSOFUP001", "CommonsMultipartResolver bean named multipartResolver should be defined.");
    }
    public boolean upload(MultipartFile multipartFile, String fileUploadPolicyName, List<FCSFileVO> mappedFileInfo, Map<String, Object> resultData)
    {
        return upload(Arrays.asList(new MultipartFile[] { multipartFile }), mappedFileInfo,resultData);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public final boolean upload(MultipartHttpServletRequest request, List<FCSFileVO> mappedFileInfo, Map<String, Object> resultData){
        List<MultipartFile> multipartFileList = new ArrayList<MultipartFile>();
        for (List localeList : request.getMultiFileMap().values()) {
            multipartFileList.addAll(localeList);
        }
        return upload(multipartFileList, mappedFileInfo,resultData);
    }
    public final boolean upload(List<MultipartFile> multipartFileList, List<FCSFileVO> mappedFileInfo, Map<String, Object> resultData){
        if(!validatePolicy(resultData)) return false;
        if(NullUtil.isNone(multipartFileList)) return true;
        List<UploadFileInfo> fileList = new ArrayList<UploadFileInfo>();
        LOGGER.debug("upload Loop Started");
        try {
            for (int i = 0; i < multipartFileList.size(); ++i) {
                MultipartFile multipartFile = multipartFileList.get(i);
                LOGGER.debug("File Creation Directory : {}",mappedFileInfo.get(i).getLocationFilePath());
                File renamedFile = this.getAppliedFilePolicy().getFileNameToSave(multipartFile.getOriginalFilename(), mappedFileInfo.get(i).getLocationFilePath());
                if (!(renamedFile.getParentFile().exists())) renamedFile.getParentFile().mkdirs();
                multipartFile.transferTo(renamedFile);
                UploadFileInfo fileInfo = new UploadFileInfo(multipartFile, renamedFile);
                String fullPath = fileInfo.getServerPath().replaceAll("\\\\", "/");
                LOGGER.debug("fullPath:{}",fullPath);
                String filePath = fullPath.substring(fullPath.lastIndexOf(mappedFileInfo.get(i).getLocationFilePath())+ mappedFileInfo.get(i).getLocationFilePath().length());
                float sizes = fileInfo.getSize();
                String sysFileName   = fileInfo.getServerFileName();
                String userFileName  = fileInfo.getClientFileName();
                userFileName = URLDecoder.decode(userFileName,"utf-8");
                mappedFileInfo.get(i).setFilePath(filePath);
                mappedFileInfo.get(i).setSizes(sizes);
                mappedFileInfo.get(i).setSysFileName(sysFileName);
                mappedFileInfo.get(i).setUserFileName(userFileName);                
                fileList.add(fileInfo);
            }
        }catch (Exception ex) {
            try{
                LOGGER.debug("On Error: cleanupTransferedFile Started");
                cleanupTransferedFile(fileList,resultData);
            }catch(Exception e){
                ;//임시 삭제시 에러 발생인 경우 Skip
            }
            resultData.put(GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_FILE_POLICY_NULL);
            resultData.put(GlobalConstants.M_MESSAGE, "[Foundation]No Such Policy exists : " + this.getAppliedFilePolicyName() + ",System Error: " + ex.getMessage());
            return false;
        }
        return true;
    }
    public final List<UploadFileInfo> uploadWithTargetDir(List<MultipartFile> multipartFileList, String targetDir, Map<String, Object> resultData){
        if((NullUtil.isNone(multipartFileList)) || !this.validateFileSizes(multipartFileList, resultData)) return new ArrayList<UploadFileInfo>();
        if(!validatePolicy(resultData)) return new ArrayList<UploadFileInfo>();
        List<UploadFileInfo> fileList = new ArrayList<UploadFileInfo>();
        LOGGER.debug("upload Loop Started");
        try {
            for (int i = 0; i < multipartFileList.size(); ++i) {
                MultipartFile multipartFile = multipartFileList.get(i);
                LOGGER.debug("File Creation Directory : {}",targetDir);
                File renamedFile = this.getAppliedFilePolicy().getFileNameToSave(multipartFile.getOriginalFilename(), targetDir);
                if (!(renamedFile.getParentFile().exists())) renamedFile.getParentFile().mkdirs();
                multipartFile.transferTo(renamedFile);
                UploadFileInfo fileInfo = new UploadFileInfo(multipartFile, renamedFile);
                fileList.add(fileInfo);
            }
        }catch (Exception ex) {
            try{
                LOGGER.debug("On Error: cleanupTransferedFile Started");
                cleanupTransferedFile(fileList,resultData);
            }catch(Exception e){
                ;//임시 삭제시 에러 발생인 경우 Skip
            }
            resultData.put(GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_FILE_POLICY_NULL);
            resultData.put(GlobalConstants.M_MESSAGE, "[Foundation]No Such Policy exists : " + this.getAppliedFilePolicyName() + ",System Error: " + ex.getMessage());
            return new ArrayList<UploadFileInfo>();
        }
        return fileList;
    }
    public final boolean validateFileSizes(List<MultipartFile> multipartFileList, Map<String, Object> resultData){
        if (this.getAppliedFilePolicy() == null){
            resultData.put(GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_FILE_POLICY_NULL);
            resultData.put(GlobalConstants.M_MESSAGE, "[Foundation]No Such Policy exists : " + this.getAppliedFilePolicyName());
            return false;
        }
        if(NullUtil.isNone(multipartFileList)) return true;
        long filesTotalSize = 0;
        int i = 0;
        boolean isSuccess  = true;
        for(MultipartFile file : multipartFileList){
            filesTotalSize = filesTotalSize + file.getSize();
            try{
            this.getAppliedFilePolicy().validate(file, this.getAppliedFilePolicyName(), filesTotalSize);
            }catch (Exception e){
                resultData.put(GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_FILE_VALIDATION_ERROR);
                resultData.put(GlobalConstants.M_MESSAGE, "[Foundation]File Validation Erorr During file Upload Policy(" + this.getAppliedFilePolicyName() + ", File: " + multipartFileList.get(i).getOriginalFilename() + "): " + e.getMessage());
                isSuccess = false;
            }
            i++;
        }
        return isSuccess;
    }  
    protected final boolean cleanupTransferedFile(List<UploadFileInfo> uploadFiles,Map<String, Object> resultData)
    {
        for (int i = 0; i < uploadFiles.size(); ++i){
            if(!cleanupTransferedFile(uploadFiles.get(i),resultData)) return false;
        }
        return true;
    }
    private final boolean cleanupTransferedFile(UploadFileInfo uploadFile,Map<String, Object> resultData)
    {
       try {
           File deleteFile = uploadFile.getServerFile();
           boolean isDeleted = deleteFile.delete();
           if (!(isDeleted)) LOGGER.error("Upload rollback process failed : following file cannot be deleted. [{}]", deleteFile.getAbsolutePath());
        }
        catch (Exception e)
        {
            LOGGER.debug(e.getMessage());
        }
       return true;//임시화일 삭제시 에러 발생해도 skip하도록 처리
    }
}
