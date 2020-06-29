/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : BBSItem.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.common.dom;


import java.util.Map;

import com.rap.omc.api.object.dom.BusinessObjectMaster;
import com.rap.omc.api.util.omc.ThreadLocalUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.framework.exception.ApplicationException;

import rap.api.object.common.model.BBSItemVO;


public class BBSItem extends BusinessObjectMaster {
    public BBSItem(String obid){
        super(obid);
    }
    public BBSItem(BBSItemVO vo){
        super(vo);
    }
    @Override
    public BBSItemVO getVo(){
        return (BBSItemVO)super.getVo();
    }
    @Override
    public void initialize(){
        super.initialize();
        initializeBBSItem();
    }
    public void initializeBBSItem(){
    /*code here*/
    }
    @Override
    public String toString() {
        return "BBSItem[toString()=" + super.toString() + "]";
    }


    @Override
    protected void validateForChange(String newClassName, String newName, String newLifeCycle, String newStates, Map<String, Object> map){
        super.validateForChange(newClassName, newName, newLifeCycle, newStates, map);
        /*code below*/

    }

    @Override
    protected void preProcessForChange(String newClassName, String newName, String newLifeCycle, String newStates, Map<String, Object> map){
        super.preProcessForChange(newClassName, newName, newLifeCycle, newStates, map);
        /*code below*/

    }

    @Override
    protected void postProcessForChange(String newClassName, String newName, String newLifeCycle, String newStates, Map<String, Object> map){
        super.postProcessForChange(newClassName, newName, newLifeCycle, newStates, map);
        /*code below*/

    }

   @Override
    protected void validateForCreate(Map<String, Object> map){
        super.validateForCreate(map);
        /*code below*/

    }

   @Override
    protected void preProcessForCreate(Map<String, Object> map){
        super.preProcessForCreate(map);
        /*code below*/

    }

   @Override
    protected void postProcessForCreate(Map<String, Object> map){
        super.postProcessForCreate(map);
        /*code below*/

    }

   @Override
    protected void validateForDelete(Map<String, Object> map){
        super.validateForDelete(map);
        /*code below*/

    }

   @Override
    protected void preProcessForDelete(Map<String, Object> map){
        super.preProcessForDelete(map);
        String userID = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, "");
        //boolean isAdmin = UserServiceUtil.hasRole( GlobalConstants.ROLE_NAME_SYSTEM_ADMIN);
        boolean isAdmin = false;
        
        if( userID.equals(this.getVo().getCreator()) 
            || userID.equals(this.getVo().getModifier()) 
            || userID.equals(this.getVo().getOwner()) 
            || userID.equals(this.getVo().getCheckouter()) 
            || userID.equals(this.getVo().getLocker()) 
            //|| userID.equals(PlmConstants.INTEGRATION_USER)
            || userID.equals(GlobalConstants.USER_CORPORATE)
            || isAdmin ){
        } else {
            throw new ApplicationException("plm.common.error.delete.access");
        }

    }

   @Override
    protected void postProcessForDelete(Map<String, Object> map){
        super.postProcessForDelete(map);
        /*code below*/

    }

   @Override
    protected void validateForModify(Map<String, Object> map){
        super.validateForModify(map);
        /*code below*/

    }

   @Override
    protected void preProcessForModify(Map<String, Object> map){
        super.preProcessForModify(map);
        String userID = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, "");
        //boolean isAdmin = UserSessionUtil.hasRole(PlmConstants.ROLE_NAME_SYSTEM_ADMIN );
        boolean isAdmin = false;
        
        if( userID.equals(this.getVo().getCreator()) 
            || userID.equals(this.getVo().getModifier()) 
            || userID.equals(this.getVo().getOwner()) 
            || userID.equals(this.getVo().getCheckouter()) 
            || userID.equals(this.getVo().getLocker())
            //|| userID.equals(PlmConstants.INTEGRATION_USER)
            || userID.equals(GlobalConstants.USER_CORPORATE)
            //|| userID.equals(PlmConstants.ADMIN_USER)
            || isAdmin ){
            super.preProcessForModify(map);
        } else {
            throw new ApplicationException("plm.common.error.modify.access");
        }

    }

   @Override
    protected void postProcessForModify(Map<String, Object> map){
        super.postProcessForModify(map);
        /*code below*/

    }

   @Override
    protected void validateForWithdraw(Map<String, Object> map){
        super.validateForWithdraw(map);
        /*code below*/

    }

   @Override
    protected void preProcessForWithdraw(Map<String, Object> map){
        super.preProcessForWithdraw(map);
        /*code below*/

    }

   @Override
    protected void postProcessForWithdraw(Map<String, Object> map){
        super.postProcessForWithdraw(map);
        /*code below*/

    }

   @Override
    protected void validateForDemote(Map<String, Object> map){
        super.validateForDemote(map);
        /*code below*/

    }

   @Override
    protected void preProcessForDemote(Map<String, Object> map){
        super.preProcessForDemote(map);
        /*code below*/

    }

   @Override
    protected void postProcessForDemote(Map<String, Object> map){
        super.postProcessForDemote(map);
        /*code below*/

    }

   @Override
    protected void validateForPromote(Map<String, Object> map){
        super.validateForPromote(map);
        /*code below*/

    }

   @Override
    protected void preProcessForPromote(Map<String, Object> map){
        super.preProcessForPromote(map);
        /*code below*/

    }

   @Override
    protected void postProcessForPromote(Map<String, Object> map){
        super.postProcessForPromote(map);
        /*code below*/

    }

   @Override
    protected void validateForClone(Map<String, Object> map){
        super.validateForClone(map);
        /*code below*/

    }

   @Override
    protected void preProcessForClone(Map<String, Object> map){
        super.preProcessForClone(map);
        /*code below*/

    }

   @Override
    protected void postProcessForClone(Map<String, Object> map){
        super.postProcessForClone(map);
        /*code below*/

    }
   /* ���� �߼� �� ����ϴ� Form*/
   public String[][] getHtmlFormInfo(){
       String[][] result = {
               { "names", "ID", "", "3" },
               { "module", "Module", "", "3" },
               { "titles", "Subject", "", "3" },
               { "descriptions", "Body", "", "3" },
               { "created", "Created Date", "", "3" },
               { "publishStartDate", "Publish Start", "", "3" },
               { "publishEndDate", "Publish End", "", "3" },
               { "creatorName", "Creator", "", "3" },
               { "workPhoneNumber", "Tel", "", "3" }
       };
       return result;
   }
//   
//   public HashMap<String,Object> getHtmlFormValueInfo(){
//       BBSItemVO vo = getVo();
//       HashMap<String,Object> data = new HashMap<String, Object>();
//       Users userDom = Users.getUsers(vo.getCreator());
//       SimpleDateFormat date = new SimpleDateFormat(PlmConstants.DEFAULT_DATE_FORMAT);
//       SimpleDateFormat time = new SimpleDateFormat(PlmConstants.DEFAULT_DATETIME_MINUTE_FORMAT);
//
//       data.put("names", vo.getNames());
//       data.put("module", vo.getModule());
//       data.put("titles", vo.getTitles());
//       data.put("descriptions", vo.getBbsContents());
//       data.put("created",  time.format( vo.getCreated()));
//       data.put("publishStartDate", date.format( vo.getPublishStartDate()));
//       data.put("publishEndDate",  date.format( vo.getPublishEndDate()));
//       data.put("creatorName", userDom.getVo().getTitles());
//       data.put("workPhoneNumber", userDom.getVo().getWorkPhoneNumber());
//       
//       return data;
//   }
//   
   /**
    * ���Ϲ߼� �� ���Ǵ� Attach Files List HTML ����
    * @return
    */
//   public HashMap<String,Object> getAttacheFileListInfo(){
//       String[][] gridKey = {
//               { "fileName",      "1", "1" },
//               { "comment",       "1", "1" },
//               { "creator",       "1", "1" },
//               { "creationDate",  "1", "1" }
//       };
//       String[][][] gridHeader = {
//               {
//                   {"File Name",      "1", "1", "300"},
//                   {"Comments",       "1", "1", "100"},
//                   {"Creator",        "1", "1", "100"},
//                   {"Creation Date",  "1", "1", "200"}
//               }
//       };
//
//       List<HashMap<String,Object>> gridValueList = new ArrayList<HashMap<String,Object>>();
//       SimpleDateFormat sdf = new SimpleDateFormat(PlmConstants.DEFAULT_DATETIME_MINUTE_FORMAT);
//       List<FilesVO> targetList = this.getReleatedFiles( true );
//       if( targetList != null && targetList.size() > 0 ){
//           FilesVO fileVo = null;
//           String fileTemplate = "<span class=\"gpdmFileDownload\" onclick=\"fnDownloadFile('" + MailContentsUtil.getHostUrl()+ "','%OBID%','%CHECK_OUT_URL%')\">%USER_FILE_NAME%</span>";
//           String tmpFileName = "";
//           HashMap<String,Object> attacheFileList = null;
//           
//           for( int inx = 0; inx < targetList.size(); inx++ ){
//               fileVo = targetList.get(inx);
//               
//               // File Download Info Setting
//               tmpFileName = fileTemplate.replace( "%OBID%", fileVo.getObid() );
//               tmpFileName = tmpFileName.replace( "%USER_FILE_NAME%", fileVo.getUserFileName() );
//               tmpFileName = tmpFileName.replace( "%CHECK_OUT_URL%", fileVo.getOutData().get( "serviceCheckOutUrl" ).toString() );
//               
//               attacheFileList = new HashMap<String, Object>();
//               attacheFileList.put("fileName", tmpFileName );
//               attacheFileList.put("comment", fileVo.getDescriptions());
//               attacheFileList.put("creator", fileVo.getOutData().get("this_creatorName"));
//               attacheFileList.put("creationDate", sdf.format(fileVo.getCreated()));
//
//               gridValueList.add(attacheFileList);
//           }
//       }
//
//       HashMap<String,Object> gridData = new HashMap<String, Object>();
//       gridData.put("gridTitle", "Attached File List");
//       gridData.put("gridKey", gridKey);
//       gridData.put("gridHeader", gridHeader);
//       gridData.put("gridValueList", gridValueList);
//
//       return gridData;
//   }
   
   public static BBSItemVO getParentItem(String obidUpper){
       BBSItemVO parentVO = null;
       
       BBSItem upperDom = new BBSItem(obidUpper);
       if( upperDom != null && upperDom.getVo().getObidUpper() != null && !"Y".equals(upperDom.getVo().getObidUpper()) ){
           parentVO = BBSItem.getParentItem( upperDom.getVo().getObidUpper() );
       }
       else{
           parentVO = upperDom.getVo();
       }
       
       return parentVO;
   }
   
}

