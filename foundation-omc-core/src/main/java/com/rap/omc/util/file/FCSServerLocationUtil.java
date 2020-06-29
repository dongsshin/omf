/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : SFcsLocation.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 2. 4.  jongjung.kwon   Initial
 * ===========================================
 */
package com.rap.omc.util.file;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rap.omc.api.object.dom.BusinessObjectRoot;
import com.rap.omc.api.object.dom.Files;
import com.rap.omc.api.object.model.FilesVO;
import com.rap.omc.api.object.model.FilesVOMixIn;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.omc.ThreadLocalUtil;
import com.rap.omc.constants.FoundationConstants;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.foundation.file.model.FCSParameterVO;
import com.rap.omc.foundation.file.model.FcsLocationVO;
import com.rap.omc.foundation.file.model.FileServiceURLVO;
import com.rap.omc.foundation.file.model.OtpVO;
import com.rap.omc.foundation.file.service.OtpService;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.api.util.spring.SpringFactoryUtil;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.util.NullUtil;


/**
 * <pre>
 * Class : SFcsLocation
 * Description : TODO
 * </pre>
 * 
 * @author jongjung.kwon
 */
public class FCSServerLocationUtil {
    
    private static FCSServerLocationUtil nInstance;

    private OtpService otpService;
    //private ConfigService propertiesService;
    
    /**
     * Singleton을 구성된 getInstance() method
     */
    private synchronized static FCSServerLocationUtil getInstance(){
        if (nInstance == null) {
            nInstance = new FCSServerLocationUtil();
            nInstance.otpService = (OtpService)SpringFactoryUtil.getBean("otpService");
            //nInstance.propertiesService = (ConfigService)SpringFactoryUtil.getBean("propertiesService");
        }
        return nInstance;
    }
    /**
     * Property 이름을 가지고 Value를 찾을 때 사용
     *
     * @param propertyName Property Name
     * @return
     */
    public static String getProperty(String propertyName){
    	return "LLLLLLLLLLLLLLLLLLLLLLLLLLL";
        //return getInstance().propertiesService.getString(propertyName);
    }
    /**
     * Location혹은 Strore 이름을 가지고 해당 Object를 찾을 때 사용
     *
     * @param storeOrLocationName
     * @return
     */
    
    /*****************************************************************************************************************/
    /**                Defualt Method로스 응용에서는 사용할 이유가 없음
    /*****************************************************************************************************************/
    
    public static FcsLocationVO getFileStore(String storeName){   
        return FileFCSServiceCoreUtil.getFileStore(storeName);
    }
    public static FcsLocationVO getFileLocation(String locationName){   
        return FileFCSServiceCoreUtil.getFileLocation(locationName);
    }
    public static FcsLocationVO getCurrentLocationForUser(String storeName,String userId) {   
        FcsLocationVO fcsLocation= getCurrentLocationForSite(storeName,ThreadLocalUtil.getUserLoginSite());
        return fcsLocation;
    }
    public static FcsLocationVO getCurrentLocationForSite(String storeName,String site) {   
        FcsLocationVO fcsLocation= FileFCSServiceCoreUtil.getFcsLocationForSite(site,storeName);
        return fcsLocation;
    }
    
    public static FcsLocationVO getCurrentStoreUsingBizObject(String bizObid) {
        FcsLocationVO fcsLocation= FileFCSServiceCoreUtil.getStoreUsingObject(bizObid);
        return fcsLocation;
    }
    public static FcsLocationVO getCurrentStoreForLifeCycle(String lifeCycle) {   
        FcsLocationVO fcsLocation= FileFCSServiceCoreUtil.getCurrentStoreForLifeCycle(lifeCycle);
        return fcsLocation;
    }
    public static FileServiceURLVO getFileServiceURLWithLocation(FcsLocationVO  fcsLocationVO) {   
        return(getFileServiceURLSub(fcsLocationVO));
    }
    public static FileServiceURLVO getFileServiceURLForDownloadWithLocation(String locationName) {   
        return getFileServiceURLWithLocation(getFileLocation(locationName));
        
    }
    public static FileServiceURLVO getFileServiceURLForDownloadWithFile(String fileObid) {
        Files fileDom = new Files(fileObid);
        return getFileServiceURLForDownloadWithFile(fileDom.getVo());
    }
    public static FileServiceURLVO getFileServiceURLForDownloadWithFile(FilesVO fileVO) {
        return getFileServiceURLForDownloadWithLocation(fileVO.getFileLocation());
    }
    public static FileServiceURLVO getFileServiceURLForDownloadWithFile(List<FilesVO> fileVOList) {
        Map<String,FileServiceURLVO> map = new HashMap<String,FileServiceURLVO>();
        FileServiceURLVO tempFileServiceURLVO = null;
        for(FilesVO vo : fileVOList){
            tempFileServiceURLVO = getFileServiceURLForDownloadWithFile(vo);
            map.put(tempFileServiceURLVO.getServiceUrl(), tempFileServiceURLVO);
        }
        if(map.keySet().size() == 0) return null;
        if(map.keySet().size() > 1) throw new FoundationException("[Foundation.FCSServerLocationUtil.getFileServiceURLInfoWithLifeCycle]Cannot support muti Site Service.");
        return(tempFileServiceURLVO);
    }
    
    /*****************************************************************************************************************/
    /**                File Check In을 위해서는 아래 Method를 통해서 반드시 URL를 Get해야 함.
    /*****************************************************************************************************************/
    public static FileServiceURLVO getFileServiceURLForCheckInWithLocation(FcsLocationVO  fcsLocationVO) {
        return getFileServiceURLSub(fcsLocationVO);
    }
    public static FileServiceURLVO getFileServiceURLForCheckInWithFile(FilesVO  fileVO) {
        return getFileServiceURLForCheckInWithLocation(getFileLocation(fileVO.getFileLocation()));
    }
    public static FileServiceURLVO getFileServiceURLForCheckInFileWithBizObid(String obid, String userId) {   
        BusinessObjectRoot bizDom = new BusinessObjectRoot(obid);
        if(bizDom.getVo().getClassName().equals(FoundationConstants.BIZCLASS_FILES)) throw new FoundationException("[Foundation.FCSServerLocationUtil.getFileServiceURLInfoWithBizObid()]Files Object is not supported.");
        FcsLocationVO  fcsStoreVO = getCurrentStoreForLifeCycle(bizDom.getVo().getLifeCycle());
        if(NullUtil.isNull(fcsStoreVO)) return null;
        if(userId.equals(GlobalConstants.NO_USER_ID)) throw new FoundationException("[Foundation.FCSServerLocationUtil.getFileServiceURLInfoWithBizObid()]User info invalid.");
        FcsLocationVO  fcsLocationVO = getCurrentLocationForUser(fcsStoreVO.getStoreName(),userId);
        if(NullUtil.isNull(fcsLocationVO)) return null;
        return getFileServiceURLForCheckInWithLocation(fcsLocationVO);
    }
    public static FileServiceURLVO getFileServiceURLForCheckInFileWithBizObidSet(Set<String> obidSet, String userId) {   
        Map<String,FileServiceURLVO> map = new HashMap<String,FileServiceURLVO>();
        FileServiceURLVO tempFileServiceURLVO = null;
        for(String obid : obidSet){
            tempFileServiceURLVO = getFileServiceURLForCheckInFileWithBizObid(obid,userId);
            map.put(tempFileServiceURLVO.getServiceUrl(), tempFileServiceURLVO);
        }
        if(map.keySet().size() == 0) return null;
        if(map.keySet().size() > 1) throw new FoundationException("[Foundation.FCSServerLocationUtil.getFileServiceURLInfoWithLifeCycle]Cannot support muti Site Service.");
        return(tempFileServiceURLVO);
    }
    public static FileServiceURLVO getFileServiceURLForCheckInFileWithLifeCycle(String lifeCycleName, String userId) {   
        FcsLocationVO  fcsStoreVO = getCurrentStoreForLifeCycle(lifeCycleName);
        if(NullUtil.isNull(fcsStoreVO)) return null;
        FcsLocationVO  fcsLocationVO = getCurrentLocationForUser(fcsStoreVO.getStoreName(),userId);
        if(NullUtil.isNull(fcsLocationVO)) return null;
        return getFileServiceURLForCheckInWithLocation(fcsLocationVO);
    }
    
    public static FileServiceURLVO getFileServiceURLForCheckInFileWithStore(String storeName, String userId) {   
        FcsLocationVO  fcsLocationVO = getCurrentLocationForUser(storeName,userId);
        if(NullUtil.isNull(fcsLocationVO)) return null;
        return getFileServiceURLForCheckInWithLocation(fcsLocationVO);
    }
    
    public static FileServiceURLVO getFileServiceURLForCheckInFileWithLifeCycleSet(Set<String> lifeCycleNameSet, String userId) {   
        
        Map<String,FileServiceURLVO> map = new HashMap<String,FileServiceURLVO>();
        FileServiceURLVO tempFileServiceURLVO = null;
        for(String lifeCycle : lifeCycleNameSet){
            tempFileServiceURLVO = getFileServiceURLForCheckInFileWithLifeCycle(lifeCycle,userId);
            map.put(tempFileServiceURLVO.getServiceUrl(), tempFileServiceURLVO);
        }
        if(map.keySet().size() == 0) return null;
        if(map.keySet().size() > 1) throw new FoundationException("[Foundation.FCSServerLocationUtil.getFileServiceURLInfoWithLifeCycle]Cannot support muti Site Service.");
        return(tempFileServiceURLVO);
    }

    /*****************************************************************************************************************/
    public static OtpVO createOtp(){
        return createOtp(new ArrayList<FCSParameterVO>());  
    }
    public static OtpVO createOtp(List<FCSParameterVO> parmList){
        Map<String, Object> parmMap = new HashMap<String, Object>();
        parmMap.put("userId", ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, GlobalConstants.NO_USER_ID));
        parmMap.put("remoteAddr", ThreadLocalUtil.getString(ThreadLocalUtil.KEY.remoteAddr, ""));
        parmMap.put("requestUri", ThreadLocalUtil.getString(ThreadLocalUtil.KEY.requestUri, ""));
        parmMap.put("plantUnit", ThreadLocalUtil.getString(ThreadLocalUtil.KEY.plantUnit, ""));
        parmMap.put("timeZone", ThreadLocalUtil.getString(ThreadLocalUtil.KEY.timeZone, "Asia/Seoul"));
        parmMap.put("businessUnit", ThreadLocalUtil.getString(ThreadLocalUtil.KEY.businessUnit, ""));
        parmMap.put("divisionUnit", ThreadLocalUtil.getString(ThreadLocalUtil.KEY.divisionUnit, ""));
        parmMap.put("researchCenter", ThreadLocalUtil.getString(ThreadLocalUtil.KEY.divisionUnit, ""));
        parmMap.put("executor", ThreadLocalUtil.getString(ThreadLocalUtil.KEY.executor, ""));
        parmMap.put("locale", ThreadLocalUtil.getString(ThreadLocalUtil.KEY.locale, OmcSystemConstants.OMC_LOCALE_LANG_KO));
        parmMap.put("site", ThreadLocalUtil.getString(ThreadLocalUtil.KEY.locale, "LG"));
        parmMap.put("parameterVOList", parmList);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.convertValue(parmMap, JsonNode.class);
        String sessionInfoStr = "";
        try {
            sessionInfoStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new FoundationException("[Foundation.FCSServerLocationUtil.createOtp]Error During Convert to Json.Error Desc:" + e.getMessage());
        }
        return createOtp(sessionInfoStr);
    }
    public static OtpVO createOtp(Map<String, String> map){
        Map<String, Object> parmMap = new HashMap<String, Object>();
        parmMap.put("userId", ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, GlobalConstants.NO_USER_ID));
        parmMap.put("remoteAddr", ThreadLocalUtil.getString(ThreadLocalUtil.KEY.remoteAddr, ""));
        parmMap.put("requestUri", ThreadLocalUtil.getString(ThreadLocalUtil.KEY.requestUri, ""));
        parmMap.put("plantUnit", ThreadLocalUtil.getString(ThreadLocalUtil.KEY.plantUnit, ""));
        parmMap.put("timeZone", ThreadLocalUtil.getString(ThreadLocalUtil.KEY.timeZone, "Asia/Seoul"));
        parmMap.put("businessUnit", ThreadLocalUtil.getString(ThreadLocalUtil.KEY.businessUnit, ""));
        parmMap.put("divisionUnit", ThreadLocalUtil.getString(ThreadLocalUtil.KEY.divisionUnit, ""));
        parmMap.put("researchCenter", ThreadLocalUtil.getString(ThreadLocalUtil.KEY.divisionUnit, ""));
        parmMap.put("executor", ThreadLocalUtil.getString(ThreadLocalUtil.KEY.executor, ""));
        parmMap.put("locale", ThreadLocalUtil.getString(ThreadLocalUtil.KEY.locale, OmcSystemConstants.OMC_LOCALE_LANG_KO));
        if(!NullUtil.isNull(map)){
            for(String key : map.keySet()){
                parmMap.put(key, map.get(key));
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.convertValue(parmMap, JsonNode.class);
        String sessionInfoStr = "";
        try {
            sessionInfoStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new FoundationException("[Foundation.FCSServerLocationUtil.createOtp]Error During Convert to Json.Error Desc:" + e.getMessage());
        }
        return createOtp(sessionInfoStr);
    }
    public static OtpVO createOtp(String additional){
        OtpVO input = new OtpVO();
        input.setRemoteAddr(ThreadLocalUtil.getString(ThreadLocalUtil.KEY.remoteAddr, GlobalConstants.NO_REMOTE_ADDR) );
        input.setCreator(ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, GlobalConstants.NO_USER_ID));
        input.setCreated(new Date());
        input.setModifier(ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, GlobalConstants.NO_USER_ID));
        input.setModified(new Date());
        input.setSessionInfo(additional);
        getInstance().otpService.createOtp(input);        
        return input;
    }
    public static OtpVO checkAndGetOtp(OtpVO input){
        return getInstance().otpService.checkAndGetOtp(input);
    }
    public static OtpVO getOtp(OtpVO input){
        return getInstance().otpService.getOtp(input);
    }
    @SuppressWarnings("unchecked")
    public static Map<String,Object> convertSessionInfo(OtpVO otpVO){
        ObjectMapper mapper = new ObjectMapper();
        mapper.addMixIn(FilesVO.class, FilesVOMixIn.class);
        Map<String,Object> sessionInfoMap = null;
        TypeReference<HashMap<String,Object>> typeRef  = new TypeReference<HashMap<String,Object>>(){};
        try {
            sessionInfoMap = mapper.readValue(otpVO.getSessionInfo(), typeRef);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Object obj = (ArrayList<Object>)sessionInfoMap.get(OmcFileConstants.C_FCS_PARAMETER_VO_LIST);  
        List<FCSParameterVO> parameterVOList = new ArrayList<FCSParameterVO>();
        if(!NullUtil.isNull(obj)){
            List<Object> subObject = (ArrayList<Object>)(ArrayList<Object>)sessionInfoMap.get(OmcFileConstants.C_FCS_PARAMETER_VO_LIST);       
            TypeReference<List<FCSParameterVO>> parameterTypeRef  = new TypeReference<List<FCSParameterVO>>(){};
            parameterVOList = mapper.convertValue(subObject, parameterTypeRef);
        }
        sessionInfoMap.put(OmcFileConstants.C_FCS_PARAMETER_VO_LIST,parameterVOList);
        return sessionInfoMap;
    }
    /**
     *
     * @param outfcsLocationVO
     * @return
     */
    public static String getCopyFileServiceUrl(FcsLocationVO outfcsLocationVO){
        return outfcsLocationVO.getServiceUrl() + getProperty("fcs.serviceUri.copyFileInServer");
    }
    public static String getCheckFileSizeServiceUrl(FcsLocationVO outfcsLocationVO){
        return outfcsLocationVO.getServiceUrl() + getProperty("fcs.serviceUri.getFileSizeInServer");
    }
    /**
     * Token Check Intercepter에서 사용함
     *
     * @param requestUrl
     * @return
     */
    public static boolean checkURLForFcsIntercepter(String requestUrl){
        String strUrl = getProperty("fms.homeUrl");
        //개발 및 테스트중에 아래 중  strUrl를 조정해서 Skip하도록 함.
        if(requestUrl.startsWith(strUrl)) return true;
        return true;
    }
    private static FileServiceURLVO getFileServiceURLSub(FcsLocationVO  fcsLocationVO) {
        FileServiceURLVO fileServiceURLVO = new FileServiceURLVO();
        String currentFcsEnvironment = getProperty("fcsEnvironment.current");
        if(!StrUtil.isEmpty(currentFcsEnvironment) && currentFcsEnvironment.equals("local")){
            String localServiceUrl = getProperty("fcsEnvironment.localServiceUrl");
            if(!StrUtil.isEmpty(localServiceUrl)) fcsLocationVO.setServiceUrl(localServiceUrl);
        }
        setServiceURL(fileServiceURLVO,fcsLocationVO);
        return fileServiceURLVO;
    }
    
    private static void setServiceURL(FileServiceURLVO fileServiceURLVO,FcsLocationVO  fcsLocationVO){
        if( !NullUtil.isNull(fcsLocationVO) ){
            fileServiceURLVO.setServiceUrl(fcsLocationVO.getServiceUrl());
            fileServiceURLVO.setFileRootPath(fcsLocationVO.getFilePath());
            fileServiceURLVO.setSchemaObid(fcsLocationVO.getLocationObid());
            fileServiceURLVO.setServiceCheckOutAllUrl              (fcsLocationVO.getServiceUrl() + getProperty("fcs.serviceUri.checkOutAll"));
            fileServiceURLVO.setServiceCheckOutAllUrl              (fcsLocationVO.getServiceUrl() + getProperty("fcs.serviceUri.checkOutAll"));
            fileServiceURLVO.setServiceCheckOutUrl                 (fcsLocationVO.getServiceUrl() + getProperty("fcs.serviceUri.checkOut"));
            fileServiceURLVO.setServiceCheckInUrl                  (fcsLocationVO.getServiceUrl() + getProperty("fcs.serviceUri.checkIn"));
            fileServiceURLVO.setServiceCheckInFromServerUrl(fcsLocationVO.getServiceUrl() + getProperty("fcs.serviceUri.checkInFromFmsToServer"));
            fileServiceURLVO.setServiceCheckOutFromServerUrl(fcsLocationVO.getServiceUrl() + getProperty("fcs.serviceUri.checkOutFromFmsToServer"));
            fileServiceURLVO.setServiceCopyFileInServerUrl         (fcsLocationVO.getServiceUrl() + getProperty("fcs.serviceUri.copyFileInServer"));
            fileServiceURLVO.setServiceMoveFileForDeleteServerUrl(fcsLocationVO.getServiceUrl() + getProperty("fcs.serviceUri.moveFileForDelete"));
        }
    }
    
    public static String getBasePrivateDir(){
    	return "";
        //return getInstance().propertiesService.getString("fms.basePrivateDir");
    }
    
}

