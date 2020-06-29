/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ChangeAuth.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.organization.dom;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.rap.omc.api.object.dom.BusinessObjectMaster;
import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.api.util.omc.ThreadLocalUtil;
import com.rap.omc.api.util.general.NameGeneratorUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.util.StringUtil;

import rap.api.object.organization.model.ChangeAuthLogVO;
import rap.api.object.organization.model.ChangeAuthVO;
import rap.api.object.organization.model.UsersVO;
import rap.application.constants.ApplicationSchemaConstants;
import rap.application.constants.CodeConstants;


public class ChangeAuth extends BusinessObjectMaster {
    public ChangeAuth(String obid){
        super(obid);
    }
    public ChangeAuth(ChangeAuthVO vo){
        super(vo);
    }
    @Override
    public ChangeAuthVO getVo(){
        return (ChangeAuthVO)super.getVo();
    }
    @Override
    public void initialize(){
        super.initialize();
        initializeChangeAuth();
    }
    public void initializeChangeAuth(){
    /*code here*/
    }
    @Override
    public String toString() {
        return "ChangeAuth[toString()=" + super.toString() + "]";
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
        /*code below*/

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
        /*code below*/

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
   
   
   /**
    * ChangeAuthLog ����
    *
    * @param opType
    */
   private void createLog(String opType){
       ChangeAuthVO targetVO = this.getVo();
       ChangeAuthLogVO logVO = new ChangeAuthLogVO();
       logVO.setClassName(ApplicationSchemaConstants.BIZCLASS_CHANGEAUTHLOG);
       logVO.setLifeCycle(ApplicationSchemaConstants.LIFECYCLE_WITHOUT_STATE);
       logVO.setStates(ApplicationSchemaConstants.STATE_WITHOUT_STATE_EXISTS);
       logVO.setOpType(opType);
       logVO.setTitles(targetVO.getTitles());
       logVO.setDescriptions(targetVO.getDescriptions());
       logVO.setFromObid(targetVO.getFromObid());
       logVO.setDivisionName(targetVO.getDivisionName());
       logVO.setRoleName(targetVO.getRoleName());
       logVO.setDistributeName(targetVO.getDistributeName());
       logVO.setPerson(targetVO.getPerson());
       logVO.setAuthName(targetVO.getAuthName());
       logVO.setUseFlag(targetVO.getUseFlag());
       logVO.setTargetObid(targetVO.getObid());

       ChangeAuthLog createDom = (ChangeAuthLog)DomUtil.toDom(logVO);
       createDom.createObject();
   }

   /**
    * check auth
    *
    * @param auth
    * @return
    */
   public static boolean isAuth(String auth){
       List<ChangeAuthVO> changeAuthVO = getAuthList(auth);
       if (changeAuthVO.size() == 0){
           return false;
       }
       else{
           return true;
       }
   }

   /**
    * check auth
    *
    * @param auth
    * @return
    */
   public static boolean isAuth(String auth, String divisionName){
       List<ChangeAuthVO> changeAuthVO = getAuthList(auth, divisionName,
               ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, ""));
       if (changeAuthVO.size() == 0){
           return false;
       }
       else{
           return true;
       }
   }

   /**
    * check auth
    *
    * @param auth
    * @param plantName
    * @param userId
    * @return
    */
   public static boolean isAuth(String auth, String divisionName, String userId){
       List<ChangeAuthVO> changeAuthVO = getAuthList(auth, divisionName, userId);
       if (changeAuthVO.size() == 0){
           return false;
       }
       else{
           return true;
       }
   }
   /**
    * sub code master ( role ) �� ���� ���� �Ǵ� ���.
    *
    * @param auth
    * @param plantName
    * @param roleName
    * @param userId
    * @return
    */
   public static boolean isAuth(String auth, String plantName, String roleName, String userId){
       ChangeAuthVO changeAuthVO = getUserAuthByDistributeName(auth, plantName, roleName, userId);
       if (changeAuthVO == null){
           return false;
       }
       else{
           return true;
       }
   }

   public static List<ChangeAuthVO> getAuthList(String authName){
       return getAuthList(authName, ThreadLocalUtil.getString(ThreadLocalUtil.KEY.plantUnit, ""),ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, ""));
   }

   public static List<ChangeAuthVO> getAuthList(String authName, String divisionName, String userId){
       StringBuffer wherePattern = new StringBuffer();
       StringBuffer paramPattern = new StringBuffer();

       StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[authName]",GlobalConstants.OQL_OPERATOR_EQUAL, authName);
       StringUtil.constructWherePattern(wherePattern, paramPattern, "this.fromObid.self[DivisionUnit].names",GlobalConstants.OQL_OPERATOR_EQUAL, divisionName);
       if(!StrUtil.isEmpty(userId)) StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[person]",GlobalConstants.OQL_OPERATOR_EQUAL, userId);

       List<ChangeAuthVO> changeAuth = findObjects(ApplicationSchemaConstants.BIZCLASS_CHANGEAUTH, 
                                                   GlobalConstants.FLAG_TYPE_ALL, 
                                                   GlobalConstants.FLAG_TYPE_ALL, 
                                                   "", wherePattern.toString(), paramPattern.toString(), false, 0);

       return changeAuth;
   }
   public static List<ChangeAuthVO> getAuthList(String authName, String divisionName){
	   return getAuthList(authName,"",divisionName);
   }

   /**
    * UserId �������� ChangeAuth ��� ��ȸ
    * @param userId
    * @return
    */
   public static List<ChangeAuthVO> getAuthListByUserId(String userId){
       StringBuffer selectPattern = new StringBuffer();
       StringBuffer wherePattern = new StringBuffer();
       StringBuffer paramPattern = new StringBuffer();

       // Select ����
       StringUtil.constructSelectPattern(selectPattern, "FN_GET_CODEDETAIL(@this.[authName],'" + CodeConstants.CODE_NAME_AUTH_TYPE + "','LGE') authTypeDesc");
       StringUtil.constructSelectPattern(selectPattern, "SortBy@this.[authName]");
       
       // Where ����
       StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[person]", GlobalConstants.OQL_OPERATOR_EQUAL, userId);

       List<ChangeAuthVO> changeAuthList = ObjectRoot.searchObjects(ApplicationSchemaConstants.BIZCLASS_CHANGEAUTH,
               GlobalConstants.FLAG_TYPE_ALL,
               GlobalConstants.FLAG_TYPE_ALL,
               GlobalConstants.FLAG_TYPE_ALL,
               GlobalConstants.FLAG_TYPE_ALL,
               false, false, false, false,
               selectPattern.toString(),
               "",
               wherePattern.toString(),
               paramPattern.toString(),
               false,
               0
       );

       return changeAuthList;
   }

   /**
    * sub code master ( role ) �� ���� ���� �Ǵ� ���.
    *
    * @param auth
    * @param plantName
    * @param roleName
    * @param userId
    * @return
    */
   public static ChangeAuthVO getUserAuthByDistributeName(String authName, String plantName, String distributeName, String userId){
       List<ChangeAuthVO> changeAuthVOList = getAuthListByDistributeName(authName, plantName, distributeName);
       for(ChangeAuthVO changeAuthVO : changeAuthVOList){
           if(userId.equals(changeAuthVO.getPerson())){
               return changeAuthVO;
           }
       }
       return null;
   }

   public static List<ChangeAuthVO> getAuthListByDistributeName(String authName, String plantName, String distributeName){
       StringBuffer selectPattern = new StringBuffer();
       StringUtil.constructSelectPattern(selectPattern, "\"getUserInfo\"(@this.[person],0) userObid");
       StringUtil.constructSelectPattern(selectPattern, "\"getUserInfo\"(@this.[person],1) userName");

       StringBuffer wherePattern = new StringBuffer();
       StringBuffer paramPattern = new StringBuffer();

       StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[authName]",
               GlobalConstants.OQL_OPERATOR_EQUAL, authName);
       StringUtil.constructWherePattern(wherePattern, paramPattern, "this.fromObid.self[PlantUnit].names",
               GlobalConstants.OQL_OPERATOR_EQUAL, plantName);
       StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[distributeName]",
               GlobalConstants.OQL_OPERATOR_IN, distributeName);

       List<ChangeAuthVO> changeAuth = findObjects(ApplicationSchemaConstants.BIZCLASS_CHANGEAUTH,
               GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, selectPattern.toString(),
               wherePattern.toString(), paramPattern.toString(), false, 0);

       List<ChangeAuthVO> result = new ArrayList<ChangeAuthVO>();
       for (int inx = 0; inx < changeAuth.size(); inx++) {
           if (!StrUtil.isEmpty(changeAuth.get(inx).getOutDataStringValue("userName"))) {
               result.add(changeAuth.get(inx));
           }
       }
       return result;
   }

   public static List<ChangeAuthVO> getChangeAuthPersonList(ChangeAuthVO searchInfo){
       return getChangeAuthPersonList( searchInfo, false );
   }

   /**
    * ChangeAuth Type �������� ����� �˻�
    *
    * @param searchInfo
    * @return
    */
   public static List<ChangeAuthVO> getChangeAuthPersonList(ChangeAuthVO searchInfo, boolean isForDivisionAdmin){
       StringBuffer selectPattern = new StringBuffer();
       StringBuffer fromPattern = new StringBuffer();
       StringBuffer wherePattern = new StringBuffer();
       StringBuffer paramPattern = new StringBuffer();

       fromPattern.append("<this>FromConnectedWithThis<[DivisionUnit]@PU>");

       StringUtil.constructSelectPattern(selectPattern, "FN_GET_CODEDETAIL(@this.[authName],'" + CodeConstants.CODE_NAME_AUTH_TYPE + "') authDesc");
       StringUtil.constructSelectPattern(selectPattern, "@PU.[descriptions] plantDesc");
       StringUtil.constructSelectPattern(selectPattern, "\"getUserInfo\"(@this.[person],0) userObid");
       StringUtil.constructSelectPattern(selectPattern, "\"getUserInfo\"(@this.[person],1) userName");
       StringUtil.constructSelectPattern(selectPattern, "\"getUserInfo\"(@this.[person],2) userDept");
       StringUtil.constructSelectPattern(selectPattern, "\"getUserInfo\"(@this.[person],3) userStates");
       StringUtil.constructSelectPattern(selectPattern, "\"getUserInfo\"(@this.[person],4) jikWiName");
       if( isForDivisionAdmin ){
           StringUtil.constructSelectPattern(selectPattern, "\"getUserInfo\"(@this.[person],5) userEmail");
           StringUtil.constructSelectPattern(selectPattern, "\"getUserInfo\"(@this.[person],7) userNameEng");
           StringUtil.constructSelectPattern(selectPattern, "\"getUserInfo\"(@this.[person],8) userNameOnly");
           StringUtil.constructSelectPattern(selectPattern, "\"getUserInfo\"(@this.[person],9) userDeptEng");
           StringUtil.constructSelectPattern(selectPattern, "@PU.[divisionUnitCode] divisionName");
           //StringUtil.constructSelectPattern(selectPattern, "\"getObjectInfoWithName\"('DivisionUnit',@PU.[divisionUnitCode],'-','titles') divisionDesc");
       }
       StringUtil.constructSelectPattern(selectPattern, "omcAPI.getUserProperty(@this.[person],'Work Group') workGroup");
       StringUtil.constructSelectPattern(selectPattern, "SortBy@this.[names]");

       if (!StrUtil.isEmpty(searchInfo.getDivisionName())) StringUtil.constructWherePattern(wherePattern, paramPattern, "@PU.[names]",GlobalConstants.OQL_OPERATOR_EQUAL, searchInfo.getDivisionName());
       if (!StrUtil.isEmpty(searchInfo.getAuthName())) StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[authName]",GlobalConstants.OQL_OPERATOR_EQUAL, searchInfo.getAuthName());
       if(!StrUtil.isEmpty(searchInfo.getRoleName())) StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[roleName]",GlobalConstants.OQL_OPERATOR_EQUAL, searchInfo.getRoleName());
       StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[useFlag]", GlobalConstants.OQL_OPERATOR_EQUAL, "Y");

       List<ChangeAuthVO> result = ObjectRoot.searchObjects(
               ApplicationSchemaConstants.BIZCLASS_CHANGEAUTH,
               GlobalConstants.FLAG_TYPE_ALL,
               GlobalConstants.FLAG_TYPE_ALL,
               selectPattern.toString(),
               fromPattern.toString(),
               wherePattern.toString(),
               paramPattern.toString(),
               true,
               0
       );
       List<ChangeAuthVO> searchResult = new ArrayList<ChangeAuthVO>();
       if (searchInfo != null && !StrUtil.isEmpty(searchInfo.getPerson())) {
           for (int inx = 0; inx < result.size(); inx++) {
               if (result.get(inx).getOutData().get("userName") != null && result.get(inx).getOutData().get("userName").toString().indexOf(searchInfo.getPerson()) >= 0) {
                   searchResult.add(result.get(inx));
               }
           }
       } else {
           for (int inx = 0; inx < result.size(); inx++) {
               if (!StrUtil.isEmpty(result.get(inx).getOutDataStringValue("userName"))) {
                   searchResult.add(result.get(inx));
               }
           }
       }

       return searchResult;
   }

   /**
    * ChangeAuth Type, Business Unit �������� ����� �˻�
    * @param searchInfo
    * @return
    */
   public static List<ChangeAuthVO> getChangeAuthPersonListForBusinessUnit(ChangeAuthVO searchInfo, String businessUnitCode){
       StringBuffer selectPattern = new StringBuffer();
       StringBuffer fromPattern = new StringBuffer();
       StringBuffer wherePattern = new StringBuffer();
       StringBuffer paramPattern = new StringBuffer();

       fromPattern.append("<this>FromConnectedWithThis<[PlantUnit]@PU>");

       StringUtil.constructSelectPattern(selectPattern, "FN_GET_CODEDETAIL(@this.[authName],'" + CodeConstants.CODE_NAME_AUTH_TYPE + "') authDesc");
       StringUtil.constructSelectPattern(selectPattern, "@PU.[descriptions] plantDesc");
       StringUtil.constructSelectPattern(selectPattern, "\"getUserInfo\"(@this.[person],0) userObid");
       StringUtil.constructSelectPattern(selectPattern, "\"getUserInfo\"(@this.[person],1) userName");
       StringUtil.constructSelectPattern(selectPattern, "\"getUserInfo\"(@this.[person],2) userDept");
       StringUtil.constructSelectPattern(selectPattern, "\"getUserInfo\"(@this.[person],3) userStates");
       StringUtil.constructSelectPattern(selectPattern, "\"getUserInfo\"(@this.[person],4) jikWiName");
       StringUtil.constructSelectPattern(selectPattern, "\"getUserInfo\"(@this.[person],5) userEmail");
       StringUtil.constructSelectPattern(selectPattern, "SortBy@this.[names]");

       if( searchInfo != null ){
           if( !StrUtil.isEmpty(searchInfo.getDivisionName()) ){
               StringUtil.constructWherePattern(wherePattern, paramPattern, "@PU.[businessUnitCode]",
                       GlobalConstants.OQL_OPERATOR_EQUAL, businessUnitCode);
           }
           if( !StrUtil.isEmpty(searchInfo.getAuthName()) ){
               StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[authName]",
                       GlobalConstants.OQL_OPERATOR_EQUAL, searchInfo.getAuthName());
           }
       }

       // UseFlag = Y �� �Ǹ� ��ȸ
       StringUtil.constructWherePattern( wherePattern, paramPattern, "@this.[useFlag]", GlobalConstants.OQL_OPERATOR_EQUAL, "Y" );

       List<ChangeAuthVO> result = ObjectRoot.searchObjects(
               ApplicationSchemaConstants.BIZCLASS_CHANGEAUTH,
               GlobalConstants.FLAG_TYPE_ALL,
               GlobalConstants.FLAG_TYPE_ALL,
               selectPattern.toString(),
               fromPattern.toString(),
               wherePattern.toString(),
               paramPattern.toString(),
               true,
               0
       );

       List<ChangeAuthVO> searchResult = new ArrayList<ChangeAuthVO>();
       if (searchInfo != null && !StrUtil.isEmpty(searchInfo.getPerson())) {
           for (int inx = 0; inx < result.size(); inx++) {
               if (result.get(inx).getOutData().get("userName") != null && result.get(inx).getOutData().get("userName").toString().indexOf(searchInfo.getPerson()) >= 0) {
                   searchResult.add(result.get(inx));
               }
           }
       } else {
           // Users ���̺� ���� ������ ����
           for (int inx = 0; inx < result.size(); inx++) {
               if (!StrUtil.isEmpty(result.get(inx).getOutDataStringValue("userName"))) {
                   searchResult.add(result.get(inx));
               }
           }
       }

       return searchResult;
   }

   /**
    * Division Admin List ��ȸ
    * @return
    */
   public static List<ChangeAuthVO> getDivisionAdminList(){
       ChangeAuthVO searchInfo = new ChangeAuthVO();
       searchInfo.setAuthName( CodeConstants.CHANGE_AUTH_TYPE_DIVISION_APPR );
       return getChangeAuthPersonList( searchInfo, true );
   }
   
   public void createChangeAuth(List<UsersVO> userList){       
       //1. Init Default Data
       this.getVo().setClassName( ApplicationSchemaConstants.BIZCLASS_CHANGEAUTH );
       this.getVo().setStates( ApplicationSchemaConstants.STATE_WITHOUT_STATE_EXISTS );
       this.getVo().setTitles( this.getVo().getAuthName() );
       this.getVo().setFromObid( DivisionUnit.getObidByDivisionName(this.getVo().getDivisionName()) );

       //2. Create Object
       for( int inx = 0; inx < userList.size(); inx++ ){
           this.getVo().setPerson( userList.get(inx).getNames() );
           this.getVo().setNames( getNamesForIdGen() );
           this.createObject();
       }
   }
   
   public void createChangeAuth(){
       //1. Init Default Data
       this.getVo().setNames( getNamesForIdGen() );
       this.getVo().setClassName( ApplicationSchemaConstants.BIZCLASS_CHANGEAUTH );
       this.getVo().setStates( ApplicationSchemaConstants.STATE_WITHOUT_STATE_EXISTS );
       this.getVo().setTitles( this.getVo().getAuthName() );
       this.getVo().setFromObid( DivisionUnit.getObidByDivisionName(this.getVo().getDivisionName()) );
       this.getVo().setUseFlag( "Y" );
       this.createObject();
   }
   
   private String getNamesForIdGen(){
       return NameGeneratorUtil.generateUniqueName("DSS-TEST");
   }
   public static final List<ChangeAuthVO> getAuthListByDivisionAndUser(String userId, String divisionUnitCode){
       StringBuffer selectPattern = new StringBuffer();
       StringBuffer wherePattern = new StringBuffer();
       StringBuffer paramPattern = new StringBuffer();

       StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[person]",GlobalConstants.OQL_OPERATOR_EQUAL, userId);
       StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[divisionName]",GlobalConstants.OQL_OPERATOR_LIKE, divisionUnitCode);

       List<ChangeAuthVO> changeAuthList = ObjectRoot.findObjects(ApplicationSchemaConstants.BIZCLASS_CHANGEAUTH,
               GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, selectPattern.toString(),
               wherePattern.toString(), paramPattern.toString(), false, 0);

       return changeAuthList;
   }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
}

