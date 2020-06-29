/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : Department.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.organization.dom;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rap.omc.api.object.dom.BusinessObjectMaster;
import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.api.object.model.ObjectRootVO;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;
import com.rap.omc.util.TimeServiceUtil;

import rap.api.object.bizplan.model.ManagementGroupVO;
import rap.api.object.organization.model.AccountingDepartmentVO;
import rap.api.object.organization.model.DepartmentVO;
import rap.api.object.organization.model.UsersVO;
import rap.api.object.project.model.ProjectsVO;
import rap.application.constants.ApplicationSchemaConstants;


public class Department extends AbstractHrDepartment {
    public Department(String obid){
        super(obid);
    }
    public Department(DepartmentVO vo){
        super(vo);
    }
    @Override
    public DepartmentVO getVo(){
        return (DepartmentVO)super.getVo();
    }
    @Override
    public void initialize(){
        super.initialize();
        initializeDepartment();
    }
    public void initializeDepartment(){
    /*code here*/
    }
    @Override
    public String toString() {
        return "Department[toString()=" + super.toString() + "]";
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
    public DepartmentVO getVOforName(String names){
        return ObjectRoot.findObject(this.getVo().getClassName(), names, false);
    }
    
    
    public List<ProjectsVO> getProject(){
        return this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_HASTEAM, ApplicationSchemaConstants.BIZCLASS_PROJECTS, GlobalConstants.FLAG_TYPE_FROM );
    }
    
    public String getAccountingDepartmentObid(){
        List<AccountingDepartmentVO> accDeptList = this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_HASACCOUNTINGDEPARTMENT);
        
        if(accDeptList.size() > 0 ){ return accDeptList.get(0).getObid(); }
        return null;
    }
    
    public AccountingDepartment getAccountingDepartment(){
        List<AccountingDepartmentVO> accDeptList = this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_HASACCOUNTINGDEPARTMENT);
        
        if(accDeptList.size() > 0 ){ return DomUtil.toDom(accDeptList.get(0)); }
        return null;
    }
    public AccountingDepartment getAccountingDepartment(boolean isActive){
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();
        
        if ( isActive ) {
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@REL.[useYn]", GlobalConstants.OQL_OPERATOR_EQUAL, "Y");
        }
        List<AccountingDepartmentVO> accDeptList = this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_HASACCOUNTINGDEPARTMENT, 
                ApplicationSchemaConstants.BIZCLASS_ACCOUNTINGDEPARTMENT, 
                GlobalConstants.FLAG_TYPE_FROM, 
                wherePattern.toString(), 
                paramPattern.toString(), 
                0);
        
        if ( accDeptList.size() > 0 ){ return DomUtil.toDom(accDeptList.get(0)); }
        return null;
    }
    public ManagementGroupVO getManagementGroup(){
        AccountingDepartment accountingDepartmentDom = this.getAccountingDepartment();
        if(NullUtil.isNull(accountingDepartmentDom)) return null;
        List<ManagementGroupVO> list = accountingDepartmentDom.getBudgetUnitList(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
        if(NullUtil.isNull(list)) return null;
        return list.get(0);
    }
    public static final AccountingDepartment getAccountingDepartment(String hrDeptCode){
        DepartmentVO deptVO = ObjectRoot.findObject(ApplicationSchemaConstants.BIZCLASS_DEPARTMENT, hrDeptCode, false);
        
        if(!NullUtil.isNull(deptVO) ){
            Department dept =  DomUtil.toDom(deptVO); 
            return dept.getAccountingDepartment();
        
        }
        return null;
    }
    
    public static final Department getDepartment(String deptCode){
        if(!NullUtil.isNone(deptCode)) {
            DepartmentVO deptVO = ObjectRoot.findObject(ApplicationSchemaConstants.BIZCLASS_DEPARTMENT, deptCode, false);
            if(!NullUtil.isNull(deptVO) ){ return DomUtil.toDom(deptVO); }
        }
        return null;
    }
    
    /**
     * �����׷� ��� �μ� �ߺ� üũ
     *
     * @param mgtGroupRelData
     * @param deptRelData
     * @return
     */
    public List<ManagementGroupVO> getManagementGroupDept() {
        StringBuffer fromPattern = new StringBuffer();
        fromPattern.append("<this>ThisConnectedWithFrom<[ManagementGroupList]@MGL>+");
        fromPattern.append("<this>ThisConnectedWithFrom<[HasManagementGroupDept]@REL>++");
        
        StringBuffer selectPattern = new StringBuffer();
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();
        
        StringUtil.constructSelectPattern(selectPattern, "@MGL.[names] mgmtGroupListName");
        StringUtil.constructSelectPattern(selectPattern, "@MGL.[obid] mgmtGroupListObid");
        
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@REL.[toObid]", GlobalConstants.OQL_OPERATOR_EQUAL, this.getVo().getObid());
        
        List<ManagementGroupVO> result = ObjectRoot.searchObjects( ApplicationSchemaConstants.BIZCLASS_MANAGEMENTGROUP,
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
    
    /**
     * ������Ʈ�� �������������� �˻��Ѵ�. 
     */
    public static List<DepartmentVO> getProjectLeaderInfo(String departmentCode){
    
        Date toDate = TimeServiceUtil.getDBLocalTime();
        SimpleDateFormat sdfYmd = new SimpleDateFormat("yyyyMMdd");
        String dateYmd = sdfYmd.format(toDate);
        
        StringBuffer selectPattern = new StringBuffer();
        StringBuffer wherePattern  = new StringBuffer();
        StringBuffer paramPattern  = new StringBuffer();
    
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[departmentCode]",GlobalConstants.OQL_OPERATOR_EQUAL, departmentCode);
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[affiliateCode]",GlobalConstants.OQL_OPERATOR_EQUAL, "EKHQ");
        StringUtil.constructWherePattern(wherePattern, paramPattern, "IFNULL(@this.[leaderEmployeeNo], '-')", GlobalConstants.OQL_OPERATOR_NOT_EQUAL, "-");
        StringUtil.constructWherePattern(wherePattern, paramPattern, "IFNULL(@this.[dateFrom],'"+dateYmd+"')", GlobalConstants.OQL_OPERATOR_LESS_EQTHAN, dateYmd);
        StringUtil.constructWherePattern(wherePattern, paramPattern, "IFNULL(@this.[dateTo],'"+dateYmd+"')", GlobalConstants.OQL_OPERATOR_GREATER_EQTHAN, dateYmd);
    
        List<DepartmentVO> resultList = ObjectRoot.findObjects( ApplicationSchemaConstants.BIZCLASS_DEPARTMENT,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                selectPattern.toString(),
                wherePattern.toString(),
                paramPattern.toString(),
                false,
                0);
    
        return resultList;
    }
    /**
     * 
     *
     * @param inOnly
     * @param isActivieOnly
     * @return
     */
    public final List<UsersVO> getMemberList(boolean inOnly, boolean isActivieOnly){
        if(StrUtil.isEmpty(this.getVo().getHrDeptCode())) return new ArrayList<UsersVO>();
        Set<String> hrDeptparmetSet = new HashSet<String>();
        hrDeptparmetSet.add(this.getVo().getHrDeptCode());
        return getMemberListSub(hrDeptparmetSet,inOnly,isActivieOnly);
    }
    /**
     * 
     *
     * @param hrDeptparmetSet
     * @param inOnly
     * @param isActivieOnly
     * @return
     */
    public static final List<UsersVO> getMemberList(Set<String> hrDeptparmetSet, boolean inOnly, boolean isActivieOnly){
        return getMemberListSub(hrDeptparmetSet,inOnly,isActivieOnly);
    }
    /**
     * 
     *
     * @param hrDeptparmetSet
     * @param inOnly
     * @param isActivieOnly
     * @return
     */
    private static final List<UsersVO> getMemberListSub(Set<String> hrDeptparmetSet, boolean inOnly, boolean isActivieOnly){
        StringBuffer wherePattern  = new StringBuffer();
        StringBuffer paramPattern  = new StringBuffer();
        if(inOnly) StringUtil.constructWherePattern(wherePattern, paramPattern, "substr(@this.[names],1,1)", GlobalConstants.OQL_OPERATOR_NOT_IN, "X,Y");
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[hrDepartmentCode]", GlobalConstants.OQL_OPERATOR_IN, StrUtil.convertSet2Str(hrDeptparmetSet));
        if(isActivieOnly) {
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[states]", GlobalConstants.OQL_OPERATOR_EQUAL, ApplicationSchemaConstants.STATE_PERSON_ACTIVE);
            StringUtil.constructWherePattern(wherePattern, paramPattern, "IFNULL(@this.[inOffiStatFlag],'Y')", GlobalConstants.OQL_OPERATOR_NOT_EQUAL, "T");
        }
        return BusinessObjectMaster.findObjects(ApplicationSchemaConstants.BIZCLASS_USERS, wherePattern.toString(), paramPattern.toString());
    }
    /**
     * 
     *
     * @return
     */
    public DepartmentVO getUpperDepartment(){
        if(!StrUtil.isEmpty(this.getVo().getHrAccessDeptCode())){
            return BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_DEPARTMENT, this.getVo().getHrAccessDeptCode());
        }
        return null;
    }
    /**
     * 
     *
     * @return
     */
    public List<DepartmentVO> getLowerDepartmentList(){
        return getLowerDepartmentList(1,false);
    }
    /**
     * 
     *
     * @param wantedLevel
     * @param includeSelf
     * @return
     */
    public List<DepartmentVO> getLowerDepartmentList(int wantedLevel, boolean includeSelf){
        List<DepartmentVO> departmentVOList = new ArrayList<DepartmentVO>();
        departmentVOList.add(this.getVo());
        return getLowerDepartmentList(departmentVOList,wantedLevel,includeSelf);
    }
    /**
     * 
     *
     * @param departmentVOList
     * @param wantedLevel
     * @param includeSelf
     * @return
     */
    public static List<DepartmentVO> getLowerDepartmentList(List<DepartmentVO> departmentVOList, int wantedLevel, boolean includeSelf){
        List<DepartmentVO> departmentVOResultList = new ArrayList<DepartmentVO>();
        int seq = 1;
        for(DepartmentVO vo : departmentVOList){
            vo.setUniqueString(StrUtil.LPAD(seq, 5, "0"));vo.setExplodedDepth(0);
            seq++;
        }
        if(includeSelf) departmentVOResultList.addAll(departmentVOList);
        getLowerDepartmentListSub(departmentVOList,departmentVOResultList,wantedLevel,1);
        return departmentVOResultList;
    }
    /**
     * 
     *
     * @param departmentVOList
     * @param wantedLevel
     * @return
     */
    public static List<DepartmentVO> getLowerDepartmentList(List<DepartmentVO> departmentVOList, int wantedLevel){
        return getLowerDepartmentList(departmentVOList,wantedLevel,true);
    }
    /**
     * 
     *
     * @param departmentVOList
     * @return
     */
    public static List<DepartmentVO> getLowerDepartmentList(List<DepartmentVO> departmentVOList){
        return getLowerDepartmentList(departmentVOList,1,true);
    }
    /**
     * 
     *
     * @param departmentVOList
     * @param departmentVOResultList
     * @param wantedLevel
     * @param currentLevel
     */
    private static void getLowerDepartmentListSub(List<DepartmentVO> departmentVOList, List<DepartmentVO> departmentVOResultList, int wantedLevel, int currentLevel){
        if(currentLevel > wantedLevel) return;
        StringBuffer wherePattern  = new StringBuffer();
        StringBuffer paramPattern  = new StringBuffer();
        Set<String> deparmentNameSet = ObjectRoot.getDistinctValueSet(departmentVOList, "names", true);
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[hrUpperDeptCode]",GlobalConstants.OQL_OPERATOR_IN, StrUtil.convertSet2Str(deparmentNameSet));
        List<DepartmentVO> tempDepartmentVOList = ObjectRoot.findObjects(ApplicationSchemaConstants.BIZCLASS_DEPARTMENT, wherePattern.toString(), paramPattern.toString());
        Map<String,ObjectRootVO> departmentMapDB = ObjectRoot.makeVoDB(departmentVOList, "names");
        int seq = 1;
        for(DepartmentVO deptVO : tempDepartmentVOList){
            DepartmentVO upperDepartmentVO = (DepartmentVO)departmentMapDB.get(deptVO.getHrUpperDeptCode());
            deptVO.setUniqueStringParent(upperDepartmentVO.getUniqueString());
            deptVO.setUniqueString(upperDepartmentVO.getUniqueString() + StrUtil.LPAD(seq, 5, "0"));
            deptVO.setOutDataAttributeValue("upperDepartment", upperDepartmentVO);
            deptVO.setExplodedDepth(currentLevel);
        }
        departmentVOResultList.addAll(tempDepartmentVOList);
        getLowerDepartmentListSub(tempDepartmentVOList,departmentVOResultList,wantedLevel,currentLevel+1);
    }
    
    /**
     * HR�μ� - BudgetUnit List ��ȸ
     *
     * @param planYear
     * @return
     */
    public List<ManagementGroupVO> getBudgetUnitList(String planYear){
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();
        
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@REL.[useYn]",GlobalConstants.OQL_OPERATOR_EQUAL, "Y");
        if ( NullUtil.isNone(planYear) ) {
            planYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        }
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[planYear]",GlobalConstants.OQL_OPERATOR_EQUAL, planYear);
        
        return this.getRelatedObjects(
                ApplicationSchemaConstants.RELCLASS_HASMANAGEMENTGROUPDEPT, 
                ApplicationSchemaConstants.BIZCLASS_MANAGEMENTGROUP, 
                GlobalConstants.FLAG_TYPE_FROM, 
                wherePattern.toString(), 
                paramPattern.toString(), 
                0
                );
    }
}

