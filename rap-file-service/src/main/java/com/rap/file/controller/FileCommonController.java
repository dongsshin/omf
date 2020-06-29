/**
 * ===========================================
 * System Name : LGE PLM Project
 * Program ID : FileDextController.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 7. 26.  Noh.M.S   Initial
 * ===========================================
 */
package com.rap.file.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import com.rap.file.service.FileCommonService;
import com.rap.omc.api.object.dom.BusinessObjectRoot;
import com.rap.omc.api.object.dom.Files;
import com.rap.omc.api.object.model.FilesVO;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.constants.ResponseConstants;
import com.rap.omc.foundation.file.model.FCSParameterVO;
import com.rap.omc.framework.controller.BaseController;
import com.rap.omc.framework.exception.ApplicationException;
import com.rap.omc.framework.file.download.FileDownloadView;
import com.rap.omc.framework.file.download.PdmFileDownloadView;
import com.rap.omc.framework.file.upload.FileUploadRequest;
import com.rap.omc.framework.file.upload.PdmFileUpload;
import com.rap.omc.framework.file.upload.policy.PdmFileUploadPolicy;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.ResponseMapper;
import com.rap.omc.util.file.FileCommonUtil;
import com.rap.omc.util.file.FileManagementUtil;

@Controller
public class FileCommonController extends BaseController{
 
    private static final Logger LOGGER = LoggerFactory.getLogger(FileCommonController.class);

    private final String FILE_UPLOAD_POLICY1 = "policy1";
    
    @Resource(name="fileCommonService")
    FileCommonService fileCommonService;

    @Resource(name="fileUpload")
    PdmFileUpload fileUpload;
    
    @Resource(name = FILE_UPLOAD_POLICY1)
    private PdmFileUploadPolicy uploadPolicy;
    
//    @Resource(name="drmService")
//    DRMService drmService;
//    
    /***************************************************************************************************************************************************/
    /*                                  Download File From WEB(Multi File)(Not Developped)                                                                             */
    /***************************************************************************************************************************************************/
    @RequestMapping(value="/fcs/checkOutAllAjax.do")
    public FileDownloadView multiFileListDownload(@RequestParam List<String> obid) {
        List<FilesVO> fileList = new ArrayList<FilesVO>();
        for(String tempObid : obid){
           Files fileDom = DomUtil.toDom(tempObid);
           FilesVO file = fileDom.getVo();
           fileList.add(file);
        }
        File[] files = new File[fileList.size()];        
        HashMap<String, String> fileReplaceMapper = new HashMap<String, String>(); 

        String localFile = null;
        for(int i=0; i< fileList.size(); i++){                
            localFile = FileManagementUtil.getPrivateDir() + fileList.get(i).getUserFileName();
            files[i] = FileCommonUtil.getFile(localFile);
            fileReplaceMapper.put(files[i].getAbsolutePath(), fileList.get(i).getUserFileName());
        }
        return new PdmFileDownloadView(files, "aliasFiles.zip", fileReplaceMapper);
    }
    
    /***************************************************************************************************************************************************/
    /*                                  CheckIn Physical File From Server(Developped)                                                                   */
    /***************************************************************************************************************************************************/
    @SuppressWarnings("unchecked")
    @RequestMapping(value="/fcs/checkInFromServer.do", method = RequestMethod.POST)
    public String checkInFromServer(@RequestParam List<MultipartFile> files, 
                                    @RequestParam(value = "oneTimePasswd"  , required = true)String oneTimePasswd,
                                    ModelMap model) {
        LOGGER.debug("checkInFromServer.do Started");
        List<FCSParameterVO> parameterVOList = fileCommonService.getFCSParameterVOList(oneTimePasswd);
        Map<String, Object> resultData = new HashMap<String, Object>();
        fileUploadSubProcessForGeneral(parameterVOList, files, resultData);
        
        ResponseMapper rm = new ResponseMapper();
        rm.setStatusCode((int)resultData.get(GlobalConstants.M_STATUS_CODE));
        rm.setMessage((String)resultData.get(GlobalConstants.M_MESSAGE));
        Map<String,Object> map = (Map<String,Object>)resultData.get(GlobalConstants.M_DATA);
        rm.setData(GlobalConstants.D_FILES, map.get(GlobalConstants.D_FILES));
        rm.setModelMap(model);
        return GlobalConstants.AJAX_VIEW;
    }
    /***************************************************************************************************************************************************/
    /*                                  Checkout Physical File From Server(Developped)                                                                             */
    /***************************************************************************************************************************************************/
    @RequestMapping(value="/fcs/checkOutFromServer.do")
    public PdmFileDownloadView checkOutFromServer(@RequestParam(value = "oneTimePasswd"  , required = true)String oneTimePasswd, 
                                                  HttpServletResponse response){
        List<FCSParameterVO> parameterVOList = fileCommonService.getFCSParameterVOList(oneTimePasswd);
        Map<String, Object> resultData = new HashMap<String, Object>();
        File file = fileCheckOutFilesFromServer(parameterVOList,resultData);
        for(String key : resultData.keySet()){
            response.addHeader(key, resultData.get(key).toString());
        }
        return new PdmFileDownloadView(file, (String)resultData.get("userFileName")); 
    }
    /***************************************************************************************************************************************************/
    /*                                  Generic Upload File From WEB (Developped)                                                                                 */
    /***************************************************************************************************************************************************/
    @RequestMapping(value = "/fcs/uploadFromWEB.do", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> noorafUploadFromWEB(HttpServletRequest request,
            @RequestParam("file") List<MultipartFile> multipartFiles,
                                                               @RequestParam(value = "oneTimePasswd"  , required = true)String oneTimePasswd,
                                                               @RequestParam(value = "jsonFileVOList", required = true)String jsonFileVOList,
                                                               @RequestParam(value = "fileUniqueIdList", required = true)List<String> fileUniqueIdList){
        LOGGER.debug("uploadFromWEB Started");
        fileCommonService.getFCSParameterVOList(oneTimePasswd);
        FileUploadRequest fileUploadRequest = new FileUploadRequest();
        fileUploadRequest.setUniqueIdList(fileUniqueIdList);
        fileUploadRequest.setFileDataList(multipartFiles);
        Map<String, Object> resultData = new HashMap<String, Object>();
        fileUploadSubProcess(jsonFileVOList, fileUploadRequest, resultData);
        return resultData;
    }
    /***************************************************************************************************************************************************/
    /*                                  Upload File From WEB(Only Meta)(Developped)                                                                                */
    /***************************************************************************************************************************************************/
    @SuppressWarnings({ "unchecked", "unused" })
    @RequestMapping(value = "/fcs/exnjUploadEmptyFromWEB.do", method = RequestMethod.POST)
    public String exnjUploadEmptyFromWEB(HttpServletRequest request,
                                         @RequestParam(value = "oneTimePasswd" , required = true)String oneTimePasswd, 
                                         @RequestParam(value = "jsonFileVOList", required = true) String jsonFileVOList,
                                         ModelMap model){
        LOGGER.debug("exnjUploadEmptyFromWEB Started");
        List<FCSParameterVO> parameterVOList = fileCommonService.getFCSParameterVOList(oneTimePasswd);
        ResponseMapper rm = new ResponseMapper();

        Map<String, Object> resultData = new HashMap<String, Object>();
        fileUploadSubProcess(jsonFileVOList, new FileUploadRequest() , resultData);

        rm.setStatusCode((int)resultData.get(GlobalConstants.M_STATUS_CODE));
        rm.setMessage((String)resultData.get(GlobalConstants.M_MESSAGE));
        Map<String,Object> map = (Map<String,Object>)resultData.get(GlobalConstants.M_DATA);
        rm.setData(GlobalConstants.D_FILES, map.get(GlobalConstants.D_FILES));
        rm.setModelMap(model);
        return GlobalConstants.AJAX_VIEW;
    }
    /***************************************************************************************************************************************************/
    /*                                  Download File From WEB(Single File)(Developped)                                                                            */
    /***************************************************************************************************************************************************/
    @RequestMapping(value="/fcs/checkOutFromWEBAjax.do")//(Development Completed)
    public PdmFileDownloadView checkOutFromWEBAjax(@RequestParam String obid, @RequestParam String userId){
        Files fileDom = new Files(obid);
        File file = null;
        try{
            file = FileCommonUtil.getFile(fileDom.getFullFilePath());
            if(!file.exists()){
                LOGGER.error("Download file not found : " + file.getAbsolutePath());
                throw new ApplicationException("api.object.error.file.noFile");
            }
        }catch(Exception e){
            LOGGER.error(e.getMessage());
            throw new ApplicationException("api.object.error.file.noFile");
        }
        
        BusinessObjectRoot businessObjectRoot = DomUtil.toDom(fileDom.getVo().getFromObid());
        String downOption;
//        if(drmService.isApplyDRMFileByClass(businessObjectRoot, fileDom)) {
//            if(drmService.isApplyDRMFileByAuth(businessObjectRoot, userId)) {
//                file = drmService.fileEncryption(file, fileDom, userId);
//                downOption = PlmConstants.DOWN_OPTION_ENCRYPTION;
//            }else{
//                downOption = PlmConstants.DOWN_OPTION_RAW;
//            }
//            drmService.createFileDownloadLog(businessObjectRoot, fileDom, userId, downOption);
//        }
        return new PdmFileDownloadView(file, fileDom.getVo().getUserFileName());
    }

    /***************************************************************************************************************************************************/
    /*                                  Copy Physical File From Main Server(Developped)                                                                            */
    /***************************************************************************************************************************************************/
    @RequestMapping(value="/fcs/copyFileForObject.do", method = RequestMethod.POST) //(Development Completed)
    public String copyFileForObject( @RequestParam String oneTimePasswd,HttpServletRequest req, ModelMap model) {
        
        List<FCSParameterVO> parameterVOList = fileCommonService.getFCSParameterVOList(oneTimePasswd);
        Map<String, Object> resultData = new HashMap<String, Object>();
        fileCopyInFCSServerForObject(parameterVOList,resultData);
        
        ResponseMapper rm = new ResponseMapper();
        
        rm.setStatusCode((int)resultData.get(GlobalConstants.M_STATUS_CODE));
        rm.setMessage((String)resultData.get(GlobalConstants.M_MESSAGE));
        rm.setData(GlobalConstants.D_FILES, resultData.get(GlobalConstants.D_FILES));
        rm.setModelMap(model);
        return GlobalConstants.AJAX_VIEW;
    }
    
    /***************************************************************************************************************************************************/
    /*                                  Download File From EP & Mobile(Single File)(Developped)                                                                            */
    /***************************************************************************************************************************************************/
    @RequestMapping(value="/ep/checkOutFromEPAndMobile.do")//(Development Completed)
    public PdmFileDownloadView checkOutFromEPAndMobile(@RequestParam String obid){
        Files fileDom = new Files(obid);
        File file = null;
        try{
            file = FileCommonUtil.getFile(fileDom.getFullFilePath());
            if(!file.exists()){
                LOGGER.error("Download file not found : " + file.getAbsolutePath());
                throw new ApplicationException("api.object.error.file.noFile");
            }
        }catch(Exception e){
            LOGGER.error(e.getMessage());
            throw new ApplicationException("api.object.error.file.noFile");
        }
        return new PdmFileDownloadView(file, fileDom.getVo().getUserFileName());
    }
    private File fileCheckOutFilesFromServer(List<FCSParameterVO> parameterVOList,Map<String, Object> resultData){
        LOGGER.debug("fileCheckOutFiles Started");
        File file = null;
        if(NullUtil.isNone(parameterVOList)) {
            resultData.put(GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_NO_META);
            resultData.put(GlobalConstants.D_FILES,  "");
            resultData.put(GlobalConstants.M_SYSTEM_ERROR_MESSAGE, "[Founation.FileCommonServiceImpl.fileCopyInFCSServerForObject]No Data to Copy.");
            return null;
        }
        FCSParameterVO parameterVO = parameterVOList.get(0);
        Files fileDom = null;
        //Files fileDom = new Files(parameterVO.getFileObid());     
        try{
            if(NullUtil.isNull(parameterVO.getFileVO())){
                fileDom = new Files(parameterVO.getFileObid());            
            }
            else{
                fileDom = new Files(parameterVO.getFileVO());
            }            
        }catch(Exception e){
            LOGGER.error(e.getMessage());
            resultData.put(GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_NO_META);
            resultData.put(GlobalConstants.D_FILES,  "");
            resultData.put(GlobalConstants.M_SYSTEM_ERROR_MESSAGE, "[Founation.FileCommonServiceImpl.fileCheckOutFiles]Files Object is invalid." + e.getMessage());
            return null;
        }
        resultData.put("userFileName", fileDom.getVo().getUserFileName());
        try{
            String fileFullPath = fileDom.getFullFilePath();
            LOGGER.error("Download Full File Path : " + fileFullPath);
            file = FileCommonUtil.getFile(fileDom.getFullFilePath());
            if(!file.exists()){
                LOGGER.error("Download file not found : " + file.getAbsolutePath());
                resultData.put(GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_NO_META);
                resultData.put(GlobalConstants.D_FILES,  "");
                resultData.put(GlobalConstants.M_SYSTEM_ERROR_MESSAGE, "[Founation.FileCommonServiceImpl.fileCheckOutFiles]File Not Found.");
                return null;
            }
            return file;
        }catch(Exception e){
            LOGGER.error(e.getMessage());
            resultData.put(GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_NO_META);
            resultData.put(GlobalConstants.D_FILES,  "");
            resultData.put(GlobalConstants.M_SYSTEM_ERROR_MESSAGE, "[Founation.FileCommonServiceImpl.fileCheckOutFiles]File getting Error." + e.getMessage());
            return null;
        }
    }
    private void fileUploadSubProcessForGeneral(List<FCSParameterVO> parameterVOList,List<MultipartFile> files,Map<String, Object> resultData){
        LOGGER.debug("fileUploadSubProcess Started");
        fileUpload.setAppliedFilePolicy(uploadPolicy, FILE_UPLOAD_POLICY1);
        fileCommonService.fileUploadProcessForGeneral(fileUpload,parameterVOList, files, resultData);
    }
    private void fileUploadSubProcess(String jsonFileVOList,FileUploadRequest fileUploadRequest,Map<String, Object> resultData){
        LOGGER.debug("fileUploadSudbProcess Started");
        fileUpload.setAppliedFilePolicy(uploadPolicy, FILE_UPLOAD_POLICY1);
        fileCommonService.fileUploadProcessForDext(fileUpload,jsonFileVOList, fileUploadRequest, resultData);
    }
    
    private void fileCopyInFCSServerForObject(List<FCSParameterVO> parameterVOList,Map<String, Object> resultData){
        LOGGER.debug("fileUploadSudbProcess Started");
        fileUpload.setAppliedFilePolicy(uploadPolicy, FILE_UPLOAD_POLICY1);
        fileCommonService.fileCopyInFCSServerForObject(fileUpload,parameterVOList, resultData);
    }
}
