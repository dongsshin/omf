/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : AbstractDepartment.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.organization.dom;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import com.rap.omc.api.object.model.ObjectRootVO;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;

import rap.api.object.bizplan.model.BizPlanProjectVO;
import rap.api.object.organization.model.AbstractDepartmentVO;
import rap.api.object.organization.model.AccountingDepartmentVO;
import rap.api.object.organization.model.DepartmentVO;
import rap.api.object.relation.dom.HasWelfareAllocResult;
import rap.application.constants.ApplicationSchemaConstants;


public class AbstractDepartment extends Organizations {
    public AbstractDepartment(String obid){
        super(obid);
    }
    public AbstractDepartment(AbstractDepartmentVO vo){
        super(vo);
    }
    @Override
    public AbstractDepartmentVO getVo(){
        return (AbstractDepartmentVO)super.getVo();
    }
    @Override
    public void initialize(){
        super.initialize();
        initializeAbstractDepartment();
    }
    public void initializeAbstractDepartment(){
    /*code here*/
    }
    @Override
    public String toString() {
        return "AbstractDepartment[toString()=" + super.toString() + "]";
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
    * ���� ���� ������Ʈ
    * @param versionObid
    * @param projectNames
    * @return
    */
   public List<BizPlanProjectVO> retrieveProjectList(String versionObid, String projectNames) {
       StringBuffer selectPattern = new StringBuffer();
       StringBuffer fromPattern = new StringBuffer();
       StringBuffer wherePattern = new StringBuffer();
       StringBuffer paramPattern = new StringBuffer();
       
       StringUtil.constructSelectPattern(selectPattern, "@VER.[obid] versionObid");
       StringUtil.constructSelectPattern(selectPattern, "@DEPT.[obid] deptObid");
       StringUtil.constructSelectPattern(selectPattern, "@PJT2DEPT.[obid] pjt2deptObid");
       
       fromPattern.append("<this>ThisConnectedWithTo<[HasVersionProject]@VER2PJT>+");
       fromPattern.append("<[HasVersionProject]@VER2PJT>FromConnectedWithThis<[BizPlanVersion]@VER>+");
       fromPattern.append("<[BizPlanVersion]@VER>ThisConnectedWithFrom<[HasVersionDepartment]@VER2DEPT>+");
       fromPattern.append("<[HasVersionDepartment]@VER2DEPT>ToConnectedWithThis<[AbstractDepartment]@DEPT>+");
       fromPattern.append("<this>ThisConnectedWithFrom<[HasPlanProjectDepartment]@PJT2DEPT>+");
       fromPattern.append("<[HasPlanProjectDepartment]@PJT2DEPT>ToConnectedWithThis<[AbstractDepartment]@DEPT>+");
       
       String deptObid = this.getVo().getObid();
       if (!StrUtil.isEmpty(deptObid)) {
           StringUtil.constructWherePattern(wherePattern, paramPattern, "@DEPT.[obid]", GlobalConstants.OQL_OPERATOR_EQUAL, deptObid);
       }
       if (!StrUtil.isEmpty(versionObid)) {
           StringUtil.constructWherePattern(wherePattern, paramPattern, "@VER.[obid]", GlobalConstants.OQL_OPERATOR_EQUAL, versionObid);            
       }
       if (!StrUtil.isEmpty(projectNames)) {
           StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[names]", GlobalConstants.OQL_OPERATOR_EQUAL, projectNames);
       }
       StringUtil.addSortByPattern(selectPattern, "@this.[titles] asc");
       
       List<BizPlanProjectVO> resultList = ObjectRoot.searchObjects(
               ApplicationSchemaConstants.BIZCLASS_BIZPLANPROJECT
               , GlobalConstants.FLAG_TYPE_ALL
               , GlobalConstants.FLAG_TYPE_ALL 
               , selectPattern.toString()
               , fromPattern.toString()
               , wherePattern.toString()
               , paramPattern.toString()
               , false
               , 0
               );
       return resultList;
   }
   
   /**
    * �����Ļ���(�Ϲ�) ��ΰ�� ���
    * @param planYear
    * @param versionObid
    * @return
    */
   public List<BizPlanProjectVO> getRelatedProjectList4HasWelfareAllocResult(String planYear, String versionObid) {
       StringBuffer selectPattern = new StringBuffer();
       StringBuffer wherePattern = new StringBuffer();
       StringBuffer paramPattern = new StringBuffer();
       
       if (!StrUtil.isEmpty(planYear)) {
           StringUtil.constructWherePattern(wherePattern, paramPattern, "@REL.[planYear]", GlobalConstants.OQL_OPERATOR_EQUAL, planYear);            
       }
       if (!StrUtil.isEmpty(versionObid)) {
           StringUtil.constructWherePattern(wherePattern, paramPattern, "@REL.[versionObid]", GlobalConstants.OQL_OPERATOR_EQUAL, versionObid);
       }
       
       StringUtil.addSortByPattern(selectPattern, "@this.[titles] asc");
       
       List<BizPlanProjectVO> list = this.getRelatedObjects(
               ApplicationSchemaConstants.RELCLASS_HASWELFAREALLOCRESULT
               , ApplicationSchemaConstants.BIZCLASS_BIZPLANPROJECT
               , GlobalConstants.FLAG_TYPE_TO
               , selectPattern.toString()
               , wherePattern.toString()
               , paramPattern.toString()
               , false
               , false
               , 0
               , 0
               );
       return list;
   }
   
   /**
    * �����Ļ���(�Ϲ�) ��ΰ�� ����
    * @param planYear
    * @param versionObid
    */
   public void deleteRelationObject4WelfareAllocResult(String planYear, String versionObid) {
       List<BizPlanProjectVO> list = this.getRelatedProjectList4HasWelfareAllocResult(planYear, versionObid);
       if (list != null && list.size() > 0) {
           BizPlanProjectVO projectVO = null;
           Map<String, Object> projectOutData = null;
           String resultObid = null;
           HasWelfareAllocResult resultDOM = null;
           for (int i = 0; i < list.size(); i++) {
               projectVO = list.get(i);
               projectOutData = projectVO.getOutData();
               resultObid = (String) projectOutData.get("rel_obid");
               resultDOM = (HasWelfareAllocResult) DomUtil.toDom(resultObid);
               resultDOM.deleteObject();
           }
       }
   }
   
   /**
    * �����Ļ���(�Ϲ�) �ݾ� ���
    * @param planYear
    * @param versionObid
    * @param projectObid
    * @return
    */
   public List<BusinessRelationObjectVO> getRelationshipList4HasWelfareAmount(String planYear, String versionObid, String projectObid) {
       StringBuffer selectPattern = new StringBuffer();
       StringBuffer wherePattern = new StringBuffer();
       StringBuffer paramPattern = new StringBuffer();
       
       if (!StrUtil.isEmpty(planYear)) {
           StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[planYear]", GlobalConstants.OQL_OPERATOR_EQUAL, planYear);            
       }
       if (!StrUtil.isEmpty(versionObid)) {
           StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[versionObid]", GlobalConstants.OQL_OPERATOR_EQUAL, versionObid);
       }
       if (!StrUtil.isEmpty(projectObid)) {
           StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[toObid]", GlobalConstants.OQL_OPERATOR_EQUAL, projectObid);
       }
       
       List<BusinessRelationObjectVO> list = this.getRelationship(
               ApplicationSchemaConstants.RELCLASS_HASWELFAREAMOUNT
               , ApplicationSchemaConstants.BIZCLASS_BIZPLANPROJECT
               , GlobalConstants.FLAG_TYPE_TO
               , selectPattern.toString()
               , wherePattern.toString()
               , paramPattern.toString()
               );
       return list;
   }
   
   /**
    * ������ ��α���
    * @param versionObid
    * @param projectObid
    * @return
    */
   public List<BusinessRelationObjectVO> getRelationshipList4HasPlanIndirectAllocFactor(String versionObid, String allocNo, String projectObid, String notAllocatedYn) {
       StringBuffer selectPattern = new StringBuffer();
       StringBuffer wherePattern = new StringBuffer();
       StringBuffer paramPattern = new StringBuffer();
       
       if (!StrUtil.isEmpty(versionObid)) {
           StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[versionObid]", GlobalConstants.OQL_OPERATOR_EQUAL, versionObid);
       }
       if (!StrUtil.isEmpty(allocNo)) {
           StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[allocNo]", GlobalConstants.OQL_OPERATOR_EQUAL, allocNo);
       }
       if (!StrUtil.isEmpty(projectObid)) {
           StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[toObid]", GlobalConstants.OQL_OPERATOR_EQUAL, projectObid);
       }
       if (!StrUtil.isEmpty(notAllocatedYn)) {
           StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[notAllocatedYn]", GlobalConstants.OQL_OPERATOR_EQUAL, notAllocatedYn);
       }
       
       List<BusinessRelationObjectVO> list = this.getRelationship(
               ApplicationSchemaConstants.RELCLASS_HASPLANINDIRECTALLOCFACTOR
               , ApplicationSchemaConstants.BIZCLASS_BIZPLANPROJECT
               , GlobalConstants.FLAG_TYPE_TO
               , selectPattern.toString()
               , wherePattern.toString()
               , paramPattern.toString()
               );
       return list;
   }
   public static Map<String, String> getDepartmentAndAccountDivisionMap(Set<String> departmentCodeSet){
       StringBuffer wherePattern = new StringBuffer();
       StringBuffer paramPattern = new StringBuffer();
       StringBuffer selectPattern = new StringBuffer();
     //Account Department������ �켱�� �Ѵ�.
       StringUtil.constructSelectPattern(selectPattern, "SortBy@this.[className] asc,@this.[created] desc");
       StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[departmentCode]",GlobalConstants.OQL_OPERATOR_IN, StrUtil.convertSet2Str(departmentCodeSet));
       List<ObjectRootVO> voList = findObjects(ApplicationSchemaConstants.BIZCLASS_DEPARTMENT + "," + ApplicationSchemaConstants.BIZCLASS_ACCOUNTINGDEPARTMENT, selectPattern.toString(), wherePattern.toString(), paramPattern.toString());
       //SortUtil.sort(voList, "className",false);
       Map<String, String> map = new HashMap<String, String>();
       String departmentCode = "";
       String accountingUnitCode = "";
       for(ObjectRootVO vo: voList){
           if(vo instanceof AccountingDepartmentVO){
               departmentCode     = ((AccountingDepartmentVO)vo).getDepartmentCode();
               accountingUnitCode = ((AccountingDepartmentVO)vo).getAccountingUnitCode();
               if(NullUtil.isNull(map.get(departmentCode)) && !StrUtil.isEmpty(accountingUnitCode)){
                   map.put(departmentCode, accountingUnitCode);
               }
           }else if(vo instanceof DepartmentVO){
               departmentCode     = ((DepartmentVO)vo).getDepartmentCode();
               accountingUnitCode = ((DepartmentVO)vo).getAccountingUnitCode();
               if(NullUtil.isNull(map.get(departmentCode)) && !StrUtil.isEmpty(accountingUnitCode)){
                   map.put(departmentCode, accountingUnitCode);
               }
           }
       }
       return map;
   }
}

