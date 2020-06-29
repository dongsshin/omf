package com.rap.omc.framework.file.upload;


import java.io.File;
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

import com.rap.omc.framework.file.upload.exception.FileUploadException;
import com.rap.omc.framework.file.upload.model.UploadFileInfo;
import com.rap.omc.framework.file.upload.policy.DefaultFileUploadPolicy;
import com.rap.omc.framework.file.upload.policy.FileUploadPolicy;
import com.rap.omc.framework.refresh.BeanRefreshSupport;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class FileUpload implements InitializingBean , ApplicationContextAware , BeanRefreshSupport
{

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUpload.class);

    
    public static final String GLOBAL_POLICY = null;

    private ApplicationContext applicationContext;

    private FileUploadPolicy globalPolicy = new DefaultFileUploadPolicy();

    
    private Map<String, FileUploadPolicy> optionalPolicyMap = new HashMap();

    
    public void setApplicationContext(ApplicationContext applicationContext)throws BeansException
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

    
    public void afterPropertiesSet()
    {
        CommonsMultipartResolver multipartResolver = (CommonsMultipartResolver)this.applicationContext.getBean("multipartResolver");
        if (multipartResolver != null) return;
        throw new FileUploadException("DSOFUP001","CommonsMultipartResolver bean named multipartResolver should be defined.");
    }

    
    public UploadFileInfo upload(MultipartFile multipartFile)
    {
        return upload(multipartFile, null);
    }

    
    public UploadFileInfo upload(MultipartFile multipartFile, String policyName)
    {
        return upload(multipartFile, policyName, null);
    }

    
    public UploadFileInfo upload(MultipartFile multipartFile, String policyName, String subDir)
    {
        List list = upload(Arrays.asList(new MultipartFile[] { multipartFile }), policyName, subDir);
        return ((UploadFileInfo)list.get(0));
    }

    
    public List<UploadFileInfo> upload(List<MultipartFile> multipartFileList)
    {
        return upload(multipartFileList, null);
    }

    
    public List<UploadFileInfo> upload(List<MultipartFile> multipartFileList, String policyName)
    {
        return upload(multipartFileList, policyName, null);
    }

    
    public List<UploadFileInfo> upload(MultipartHttpServletRequest request)
    {
        return upload(request, null);
    }

    
    public List<UploadFileInfo> upload(MultipartHttpServletRequest request, String policyName)
    {
        return upload(request, policyName, null);
    }

    
    
	public List<UploadFileInfo> upload(MultipartHttpServletRequest request, String policyName, String subDir)
    {
        List multipartList = new ArrayList();
        for (List localeList : request.getMultiFileMap().values()) {
            multipartList.addAll(localeList);
        }
        
        return upload(multipartList, policyName, subDir);
    }

    
    protected List<UploadFileInfo> upload(List<MultipartFile> multipartFileList, String policyName,
            String subDir)
    {
        FileUploadPolicy policy = null;
        String filePolicyName = "";
        
        if (policyName == null) {
            policy = this.globalPolicy;
            filePolicyName = "globalPolicy";
        }
        else {
            policy = (FileUploadPolicy)this.optionalPolicyMap.get(policyName);
            filePolicyName = policyName;
        }
        if (policy == null) throw new FileUploadException("DSOFUP002", "No Such Policy exists : " + filePolicyName);
        
        List fileList = new ArrayList();
        try {
            int filesTotalSize = 0;
            for (int i = 0; i < multipartFileList.size(); ++i) {
                MultipartFile multipartFile = (MultipartFile)multipartFileList.get(i);
                
                if (!(multipartFile.isEmpty())) {
                    policy.validate(multipartFile, filePolicyName, filesTotalSize);
                    File renamedFile = policy.getFileNameToSave(multipartFile.getOriginalFilename(), subDir);
                    
                    if (!(renamedFile.getParentFile().exists())) {
                        renamedFile.getParentFile().mkdirs();
                    }
                     multipartFile.transferTo(renamedFile);
                    
                    fileList.add(new UploadFileInfo(multipartFile, renamedFile));
                    filesTotalSize = (int)(filesTotalSize + multipartFile.getSize());
                }
            }
        }
        catch (Exception ex) {
            cleanupTransferedFile(fileList);
            if (ex instanceof FileUploadException) {
                throw ((FileUploadException)ex);
            }
            throw new FileUploadException(ex);
        }
        return fileList;
    }

    
    public void cleanupTransferedFile(List<UploadFileInfo> uploadFiles)
    {
        for (int i = 0; i < uploadFiles.size(); ++i)
            try {
                File deleteFile = ((UploadFileInfo)uploadFiles.get(i)).getServerFile();
                
                boolean isDeleted = deleteFile.delete();
                if (!(isDeleted))
                    LOGGER.error("Upload rollback process failed : following file cannot be deleted. [{}]",
                            deleteFile.getAbsolutePath());
            }
            catch (Exception e)
            {
                LOGGER.debug(e.getMessage());
            }
    }

    
    public void cleanupTransferedFile(UploadFileInfo uploadFile)
    {
        try {
            File deleteFile = uploadFile.getServerFile();
            
            boolean isDeleted = deleteFile.delete();
            if (!(isDeleted))
                LOGGER.error("Upload rollback process failed : following file cannot be deleted. [{}]", deleteFile.getAbsolutePath());
        }
        catch (Exception e)
        {
            LOGGER.debug(e.getMessage());
        }
    }
}
