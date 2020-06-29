/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : RestTemplateUtil.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 3. 24.  jongjung.kwon   Initial
 * ===========================================
 */
package com.rap.omc.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.rap.omc.api.object.model.FilesVO;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.constants.ResponseConstants;
import com.rap.omc.foundation.file.model.FileServiceURLVO;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.api.util.spring.SpringFactoryUtil;
import com.rap.omc.util.file.FCSServerLocationUtil;
import com.rap.omc.util.file.FileManagementUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



/**
 * <pre>
 * Class : RestTemplateUtil
 * Description : TODO
 * </pre>
 * 
 * @author jongjung.kwon
 */
public class RestTemplateUtil {
    private RestTemplate restTemplate;
    private static RestTemplateUtil cInstance;
    private static final Logger LOGGER = LoggerFactory.getLogger(RestTemplateUtil.class);

    /**
     * Singleton을 구성된 getInstance() method
     */
    private synchronized static RestTemplateUtil getInstance(){
        if (cInstance == null) {
            cInstance = new RestTemplateUtil();
            cInstance.restTemplate = (RestTemplate)SpringFactoryUtil.getBean("restTemplate");
        }
        return cInstance;
    }

    public static void checkOutFileFromServer( FilesVO fileVO) throws FoundationException{
        String tempDownloadDir = FileManagementUtil.getPrivateDir();
        FileServiceURLVO fileServiceURLVO = FCSServerLocationUtil.getFileServiceURLForDownloadWithFile(fileVO);
        
        HttpEntityUtil pdmEntity = new HttpEntityUtil();
        pdmEntity.addFormData("obid",fileVO.getObid());
        pdmEntity.setHttpEntity();
        BufferedOutputStream os= null;

        try {
            HttpEntity<?> entity = pdmEntity.getHttpEntity();
            ResponseEntity<byte[]> response  = getInstance().restTemplate.postForEntity(fileServiceURLVO.getServiceCheckOutUrl(), entity, byte[].class);
            File targetDir = new File(tempDownloadDir);

            if(!targetDir.exists()) { 
                targetDir.mkdirs();
            }

            File downFile = new File(tempDownloadDir, fileVO.getUserFileName());
            os = new BufferedOutputStream(new FileOutputStream(downFile));
            os.write(response.getBody());

        } catch (IOException e) {
            e.printStackTrace();
            throw new FoundationException("api.object.error.file.checkOutFileFromServer",e);
        }finally{
            try {
                os.flush();
                os.close();
            } catch (Exception e) {
                throw new FoundationException("api.object.error.file.checkOutFileFromServer",e);
            } 
            
        }
    }
    public static FilesVO checkInFileToServer(FilesVO fileVO){
        String tempUploadDir = FileManagementUtil.getPrivateDir();
        HttpEntity<?> entity=null;
        FileServiceURLVO fileServiceURLVO = FCSServerLocationUtil.getFileServiceURLForCheckInWithFile(fileVO);
        String fileLocationPath=null;
        JSONObject result=null;
        
        try{
            fileLocationPath = fileServiceURLVO.getFileRootPath();
            HttpEntityUtil pdmEntity = new HttpEntityUtil();        
            pdmEntity.addFormData("files", new FileSystemResource(tempUploadDir + File.separator + fileVO.getUserFileName()));        
            pdmEntity.addFormData("fileLocationPath", fileLocationPath);
            pdmEntity.setHeaderContentType(MediaType.MULTIPART_FORM_DATA);
            pdmEntity.setHttpEntity();
            
            entity = pdmEntity.getHttpEntity();
            
            ResponseEntity<JSONObject> response  = getInstance().restTemplate.postForEntity(fileServiceURLVO.getServiceCheckInFromServerUrl(), entity, JSONObject.class);       
            result = response.getBody();

        }catch(Exception e){
            throw new FoundationException("api.object.error.file.checkInFileToServer",e);
        }
        if(result.getInt(GlobalConstants.M_STATUS_CODE) == ResponseConstants.STATUS_SUCCESS){
        
            JSONObject dataJson = result.getJSONObject(GlobalConstants.M_DATA);
            FilesVO resultFile = new FilesVO();
            
            JSONArray fileArray = dataJson.getJSONArray("files");
            JSONObject fileObject = fileArray.getJSONObject(0);
            resultFile.setUserFileName(fileObject.getString("userFileName"));
            resultFile.setSysFileName(fileObject.getString("sysFileName"));
            resultFile.setFilePath(fileObject.getString("filePath"));
            resultFile.setSizes((float)fileObject.getLong("sizes"));
            resultFile.setFileFormat(fileVO.getFileFormat());  
            
            return resultFile;
            
        }else{            
            LOGGER.error("checkInFileToServer : " + result.getString(GlobalConstants.M_MESSAGE));
            throw new FoundationException("api.object.error.file.checkInFileToServer");
            
        }
    }
}
