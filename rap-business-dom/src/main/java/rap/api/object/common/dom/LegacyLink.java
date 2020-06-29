/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : LegacyLink.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.common.dom;


import java.util.List;
import java.util.Map;

import com.rap.omc.api.object.dom.BusinessObjectMaster;
import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.util.StringUtil;

import rap.api.object.common.model.LegacyLinkVO;
import rap.application.constants.ApplicationSchemaConstants;


public class LegacyLink extends BusinessObjectMaster {
    public LegacyLink(String obid){
        super(obid);
    }
    public LegacyLink(LegacyLinkVO vo){
        super(vo);
    }
    @Override
    public LegacyLinkVO getVo(){
        return (LegacyLinkVO)super.getVo();
    }
    @Override
    public void initialize(){
        super.initialize();
        initializeLegacyLink();
    }
    public void initializeLegacyLink(){
    /*code here*/
    }
    @Override
    public String toString() {
        return "LegacyLink[toString()=" + super.toString() + "]";
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
    * 사용자의 BusinessUnit, Division에 따른 LegacyLink 목록 조회
    * @param userId
    * @return
    */
   public static List<LegacyLinkVO> retrieveLegacyLinkForLoginUser(String userId){
       StringBuffer selectPattern = new StringBuffer();
       StringBuffer wherePattern = new StringBuffer();
       StringBuffer paramPattern = new StringBuffer();
       
       StringUtil.addSortByPattern(selectPattern, "@this.[titles]");
       StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[company]", GlobalConstants.OQL_OPERATOR_EQUAL, "PlmConstants.COMPANY_LGE");
       
       List<LegacyLinkVO> result = ObjectRoot.searchObjects(
               ApplicationSchemaConstants.BIZCLASS_LEGACYLINK,
               GlobalConstants.FLAG_TYPE_ALL,
               GlobalConstants.FLAG_TYPE_ALL,
               GlobalConstants.FLAG_TYPE_ALL,
               GlobalConstants.FLAG_TYPE_ALL,
               false, false, false, false,
               selectPattern.toString(),
               null,
               wherePattern.toString(),
               paramPattern.toString(),
               false,
               0
       );
       return result;
   }
   
   /**
    * BusinessUnit 별 LegacyLink 목록 조회
    * @param userId
    * @return
    */
   public static List<LegacyLinkVO> retrieveLegacyLinkByBusinessUnit(String businessUnitCode){
       StringBuffer selectPattern = new StringBuffer();
       StringBuffer wherePattern = new StringBuffer();
       StringBuffer paramPattern = new StringBuffer();
       
       StringUtil.addSortByPattern(selectPattern, "@this.[titles]");
       StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[company]", GlobalConstants.OQL_OPERATOR_EQUAL, businessUnitCode);
       
       List<LegacyLinkVO> result = ObjectRoot.searchObjects(
               ApplicationSchemaConstants.BIZCLASS_LEGACYLINK,
               GlobalConstants.FLAG_TYPE_ALL,
               GlobalConstants.FLAG_TYPE_ALL,
               GlobalConstants.FLAG_TYPE_ALL,
               GlobalConstants.FLAG_TYPE_ALL,
               false, false, false, false,
               selectPattern.toString(),
               null,
               wherePattern.toString(),
               paramPattern.toString(),
               false,
               0
       );
       
       return result;
   }
}

