/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : AuthorizationGroup.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.common.dom;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rap.omc.api.object.dom.BusinessObjectMaster;
import com.rap.omc.api.object.dom.BusinessRelationObject;
import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import com.rap.omc.api.oql.model.OmcOQLCondition;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.api.util.general.NameGeneratorUtil;
import com.rap.omc.api.util.general.UserManagementUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.dataaccess.paging.model.PagingEntity;
import com.rap.omc.dataaccess.paging.model.PagingList;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;

import rap.api.object.common.model.AuthorizationGroupVO;
import rap.api.object.organization.dom.Users;
import rap.api.object.organization.model.UsersVO;
import rap.api.object.relation.model.HasAuthorizationGroupVO;
import rap.application.constants.ApplicationSchemaConstants;


public class AuthorizationGroup extends BusinessObjectMaster {
    public AuthorizationGroup(String obid){
        super(obid);
    }
    public AuthorizationGroup(AuthorizationGroupVO vo){
        super(vo);
    }
    @Override
    public AuthorizationGroupVO getVo(){
        return (AuthorizationGroupVO)super.getVo();
    }
    @Override
    public void initialize(){
        super.initialize();
        initializeAuthorizationGroup();
    }
    public void initializeAuthorizationGroup(){
    /*code here*/
    }
    @Override
    public String toString() {
        return "AuthorizationGroup[toString()=" + super.toString() + "]";
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
    * create AuthorizationGroup
    */
   public AuthorizationGroupVO createAuthorizationGroup(){       
       //1. Init Default Data
       this.getVo().setClassName(ApplicationSchemaConstants.BIZCLASS_AUTHORIZATIONGROUP);
       this.getVo().setStates(ApplicationSchemaConstants.STATE_WITHOUT_STATE_EXISTS);
       this.getVo().setLifeCycle(ApplicationSchemaConstants.LIFECYCLE_WITHOUT_STATE);
       this.getVo().setNames(NameGeneratorUtil.generateUniqueName("GROUP-ID"));

       this.createObject();
       
       UserManagementUtil.createGroup(this.getVo().getNames(),this.getVo().getTitles());
       
       return this.getVo();
   }
   
   /**
    * update AuthorizationGroup
    */
   public AuthorizationGroupVO updateAuthorizationGroup( AuthorizationGroupVO authVO ) {
       //1. Init Default Data
       this.getVo().setObid(authVO.getObid());
       this.getVo().setTitles(authVO.getTitles());
       this.getVo().setModule(authVO.getModule());
       this.getVo().setUseYn(authVO.getUseYn());
       this.getVo().setDescriptions(authVO.getDescriptions());
       
       this.modifyObject();
       
       return this.getVo();
   }
   
   /**
    * retrieve AuthorizationGroup
    */
   public static List<AuthorizationGroupVO> retrieveAuthorizationGroup( AuthorizationGroupVO authVo, String userCountYn ) {
       // Where ���� ����
       StringBuffer wherePatternBuf = new StringBuffer();
       StringBuffer paramPatternBuf = new StringBuffer();
       
       // Business Unit Code�� �ش��ϴ� Plant�� ��ȸ
       if( !StrUtil.isEmpty(authVo.getTitles()) ){
           List<OmcOQLCondition> conditionList = new ArrayList<OmcOQLCondition>();
           conditionList.add(new OmcOQLCondition("@this.[titles]", authVo.getTitles() + GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.OQL_OPERATOR_LIKE));
           conditionList.add(new OmcOQLCondition("@this.[engTitles]", authVo.getTitles() + GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.OQL_OPERATOR_LIKE));
           StringUtil.constructOrWherePattern(wherePatternBuf, paramPatternBuf, conditionList);
       }

       if( !StrUtil.isEmpty(authVo.getModule()) ){
           StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[module]",
                   GlobalConstants.OQL_OPERATOR_EQUAL, authVo.getModule());
       }
       
       if( !StrUtil.isEmpty(authVo.getUseYn()) ){
           StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[useYn]",
                   GlobalConstants.OQL_OPERATOR_EQUAL, authVo.getUseYn());
       }
       
       List<AuthorizationGroupVO> list = ObjectRoot.findObjects(
               ApplicationSchemaConstants.BIZCLASS_AUTHORIZATIONGROUP, // className
               GlobalConstants.FLAG_TYPE_ALL, //namePattern, 
               GlobalConstants.FLAG_TYPE_ALL, //revisionPattern, 
               "SortBy@this.[titles]", // selectPattern
               wherePatternBuf.toString(),    // wherePattern,         
               paramPatternBuf.toString(),    // parameterPattern, 
               false,                         //expandType
               0
       );
       
       if ("Y".equals(userCountYn)) {
           
           List<AuthorizationGroupVO> result = new ArrayList<AuthorizationGroupVO>();
           
           for (AuthorizationGroupVO vo: list) {
               //AuthorizationGroupVO resultVo = vo;
               
               wherePatternBuf.setLength(0);
               paramPatternBuf.setLength(0);
               
               StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[fromObid]", GlobalConstants.OQL_OPERATOR_EQUAL, vo.getObid());
               
               PagingEntity searchInfo = new PagingEntity();
               searchInfo.setRowSize(1);
               List<HasAuthorizationGroupVO> relList = ObjectRoot.findObjectPagingList(
                       ApplicationSchemaConstants.RELCLASS_HASAUTHORIZATIONGROUP,
                       GlobalConstants.FLAG_TYPE_ALL,
                       GlobalConstants.FLAG_TYPE_ALL,
                       new StringBuffer().toString(),
                       wherePatternBuf.toString(),
                       paramPatternBuf.toString(),
                       false,
                       searchInfo
               );
               
               //���ѿ� ���� �ο���
               HashMap<String,Object> outData = new HashMap<String,Object>();
               outData = vo.getOutData();
               if ( relList instanceof PagingList ) {
                   PagingList<HasAuthorizationGroupVO> pagingList = (PagingList<HasAuthorizationGroupVO>)relList;
                   outData.put("userCount", pagingList.getRows());
               }
               
               //outData.put("userCount", retrieveAuthGroupUserList(vo.getObid(), null, null).size());
               vo.setOutData(outData);
               result.add(vo);
           }
       
           return result;
           
       } else {
           return list;
       }

   }
   
   
   /**
    * retrieve rel (AuthorizationGroup - Users)
    */
   public static List<UsersVO> retrieveAuthGroupUserList( String fromObid, String toObid, String module ) {
       
       StringBuffer selectPattern = new StringBuffer();
       StringBuffer fromPattern = new StringBuffer();
       StringBuffer wherePattern = new StringBuffer();
       StringBuffer paramPattern = new StringBuffer();

       fromPattern.append("<this>ThisConnectedWithTo<[HasAuthorizationGroup]@REL>+");
       fromPattern.append("<[HasAuthorizationGroup]@REL>FromConnectedWithThis<[AuthorizationGroup]@AG>+"); 
       
       StringUtil.constructSelectPattern(selectPattern, "@AG.[obid] authGroupObid");
       StringUtil.constructSelectPattern(selectPattern, "@AG.[titles] authGroupTitles");
       StringUtil.constructSelectPattern(selectPattern, "@AG.[module] authGroupModule");
       StringUtil.constructSelectPattern(selectPattern, "@REL.[obid] relObid");
       StringUtil.constructSelectPattern(selectPattern, "IFNULL(getUserInfo(@REL.[modifier], 'T'), @REL.[modifier]) relModifier");
       StringUtil.constructSelectPattern(selectPattern, "omcConvertUtcToLocal(@REL.[modified]) relModified");
       StringUtil.addSortByPattern(selectPattern, "authGroupModule, authGroupTitles, @this.[titles]");

       if (!StrUtil.isEmpty(fromObid)) {
           StringUtil.constructWherePattern(wherePattern, paramPattern, "@REL.[fromObid]", GlobalConstants.OQL_OPERATOR_EQUAL, fromObid);
       }

       if (!StrUtil.isEmpty(toObid)) {
           StringUtil.constructWherePattern(wherePattern, paramPattern, "@REL.[toObid]", GlobalConstants.OQL_OPERATOR_EQUAL, toObid);
       }
       
       if (!StrUtil.isEmpty(module)) {
           StringUtil.constructWherePattern(wherePattern, paramPattern, "@AG.[module]", GlobalConstants.OQL_OPERATOR_EQUAL, module);
       }
       
       return ObjectRoot.searchObjects( 
               ApplicationSchemaConstants.BIZCLASS_USERS,
               GlobalConstants.FLAG_TYPE_ALL,
               GlobalConstants.FLAG_TYPE_ALL,
               GlobalConstants.FLAG_TYPE_ALL,
               GlobalConstants.FLAG_TYPE_ALL,
               GlobalConstants.FLAG_TYPE_ALL,
               GlobalConstants.FLAG_TYPE_ALL,
               GlobalConstants.FLAG_TYPE_ALL,
               GlobalConstants.FLAG_TYPE_ALL,
               GlobalConstants.FLAG_TYPE_ALL,
               false,
               false,
               false,
               false,
               selectPattern.toString(),
               fromPattern.toString(),
               wherePattern.toString(),    
               paramPattern.toString(),
               true,
               0);
       
   }

   /**
    * create rel (AuthorizationGroup - Users)
    */
   public static void saveAuthGroupUser( String authObid, List<UsersVO> userList, String module ) {
       
       AuthorizationGroup dom = (AuthorizationGroup)DomUtil.toDom(authObid);
       for (UsersVO vo: userList) {
           //���� ���� üũ
           List<UsersVO> list;

           if ("Project".equals(module)) {
               //Project module �̸� �� ��� Project ���� ���� �� �߰�
               list = retrieveAuthGroupUserList(null, vo.getObid(), "Project");

               //1. ����
               for (UsersVO vo2: list) {
                   BusinessRelationObject userRel = (BusinessRelationObject)DomUtil.toDom(vo2.getOutData().get("relObid").toString());
                   userRel.deleteObject();
               
                   AuthorizationGroup ag = DomUtil.toDom(userRel.getVo().getFromObid(), false);
                   Users user = DomUtil.toDom(userRel.getVo().getToObid(), false);
                   UserManagementUtil.removeUserForGroup(ag.getVo().getNames(), user.getVo().getNames());
               }

               //2. �߰�
               dom.addToObject(ApplicationSchemaConstants.RELCLASS_HASAUTHORIZATIONGROUP, vo, null);
               UserManagementUtil.addUserForGroup(dom.getVo().getNames(), vo.getNames());
           } else {
               list = retrieveAuthGroupUserList(authObid, vo.getObid(), null);
               
               if (list.size() == 0) {
                   dom.addToObject(ApplicationSchemaConstants.RELCLASS_HASAUTHORIZATIONGROUP, vo, null);
                   UserManagementUtil.addUserForGroup(dom.getVo().getNames(), vo.getNames());
               }
           }
       }
       
   }
   
   /**
    * create rel (AuthorizationGroup - Users)
    */
   public static void saveUserAuthGroup( List<AuthorizationGroupVO> authGroupList, UsersVO userVo, String module, boolean isOnlyOne ) {
       
       if ("Project".equals(module)) {
           //Project module �̸� �� ��� Project ���� ���� �� �߰�
           List<UsersVO> list = retrieveAuthGroupUserList(null, userVo.getObid(), "Project");
           
           if ( isOnlyOne) {
             //1. ����
               for (UsersVO vo2: list) {
                   BusinessRelationObject userRel = (BusinessRelationObject)DomUtil.toDom(vo2.getOutData().get("relObid").toString());
                   userRel.deleteObject();
               
                   AuthorizationGroup ag = DomUtil.toDom(userRel.getVo().getFromObid(), false);
                   Users user = DomUtil.toDom(userRel.getVo().getToObid(), false);
                   UserManagementUtil.removeUserForGroup(ag.getVo().getNames(), user.getVo().getNames());
               }
           }
       }
       
       for (AuthorizationGroupVO vo: authGroupList) {
           AuthorizationGroup dom = (AuthorizationGroup)DomUtil.toDom(vo.getObid());
           
           if ("Project".equals(module)) {
               dom.addToObject(ApplicationSchemaConstants.RELCLASS_HASAUTHORIZATIONGROUP, userVo, null);
               UserManagementUtil.addUserForGroup(dom.getVo().getNames(), userVo.getNames());
           } else {
               //���� ���� üũ
               List<UsersVO> list = retrieveAuthGroupUserList(vo.getObid(), userVo.getObid(), null);
               
               if (list.size() == 0) {
                   dom.addToObject(ApplicationSchemaConstants.RELCLASS_HASAUTHORIZATIONGROUP, userVo, null);
                   UserManagementUtil.addUserForGroup(dom.getVo().getNames(), userVo.getNames());
               }
           }
       }

   }
   
   /**
    * ���ѱ׷����� ����
    */
   public static void saveAuthGroupUser( String authGroupName, UsersVO userVo ) {
       AuthorizationGroupVO authGroupVO = ObjectRoot.findObject(ApplicationSchemaConstants.BIZCLASS_AUTHORIZATIONGROUP, authGroupName, false);
       if ( !NullUtil.isNull( authGroupVO ) ) {
           AuthorizationGroup authGroup = DomUtil.toDom(authGroupVO.getObid());
           
           authGroup.addToObject(ApplicationSchemaConstants.RELCLASS_HASAUTHORIZATIONGROUP, userVo, null);
           UserManagementUtil.addUserForGroup(authGroupName, userVo.getNames());
       }
   }
   
   
   /**
    * ���ѱ׷����� ����
    */
   public static void deleteAuthGroupUser( List<AuthorizationGroupVO> authGroupList, List<UsersVO> userList ) {
       for (int inx = 0; inx < userList.size(); inx++) {
           BusinessRelationObject userRel = (BusinessRelationObject)DomUtil.toDom(userList.get(inx).getObid());
           userRel.deleteObject();
           
           AuthorizationGroup ag = DomUtil.toDom(userRel.getVo().getFromObid(), false);
           Users user = DomUtil.toDom(userRel.getVo().getToObid(), false);
           UserManagementUtil.removeUserForGroup(ag.getVo().getNames(), user.getVo().getNames());
       }
   }
   

   
   /**
    * delete rel (AuthorizationGroup - Users)
    */
   public static void deleteAuthGroupUser( AuthorizationGroup authGroupList, UsersVO userVo ) {

       List<BusinessRelationObjectVO> relList = authGroupList.getRelationship( ApplicationSchemaConstants.RELCLASS_HASAUTHORIZATIONGROUP );

       for (BusinessRelationObjectVO vo: relList) {
           if (userVo.getObid().equals(vo.getToObid())) {
               BusinessRelationObject rel = (BusinessRelationObject)DomUtil.toDom(vo.getObid());
               rel.deleteObject();
           }
       }
       UserManagementUtil.removeUserForGroup(authGroupList.getVo().getNames(), userVo.getNames());
   }
   
   public static final AuthorizationGroup getAuthorizationGroup(String names, String module){
       if ( NullUtil.isNull(names) ) { return null; };
       
       StringBuffer wherePattern = new StringBuffer();
       StringBuffer paramPattern = new StringBuffer();
       
       if ( !NullUtil.isNone(module) ) {
           StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[module]", GlobalConstants.OQL_OPERATOR_EQUAL, module);
       }
       if ( !NullUtil.isNone(names) ) {
           StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[names]", GlobalConstants.OQL_OPERATOR_EQUAL, names);
       }
       
       List<AuthorizationGroupVO> authGroupList = ObjectRoot.findObjects(ApplicationSchemaConstants.BIZCLASS_AUTHORIZATIONGROUP, wherePattern.toString(), paramPattern.toString());
       
       if ( !NullUtil.isNone(authGroupList) ) {
           AuthorizationGroupVO authorizationGroupVO = authGroupList.get(0);
           return DomUtil.toDom(authorizationGroupVO);
       }
       return null;
   }
}

