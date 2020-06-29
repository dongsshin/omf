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
package com.rap.omc.util.file;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.StringUtils;

import com.rap.omc.api.object.model.BusinessObjectRootVO;
import com.rap.omc.api.object.model.FilesVO;
import com.rap.omc.api.util.omc.ThreadLocalUtil;
import com.rap.omc.api.util.general.NameGeneratorUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.constants.ResponseConstants;
import com.rap.omc.foundation.file.model.FCSParameterVO;
import com.rap.omc.foundation.file.model.FcsLocationVO;
import com.rap.omc.foundation.file.model.FileServiceURLVO;
import com.rap.omc.foundation.file.model.OtpVO;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.schema.util.OmcApplicationConstants;
import com.rap.omc.util.HttpUtil;
import com.rap.omc.util.NullUtil;

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
public class HttpClientUtil {    
    
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtil.class);
    
    private static HttpClientUtil cInstance;   
    
    private static int TIME_OUT = 1000;
    private static RequestConfig config = RequestConfig.custom()
                            .setConnectTimeout(TIME_OUT * 1000)
                            .setConnectionRequestTimeout(TIME_OUT * 1000)
                            .setSocketTimeout(TIME_OUT * 1000).build();
    private synchronized static HttpClientUtil getInstance(){
        if (cInstance == null) {
            cInstance = new HttpClientUtil();
        }
        return cInstance;
    }
    public static void checkOutFileFromServer( FilesVO fileVO) {
        checkOutFileFromServerSub(fileVO);
    }
    public static List<FilesVO> checkInFileFromServer(BusinessObjectRootVO bizVO, List<FilesVO> fileVOList, List<File> fileList) {
        return checkInFileFromServerSub(fileVOList, bizVO,fileList);
    }
    public static void deleteFileFromServer(FilesVO fileVO){
        List<FilesVO> filesVOList = new ArrayList<FilesVO>();
        filesVOList.add(fileVO);
        deleteFileFromServerSub(filesVOList);
    }
    public static void deleteFileFromServer(List<FilesVO> filesVOList){
        deleteFileFromServerSub(filesVOList);
    }
    public static List<FCSParameterVO> copyFileInFCSServer(List<FilesVO> fileVOList, String lifeCycleName) {
        return copyFileInFCSServerSub(fileVOList,lifeCycleName);
    }
    public static String checkOutPhysicalFile(String temporaryWorkingDir, FilesVO fileVO) throws FoundationException{
        return checkOutPhysicalFileSub(temporaryWorkingDir,fileVO);
    }
    private static String checkOutFileFromServerSub(FilesVO fileVO) {
        String tempDownloadDir = FileManagementUtil.getPrivateDir();
        FileServiceURLVO fileServiceURLVO = FCSServerLocationUtil.getFileServiceURLForDownloadWithFile(fileVO.getFileLocation());
        File targetDir = new File(tempDownloadDir);
        if(!targetDir.exists())  targetDir.mkdirs();
        
        String downFile = tempDownloadDir + File.separator + fileVO.getUserFileName();
        downFile = StringUtils.cleanPath(downFile);

        Map<String,String> map = new HashMap<String,String>();
        map.put(OmcFileConstants.C_ATTR_FILE_OBID, fileVO.getObid());
        OtpVO otpVO = FCSServerLocationUtil.createOtp(map);
        
        HttpUtil http = new HttpUtil();
        try {
                http.setUrl(fileServiceURLVO.getServiceCheckOutFromServerUrl(), "UTF-8");
                http.addFormField(OmcFileConstants.C_ATTR_ONE_TIME_PASSWORD,otpVO.getNonce());
            } catch (UnsupportedEncodingException e) {
                LOGGER.debug("checkOutFileFromServer IOException");
                throw new FoundationException("api.object.error.file.checkOutFileFromServer",e);
            } catch (IOException e) {
                LOGGER.debug("checkOutFileFromServer IOException");
                throw new FoundationException("api.object.error.file.checkOutFileFromServer",e);
            }
            LOGGER.debug("checkOutFileFromServer obid :" +fileVO.getObid());              
            http.finish(downFile, fileVO.getUserFileName());
            return downFile;
    }
    @SuppressWarnings("unchecked")
    private static List<FCSParameterVO> deleteFileFromServerSub(List<FilesVO> fileVOList){
        
        if(NullUtil.isNone(fileVOList)) return new ArrayList<FCSParameterVO>();
        List<FCSParameterVO> parameterVOList = new ArrayList<FCSParameterVO>();
        FileServiceURLVO fileServiceURLVO = FCSServerLocationUtil.getFileServiceURLForDownloadWithFile(fileVOList.get(0));

        for(FilesVO vo : fileVOList){
            FCSParameterVO parameterVO = new FCSParameterVO();
            String communicationUniqueId = NameGeneratorUtil.generateUniqueName(GlobalConstants.IDGEN_IDKEY_FILE);
            parameterVO.setCommunicationUniqueId(communicationUniqueId);
            vo.getOutData().put(OmcFileConstants.C_FCS_COMMUNICATION_UNIQUE_ID, communicationUniqueId);
            parameterVO.setFileObid(vo.getObid());
            parameterVOList.add(parameterVO);
        }
        OtpVO otpVO = FCSServerLocationUtil.createOtp(parameterVOList);
        List<FCSParameterVO> fcsParameterVOList = new ArrayList<FCSParameterVO>();
        try {
            HttpUtil http = new HttpUtil();
            http.addFormField("oneTimePasswd",otpVO.getNonce());
            http.setUrl(fileServiceURLVO.getServiceCopyFileInServerUrl(), "UTF-8");
            String resultString = http.formFinish(); 
            JSONObject result = JSONObject.fromObject(resultString);
            if(result.getInt(GlobalConstants.M_STATUS_CODE) == ResponseConstants.STATUS_SUCCESS){
                JSONObject jResultObject      = result.getJSONObject(GlobalConstants.M_DATA);
                JSONArray  jFileArrayObject   = jResultObject.getJSONArray(GlobalConstants.D_FILES);
                fcsParameterVOList = (List<FCSParameterVO>)JSONArray.toCollection(jFileArrayObject, FCSParameterVO.class);
                return fcsParameterVOList;
            }else{
                String message = result.getString(GlobalConstants.M_MESSAGE);
                LOGGER.error("M_STATUS_CODE : " + result.getInt(GlobalConstants.M_STATUS_CODE));
                LOGGER.error("copyFileInFCSServer : " + message);
                throw new FoundationException("[Foundation.HttpClientUtil.deleteFileFromServerSub]Message:" + message);
            }
        } catch (Exception e) {
            LOGGER.error("deleteFileFromServerSub exception : " + e.getMessage());
            throw new FoundationException("[Foundation.HttpClientUtil.deleteFileFromServerSub]Delete Error");
        }
    }
    private static List<FilesVO> checkInFileFromServerSub(List<FilesVO> fileVOList,BusinessObjectRootVO bizVO,List<File> fileList){
        if(NullUtil.isNone(fileVOList)) throw new FoundationException("[Foundation]Not Check In Files.");
        List<FilesVO> resultFileFile = new ArrayList<FilesVO>();
        String userId = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, GlobalConstants.NO_USER_ID);
        List<FCSParameterVO> parameterVOList = new ArrayList<FCSParameterVO>();
        FcsLocationVO storeVO   = FCSServerLocationUtil.getCurrentStoreForLifeCycle(bizVO.getLifeCycle());
        FcsLocationVO locationVO = FCSServerLocationUtil.getCurrentLocationForUser(storeVO.getStoreName(), userId);
        FileServiceURLVO fileServiceURLVO = FCSServerLocationUtil.getFileServiceURLForCheckInWithLocation(locationVO);
        int i = 0;
        for(FilesVO vo : fileVOList){
            vo.setUserFileName(fileList.get(i++).getName());
            FcsLocationVO fcsLocationVO = FCSServerLocationUtil.getFileLocation(vo.getFileLocation());
            FCSParameterVO parameterVO = new FCSParameterVO();
            String communicationUniqueId = NameGeneratorUtil.generateUniqueName(GlobalConstants.IDGEN_IDKEY_FILE);
            parameterVO.setCommunicationUniqueId(communicationUniqueId);
            vo.getOutData().put(OmcFileConstants.C_FCS_COMMUNICATION_UNIQUE_ID, communicationUniqueId);
            
            parameterVO.setSourceRootPath(fcsLocationVO.getFilePath());
            parameterVO.setSourceFilePath(vo.getFilePath());
            parameterVO.setTargetRootPath(fcsLocationVO.getFilePath());
            parameterVO.setBusinessObjectObid(bizVO.getObid());
            parameterVO.setUserFileName(vo.getUserFileName());
            parameterVO.setFileFormat(vo.getFileFormat());
            parameterVO.setAssignedType(vo.getAssignedType());
            parameterVO.setBusinessObjectLifeCycle(bizVO.getLifeCycle());
            parameterVOList.add(parameterVO);
        }
        JSONObject result=null;
        try{
            OtpVO otpVO = FCSServerLocationUtil.createOtp(parameterVOList);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();

            
            int j = 0;
            for(File file : fileList){
                String encodedStr = URLEncoder.encode(file.getName(), "utf-8").replaceAll("\\+", "%20");
                builder.addBinaryBody(GlobalConstants.D_FILES, file, ContentType.APPLICATION_OCTET_STREAM, encodedStr);
            }
            builder.addTextBody(OmcFileConstants.C_ATTR_ONE_TIME_PASSWORD, otpVO.getNonce(), ContentType.create(ContentType.TEXT_PLAIN.getMimeType(), "utf-8"));
            //builder.setCharset(Charset.forName("UTF-8"));
            //builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            
            HttpEntity multipart = builder.build();
            HttpPost httpPost = new HttpPost(fileServiceURLVO.getServiceCheckInFromServerUrl());
            
            
            httpPost.addHeader("X-Requested-With", "XMLHttpRequest");
            httpPost.addHeader("PLM-Server-Name", "PLM");
            
            httpPost.setEntity(multipart);
            result = (JSONObject) callHttpClient(httpPost,"String");
            if(result.getInt(GlobalConstants.M_STATUS_CODE) != ResponseConstants.STATUS_SUCCESS){
                String message = result.getString(GlobalConstants.M_MESSAGE);
                throw new FoundationException("[Foundation.HttpClientUtil.checkInFileFromServerSub]Check In File into FCS Server Error. FCS Error message:" + message);
            }
            JSONObject dataJson  = result.getJSONObject(GlobalConstants.M_DATA);
            JSONArray  fileArray = dataJson.getJSONArray(GlobalConstants.D_FILES);
            for(@SuppressWarnings("unchecked") Iterator<JSONObject>itr = fileArray.iterator(); itr.hasNext();)
            {
                JSONObject fileObject = itr.next();
                FilesVO fileVO = new FilesVO();
                fileVO.setUserFileName(fileObject.getString("userFileName"));
                fileVO.setSysFileName(fileObject.getString("sysFileName"));
                fileVO.setFilePath(fileObject.getString("filePath"));
                fileVO.setSizes((float)fileObject.getLong("sizes"));
                fileVO.setTitles(fileObject.getString("userFileName"));
                fileVO.setFileFormat(fileObject.getString("fileFormat"));
                fileVO.setAssignedType(fileObject.getString("assignedType"));
                fileVO.setRecordMode(GlobalConstants.FILE_RECOMODE_CREATE);
                fileVO.setFromObid(bizVO.getObid());
                fileVO.setFileLocation(locationVO.getLocationName());
                fileVO.setFileStore(storeVO.getStoreName());
                resultFileFile.add(fileVO);
            }
        }catch(Exception e){
            throw new FoundationException("api.object.error.file.checkInFileToServer",e);
        }
        if(fileVOList.size() != resultFileFile.size()) throw new FoundationException("api.object.error.file.checkInFileToServer");
        for(FilesVO vo : resultFileFile){
            for(FilesVO voIn : fileVOList){
                if(vo.getUserFileName().equals(voIn.getUserFileName())){
                    vo.setFileLocation(voIn.getFileLocation());
                    vo.setFileFormat(voIn.getFileFormat());
                    vo.setAssignedType(voIn.getAssignedType());
                    vo.setFileFormat(voIn.getFileFormat());
                    vo.setTitles(voIn.getTitles());
                    vo.setDescriptions(voIn.getDescriptions());
                    vo.setAttribute01(voIn.getAttribute01());
                    vo.setAttribute02(voIn.getAttribute02());
                    vo.setAttribute03(voIn.getAttribute03());
                    vo.setAttribute04(voIn.getAttribute04());
                    vo.setAttribute05(voIn.getAttribute05());
                    vo.setAttribute06(voIn.getAttribute06());
                    vo.setAttribute07(voIn.getAttribute07());
                    vo.setAttribute08(voIn.getAttribute08());
                    vo.setAttribute09(voIn.getAttribute09());
                    vo.setAttribute10(voIn.getAttribute10());
                }
            }
        }
        return resultFileFile;
    }
    @SuppressWarnings("unchecked")
    private static List<FCSParameterVO> copyFileInFCSServerSub(List<FilesVO> fileVOList, String targetLocationName){
        
        if(NullUtil.isNone(fileVOList)) return new ArrayList<FCSParameterVO>();
        List<FCSParameterVO> parameterVOList = new ArrayList<FCSParameterVO>();

        FileServiceURLVO fileServiceURLVO = FCSServerLocationUtil.getFileServiceURLForDownloadWithFile(fileVOList.get(0));
        List<FCSParameterVO> fcsParameterVOList = new ArrayList<FCSParameterVO>();
        StringBuffer strNameListBuf = new StringBuffer();
        for(FilesVO vo : fileVOList){
            FcsLocationVO fcsLocationVO = FCSServerLocationUtil.getFileLocation(vo.getFileLocation());
            FcsLocationVO targetFcsLocationVO = FCSServerLocationUtil.getFileLocation(targetLocationName);
            
            FCSParameterVO parameterVO = new FCSParameterVO();
            String communicationUniqueId = NameGeneratorUtil.generateUniqueName(GlobalConstants.IDGEN_IDKEY_FILE);
            parameterVO.setCommunicationUniqueId(communicationUniqueId);
            vo.getOutData().put(OmcFileConstants.C_FCS_COMMUNICATION_UNIQUE_ID, communicationUniqueId);
            parameterVO.setSourceRootPath(fcsLocationVO.getFilePath());
            parameterVO.setSourceFilePath(vo.getFilePath());
            parameterVO.setTargetRootPath(targetFcsLocationVO.getFilePath());
            parameterVOList.add(parameterVO);
            strNameListBuf.append(vo.getUserFileName()).append(",");
        }
        String nameListStr =  strNameListBuf.substring(0,strNameListBuf.lastIndexOf(","));
        OtpVO otpVO = FCSServerLocationUtil.createOtp(parameterVOList);
        try {
            HttpUtil http = new HttpUtil();
            http.addFormField("oneTimePasswd",otpVO.getNonce());
            http.setUrl(fileServiceURLVO.getServiceCopyFileInServerUrl(), "UTF-8");
            String resultString = http.formFinish(nameListStr); 
            JSONObject result = JSONObject.fromObject(resultString);
            if(result.getInt(GlobalConstants.M_STATUS_CODE) == ResponseConstants.STATUS_SUCCESS){
                JSONObject jResultObject      = result.getJSONObject(GlobalConstants.M_DATA);
                JSONArray  jFileArrayObject   = jResultObject.getJSONArray(GlobalConstants.D_FILES);
                fcsParameterVOList = (List<FCSParameterVO>)JSONArray.toCollection(jFileArrayObject, FCSParameterVO.class);
                return fcsParameterVOList;
            }else{
                String message = result.getString(GlobalConstants.M_MESSAGE);
                LOGGER.error("M_STATUS_CODE : " + result.getInt(GlobalConstants.M_STATUS_CODE));
                LOGGER.error("copyFileInFCSServer : " + message);
                throw new FoundationException("api.object.error.file.copyFileInServer", new Object[] {nameListStr});
            }
        } catch (Exception e) {
            LOGGER.error("copyFileInFCSServer exception : " + e.getMessage());
            throw new FoundationException("api.object.error.file.copyFileInServer",new Object[] {nameListStr});
        }
    }
    private static String checkOutPhysicalFileSub(String temporaryWorkingDir, FilesVO fileVO) throws FoundationException{
        
        List<FCSParameterVO> parameterVOList = new ArrayList<FCSParameterVO>();
        FCSParameterVO parameterVO = new FCSParameterVO();
        String communicationUniqueId = NameGeneratorUtil.generateUniqueName(GlobalConstants.IDGEN_IDKEY_FILE);
        parameterVO.setCommunicationUniqueId(communicationUniqueId);
        fileVO.getOutData().put(OmcFileConstants.C_FCS_COMMUNICATION_UNIQUE_ID, communicationUniqueId);
        parameterVO.setFileObid(fileVO.getObid());
        
        FileServiceURLVO fileServiceURLVO = FCSServerLocationUtil.getFileServiceURLForDownloadWithFile(fileVO);
        fileVO.getOutData().put(OmcApplicationConstants.FILE_OUTATA_fileLocationPhysicalPath, fileServiceURLVO.getFileRootPath());
        fileVO.getOutData().put(OmcApplicationConstants.FILE_OUTATA_fileServiceUrl, fileServiceURLVO.getServiceUrl());
        
        parameterVO.setFileVO(fileVO);
        parameterVOList.add(parameterVO);
        OtpVO otpVO = FCSServerLocationUtil.createOtp(parameterVOList);
        
        HttpUtil http = new HttpUtil();
        String fullPath = temporaryWorkingDir + File.separator + fileVO.getUserFileName();
        try {
            http.addFormField("oneTimePasswd",otpVO.getNonce());
            http.setUrl(fileServiceURLVO.getServiceCheckOutFromServerUrl(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.debug("checkOutFileFromServer IOException");
            throw new FoundationException("api.object.error.file.checkOutFileFromServer",e);
        } catch (IOException e) {
            LOGGER.debug("checkOutFileFromServer IOException");
            throw new FoundationException("api.object.error.file.checkOutFileFromServer",e);
        }
        http.finish(fullPath, fileVO.getUserFileName());
        FileSystemResource resource = null;
        try {
            Path path = Paths.get(new URL(fullPath).toURI());
            resource = new FileSystemResource(path.toFile());
            fullPath = resource.getPath();
            
        } catch (URISyntaxException | MalformedURLException e) {
            resource= new FileSystemResource(fullPath);
            fullPath = resource.getPath();
        }
        return fullPath;
    }
    private static Object callHttpClient(HttpUriRequest httpRequest, String acceptType)
    {
        CloseableHttpClient client      = null;
        CloseableHttpResponse response  = null;
        String bodyAsString             = null;
        byte[] bodayAsByteArray         = null;
        try {
            client = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
            response = client.execute(httpRequest);                 
            if("String".equals(acceptType)){
                bodyAsString = EntityUtils.toString(response.getEntity());
            }else if("ByteArray".equals(acceptType)){
                bodayAsByteArray = EntityUtils.toByteArray(response.getEntity());
            }
            int status = response.getStatusLine().getStatusCode();                    
            if ( status != HttpStatus.SC_OK ) {
                LOGGER.error("Error : http status - " + status);
                throw new FoundationException("http status " + status, "");
            }
        } catch (ParseException e) {
            e.printStackTrace();
            throw new FoundationException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FoundationException(e);
        } finally {
            try {
                if ( response != null ) response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if ( client   != null ) client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if("String".equals(acceptType)){
            JSONObject resultBody = JSONObject.fromObject(bodyAsString);        
            if ( resultBody == null ) {
                return null;
            }
            return resultBody;
        }else if("ByteArray".equals(acceptType)){
            
            return bodayAsByteArray;
        }
        return null;
    }
}
