/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : AccountingDepartment.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.organization.dom;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;

import rap.api.object.bizplan.dom.ManagementGroup;
import rap.api.object.bizplan.model.ManagementGroupListVO;
import rap.api.object.bizplan.model.ManagementGroupVO;
import rap.api.object.common.model.CodeDetailVO;
import rap.api.object.organization.model.AccountingDepartmentVO;
import rap.api.object.project.model.ProjectsVO;
import rap.api.object.relation.model.HasAccountingDepartmentVO;
import rap.api.object.relation.model.HasVersionDepartmentVO;
import rap.application.constants.ApplicationSchemaConstants;


public class AccountingDepartment extends AbstractAccountDepartment {
    public AccountingDepartment(String obid){
        super(obid);
    }
    public AccountingDepartment(AccountingDepartmentVO vo){
        super(vo);
    }
    @Override
    public AccountingDepartmentVO getVo(){
        return (AccountingDepartmentVO)super.getVo();
    }
    @Override
    public void initialize(){
        super.initialize();
        initializeAccountingDepartment();
    }
    public void initializeAccountingDepartment(){
    /*code here*/
    }
    @Override
    public String toString() {
        return "AccountingDepartment[toString()=" + super.toString() + "]";
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
    public Department getHrDepartment(){
        List<HasAccountingDepartmentVO> relList = this.getRelationship(ApplicationSchemaConstants.RELCLASS_HASACCOUNTINGDEPARTMENT);
        
        Department hrDept = null;
        
        for(HasAccountingDepartmentVO vo : relList){
            if(vo.getUseYn().equals("Y")) {
                hrDept = DomUtil.toDom(vo.getToObid());
                return hrDept;                       
            }
        }
    
        return hrDept;
    }
    
    /**
     * �ش翬���� �����ȹ Version�� ȸ��μ����� Relation�� �ִ��� Check
     */
    public boolean isPlanVersionRelExist(String establishYear){
        boolean isExist = false;
        List<HasVersionDepartmentVO> resultList = new ArrayList<HasVersionDepartmentVO>();
        
        StringBuffer selectPattern = new StringBuffer();
        StringBuffer fromPattern = new StringBuffer();
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();
        
        StringUtil.constructSelectPattern(selectPattern, "@BPV.[obid] versionObid");
        
        fromPattern.append("<this>FromConnectedWithThis<[BizPlanVersion]@BPV>+");
        fromPattern.append("<[HasManagementGroupVersion]@HMGV>ToConnectedWithThis<[BizPlanVersion]@BPV>+");
        fromPattern.append("<[HasManagementGroupVersion]@HMGV>FromConnectedWithThis<[ManagementGroup]@MG>+");
    
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@BPV.[previousObid]", GlobalConstants.OQL_OPERATOR_EQUAL, "1");
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[toObid]", GlobalConstants.OQL_OPERATOR_EQUAL, this.getVo().getObid());
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@MG.[planYear]", GlobalConstants.OQL_OPERATOR_EQUAL, establishYear);
    
        resultList = ObjectRoot.searchObjects(ApplicationSchemaConstants.RELCLASS_HASVERSIONDEPARTMENT,
                  GlobalConstants.FLAG_TYPE_ALL,
                  GlobalConstants.FLAG_TYPE_ALL,
                  selectPattern.toString(),
                  fromPattern.toString(),
                  wherePattern.toString(),
                  paramPattern.toString(),
                  false,
                  0);
        
        if( !NullUtil.isNull( resultList ) && resultList.size() > 0 ){
            isExist = true;
        }
        
        return isExist;
    }
    
    public List<ProjectsVO> getProjectList() {
        return this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_HASTEAM, ApplicationSchemaConstants.BIZCLASS_PROJECTS, GlobalConstants.FLAG_TYPE_FROM);
    }
    public List<ManagementGroupVO> getBudgetUnitList(String planYear,boolean includeInactive) {
        return getBudgetUnitListSub(planYear,includeInactive);
    }
    /**
     * ȸ��μ� - BudgetUnit List ��ȸ
     *
     * @param planYear
     * @return
     */
    public List<ManagementGroupVO> getBudgetUnitList(String planYear) {
        return getBudgetUnitListSub(planYear,false);
    }
    /**
     * ȸ��μ� - BudgetUnit List ��ȸ
     *
     * @param planYear
     * @return
     */
    private List<ManagementGroupVO> getBudgetUnitListSub(String planYear,boolean includeInactive) {
        List<AccountingDepartmentVO> accountingDepartmentVOList = new ArrayList<AccountingDepartmentVO>();
        accountingDepartmentVOList.add(this.getVo());
        return getBudgetUnitListCore(accountingDepartmentVOList,planYear,includeInactive);
    }
    public static List<ManagementGroupVO> getBudgetUnitList(List<AccountingDepartmentVO> accountingDepartmentVOList, String planYear) {
        return getBudgetUnitListSub(accountingDepartmentVOList,planYear,false);
    }
    public static List<ManagementGroupVO> getBudgetUnitList(List<AccountingDepartmentVO> accountingDepartmentVOList, String planYear,boolean includeInactive) {
        return getBudgetUnitListSub(accountingDepartmentVOList,planYear,includeInactive);
    }
    private static List<ManagementGroupVO> getBudgetUnitListSub(List<AccountingDepartmentVO> accountingDepartmentVOList, String planYear,boolean includeInactive) {
        return getBudgetUnitListCore(accountingDepartmentVOList,planYear,includeInactive);
    }
    private static List<ManagementGroupVO> getBudgetUnitListCore(List<AccountingDepartmentVO> accountingDepartmentVOList, String planYear,boolean includeInactive) {
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();
        
        if(!includeInactive) StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[useYn]", GlobalConstants.OQL_OPERATOR_EQUAL, "Y");
        if (StrUtil.isEmpty(planYear) ) {
            planYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        }
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[planYear]", GlobalConstants.OQL_OPERATOR_EQUAL, planYear);
        return ObjectRoot.getRelatedObjectSet(accountingDepartmentVOList, ApplicationSchemaConstants.RELCLASS_HASMANAGEMENTGROUPACCOUNTDEPT, ApplicationSchemaConstants.BIZCLASS_MANAGEMENTGROUP, GlobalConstants.FLAG_TYPE_FROM,"",wherePattern.toString(), 
                paramPattern.toString());
    }
    /**
     * ȸ��μ� - Budget Unit - Division ��ȸ
     *
     * @return
     */
    public List<CodeDetailVO> getDivisionMappedBudgetUnit() {
        String accountDeptObid = this.getVo().getObid();
        List<ManagementGroupVO> buList = null;
        if ( !NullUtil.isNone(accountDeptObid) ) {
            AccountingDepartment acctDept = DomUtil.toDom(accountDeptObid);
            if ( !NullUtil.isNull(acctDept) ) {
                buList = acctDept.getBudgetUnitList("");
            }
        }
        List<CodeDetailVO> result = new ArrayList<CodeDetailVO>();
        
        if ( !NullUtil.isNone(buList) ) {
            CodeDetailVO codeDetailVO = new CodeDetailVO();
            codeDetailVO.setOutDataAttributeValue("codeMasterName", "DIVISION_MGTGROUP");
            for ( ManagementGroupVO buVO : buList ) {
                ManagementGroup buDom = DomUtil.toDom(buVO);
                List<ManagementGroupListVO> buMasterList = buDom.getBudgetUnitMasterInfo();
                if ( !NullUtil.isNone(buMasterList) ) {
                	codeDetailVO.setAttribute01(buMasterList.get(0).getNames());
                    List<CodeDetailVO> buDivisionList = buDom.getBudgetUnitDivision(codeDetailVO);
                    if ( !NullUtil.isNone(buDivisionList) ) {
                        for ( CodeDetailVO codeVO : buDivisionList ) {
                            result.add(codeVO);
                        }
                    }
                }
            }
        }
        return result;
    }
}

