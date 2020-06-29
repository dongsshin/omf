/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : AbstractAccountDepartment.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.organization.dom;


import java.util.List;
import java.util.Map;

import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.foundation.user.model.UserSession;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;

import rap.api.object.organization.model.AbstractAccountDepartmentVO;
import rap.api.object.organization.model.AbstractHrDepartmentVO;
import rap.application.constants.ApplicationSchemaConstants;


public class AbstractAccountDepartment extends AbstractDepartment {
    public AbstractAccountDepartment(String obid){
        super(obid);
    }
    public AbstractAccountDepartment(AbstractAccountDepartmentVO vo){
        super(vo);
    }
    @Override
    public AbstractAccountDepartmentVO getVo(){
        return (AbstractAccountDepartmentVO)super.getVo();
    }
    @Override
    public void initialize(){
        super.initialize();
        initializeAbstractAccountDepartment();
    }
    public void initializeAbstractAccountDepartment(){
    /*code here*/
    }
    @Override
    public String toString() {
        return "AbstractAccountDepartment[toString()=" + super.toString() + "]";
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
   public static final AbstractAccountDepartment getDepartment(String deptCode){
       AbstractAccountDepartmentVO deptVO = ObjectRoot.findObject(ApplicationSchemaConstants.BIZCLASS_ABSTRACTACCOUNTDEPARTMENT, deptCode, false);
       if(!NullUtil.isNull(deptVO) ){ return DomUtil.toDom(deptVO); }
       return null;
   }
   
   public AbstractHrDepartment getAbstractHrDepartment() {
       List<AbstractHrDepartmentVO> absHrDepartmentList = this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_HASACCOUNTINGDEPARTMENT, GlobalConstants.FLAG_TYPE_TO);
       if ( absHrDepartmentList.size() > 0 ) { return DomUtil.toDom(absHrDepartmentList.get(0)); }
       return null;
   }
   public void updateAbstractAccountDepartment(String realAccountingUnitCode, String affiliateCode) {
       this.getVo().setAccountingUnitCode(realAccountingUnitCode);
       this.getVo().setAffiliateCode(affiliateCode);
       this.modifyObject();
   }
   
   /**
    * 감각상각비(개발비) > mgt.Group, version 별, department 목록 조회
    *
    * @return
    */
   public static List<AbstractAccountDepartmentVO> retrieveManagementGroupbyDepartment(String mgtGroupObid, String versionObid, UserSession userSession){
       
       List<AbstractAccountDepartmentVO> result = null;

       
       StringBuffer selectPattern = new StringBuffer();
       StringBuffer fromPattern = new StringBuffer();
       StringBuffer wherePattern = new StringBuffer();
       StringBuffer paramPattern = new StringBuffer();
       
       fromPattern.append("<this>ThisConnectedWithTo<[HasManagementGroupAccountDept]@REL>+");
       fromPattern.append("<[HasManagementGroupAccountDept]@REL>FromConnectedWithThis<[ManagementGroup]@MGTGR>+");
       fromPattern.append("<[ManagementGroup]@MGTGR>ThisConnectedWithFrom<[HasManagementGroupVersion]@REL2>+");
       fromPattern.append("<[HasManagementGroupVersion]@REL2>ToConnectedWithThis<[BizPlanVersion]@VER>+");
       fromPattern.append("<[BizPlanVersion]@VER>ThisConnectedWithFrom<[HasVersionDepartment]@REL3>+");
       fromPattern.append("<[HasVersionDepartment]@REL3>ToConnectedWithThis<this>+");
       
       StringUtil.constructWherePattern(wherePattern, paramPattern, "@MGTGR.[obid]", GlobalConstants.OQL_OPERATOR_EQUAL, mgtGroupObid);
       StringUtil.constructWherePattern(wherePattern, paramPattern, "@VER.[obid]", GlobalConstants.OQL_OPERATOR_EQUAL, versionObid);
       
       if(GlobalConstants.LANG_KO.equals(userSession.getUserLocale())){
           StringUtil.constructSelectPattern(selectPattern, "SortBy@this.[descriptions] asc");
       }else{
           StringUtil.constructSelectPattern(selectPattern, "SortBy@this.[titles] asc");
       }
       
       result = ObjectRoot.searchObjects(                
               ApplicationSchemaConstants.BIZCLASS_ABSTRACTDEPARTMENT,
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

       return result;
   }

   
   public static AbstractAccountDepartmentVO getVOforName(String names){
       return ObjectRoot.findObject(ApplicationSchemaConstants.BIZCLASS_ABSTRACTACCOUNTDEPARTMENT, names, false);
   }
   
   public static AbstractHrDepartmentVO getHRDepartmentforName(String names){
       StringBuffer selectPattern = new StringBuffer();
       StringBuffer fromPattern = new StringBuffer();
       StringBuffer wherePattern = new StringBuffer();
       StringBuffer paramPattern = new StringBuffer();

       StringUtil.addSortByPattern(selectPattern, "@this.[created] desc");
       
       fromPattern.append("<this>ThisConnectedWithTo<[HasAccountingDepartment]@HAD>+");
       fromPattern.append("<[HasAccountingDepartment]@HAD>FromConnectedWithThis<[AccountingDepartment]@AD>+");

       StringUtil.constructWherePattern(wherePattern, paramPattern, "@AD.[names]", GlobalConstants.OQL_OPERATOR_EQUAL, names);
       StringUtil.constructWherePattern(wherePattern, paramPattern, "@HAD.[useYn]", GlobalConstants.OQL_OPERATOR_EQUAL, "Y");
       
       List<AbstractHrDepartmentVO> result = ObjectRoot.searchObjects(ApplicationSchemaConstants.BIZCLASS_ABSTRACTHRDEPARTMENT,
               GlobalConstants.FLAG_TYPE_ALL,
               GlobalConstants.FLAG_TYPE_ALL,
               selectPattern.toString(),
               fromPattern.toString(),
               wherePattern.toString(),
               paramPattern.toString(),
               false,
               0
       );
       
       if(result.size() == 0) return null;
       return (AbstractHrDepartmentVO)result.get(0);
   }
}

