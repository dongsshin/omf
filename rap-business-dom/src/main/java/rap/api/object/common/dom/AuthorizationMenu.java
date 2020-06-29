/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : AuthorizationMenu.java
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
import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.api.util.general.UserManagementUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.foundation.menu.model.MenuSubMenuInfo;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;

import rap.api.object.common.model.AuthorizationGroupVO;
import rap.api.object.common.model.AuthorizationMenuVO;
import rap.application.constants.ApplicationSchemaConstants;


public class AuthorizationMenu extends BusinessObjectMaster {
    public AuthorizationMenu(String obid){
        super(obid);
    }
    public AuthorizationMenu(AuthorizationMenuVO vo){
        super(vo);
    }
    @Override
    public AuthorizationMenuVO getVo(){
        return (AuthorizationMenuVO)super.getVo();
    }
    @Override
    public void initialize(){
        super.initialize();
        initializeAuthorizationMenu();
    }
    public void initializeAuthorizationMenu(){
    /*code here*/
    }
    @Override
    public String toString() {
        return "AuthorizationMenu[toString()=" + super.toString() + "]";
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

   static public List<AuthorizationMenuVO> retrieveAuthMenuDetail(List<MenuSubMenuInfo> subMenuList, String mgName) {

       //������ ���Ѹ� ���� ��ȸ
       AuthorizationGroupVO authGroupVO = ObjectRoot.findObject(ApplicationSchemaConstants.BIZCLASS_AUTHORIZATIONGROUP, mgName, false);
       
       List<AuthorizationMenuVO> authMenuVo = new ArrayList<AuthorizationMenuVO>();
       for (MenuSubMenuInfo subMenuVo : subMenuList) {
           
           AuthorizationMenuVO authMenuList = retrieveAuthMenuDetail(authGroupVO, subMenuVo.getMnuName());

           HashMap<String,Object> outData = new HashMap<String,Object>();
           outData.put("names", subMenuVo.getMnuName());
           outData.put("titles", subMenuVo.getMnuLabel());
           outData.put("level", subMenuVo.getMnuLevel());
           
           if (authMenuList != null) {
               authMenuList.setOutData(outData);
               
               authMenuVo.add(authMenuList);
           } else {
               AuthorizationMenuVO emptyAuthMenu = new AuthorizationMenuVO();
               emptyAuthMenu.setFromObid(authGroupVO.getObid());
               emptyAuthMenu.setOutData(outData);
               emptyAuthMenu.setUseYn("");
               emptyAuthMenu.setOrgRange("");
               emptyAuthMenu.setCrudFlag("");
               
               authMenuVo.add(emptyAuthMenu);
           }
       }
       
       return authMenuVo;
   }
   
   /**
     *
     * @param authGroupVO
     * @param subMenuVo
     * @return
     */
    public static AuthorizationMenuVO retrieveAuthMenuDetail(AuthorizationGroupVO authGroupVO, String menuName){
       StringBuffer selectPatternBuf = new StringBuffer();
       StringBuffer wherePatternBuf  = new StringBuffer();
       StringBuffer paramPatternBuf  = new StringBuffer();

       StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[fromObid]", GlobalConstants.OQL_OPERATOR_EQUAL, authGroupVO.getObid());
       StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[names]", GlobalConstants.OQL_OPERATOR_EQUAL, menuName + "-" + authGroupVO.getObid());

       String selectPattern = selectPatternBuf.toString();
       String wherePattern = wherePatternBuf.toString();
       String paramPattern = paramPatternBuf.toString();

       //����Ǿ� �ִ� ���ѱ׷캰�޴� ����Ʈ ��ȸ
       AuthorizationMenuVO authMenuList = ObjectRoot.findObject(ApplicationSchemaConstants.BIZCLASS_AUTHORIZATIONMENU,//String className,
               GlobalConstants.FLAG_TYPE_ALL,//String namePattern,
               GlobalConstants.FLAG_TYPE_ALL,//String revisionPattern,
               selectPattern.toString(),//String selectPattern,
               wherePattern.toString(),//String wherePattern,
               paramPattern.toString(),//String parameterPattern,
               false);//boolean expandType;
        return authMenuList;
    }
   
   public AuthorizationMenuVO createAuthorizationMenu(){       
       this.getVo().setClassName(ApplicationSchemaConstants.BIZCLASS_AUTHORIZATIONMENU);
       this.getVo().setStates(ApplicationSchemaConstants.STATE_WITHOUT_STATE_EXISTS);
       this.getVo().setLifeCycle(ApplicationSchemaConstants.LIFECYCLE_WITHOUT_STATE);

       this.createObject();
       
       if(!NullUtil.isNone(this.getVo().getUseYn()) &&  this.getVo().getUseYn().equals("Y")) {
           AuthorizationGroup ag = DomUtil.toDom(this.getVo().getFromObid());
           ag.getVo().getNames();
           
           UserManagementUtil.addMenuForGroup(ag.getVo().getNames(),this.getVo().getDescriptions(),null);
       }
       return this.getVo();
   }
   
   public AuthorizationMenuVO updateAuthorizationMenu( AuthorizationMenuVO authMenuVO ){
       String curUseYn = this.getVo().getUseYn(); 
       
       this.getVo().setObid(authMenuVO.getObid());
       this.getVo().setUseYn(authMenuVO.getUseYn());
       this.getVo().setOrgRange(authMenuVO.getOrgRange());
       this.getVo().setCrudFlag(authMenuVO.getCrudFlag());
       this.getVo().setDescriptions(authMenuVO.getDescriptions());
       
       this.modifyObject();
       
       if(NullUtil.isNone(curUseYn) && this.getVo().getUseYn().equals("Y")){
           AuthorizationGroup ag = DomUtil.toDom(this.getVo().getFromObid());
           ag.getVo().getNames();           
           UserManagementUtil.addMenuForGroup(ag.getVo().getNames(),this.getVo().getDescriptions(),null);
       } else if(!NullUtil.isNone(curUseYn) && curUseYn.equals("N") && this.getVo().getUseYn().equals("Y")){
           AuthorizationGroup ag = DomUtil.toDom(this.getVo().getFromObid());
           ag.getVo().getNames();           
           UserManagementUtil.addMenuForGroup(ag.getVo().getNames(),this.getVo().getDescriptions(),null);
       } else if(!NullUtil.isNone(curUseYn) && curUseYn.equals("Y") && this.getVo().getUseYn().equals("N")){
           AuthorizationGroup ag = DomUtil.toDom(this.getVo().getFromObid());
           ag.getVo().getNames();           
           UserManagementUtil.removeMenuForGroup(ag.getVo().getNames(),this.getVo().getDescriptions());
       }
       
       return this.getVo();
   }
   
   /*
    * AuthorizationGroup Obid �� Menu List ��������
    */
   public static List<AuthorizationMenuVO> retrieveAuthMenuDetail(String fromObid, String names, String useYn) {
       StringBuffer selectPatternBuf = new StringBuffer();
       StringBuffer wherePatternBuf  = new StringBuffer();
       StringBuffer paramPatternBuf  = new StringBuffer();

       StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[fromObid]", GlobalConstants.OQL_OPERATOR_EQUAL, fromObid);
       if ( !StrUtil.isEmpty(names) ) {
           StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[names]", GlobalConstants.OQL_OPERATOR_EQUAL, names);
       }
       if ( !StrUtil.isEmpty(useYn) ) {
           StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[useYn]", GlobalConstants.OQL_OPERATOR_EQUAL, useYn);
       }
       
       //����Ǿ� �ִ� ���ѱ׷캰�޴� ����Ʈ ��ȸ
       List<AuthorizationMenuVO> authMenuList = ObjectRoot.findObjects(
               ApplicationSchemaConstants.BIZCLASS_AUTHORIZATIONMENU, 
               selectPatternBuf.toString(), 
               wherePatternBuf.toString(), 
               paramPatternBuf.toString()
               );
        return authMenuList;
   }
}

