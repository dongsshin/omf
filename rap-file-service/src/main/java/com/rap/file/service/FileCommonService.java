/**
 * ===========================================
 * System Name : LGE PLM Project
 * Program ID : FileDextService.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 7. 26.  Noh.M.S   Initial
 * ===========================================
 */
package com.rap.file.service;


import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.rap.omc.api.object.model.FilesVO;
import com.rap.omc.foundation.file.model.FCSParameterVO;
import com.rap.omc.framework.file.upload.FileUploadRequest;
import com.rap.omc.framework.file.upload.PdmFileUpload;
import com.rap.omc.framework.file.upload.policy.PdmFileUploadPolicy;

import net.sf.json.JSONObject;


/**
 * <pre>
 * Class : FileCommonService
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public interface FileCommonService {
    public List<FCSParameterVO> getFCSParameterVOList(String oneTimePasswd);
    public List<FilesVO> getAttachecFiles(String bizObid,String fileFormat, String assignedType);
    public FilesVO getDetailedFileInfo(String fileObid);
    public void makeUploadFileSystemInfo(PdmFileUploadPolicy uploadPolicy,List<String> fileList, List<String> formatList, String fileLocationPath, Map<String, Object> resultData);
    public JSONObject checkOtp(String url, String oneTimePasswd, String remoteAddr);
    public JSONObject getFileLocationPath(String url, String oneTimePasswd, String remoteAddr, String locationObid);
    public JSONObject getFilePath(String url, String obid, String remoteAddr);
    public JSONObject getAllFilePath(String url, List<String> obid, String remoteAddr);
    public void fileCopyInFCSServerForObject(PdmFileUpload fileUpload, List<FCSParameterVO> parameterVOList, Map<String, Object> resultData);
    public void fileUploadProcessForDext(PdmFileUpload fileUpload, String jsonFileVOList,FileUploadRequest fileUploadRequest,Map<String, Object> resultData);
    public boolean fileUploadProcessForGeneral(PdmFileUpload fileUpload, List<FCSParameterVO> parameterVOList, List<MultipartFile> multipartFileList, Map<String, Object> resultData);
}
