/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : FileUtil.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 6. 17.  jongjung.kwon   Initial
 * ===========================================
 */
package com.rap.omc.api.util.omc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rap.omc.api.object.dom.Files;
import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.api.object.model.FilesVO;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.FoundationDaoUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.foundation.file.model.FCSFileVO;
import com.rap.omc.foundation.file.model.FcsLocationVO;
import com.rap.omc.foundation.lifecycle.model.FormatInfo;
import com.rap.omc.schema.util.OmcApplicationConstants;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.util.foundation.LifeCycleUtil;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;
import com.rap.omc.util.file.FCSServerLocationUtil;
import com.rap.omc.util.file.HttpClientUtil;


/**
 * <pre>
 * Class : FileUtil
 * Description : TODO
 * </pre>
 * 
 * @author jongjung.kwon
 */
public class FileFoundationUtil {
    private static String[] validRecodeModeSet = {GlobalConstants.CREATE_RECORD_MODE,
                                                  GlobalConstants.UPDATE_RECORD_MODE,
                                                  GlobalConstants.DELETE_RECORD_MODE};
    private static FileFoundationUtil nInstance;

    //private ConfigService propertiesService;
    
    private String fileDeleteMethodType;
    
    /**
     * Singleton을 구성된 getInstance() method
     */
    private synchronized static FileFoundationUtil getInstance(){
        if (nInstance == null) {
            nInstance = new FileFoundationUtil();      
            //nInstance.propertiesService = (ConfigService)SpringFactoryUtil.getBean("propertiesService");
            //nInstance.fileDeleteMethodType = nInstance.propertiesService.getString("fms.fileDeleteMethodType");
        }
        return nInstance;
    }
    public static void deleteFile(FilesVO file){
        insertFileDeleteHistory(file);
        if("online".equals(getInstance().fileDeleteMethodType)){
            HttpClientUtil.deleteFileFromServer(file);
            updateFileDeleteHistory(file);
        }        
    }
    public static String parseFileForLogging(String fileAddType, List<FilesVO> fileList){
        
        StringBuffer sbObid = new StringBuffer("|");
        StringBuffer sbCrud = new StringBuffer("|");
        StringBuffer sbFilePath = new StringBuffer("|");
        StringBuffer sbUserFileName = new StringBuffer("|");
        StringBuffer sbFileSize = new StringBuffer("|");

        for(FilesVO file : fileList){
           sbObid.append(file.getObid()).append(",");
           sbFilePath.append(file.getFilePath()).append(",");
           sbUserFileName.append(file.getUserFileName()).append(",");
           sbFileSize.append(file.getSizes()).append(",");
           
           if(GlobalConstants.FILE_APPEND.equals(fileAddType)){
               sbCrud.append(GlobalConstants.CREATE_RECORD_MODE).append(",");
           }else if (GlobalConstants.FILE_REPLACE.equals(fileAddType)) {
               sbCrud.append(GlobalConstants.UPDATE_RECORD_MODE).append(",");
           } else if (GlobalConstants.FILE_MODIFY.equals(fileAddType)) {
               if (GlobalConstants.CREATE_RECORD_MODE.equals(file.getActType())) {
                   sbCrud.append(GlobalConstants.CREATE_RECORD_MODE).append(",");
               } else if (GlobalConstants.UPDATE_RECORD_MODE.equals(file.getActType())) {
                   sbCrud.append(GlobalConstants.UPDATE_RECORD_MODE).append(",");
               } else if (GlobalConstants.DELETE_RECORD_MODE.equals(file.getActType())) {
                   sbCrud.append(GlobalConstants.DELETE_RECORD_MODE).append(",");
               }
           } else if (GlobalConstants.FILE_DELETE.equals(fileAddType)) {
               sbCrud.append(GlobalConstants.DELETE_RECORD_MODE).append(",");
           }
        }
        
        sbObid.deleteCharAt(sbObid.length()-1);
        sbCrud.deleteCharAt(sbCrud.length()-1);
        sbFilePath.deleteCharAt(sbFilePath.length()-1);
        sbUserFileName.deleteCharAt(sbUserFileName.length()-1);
        sbFileSize.deleteCharAt(sbFileSize.length()-1);
        
        return sbObid.append(sbCrud.toString()).append(sbFilePath.toString()).append(sbUserFileName.toString()).append(sbFileSize.append("|")).toString();
    }
    
    public static String parseFileForLogging(String fileAddType, FilesVO file){
        
        StringBuffer sbObid = new StringBuffer("|");
        StringBuffer sbCrud = new StringBuffer("|");
        StringBuffer sbFilePath = new StringBuffer("|");
        StringBuffer sbUserFileName = new StringBuffer("|");
        StringBuffer sbFileSize = new StringBuffer("|");
        
        sbObid.append(file.getObid());
        sbFilePath.append(file.getFilePath());
        sbUserFileName.append(file.getUserFileName());
        sbFileSize.append(file.getSizes());

        if(GlobalConstants.FILE_APPEND.equals(fileAddType)){
           sbCrud.append(GlobalConstants.CREATE_RECORD_MODE);
        }else if (GlobalConstants.FILE_REPLACE.equals(fileAddType)) {
           sbCrud.append(GlobalConstants.UPDATE_RECORD_MODE);
        } else if (GlobalConstants.FILE_MODIFY.equals(fileAddType)) {
           if (GlobalConstants.CREATE_RECORD_MODE.equals(file.getActType())) {
               sbCrud.append(GlobalConstants.CREATE_RECORD_MODE);
           } else if (GlobalConstants.UPDATE_RECORD_MODE.equals(file.getActType())) {
               sbCrud.append(GlobalConstants.UPDATE_RECORD_MODE);
           } else if (GlobalConstants.DELETE_RECORD_MODE.equals(file.getActType())) {
               sbCrud.append(GlobalConstants.DELETE_RECORD_MODE);
           }
        } else if (GlobalConstants.FILE_DELETE.equals(fileAddType)) {
           sbCrud.append(GlobalConstants.DELETE_RECORD_MODE);
        }
        
        return sbObid.append(sbCrud.toString()).append(sbFilePath.toString()).append(sbUserFileName.toString()).append(sbFileSize.append("|")).toString();
        //return sbObid.append(sbCrud.toString()).append(sbFilePath.append("|")).toString(); 
    }
    
    public static String parseFileForLogging(FilesVO file){
        
        StringBuffer sbObid = new StringBuffer("|");
        StringBuffer sbCrud = new StringBuffer("|");
        StringBuffer sbFilePath = new StringBuffer("|");
        StringBuffer sbUserFileName = new StringBuffer("|");
        StringBuffer sbFileSize = new StringBuffer("|");
        
        sbObid.append(file.getObid());
        sbCrud.append("R");
        sbFilePath.append(file.getFilePath());
        sbUserFileName.append(file.getUserFileName());
        sbFileSize.append(file.getSizes());
        return sbObid.append(sbCrud.toString()).append(sbFilePath.toString()).append(sbUserFileName.toString()).append(sbFileSize.append("|")).toString();
    }
    
    /**
    *
    * @param fileList
    * @return
    */
   public static String parseFileForLogging(List<FilesVO> fileList){

       StringBuffer sbObid = new StringBuffer("|");
       StringBuffer sbCrud = new StringBuffer("|");
       StringBuffer sbFilePath = new StringBuffer("|");
       StringBuffer sbUserFileName = new StringBuffer("|");
       StringBuffer sbFileSize = new StringBuffer("|");
       
       for(FilesVO file : fileList){
          sbObid.append(file.getObid()).append(",");
          sbCrud.append("R").append(",");
          sbFilePath.append(file.getFilePath()).append(",");
          sbUserFileName.append(file.getUserFileName()).append(",");
          sbFileSize.append(file.getSizes()).append(",");
            
       }
       sbObid.deleteCharAt(sbObid.length()-1);
       sbCrud.deleteCharAt(sbCrud.length()-1);
       sbFilePath.deleteCharAt(sbFilePath.length()-1);
       sbUserFileName.deleteCharAt(sbUserFileName.length()-1);
       sbFileSize.deleteCharAt(sbFileSize.length()-1);
       return sbObid.append(sbCrud.toString()).append(sbFilePath.toString()).append(sbUserFileName.toString()).append(sbFileSize.append("|")).toString();
    }
   
    private static void insertFileDeleteHistory(FilesVO file){
        file.setFromObid(file.getFromObid() + ":" + GlobalConstants.FILE_DELETE_ING_STATUS);
        FoundationDaoUtil.insert("file.insertFileDeleteHistory", file);
    }
    
    private static void updateFileDeleteHistory(FilesVO file){
        
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("obid", file.getObid());
        param.put("beforeFromObid", file.getFromObid());
        param.put("afterFromObid", file.getFromObid().replace(GlobalConstants.FILE_DELETE_ING_STATUS, GlobalConstants.FILE_DELETE_COMPLETE_STATUS));
        param.put("filePath", file.getFilePath());
        
        FoundationDaoUtil.insert("file.updateFileDeleteHistory", param);
    }
    public static boolean isUploadedExists(List<FCSFileVO> willBeUploadedList, String ... recodeMode){
        for(FCSFileVO fileVO : willBeUploadedList){
            if(!NullUtil.isNone(fileVO.getRecordMode())){
                for(int i = 0; i < recodeMode.length; i++){
                    if(recodeMode[i].equals(fileVO.getRecordMode())) return true;
                }
            }
        }
        return false;
    }
    public static boolean isNullRecodeModeExists(List<FCSFileVO> willBeUploadedList){
        for(FCSFileVO fileVO : willBeUploadedList){
            if(NullUtil.isNone(fileVO.getRecordMode())) return true;
        }
        return false;
    }
    public static boolean checkRecodeMode(List<FCSFileVO> willBeUploadedList,Map<String, Object> resultData){
        for(FCSFileVO fileVO : willBeUploadedList){
            if(!NullUtil.isNone(fileVO.getRecordMode())){
                if(!StrUtil.in(fileVO.getRecordMode(),validRecodeModeSet)){
                    resultData.put(GlobalConstants.M_MESSAGE, "[Foundation] Invalid Recod Mode(" + fileVO.getRecordMode() + ")");
                    return false;
                }
            }
        }
        return true;
    }
    public static List<FilesVO> getReleatedFile(FilesVO fileVO){
        StringBuffer selectPatternBuf = new StringBuffer();
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();

        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[fromObid]", GlobalConstants.OQL_OPERATOR_EQUAL, fileVO.getFromObid());
        if (!NullUtil.isNone(fileVO.getFileFormat())) {
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[fileFormat]",
                    GlobalConstants.OQL_OPERATOR_IN, fileVO.getFileFormat());
        }
        if (!NullUtil.isNone(fileVO.getAssignedType())) {
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[assignedType]",
                    GlobalConstants.OQL_OPERATOR_IN, fileVO.getAssignedType());
        }
        if (!NullUtil.isNone(fileVO.getUserFileName())) {
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[userFileName]",
                    GlobalConstants.OQL_OPERATOR_EQUAL, fileVO.getUserFileName());
        }
        if (!NullUtil.isNone(fileVO.getAttribute01())) {
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[attribute01]",
                    GlobalConstants.OQL_OPERATOR_EQUAL, fileVO.getAttribute01());
        }
        if (!NullUtil.isNone(fileVO.getAttribute02())) {
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[attribute02]",
                    GlobalConstants.OQL_OPERATOR_EQUAL, fileVO.getAttribute02());
        }
        if (!NullUtil.isNone(fileVO.getAttribute03())) {
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[attribute03]",
                    GlobalConstants.OQL_OPERATOR_EQUAL, fileVO.getAttribute03());
        }
        if (!NullUtil.isNone(fileVO.getAttribute04())) {
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[attribute04]",
                    GlobalConstants.OQL_OPERATOR_EQUAL, fileVO.getAttribute04());
        }
        if (!NullUtil.isNone(fileVO.getAttribute05())) {
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[attribute05]",
                    GlobalConstants.OQL_OPERATOR_EQUAL, fileVO.getAttribute05());
        }
        if (!NullUtil.isNone(fileVO.getAttribute06())) {
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[attribute06]",
                    GlobalConstants.OQL_OPERATOR_EQUAL, fileVO.getAttribute06());
        }
        String selectPattern = selectPatternBuf.toString();
        String wherePattern = wherePatternBuf.toString();
        String paramPattern = paramPatternBuf.toString();
        List<FilesVO> fileList = ObjectRoot.findObjects(OmcSystemConstants.BIZCLASS_FILES, GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, false,
                false, false, false, selectPattern, wherePattern, paramPattern, true, 0);

        return fileList;
    }
    /**
    *
    * @param file
    * @param policy
    */
   public static boolean isAllowedSuffix(FilesVO file, String policy){
       List<FormatInfo> formatList = LifeCycleUtil.getFormatListForLifeCycle(policy);
       boolean allowedSuffix = false;
       int extensionIndex = 0;
       if(NullUtil.isNull(file.getUserFileName().lastIndexOf("."))
               || file.getUserFileName().lastIndexOf(".") < 0){
           extensionIndex = 0;
       }else{
           extensionIndex = file.getUserFileName().lastIndexOf(".");
       }
       for (FormatInfo format : formatList) {
           if (GlobalConstants.FILE_ALL_EXTENSION.equals(format.getAllowedSuffix())) {
               allowedSuffix = true;
               break;
           } else if (file.getUserFileName().substring(extensionIndex)
                   .equalsIgnoreCase(format.getAllowedSuffix().substring(format.getAllowedSuffix().lastIndexOf(".")))) {
               allowedSuffix = true;
               break;
           }
       }
       return allowedSuffix;
   }
   public static FilesVO getDetailedFileInfo(String fileObid){
       Files fileDom = new Files(fileObid);
       FcsLocationVO location = getFCSLocationInfo(fileObid);
       fileDom.getVo().getOutData().put(OmcApplicationConstants.FILE_OUTATA_fileLocationPhysicalPath, location.getFilePath());
       return fileDom.getVo();
   }
   public static FcsLocationVO getFCSLocationInfo(String fileObid){
       Files fileDom = new Files(fileObid);
       FcsLocationVO location = FCSServerLocationUtil.getFileLocation(fileDom.getVo().getFileLocation());
       if(NullUtil.isNull(location)){
           location = FCSServerLocationUtil.getFileStore(fileDom.getVo().getFileStore());
       }
       return location;
   }
}
