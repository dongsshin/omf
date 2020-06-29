/**
 * ===========================================
 * System Name : LGE PLM Project
 * Program ID : FileDextServiceImpl.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 7. 26.  Noh.M.S   Initial
 * ===========================================
 */
package com.rap.file.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.rap.file.service.FileCommonService;
import com.rap.omc.api.object.dom.BusinessObjectRoot;
import com.rap.omc.api.object.dom.Files;
import com.rap.omc.api.object.model.FilesVO;
import com.rap.omc.api.oql.utility.OmcFoundationConstant;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.omc.FileFoundationUtil;
import com.rap.omc.api.util.omc.ThreadLocalUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.constants.ResponseConstants;
import com.rap.omc.foundation.file.model.FCSFileVO;
import com.rap.omc.foundation.file.model.FCSParameterVO;
import com.rap.omc.foundation.file.model.FcsLocationVO;
import com.rap.omc.foundation.file.model.OtpVO;
import com.rap.omc.framework.file.upload.FileUploadRequest;
import com.rap.omc.framework.file.upload.PdmFileUpload;
import com.rap.omc.framework.file.upload.policy.PdmFileUploadPolicy;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.HttpEntityUtil;
import com.rap.omc.util.file.FCSServerLocationUtil;
import com.rap.omc.util.file.FileCommonUtil;
import com.rap.omc.util.file.OmcFileConstants;

import net.sf.json.JSONObject;

/**
 * <pre>
 * Class : FileDextServiceImpl
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
@Service("fileCommonService")
public class FileCommonServiceImpl implements FileCommonService {
    static final Logger LOGGER = LoggerFactory.getLogger(FileCommonServiceImpl.class);
    
    @Resource(name="restTemplate")
    protected RestTemplate restTemplate;
    @SuppressWarnings("unchecked")
    @Override
    public List<FCSParameterVO> getFCSParameterVOList(String oneTimePasswd){
        OtpVO outputOtp = FCSServerLocationUtil.getOtp(new OtpVO(oneTimePasswd));
        Map<String,Object>   sessionMap = FCSServerLocationUtil.convertSessionInfo(outputOtp);
        
        List<FCSParameterVO> parameterVOList = (List<FCSParameterVO>)sessionMap.get(OmcFileConstants.C_FCS_PARAMETER_VO_LIST);
        setSessionInfo(sessionMap);
        return parameterVOList;
    }
    private List<FCSFileVO> convertJsonToFileVO(List<FCSParameterVO> parameterVOList){    
        List<FCSFileVO> resultFileVOList = new ArrayList<FCSFileVO>();
        String userId = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, null);
        
        for(FCSParameterVO vo : parameterVOList){
            FCSFileVO fcsFileVO = new FCSFileVO();
            String bizObid = vo.getBusinessObjectObid();
            //* Main WAS에서 DB Commit되지 않은상태(예: Object생성시)에서 Call될 수 있기 때문에 Object를 다시 Instance화하면 안됨.
            //BusinessObjectRoot bizDom = new BusinessObjectRoot(bizObid);
            fcsFileVO.setRecordMode(GlobalConstants.FILE_RECOMODE_CREATE);
            fcsFileVO.setFromObid(bizObid);
            fcsFileVO.setLifeCycle(vo.getBusinessObjectLifeCycle());
            fcsFileVO.setAssignedType(vo.getAssignedType());
            fcsFileVO.setFileFormat(vo.getFileFormat());
            fcsFileVO.setUserFileName(vo.getUserFileName());
            
            FcsLocationVO storeVO = FCSServerLocationUtil.getCurrentStoreForLifeCycle(vo.getBusinessObjectLifeCycle());
            FcsLocationVO locationVO = FCSServerLocationUtil.getCurrentLocationForUser(storeVO.getStoreName(), userId);
            fcsFileVO.setLocationFilePath(locationVO.getFilePath());
            resultFileVOList.add(fcsFileVO);
        }
        return resultFileVOList; 
    }    
    
    /**
     * 
     * @param fileObid
     * @return
     * @see lgcns.rnd.application.file.service.FileCommonService#getFullFlePath(java.lang.String)
     */
    @Override
    public FilesVO getDetailedFileInfo(String fileObid){
        return FileFoundationUtil.getDetailedFileInfo(fileObid);
    }
    /**
     * 
     * @param fileList
     * @param formatList
     * @param fileLocationPath
     * @param resultData
     * @see lgcns.rnd.application.file.service.FileCommonService#makeUploadFileSystemInfo(java.util.List, java.util.List, java.lang.String, java.util.Map)
     */
    @Override
    public void makeUploadFileSystemInfo(PdmFileUploadPolicy uploadPolicy, List<String> fileList, List<String> formatList, String fileLocationPath, Map<String, Object> resultData){
        // TODO Auto-generated method stub
        String rootPath = FileCommonUtil.getAbsolutePath(uploadPolicy.getUploadTargetDir(),fileLocationPath);
        List<HashMap<String, Object>> fileListVO= new ArrayList<HashMap<String, Object>>();     
        try { 
            int inx=0;
            for(String fileInfo : fileList){
                File sysFile = FileCommonUtil.makeTempDir(rootPath);
                HashMap<String, Object> fileData = new HashMap<String, Object>();
                if(!NullUtil.isNull(sysFile)){
                    fileData.put("userFileName", fileInfo);
                    fileData.put("sysFileName", sysFile.getName());
                    fileData.put("filePath", sysFile.getAbsolutePath().substring(rootPath.length()));                       
                    fileData.put("sizes", 0);    
                    fileData.put("fileFormat", formatList.get(inx));
                    fileData.put("realFilePath", sysFile.getAbsolutePath());
                    fileListVO.add(fileData);           
                }
                inx++;
            }
            resultData.put(GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_SUCCESS);
            resultData.put(GlobalConstants.M_MESSAGE, "");
            resultData.put(GlobalConstants.D_FILES, fileListVO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            resultData.put(GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_BUSINESS_DEFAULT_ERROR);
            resultData.put(GlobalConstants.M_MESSAGE, e.getMessage());
            resultData.put(GlobalConstants.D_FILES, "");
        }
    }
    /**
     * 
     * @param url
     * @param oneTimePasswd
     * @param remoteAddr
     * @return
     * @see lgcns.rnd.application.file.service.FileCommonService#checkOtp(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public JSONObject checkOtp(String url, String oneTimePasswd, String remoteAddr){
        // TODO Auto-generated method stub
        HttpEntityUtil pdmEntity = new HttpEntityUtil();
        pdmEntity.addFormData("oneTimePasswd", oneTimePasswd);
        pdmEntity.addFormData("remoteAddr", remoteAddr);
        pdmEntity.setHttpEntity();
        HttpEntity<?> entity = pdmEntity.getHttpEntity();
        ResponseEntity<JSONObject> response  = restTemplate.postForEntity(url, entity, JSONObject.class);
        JSONObject result = (JSONObject)response.getBody();
        return result;
    }
    /**
     * 
     * @param url
     * @param oneTimePasswd
     * @param remoteAddr
     * @param locationObid
     * @return
     * @see lgcns.rnd.application.file.service.FileCommonService#getFileLocationPath(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public JSONObject getFileLocationPath(String url, String oneTimePasswd, String remoteAddr, String locationName){
        HttpEntityUtil pdmEntity = new HttpEntityUtil();
        pdmEntity.addFormData("oneTimePasswd", oneTimePasswd);
        pdmEntity.addFormData("remoteAddr"   , remoteAddr);
        pdmEntity.addFormData("locationName" , locationName);
        pdmEntity.setHttpEntity();
        HttpEntity<?> entity = pdmEntity.getHttpEntity();
        ResponseEntity<JSONObject> response  = restTemplate.postForEntity(url, entity, JSONObject.class);
        JSONObject result = (JSONObject)response.getBody();
        return result;
    }
    /**
     * 
     * @param url
     * @param obid
     * @param remoteAddr
     * @return
     * @see lgcns.rnd.application.file.service.FileCommonService#getFilePath(java.lang.String, java.lang.String, java.lang.String)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public JSONObject getFilePath(String url, String obid, String remoteAddr){
        // TODO Auto-generated method stub
        HttpEntityUtil pdmEntity = new HttpEntityUtil();
        pdmEntity.addFormData("obid", obid);
        pdmEntity.addFormData("remoteAddr", remoteAddr);
        pdmEntity.setHttpEntity();
        
        HttpEntity entity = pdmEntity.getHttpEntity();
        
        ResponseEntity<JSONObject> response  = restTemplate.postForEntity(url, entity, JSONObject.class);
        JSONObject result = (JSONObject)response.getBody();
        return result;
    }
    /**
     * 
     * @param url
     * @param obid
     * @param remoteAddr
     * @return
     * @see lgcns.rnd.application.file.service.FileCommonService#getAllFilePath(java.lang.String, java.util.List, java.lang.String)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public JSONObject getAllFilePath(String url, List<String> obid, String remoteAddr){
        // TODO Auto-generated method stub
        HttpEntityUtil pdmEntity = new HttpEntityUtil();
        pdmEntity.addFormData("obid", obid.toString().substring(1, obid.toString().length()-1));
        pdmEntity.addFormData("remoteAddr", remoteAddr);
        pdmEntity.setHttpEntity();

        HttpEntity entity = pdmEntity.getHttpEntity();
    
        ResponseEntity<JSONObject> response  = restTemplate.postForEntity(url, entity, JSONObject.class);
        JSONObject result = (JSONObject)response.getBody();
        return result;
    }
    /**
     * 
     * 
     * @param fileUpload
     * @param parameterVOList
     * @param multipartFileList
     * @param resultData
     * @return
     * @see lgcns.rnd.application.file.service.FileCommonService#fileUploadProcessForGeneral(omc.framework.fileupload.upload.PdmFileUpload, java.util.List, java.util.List, java.util.Map)
     */
    @Override
    public boolean fileUploadProcessForGeneral(PdmFileUpload fileUpload, List<FCSParameterVO> parameterVOList, List<MultipartFile> multipartFileList, Map<String, Object> resultData){
        List<FCSFileVO> resultFileVOList = convertJsonToFileVO(parameterVOList);
        if(!fileUpload.upload(multipartFileList, resultFileVOList,resultData )) return false;
        Map<String,Object> uploadData = new HashMap<String,Object>();
        List<FilesVO> filesVOList = makeFileUploadVO(resultFileVOList,resultFileVOList);
        resultData.put(GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_SUCCESS);
        resultData.put(GlobalConstants.M_MESSAGE, "");
        resultData.put(GlobalConstants.M_SYSTEM_ERROR_MESSAGE, "");
        uploadData.put(GlobalConstants.D_FILES,  filesVOList);
        resultData.put(GlobalConstants.M_DATA, uploadData);
        return true;
    }
    /**
     * 
     * 
     * @param fileUpload
     * @param parameterVOList
     * @param resultData
     * @see lgcns.rnd.application.file.service.FileCommonService#fileCopyInFCSServerForObject(omc.framework.fileupload.upload.PdmFileUpload, java.util.List, java.util.Map)
     */
    @Override
    public void fileCopyInFCSServerForObject(PdmFileUpload fileUpload, List<FCSParameterVO> parameterVOList, Map<String, Object> resultData){
        LOGGER.debug("fileCopyInFCSServerForObject Started");
        if(NullUtil.isNone(parameterVOList)) {
            resultData.put(GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_NO_META);
            resultData.put(GlobalConstants.D_FILES,  "");
            resultData.put(GlobalConstants.M_SYSTEM_ERROR_MESSAGE, "[Founation.FileCommonServiceImpl.fileCopyInFCSServerForObject]No Data to Copy.");
            return;
        }
        try{
            String targetRootPath = FileCommonUtil.getAbsolutePath(fileUpload.getAppliedFilePolicy().getUploadTargetDir(),parameterVOList.get(0).getTargetRootPath());
            for(FCSParameterVO parameterVO : parameterVOList ){
                String rootPath = FileCommonUtil.getAbsolutePath(fileUpload.getAppliedFilePolicy().getUploadTargetDir(),parameterVO.getSourceRootPath());
                File sourceFile = FileCommonUtil.getFile(rootPath+parameterVO.getSourceFilePath());
                File targetFile = FileCommonUtil.makeTempDir(targetRootPath);
                try {
                    FileCommonUtil.copyFile( sourceFile, targetFile);
                    if(targetFile.exists()){
                        parameterVO.setTargetFilePath(targetFile.getAbsolutePath().substring(targetRootPath.length()));
                        parameterVO.setTargetFileName(targetFile.getName());
                        parameterVO.setTargetFileSize((float)targetFile.length());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    resultData.put(GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_FCS_FILE_COPY_ERROR);
                    resultData.put(GlobalConstants.D_FILES,  "");
                    resultData.put(GlobalConstants.M_SYSTEM_ERROR_MESSAGE, "[Founation.FileCommonServiceImpl.fileCopyInFCSServerForObject] File Copy Error(Main)." + e.getMessage());
                    return;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            resultData.put(GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_FCS_FILE_COPY_ERROR);
            resultData.put(GlobalConstants.D_FILES,  "");
            resultData.put(GlobalConstants.M_SYSTEM_ERROR_MESSAGE, "[Founation.FileCommonServiceImpl.fileCopyInFCSServerForObject] File Copy Error(Etc)." + e.getMessage());
        }
        resultData.put(GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_SUCCESS);
        resultData.put(GlobalConstants.D_FILES,  parameterVOList);
        resultData.put(GlobalConstants.M_SYSTEM_ERROR_MESSAGE, "");
        return;
    }
    /**
     * 
     * 
     * @param fileUpload
     * @param jsonFileVOList
     * @param x5
     * @param resultData
     * @see lgcns.rnd.application.file.service.FileCommonService#fileUploadProcessForDext(omc.framework.fileupload.upload.PdmFileUpload, java.lang.String, lgcns.rnd.application.file.dext.model.DEXTUploadX5Request, java.util.Map)
     */
    @Override
    public void fileUploadProcessForDext(PdmFileUpload fileUpload, String jsonFileVOList,FileUploadRequest fileUploadRequest,Map<String, Object> resultData){
        LOGGER.debug("fileUploadSubProcess Started");
        Map<String,Object> uploadData = new HashMap<String,Object>();
        List<FCSFileVO> fcsFileVOList = new ArrayList<FCSFileVO>();
        try{
            LOGGER.debug("convertJsonToFileVO Started");
            if(!convertJsonToFileVO(jsonFileVOList,resultData,fcsFileVOList)) {
                uploadData.put(GlobalConstants.D_FILES,  fcsFileVOList);
                resultData.put(GlobalConstants.M_DATA, uploadData);
                resultData.put(GlobalConstants.M_SYSTEM_ERROR_MESSAGE, "");
                return;
            }
            if(NullUtil.isNone(fcsFileVOList)){
                resultData.put(GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_NO_META);
                resultData.put(GlobalConstants.M_MESSAGE, "[Foundation.FileDextController.fileUploadSubProcess]No File Meta)");
                uploadData.put(GlobalConstants.D_FILES,  fcsFileVOList);
                resultData.put(GlobalConstants.M_DATA, uploadData);
                resultData.put(GlobalConstants.M_SYSTEM_ERROR_MESSAGE, "");
                return;
            }
            setUserFileName(fcsFileVOList,fileUploadRequest);
            cleanCRUDMode(fcsFileVOList,fileUploadRequest.getUniqueIdList());
            List<FCSFileVO> uploadedFCSFileVOList = new ArrayList<FCSFileVO>();
            /*****************************************************************************************/
            LOGGER.debug("uploadFilesValidation Started");
            if(!uploadFilesValidation(fileUploadRequest, fcsFileVOList, resultData)){
                addResultDataForError(resultData,uploadData,fcsFileVOList);
                return;
            }
            List<FCSFileVO> mappedFileInfo = new ArrayList<FCSFileVO>();
            LOGGER.debug("getMappedFilesVOList Started");
            if(!createMappedFilesVOList(fileUploadRequest,fcsFileVOList,resultData,mappedFileInfo)){
                addResultDataForError(resultData,uploadData,fcsFileVOList);
                return;
            }
            LOGGER.debug("fileDextService.validateFileSizes Started");
            if(!validateFileSizes(fileUploadRequest, fileUpload, resultData, mappedFileInfo)){
                addResultDataForError(resultData,uploadData,fcsFileVOList);
                return;
            }
            //ThreadLocalUtil.set(ThreadLocalUtil.KEY.userId, userId);
            /*****************************************************************************************/
            LOGGER.debug("fileDextService.txnUploadFiles Started");
            if(!uploadFilesForDext(fileUploadRequest, fileUpload, fcsFileVOList, resultData,uploadedFCSFileVOList)){
                addResultDataForError(resultData,uploadData,fcsFileVOList);
                return;
            };
            LOGGER.debug("makeFileUploadVO Started");
            List<FilesVO> filesVOList = makeFileUploadVO(fcsFileVOList,uploadedFCSFileVOList);
            resultData.put(GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_SUCCESS);
            resultData.put(GlobalConstants.M_MESSAGE, "");
            resultData.put(GlobalConstants.M_SYSTEM_ERROR_MESSAGE, "");
            uploadData.put(GlobalConstants.D_FILES,  filesVOList);
            resultData.put(GlobalConstants.M_DATA, uploadData);
        }catch(Exception e){
            resultData.put(GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_SYSTEM_DEFAULT_ERROR);
            resultData.put(GlobalConstants.M_MESSAGE, "[Foundation.FileDextController.fileUploadSubProcess]Error Occured during upload." + OmcFoundationConstant.newline + "System Error Message:" + e.getMessage());
            resultData.put(GlobalConstants.M_SYSTEM_ERROR_MESSAGE, e.getMessage());
            uploadData.put(GlobalConstants.D_FILES,  fcsFileVOList);
            resultData.put(GlobalConstants.M_DATA, uploadData);
            e.printStackTrace();
        }
    }
    /**
     * 
     *
     * @param x5
     * @param fileUpload
     * @param willBeUploadedList
     * @param resultData
     * @param uploadedFCSFileVOList
     * @return
     */
    private boolean uploadFilesForDext(FileUploadRequest fileUploadRequest, PdmFileUpload fileUpload, List<FCSFileVO> willBeUploadedList, Map<String, Object> resultData, List<FCSFileVO> uploadedFCSFileVOList){
        String userID = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, null);
        if(NullUtil.isNone(userID)){
            resultData.put(GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_USER_ID_INVALID);
            resultData.put(GlobalConstants.M_MESSAGE, "[Foundation]User Id invalid.");
            return false;
        }        
        if(!getMappedFilesVOList(fileUploadRequest,willBeUploadedList,resultData,uploadedFCSFileVOList,userID)) return false;
        if(NullUtil.isNone(fileUploadRequest.getFileDataList())) return true;
        if(!fileUpload.upload(fileUploadRequest.getFileDataList(), uploadedFCSFileVOList,resultData )) return false;
        return true;
    }
    /**
     * 
     *
     * @param resultData
     * @param uploadData
     * @param fcsFileVOList
     */
    private void addResultDataForError(Map<String, Object> resultData,Map<String,Object> uploadData,List<FCSFileVO> fcsFileVOList){
        uploadData.put(GlobalConstants.D_FILES,  fcsFileVOList);
        resultData.put(GlobalConstants.M_DATA, uploadData);
        resultData.put(GlobalConstants.M_SYSTEM_ERROR_MESSAGE, "");
    }
    /**
     * 
     *
     * @param fcsFileVOList
     * @param uniqueIdList
     */
    private void cleanCRUDMode(List<FCSFileVO> fcsFileVOList,List<String> uniqueIdList){
        if(!NullUtil.isNone(uniqueIdList)){
            for(String uniqueId : uniqueIdList){
                for(FCSFileVO vo : fcsFileVOList){
                    if(uniqueId.equals(vo.getFileUniqueId())){
                        if(!NullUtil.isNone(vo.getRecordMode())){
                            if(!vo.getRecordMode().equals(GlobalConstants.FILE_RECOMODE_DELETE)){
                                vo.setRecordMode(GlobalConstants.FILE_RECOMODE_CREATE);
                            }
                        }else{
                            vo.setRecordMode(GlobalConstants.FILE_RECOMODE_CREATE);
                        }
                    }
                    if(vo.getRecordMode() == null) vo.setRecordMode(GlobalConstants.FILE_RECOMODE_EQUAL);
                }
            }
        }
        for(FCSFileVO vo : fcsFileVOList){
            if(!NullUtil.isNone(vo.getRecordMode()) && vo.getRecordMode().equals(GlobalConstants.FILE_RECOMODE_CREATE)){
                vo.setOldObid(findOldObid(fcsFileVOList,vo.getFileUniqueId()));
            }
            if(NullUtil.isNone(vo.getRecordMode())) vo.setRecordMode("=");
        }
    }
    /**
     * 
     *
     * @param fcsFileVOList
     * @param uniquFileId
     * @return
     */
    private String findOldObid(List<FCSFileVO> fcsFileVOList,String uniquFileId){
        for(FCSFileVO vo : fcsFileVOList){
            if(uniquFileId.equals(vo.getFileUniqueId()) &&!NullUtil.isNone(vo.getRecordMode()) && vo.getRecordMode().equals(GlobalConstants.FILE_RECOMODE_DELETE)){
                return vo.getObid();
            }
        }
        return "";
    }
    /**
     * 
     *
     * @param fcsFileVOList
     * @param uploadedFCSFileVOList
     * @return
     */
    private List<FilesVO> makeFileUploadVO(List<FCSFileVO> fcsFileVOList,List<FCSFileVO> uploadedFCSFileVOList){
        
        List<FilesVO> fileList = new ArrayList<FilesVO>();
        for(FCSFileVO vo : fcsFileVOList){
            if(vo.getRecordMode().equals(GlobalConstants.FILE_RECOMODE_CREATE)){
                FilesVO tempVO = convertFCSFileToFilesVO(vo);
                tempVO.setRecordMode(GlobalConstants.FILE_RECOMODE_CREATE);
                fileList.add(tempVO);
            }else if(vo.getRecordMode().equals(GlobalConstants.FILE_RECOMODE_DELETE)){
                Files tempDom = new Files(vo.getObid());
                tempDom.getVo().setSql("");
                tempDom.getVo().setColumns("");
                tempDom.getVo().setRecordMode(GlobalConstants.FILE_RECOMODE_DELETE);
                fileList.add(tempDom.getVo());
            }else if(vo.getRecordMode().equals(GlobalConstants.FILE_RECOMODE_UPDATE)){
                Files tempDom = new Files(vo.getObid());
                tempDom.getVo().setSql("");
                tempDom.getVo().setColumns("");
                tempDom.getVo().setRecordMode(GlobalConstants.FILE_RECOMODE_UPDATE);
                fileList.add(modifyFilesVO(tempDom.getVo(),vo));
            }else{
                Files tempDom = new Files(vo.getObid());
                tempDom.getVo().setRecordMode("=");
                tempDom.getVo().setSql("");
                tempDom.getVo().setColumns("");
                tempDom.getVo().setRecordMode(GlobalConstants.FILE_RECOMODE_EQUAL);
                fileList.add(tempDom.getVo());
            }
        }
        return fileList;
    }
    /**
     * 
     *
     * @param fcsFileVO
     * @return
     */
    private FilesVO convertFCSFileToFilesVO(FCSFileVO fcsFileVO){
        FilesVO fileVO = new FilesVO();
        //fileVO.setLifeCycle(ApplicationSchemaConstants.LIFECYCLE_FILE);
        //fileVO.setStates(ApplicationSchemaConstants.STATE_FILE_EXISTS);
        fileVO.setTitles(fcsFileVO.getUserFileName());
        //fileVO.setFileTimestamp(OmcUniqueIDGenerator.getObid());
        fileVO.setDescriptions(fcsFileVO.getDescriptions());
        fileVO.setFromObid(fcsFileVO.getFromObid());
        fileVO.setFileFormat(fcsFileVO.getFileFormat());
        fileVO.setFileStore(fcsFileVO.getFileStore());
        fileVO.setAssignedType(fcsFileVO.getAssignedType());
        fileVO.setSizes(fcsFileVO.getSizes());
        fileVO.setUserFileName(fcsFileVO.getUserFileName());
        fileVO.setFilePath(fcsFileVO.getFilePath());
        fileVO.setSysFileName(fcsFileVO.getSysFileName());
        fileVO.setFileLocation(fcsFileVO.getFileLocation());
        fileVO.setAttribute01(fcsFileVO.getAttribute01());
        fileVO.setAttribute02(fcsFileVO.getAttribute02());
        fileVO.setAttribute03(fcsFileVO.getAttribute03());
        fileVO.setAttribute04(fcsFileVO.getAttribute04());
        fileVO.setAttribute05(fcsFileVO.getAttribute05());
        fileVO.setAttribute06(fcsFileVO.getAttribute06());
        fileVO.setAttribute07(fcsFileVO.getAttribute07());
        fileVO.setAttribute08(fcsFileVO.getAttribute08());
        fileVO.setAttribute09(fcsFileVO.getAttribute09());
        fileVO.setAttribute10(fcsFileVO.getAttribute10());
        fileVO.setAttribute11(fcsFileVO.getAttribute11());
        fileVO.setAttribute12(fcsFileVO.getAttribute12());
        fileVO.setAttribute13(fcsFileVO.getAttribute13());
        fileVO.setAttribute14(fcsFileVO.getAttribute14());
        fileVO.setAttribute15(fcsFileVO.getAttribute15());
        return fileVO;
    }
    /**
     * 
     *
     * @param fileVO
     * @param fcsFileVO
     * @return
     */
    private FilesVO modifyFilesVO(FilesVO fileVO, FCSFileVO fcsFileVO){
        //fileVO.setFromObid(fcsFileVO.getFromObid());
        //fileVO.setFileFormat(fcsFileVO.getFileFormat());
        //fileVO.setFileStore(fcsFileVO.getFileStore());
        if(!StrUtil.isEmpty(fcsFileVO.getAssignedType())) fileVO.setAssignedType(fcsFileVO.getAssignedType());
        fileVO.setDescriptions(fcsFileVO.getDescriptions());
        //fileVO.setSizes(fcsFileVO.getSizes());
        //fileVO.setUserFileName(fcsFileVO.getUserFileName());
        //fileVO.setFilePath(fcsFileVO.getFilePath());
        //fileVO.setSysFileName(fcsFileVO.getSysFileName());
        //fileVO.setFileLocation(fcsFileVO.getFileLocation());
        //10번까지의 Attribute만 화면상에서 받음. 즉 화면상에서 Input되지 않는 것을 사용하려면 11번 이상의 Attribue를 사용해야 함.
        fileVO.setAttribute01(fcsFileVO.getAttribute01());
        fileVO.setAttribute02(fcsFileVO.getAttribute02());
        fileVO.setAttribute03(fcsFileVO.getAttribute03());
        fileVO.setAttribute04(fcsFileVO.getAttribute04());
        fileVO.setAttribute05(fcsFileVO.getAttribute05());
        fileVO.setAttribute06(fcsFileVO.getAttribute06());
        fileVO.setAttribute07(fcsFileVO.getAttribute07());
        fileVO.setAttribute08(fcsFileVO.getAttribute08());
        fileVO.setAttribute09(fcsFileVO.getAttribute09());
        fileVO.setAttribute10(fcsFileVO.getAttribute10());
        //11번 이상의 Attribute는 화면상에서 Input받는것이 아님.
        //fileVO.setAttribute11(fcsFileVO.getAttribute11());
        //fileVO.setAttribute12(fcsFileVO.getAttribute12());
        //fileVO.setAttribute13(fcsFileVO.getAttribute13());
        //fileVO.setAttribute14(fcsFileVO.getAttribute14());
        //fileVO.setAttribute15(fcsFileVO.getAttribute15());
        return fileVO;
    }
    /**
     * 
     *
     * @param x5
     * @param fcsFileVOList
     * @param resultData
     * @return
     */
    private boolean uploadFilesValidation(FileUploadRequest fileUploadRequest,List<FCSFileVO> fcsFileVOList, Map<String, Object> resultData){
        if(FileFoundationUtil.isNullRecodeModeExists(fcsFileVOList)){
            resultData.put(GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_NULL_RECODE_MODE);
            resultData.put(GlobalConstants.M_MESSAGE, "[Foundation.FileDextController.uploadFilesValidation]Null File Record Mode exists.");
            return false;
        }
        if(NullUtil.isNone(fileUploadRequest.getFileDataList())) {
            if(FileFoundationUtil.isUploadedExists(fcsFileVOList,GlobalConstants.FILE_RECOMODE_CREATE)){
                resultData.put(GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_NOFILE_RECODE_MODE_EXISTS);
                resultData.put(GlobalConstants.M_MESSAGE, "[Foundation.FileDextController.uploadFilesValidation]Added File Record Mode exists.");
                return false;
            }
            if(!FileFoundationUtil.isUploadedExists(fcsFileVOList,GlobalConstants.FILE_RECOMODE_UPDATE,GlobalConstants.FILE_RECOMODE_DELETE)){
                resultData.put(GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_FILE_EXIST_RECODE_MODE_UD);
                resultData.put(GlobalConstants.M_MESSAGE, "[Foundation.FileDextController.uploadFilesValidation]Changed File Record Mode does not exist.");
                return false;
            }
        }else{
            if(!FileFoundationUtil.isUploadedExists(fcsFileVOList,GlobalConstants.FILE_RECOMODE_CREATE)){
                resultData.put(GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_NOFILE_RECODE_MODE_C);
                resultData.put(GlobalConstants.M_MESSAGE, "[Foundation.FileDextController.uploadFilesValidation]No Added File.");
                return false;
            }
        }
        if(!checkLifeCycleOrObid(fcsFileVOList,resultData,fileUploadRequest)) return false;
        return true;
        
    }
    /**
     * 
     *
     * @param fcsFileVOList
     * @param resultData
     * @param x5
     * @return
     */
    private boolean checkLifeCycleOrObid(List<FCSFileVO> fcsFileVOList, Map<String, Object> resultData,FileUploadRequest fileUploadRequest){
       
        for(FCSFileVO vo : fcsFileVOList)    {
            if(StrUtil.isEmpty(vo.getObid()) && StrUtil.isEmpty(vo.getLifeCycle())){
                resultData.put(GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_LIFECYCLE_OBID_NULL);
                resultData.put(GlobalConstants.M_MESSAGE, "[Foundation.FileDextController.checkLifeCycleOrObid]At least, Life Cycle Or Obid must be not Empty(" +  vo.getUserFileName() + ").");
                return false;
            }else if(StrUtil.isEmpty(vo.getLifeCycle())){
                try{
                    BusinessObjectRoot dom = new BusinessObjectRoot(vo.getObid());
                    vo.setLifeCycle(dom.getVo().getLifeCycle());
                }catch(Exception e){
                    resultData.put(GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_DELETED_OBJECT);
                    resultData.put(GlobalConstants.M_MESSAGE, "[Foundation.FileDextController.checkLifeCycleOrObid]File(" + vo.getUserFileName() + ")'s Business Object Not Found(OBID:" + vo.getObid()) ;
                    return false;
                }
            }
            if(!StrUtil.isEmpty(vo.getRecordMode()) && vo.getRecordMode().equals(GlobalConstants.FILE_RECOMODE_CREATE)){
                if(StrUtil.isEmpty(vo.getAssignedType())){
                    resultData.put(GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_ASSIGN_TYPE_NULL);
                    resultData.put(GlobalConstants.M_MESSAGE, "[Foundation.FileDextController.checkLifeCycleOrObid]File(" + vo.getUserFileName() + ")'s Assigned Type is Empty.");
                    return false; 
                }
            }
            if(StrUtil.isEmpty(vo.getRecordMode())){
                resultData.put(GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_RECODE_MODE_NULL);
                resultData.put(GlobalConstants.M_MESSAGE, "[Foundation.FileDextController.checkLifeCycleOrObid]File(" + vo.getUserFileName() + ")'s Recode Mode cannot be Empty.");
                return false; 
            }
        }
        return true;
    }
    /**
     * 
     *
     * @param fileUniqueId
     * @param x5
     * @return
     */
    private String getUserFileName(String fileUniqueId,FileUploadRequest fileUploadRequest){
        List<String> unqiueIdList = fileUploadRequest.getUniqueIdList();
        for(int i = 0; i < unqiueIdList.size(); i++){
            if(fileUniqueId.equals(unqiueIdList.get(i))){
                return fileUploadRequest.getFileDataList().get(i).getOriginalFilename();
            }
        }
        return null;
    }
    /**
     * 
     *
     * @param x5
     * @param fcsFileVOList
     * @param resultData
     * @param mappedFileInfo
     * @return
     */
    private boolean createMappedFilesVOList(FileUploadRequest fileUploadRequest, List<FCSFileVO> fcsFileVOList, Map<String, Object> resultData, List<FCSFileVO> mappedFileInfo){
        List<String> uniqueIdList = fileUploadRequest.getUniqueIdList();
        int i = 0;
        if(!NullUtil.isNone(uniqueIdList)){
            for (String uniqueId : uniqueIdList) {
                FCSFileVO fileVo = findLifeCycleForUpload(fcsFileVOList,uniqueId);
                if(NullUtil.isNull(fileVo)){
                    resultData.put(GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_FILE_EXISTS_NO_META);
                    resultData.put(GlobalConstants.M_MESSAGE, "[Foundation.FileDextController.getMappedFilesVOList]Cannot Find Life Cycle for " + fileUploadRequest.getFileDataList().get(i).getOriginalFilename());
                    return false;
                }
                FcsLocationVO storeVO    = FCSServerLocationUtil.getCurrentStoreForLifeCycle(fileVo.getLifeCycle());
                if(NullUtil.isNull(storeVO)){
                    resultData.put(GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_CANNOT_FOUND_STORE);
                    resultData.put(GlobalConstants.M_MESSAGE, "[Foundation.FileDextController.getMappedFilesVOList]Cannot Find Store for '" + fileVo.getLifeCycle() + "'");
                    return false;
                }
                
                
                
                FcsLocationVO locationVO = FCSServerLocationUtil.getCurrentLocationForUser(storeVO.getStoreName(), ThreadLocalUtil.getUserId());
                if(NullUtil.isNull(locationVO)){
                    resultData.put(GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_CANNOT_FOUND_LOCATION);
                    resultData.put(GlobalConstants.M_MESSAGE, "[Foundation.FileDextController.getMappedFilesVOList]Cannot Find Location for Store('" + storeVO.getStoreName() + "') and User('" + ThreadLocalUtil.getUserId() + ")");
                    return false;
                }
                fileVo.setFileStore(locationVO.getStoreName());
                fileVo.setFileLocation(locationVO.getLocationName());
                fileVo.setLocationFilePath(locationVO.getFilePath());
                i++;
                mappedFileInfo.add(fileVo);
            } 
        }
        return true;
    }
    /**
     * 
     *
     * @param jsonFileVOList
     * @param resultData
     * @param fcsFileVOList
     * @return
     */
    private boolean convertJsonToFileVO(String jsonFileVOList,Map<String,Object> resultData, List<FCSFileVO> fcsFileVOList){    
        ObjectMapper mapper = new ObjectMapper();
        List<FCSFileVO> tempFileVOList = new ArrayList<FCSFileVO>();
        try {
            if(!NullUtil.isNone(jsonFileVOList)){
                tempFileVOList = mapper.readValue(jsonFileVOList, TypeFactory.defaultInstance().constructCollectionType(List.class, FCSFileVO.class));
                if(!NullUtil.isNone(tempFileVOList)) fcsFileVOList.addAll(tempFileVOList);
            }else{
                resultData.put(GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_NO_JSON_DATA);
                resultData.put(GlobalConstants.M_MESSAGE, "[Foundation.FileDextController.convertJsonToFileVO]No File Data.");
                return false; 
            }
        } catch (IOException e) {
            resultData.put(GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_JSON_CONVERT_ERROR);
            resultData.put(GlobalConstants.M_MESSAGE, "[Foundation.FileDextController.convertJsonToFileVO]Error Occured during convert List<FCSFileVO>." + OmcFoundationConstant.newline + e.getMessage());
            return false;
        } catch(Exception e){
            resultData.put(GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_JSON_CONVERT_ERROR);
            resultData.put(GlobalConstants.M_MESSAGE, "[Foundation.FileDextController.convertJsonToFileVO]Error Occured during convert List<FCSFileVO>." + OmcFoundationConstant.newline + e.getMessage());
            return false;
        }
        return true;
    }
    /**
     * 
     *
     * @param fcsFileVOList
     * @param x5
     */
    private void setUserFileName(List<FCSFileVO> fcsFileVOList,FileUploadRequest fileUploadRequest){
        for(FCSFileVO vo : fcsFileVOList){
            if(!StrUtil.isEmpty(vo.getRecordMode()) && vo.getRecordMode().equals(GlobalConstants.FILE_RECOMODE_CREATE)){
                vo.setUserFileName(getUserFileName(vo.getFileUniqueId(),fileUploadRequest));
            }
        }
    }
    /**
     * 
     *
     * @param x5
     * @param fileUpload
     * @param resultData
     * @param mappedFileInfo
     * @return
     */
    private boolean validateFileSizes(FileUploadRequest fileUploadRequest, PdmFileUpload fileUpload, Map<String, Object> resultData, List<FCSFileVO> mappedFileInfo){
        return(fileUpload.validateFileSizes(fileUploadRequest.getFileDataList(), resultData));
    }
    /**
     * 
     *
     * @param x5
     * @param willBeUploadedList
     * @param resultData
     * @param mappedFileInfo
     * @param userId
     * @return
     */
    private boolean getMappedFilesVOList(FileUploadRequest fileUploadRequest, List<FCSFileVO> willBeUploadedList, Map<String, Object> resultData, List<FCSFileVO> mappedFileInfo,String userId){
        List<String> uniqueIdList = fileUploadRequest.getUniqueIdList();
        if(NullUtil.isNone(uniqueIdList)) return true;
        int i = 0;
        for (String uniqueId : uniqueIdList) {
            FCSFileVO fileVo = findLifeCycleForUpload(willBeUploadedList,uniqueId);
            if(NullUtil.isNull(fileVo)){
                resultData.put(GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_USER_ID_INVALID);
                resultData.put(GlobalConstants.M_MESSAGE, "[Foundation]Cannot Find Life Cycle for " + fileUploadRequest.getFileDataList().get(i).getOriginalFilename());
                return false;
            }
            FcsLocationVO storeVO    = FCSServerLocationUtil.getCurrentStoreForLifeCycle(fileVo.getLifeCycle());
            if(NullUtil.isNull(storeVO)){
                resultData.put(GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_CANNOT_FOUND_STORE);
                resultData.put(GlobalConstants.M_MESSAGE, "[Foundation.FileDextController.getMappedFilesVOList]Cannot Find Store for '" + fileVo.getLifeCycle() + "'");
                return false;
            }
            FcsLocationVO locationVO = FCSServerLocationUtil.getCurrentLocationForUser(storeVO.getStoreName(), userId);
            if(NullUtil.isNull(locationVO)){
                resultData.put(GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_CANNOT_FOUND_LOCATION);
                resultData.put(GlobalConstants.M_MESSAGE, "[Foundation.FileDextController.getMappedFilesVOList]Cannot Find Location for Store('" + storeVO.getStoreName() + "') and User('" + userId + ")");
                return false;
            }
            fileVo.setFileStore(locationVO.getStoreName());
            fileVo.setFileLocation(locationVO.getLocationName());
            fileVo.setLocationFilePath(locationVO.getFilePath());
            i++;
            mappedFileInfo.add(fileVo);
        }
        return true;
    }
    /**
     * 
     *
     * @param willBeUploadedList
     * @param fileUniqueId
     * @return
     */
    private FCSFileVO findLifeCycleForUpload(List<FCSFileVO> willBeUploadedList,String fileUniqueId){
        for(FCSFileVO vo : willBeUploadedList){
            if(!NullUtil.isNone(vo.getRecordMode()) && vo.getRecordMode().equals(GlobalConstants.FILE_RECOMODE_CREATE) && vo.getFileUniqueId().equals(fileUniqueId)){
                return vo;
            }
        }
        return null;
    }
    /**
     * 
     * @param bizObid
     * @param fileFormat
     * @param assignedType
     * @return
     * @see lgcns.rnd.application.file.service.FileCommonService#getAttachecFiles(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public List<FilesVO> getAttachecFiles(String bizObid, String fileFormat, String assignedType){
        // TODO Auto-generated method stub
        BusinessObjectRoot bizDom = new BusinessObjectRoot(bizObid);
        //보안상 서버의 filePath정보는 못가지고 오게 false
        return bizDom.getReleatedFiles(fileFormat,assignedType,true);
    }
    /**
     * 
     *
     * @param sessionMap
     */
    private void setSessionInfo(Map<String,Object> sessionMap){
        if(!StrUtil.isEmpty((String)sessionMap.get(OmcFileConstants.C_SESSION_KEY_userId))){
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.userId, (String)sessionMap.get(OmcFileConstants.C_SESSION_KEY_userId));
        }
        if(!StrUtil.isEmpty((String)sessionMap.get(OmcFileConstants.C_SESSION_KEY_remoteAddr))){
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.remoteAddr, (String)sessionMap.get(OmcFileConstants.C_SESSION_KEY_remoteAddr));
        }
        if(!StrUtil.isEmpty((String)sessionMap.get(OmcFileConstants.C_SESSION_KEY_plantUnit))){
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.contextBizObject, (String)sessionMap.get(OmcFileConstants.C_SESSION_KEY_plantUnit));
        }
        if(!StrUtil.isEmpty((String)sessionMap.get(OmcFileConstants.C_SESSION_KEY_timeZone))){
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.timeZone, (String)sessionMap.get(OmcFileConstants.C_SESSION_KEY_timeZone));
        }
        if(!StrUtil.isEmpty((String)sessionMap.get(OmcFileConstants.C_SESSION_KEY_requestUri))){
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.requestUri, (String)sessionMap.get(OmcFileConstants.C_SESSION_KEY_requestUri));
        }
        if(!StrUtil.isEmpty((String)sessionMap.get(OmcFileConstants.C_SESSION_KEY_businessUnit))){
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.businessUnit, (String)sessionMap.get(OmcFileConstants.C_SESSION_KEY_businessUnit));
        }
        if(!StrUtil.isEmpty((String)sessionMap.get(OmcFileConstants.C_SESSION_KEY_executor))){
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.executor, (String)sessionMap.get(OmcFileConstants.C_SESSION_KEY_executor));
        }
        if(!StrUtil.isEmpty((String)sessionMap.get(OmcFileConstants.C_SESSION_KEY_locale))){
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.locale, (String)sessionMap.get(OmcFileConstants.C_SESSION_KEY_locale));
        }
        if(!StrUtil.isEmpty((String)sessionMap.get(OmcFileConstants.C_SESSION_KEY_site))){
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.userLoginSite, (String)sessionMap.get(OmcFileConstants.C_SESSION_KEY_site));
        }
    }
}