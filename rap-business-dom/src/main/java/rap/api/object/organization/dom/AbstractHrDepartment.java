/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : AbstractHrDepartment.java
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
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;

import rap.api.object.organization.model.AbstractAccountDepartmentVO;
import rap.api.object.organization.model.AbstractHrDepartmentVO;
import rap.application.constants.ApplicationSchemaConstants;


public class AbstractHrDepartment extends AbstractDepartment {
    public AbstractHrDepartment(String obid){
        super(obid);
    }
    public AbstractHrDepartment(AbstractHrDepartmentVO vo){
        super(vo);
    }
    @Override
    public AbstractHrDepartmentVO getVo(){
        return (AbstractHrDepartmentVO)super.getVo();
    }
    @Override
    public void initialize(){
        super.initialize();
        initializeAbstractHrDepartment();
    }
    public void initializeAbstractHrDepartment(){
    /*code here*/
    }
    @Override
    public String toString() {
        return "AbstractHrDepartment[toString()=" + super.toString() + "]";
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
    public AbstractAccountDepartment getAbstractAccountingDepartment(boolean isValid){
        //List<AbstractAccountDepartmentVO> accDeptList = this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_HASACCOUNTINGDEPARTMENT);
        
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();
        
        if ( isValid ) {
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@REL.[useYn]", GlobalConstants.OQL_OPERATOR_EQUAL, "Y");
        }
        
        List<AbstractAccountDepartmentVO> accDeptList = this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_HASACCOUNTINGDEPARTMENT, 
                ApplicationSchemaConstants.BIZCLASS_ABSTRACTACCOUNTDEPARTMENT, 
                GlobalConstants.FLAG_TYPE_FROM,
                "SortBy@this.[titles]",
                wherePattern.toString(), 
                paramPattern.toString(), 
                false,
                false,
                0,
                1);
        
        if(accDeptList.size() > 0 ){ return DomUtil.toDom(accDeptList.get(0)); }
        return null;
    }
    
    public static final AbstractAccountDepartment getAbstractAccountingDepartment(String hrDeptCode){
        AbstractHrDepartmentVO hrDeptVO = ObjectRoot.findObject(ApplicationSchemaConstants.BIZCLASS_ABSTRACTHRDEPARTMENT, hrDeptCode, false);
        
        if(!NullUtil.isNull(hrDeptVO) ){
            AbstractHrDepartment hrDept =  DomUtil.toDom(hrDeptVO);
            return hrDept.getAbstractAccountingDepartment(true);
        
        }
        return null;
    }
    
    public static final AbstractHrDepartment getAbstractHrDepartment(String deptCode){
        AbstractHrDepartmentVO deptVO = ObjectRoot.findObject(ApplicationSchemaConstants.BIZCLASS_ABSTRACTHRDEPARTMENT, deptCode, false);
        if(!NullUtil.isNull(deptVO) ){ return DomUtil.toDom(deptVO); }
        return null;
    }
    public void updateAbstractHrDepartment(String hrUpperDeptCode, String affiliateCode, String realAccountingUnitCode, String leaderEmployeeNo) {
        this.getVo().setHrUpperDeptCode(hrUpperDeptCode);
        this.getVo().setAffiliateCode(affiliateCode);
        this.getVo().setDivisionCode(realAccountingUnitCode);
        this.getVo().setLeaderEmployeeNo(leaderEmployeeNo);
        this.modifyObject();
    }
}

