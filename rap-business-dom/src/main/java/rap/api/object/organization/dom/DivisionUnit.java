/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : DivisionUnit.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.organization.dom;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rap.omc.api.object.dom.BusinessObjectMaster;
import com.rap.omc.api.object.dom.BusinessRelationObject;
import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.api.object.model.ObjectRootVO;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.framework.exception.ApplicationException;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;

import rap.api.object.organization.model.AffiliateUnitVO;
import rap.api.object.organization.model.DivisionPeriodVO;
import rap.api.object.organization.model.DivisionUnitVO;
import rap.api.object.project.model.ProjectDevelopmentTypeVO;
import rap.api.object.project.model.ProjectGradeVO;
import rap.api.object.project.model.ProjectLifeCycleVO;
import rap.api.object.relation.dom.UsingProjectLifeCycle;
import rap.api.object.relation.model.UsingProjectLifeCycleVO;
import rap.application.constants.ApplicationSchemaConstants;
import rap.application.constants.ProjectConstants;


public class DivisionUnit extends Organizations {
    public DivisionUnit(String obid){
        super(obid);
    }
    public DivisionUnit(DivisionUnitVO vo){
        super(vo);
    }
    @Override
    public DivisionUnitVO getVo(){
        return (DivisionUnitVO)super.getVo();
    }
    @Override
    public void initialize(){
        super.initialize();
        initializeDivisionUnit();
    }
    public void initializeDivisionUnit(){
    /*code here*/
    }
    @Override
    public String toString() {
        return "DivisionUnit[toString()=" + super.toString() + "]";
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
   static public String getObidByDivisionName(String names) {
       if(StrUtil.isEmpty(names) ) { return null; };
       DivisionUnitVO divisionUnitVo = ObjectRoot.findObject(ApplicationSchemaConstants.BIZCLASS_DIVISIONUNIT, names, false);
       return NullUtil.isNull(divisionUnitVo) ? names : divisionUnitVo.getObid();
   }
   
   static public DivisionUnitVO getObidForDivisionName(String names) {
       if(StrUtil.isEmpty(names) ) { return null; };
       return ObjectRoot.findObject(ApplicationSchemaConstants.BIZCLASS_DIVISIONUNIT, names, false);
   }
   
   static public DivisionUnitVO getVOByName(String names) {
       if(StrUtil.isEmpty(names) ) { return null; };
       return ObjectRoot.findObject(ApplicationSchemaConstants.BIZCLASS_DIVISIONUNIT, names, false);
   }
   
   static public List<DivisionUnitVO> getDivisionList(String businessUnitCode, String names, String descriptions, boolean isOrderByNames) {
       // Order by ���� ����
       StringBuffer selectPatternBuf = new StringBuffer();
       if( isOrderByNames ){
           StringUtil.addSortByPattern(selectPatternBuf, "@this.[names]");
       }
       else{
           StringUtil.addSortByPattern(selectPatternBuf, "@this.[titles]");
       }

       // Where ���� ����
       StringBuffer wherePatternBuf = new StringBuffer();
       StringBuffer paramPatternBuf = new StringBuffer();
       
       // Business Unit Code�� �ش��ϴ� Plant�� ��ȸ
       if( !StrUtil.isEmpty(businessUnitCode) ){
           StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[businessUnitCode]",
                   GlobalConstants.OQL_OPERATOR_EQUAL, businessUnitCode);
       }

           if(!StrUtil.isEmpty(names)){
               StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "LOWER(@this.[names])",
                       GlobalConstants.OQL_OPERATOR_LIKE, GlobalConstants.FLAG_TYPE_ALL + names.toLowerCase() + GlobalConstants.FLAG_TYPE_ALL);
           }

           if(!StrUtil.isEmpty(descriptions)){
               StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "LOWER(@this.[descriptions])",
                       GlobalConstants.OQL_OPERATOR_LIKE, GlobalConstants.FLAG_TYPE_ALL + descriptions.toLowerCase() + GlobalConstants.FLAG_TYPE_ALL);
           }


       StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[useFlag]",
               GlobalConstants.OQL_OPERATOR_EQUAL, "Y");
       
       List<DivisionUnitVO> result = ObjectRoot.searchObjects(
               ApplicationSchemaConstants.BIZCLASS_DIVISIONUNIT,
               GlobalConstants.FLAG_TYPE_ALL,
               GlobalConstants.FLAG_TYPE_ALL,
               GlobalConstants.FLAG_TYPE_ALL,
               ApplicationSchemaConstants.STATE_ACTIVE_INACTIVE_ACTIVE,
               GlobalConstants.FLAG_TYPE_ALL,
               GlobalConstants.FLAG_TYPE_ALL,
               GlobalConstants.FLAG_TYPE_ALL,
               GlobalConstants.FLAG_TYPE_ALL,
               GlobalConstants.FLAG_TYPE_ALL,
               false,
               false,
               false,
               false,
               selectPatternBuf.toString(),
               null,
               wherePatternBuf.toString(),
               paramPatternBuf.toString(),
               true,
               0
       );
       return result;    
   }
   
   static public List<DivisionUnitVO> getDivisionList(String businessUnitCode){
       int year = Calendar.getInstance().get(Calendar.YEAR);
       return getDivisionList(businessUnitCode, String.valueOf(year));
   }
   
   static public List<DivisionUnitVO> getDivisionList(String businessUnitCode, String yyyy){
       // Order by ���� ����
       StringBuffer selectPatternBuf = new StringBuffer();

       StringUtil.addSortByPattern(selectPatternBuf, "@this.[titles]");

       // Where ���� ����
       StringBuffer wherePatternBuf = new StringBuffer();
       StringBuffer paramPatternBuf = new StringBuffer();
       
       // Business Unit Code�� �ش��ϴ� Plant�� ��ȸ
       if( !StrUtil.isEmpty(yyyy) ){
           StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[startYyyy]",
                   GlobalConstants.OQL_OPERATOR_LESS_EQTHAN, yyyy);

           StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[endYyyy]",
                   GlobalConstants.OQL_OPERATOR_GREATER_EQTHAN, yyyy);
           
           //List<OmcOQLCondition> conditionList = new ArrayList<OmcOQLCondition>();
           //conditionList.add(new OmcOQLCondition("@this.[endYyyy]",   ""  ,GlobalConstants.OQL_OPERATOR_NOT_EQUAL));
           //conditionList.add(new OmcOQLCondition("@this.[endYyyy]",   yyyy  ,GlobalConstants.OQL_OPERATOR_LESS_EQTHAN));
           //StringUtil.constructOrWherePattern(wherePatternBuf, paramPatternBuf, conditionList);
       }
       
       List<DivisionPeriodVO> result = ObjectRoot.searchObjects(
               ApplicationSchemaConstants.BIZCLASS_DIVISIONPERIOD,
               GlobalConstants.FLAG_TYPE_ALL,
               GlobalConstants.FLAG_TYPE_ALL,
               GlobalConstants.FLAG_TYPE_ALL,
               ApplicationSchemaConstants.STATE_ACTIVE_INACTIVE_ACTIVE,
               GlobalConstants.FLAG_TYPE_ALL,
               GlobalConstants.FLAG_TYPE_ALL,
               GlobalConstants.FLAG_TYPE_ALL,
               GlobalConstants.FLAG_TYPE_ALL,
               GlobalConstants.FLAG_TYPE_ALL,
               false,
               false,
               false,
               false,
               selectPatternBuf.toString(),
               null,
               wherePatternBuf.toString(),
               paramPatternBuf.toString(),
               true,
               0
       );
       
       List<DivisionUnitVO> divisionList = new ArrayList<DivisionUnitVO>();
       
       for(int i = 0 ; i < result.size() ; i++ ){
           
           DivisionUnitVO div = ObjectRoot.findObject(ApplicationSchemaConstants.BIZCLASS_DIVISIONUNIT, result.get(i).getDivisionCode(), false);
           
           if(!NullUtil.isNone(businessUnitCode)){
               if(businessUnitCode.equals(div.getBusinessUnitCode())){
                   div.getOutData().put("hrDepartmentCode",result.get(i).getDepartmentCode());
                   divisionList.add(div);
               }
           } else {
               div.getOutData().put("hrDepartmentCode",result.get(i).getDepartmentCode());
               divisionList.add(div);
           }
       }
       
       return divisionList; 
   }
   
   public List<ProjectDevelopmentTypeVO> getUsingProjectDevelopmentType(String className){
       StringBuffer wherePatternBuf = new StringBuffer();
       StringBuffer paramPatternBuf = new StringBuffer();
       
       if(!StrUtil.isEmpty(className)){
           StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[appliedClassList]",GlobalConstants.OQL_OPERATOR_LIKE, "%" + className + "%");
       }
       
       return getRelatedObjects(ApplicationSchemaConstants.RELCLASS_USINGDEVELOPMENTTYPE, 
               ApplicationSchemaConstants.BIZCLASS_PROJECTDEVELOPMENTTYPE, GlobalConstants.FLAG_TYPE_TO, "", wherePatternBuf.toString(), paramPatternBuf.toString(), false, true, 0, 1);
       
       //return getRelatedObjects(ApplicationSchemaConstants.RELCLASS_USINGDEVELOPMENTTYPE, GlobalConstants.FLAG_TYPE_TO);
   }

   public List<ProjectDevelopmentTypeVO> getUsingProjectDevelopmentType(String className, String states ){
       StringBuffer wherePatternBuf = new StringBuffer();
       StringBuffer paramPatternBuf = new StringBuffer();
       StringBuffer selectPatternBuf = new StringBuffer();

       if(!StrUtil.isEmpty(className)) StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[appliedClassList]",GlobalConstants.OQL_OPERATOR_LIKE, "%" + className + "%");
       
       if(!StrUtil.isEmpty(states)) {
           StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[states]",
                   GlobalConstants.OQL_OPERATOR_EQUAL, states);

           if(states.equals(ApplicationSchemaConstants.STATE_ACTIVE_INACTIVE_ACTIVE))
           StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@REL.[isActive]",
                   GlobalConstants.OQL_OPERATOR_EQUAL, "Y");
       }

       StringUtil.constructSelectPattern(selectPatternBuf,"SortBy@this.[titles] asc");

       return getRelatedObjects(ApplicationSchemaConstants.RELCLASS_USINGDEVELOPMENTTYPE, 
               ApplicationSchemaConstants.BIZCLASS_PROJECTDEVELOPMENTTYPE, GlobalConstants.FLAG_TYPE_TO, 
               selectPatternBuf.toString(), wherePatternBuf.toString(), paramPatternBuf.toString(), false, true, 0, 1);
   }
   
   /* Inactive ���� ó�� */
   public List<AffiliateUnitVO> getAffiliateUnit(){
       return getRelatedObjects(ApplicationSchemaConstants.RELCLASS_DIVISION2AFFILIATE, GlobalConstants.FLAG_TYPE_TO);
   }
   
   public List<ProjectLifeCycleVO> getUsingProjectLifeCycleList(){
       return getUsingProjectLifeCycleList(null);
   }   
   public List<ProjectLifeCycleVO> getUsingProjectLifeCycleList(String projectLifeCycle){
       
       StringBuffer wherePatternBuf = new StringBuffer();
       StringBuffer paramPatternBuf = new StringBuffer();
       
       if(!StrUtil.isEmpty(projectLifeCycle)) StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[names]",GlobalConstants.OQL_OPERATOR_EQUAL, projectLifeCycle);
       
       return getRelatedObjects(ApplicationSchemaConstants.RELCLASS_USINGPROJECTLIFECYCLE, 
               ApplicationSchemaConstants.BIZCLASS_PROJECTLIFECYCLE, GlobalConstants.FLAG_TYPE_TO, 
               "", wherePatternBuf.toString(), paramPatternBuf.toString(), false, true, 0, 1);
   }
   
   public ProjectLifeCycleVO getUsingProjectLifeCycle(String projectLifeCycleName){
       
       StringBuffer wherePatternBuf = new StringBuffer();
       StringBuffer paramPatternBuf = new StringBuffer();
       
       if(!StrUtil.isEmpty(projectLifeCycleName)){
           StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[names]",
                   GlobalConstants.OQL_OPERATOR_EQUAL, projectLifeCycleName);
       }
       
       List<ProjectLifeCycleVO> projectLifeCycleVOList = getRelatedObjects(ApplicationSchemaConstants.RELCLASS_USINGPROJECTLIFECYCLE, 
               ApplicationSchemaConstants.BIZCLASS_PROJECTLIFECYCLE, GlobalConstants.FLAG_TYPE_TO, 
               "", wherePatternBuf.toString(), paramPatternBuf.toString(), false, true, 0, 1);
        
       ProjectLifeCycleVO  projectLifeCycleVO = null;
       if(projectLifeCycleVOList.isEmpty()){
           throw new ApplicationException("Project Life Cycle Error.");
       }else if(projectLifeCycleVOList.size() > 1){
           throw new ApplicationException("Project Life Cycle Error.");
       }else{
           projectLifeCycleVO = projectLifeCycleVOList.get(0);
           
       }
       return projectLifeCycleVO;
   }
   
   public List<ProjectGradeVO> getUsingProjecGrade(){
       return getUsingProjecGrade(ApplicationSchemaConstants.STATE_ACTIVE_INACTIVE_ACTIVE, "Y");
   }
   
   public List<ProjectGradeVO> getUsingProjecGrade(String states, String isActive){
       StringBuffer wherePatternBuf = new StringBuffer();
       StringBuffer paramPatternBuf = new StringBuffer();
       
       StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[states]",
               GlobalConstants.OQL_OPERATOR_EQUAL, states);
       
       StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@REL.[isActive]",
               GlobalConstants.OQL_OPERATOR_EQUAL, isActive);
       
       return getRelatedObjects(ApplicationSchemaConstants.RELCLASS_USINGPROJECTGRADE, 
               ApplicationSchemaConstants.BIZCLASS_PROJECTGRADE, GlobalConstants.FLAG_TYPE_TO, 
               "", wherePatternBuf.toString(), paramPatternBuf.toString(), false, true, 0, 1);
   }
   /**
    * �ش����� ����Ǿ��� Project Life Cycle����Ʈ�� ��ȸ�Ѵ�.
    *
    * @return
    */
   public final List<ObjectRootVO> getProjectLifeCycleList() {
       List<ProjectLifeCycleVO> lifeCycleVOList = this.getProjectLfeCycleList(true);
       return getProjectLifeCycleListSub(lifeCycleVOList,true);
   }
   /**
    * �ش����� ������� ���� Project Life Cycle����Ʈ�� ��ȸ�Ѵ�.
    *
    * @return
    */
   public final List<ObjectRootVO> getNotAppliedProjectLifeCycleList() {
       StringBuffer wherePatternBuf = new StringBuffer();
       StringBuffer paramPatternBuf = new StringBuffer();
       StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "!To[" + ApplicationSchemaConstants.RELCLASS_USINGPROJECTLIFECYCLE + "].From.names",GlobalConstants.OQL_OPERATOR_EQUAL, this.getNames());
       List<ProjectLifeCycleVO> lifeCycleVOList = ObjectRoot.findObjects(ApplicationSchemaConstants.BIZCLASS_PROJECTLIFECYCLE, wherePatternBuf.toString(), paramPatternBuf.toString());
       //return lifeCycleVOList;
       return getProjectLifeCycleListSub(lifeCycleVOList,false);
   }
   private final List<ObjectRootVO> getProjectLifeCycleListSub(List<ProjectLifeCycleVO> lifeCycleVOList, boolean isMyDivision) {
       StringBuffer wherePattern = new StringBuffer();
       StringBuffer parameterPattern = new StringBuffer();
       StringBuffer selectPattern = new StringBuffer();

       if(isMyDivision) {
           StringUtil.constructSelectParameterPattern(selectPattern, wherePattern, parameterPattern, "displayedCode"    , "REL.obid.To[" + ApplicationSchemaConstants.RELCLASS_USINGPROJECTPHASES+"{fromObid==#1}].displayedCode", this.getObid());
           StringUtil.constructSelectParameterPattern(selectPattern, wherePattern, parameterPattern, "displayedName"    , "REL.obid.To[" + ApplicationSchemaConstants.RELCLASS_USINGPROJECTPHASES+"{fromObid==#1}].displayedName", this.getObid());
           StringUtil.constructSelectParameterPattern(selectPattern, wherePattern, parameterPattern, "usingRelationObid", "REL.obid.To[" + ApplicationSchemaConstants.RELCLASS_USINGPROJECTPHASES+"{fromObid==#1}].obid", this.getObid());
           StringUtil.constructSelectParameterPattern(selectPattern, wherePattern, parameterPattern, "statesForDivision", "REL.obid.To[" + ApplicationSchemaConstants.RELCLASS_USINGPROJECTPHASES+"{fromObid==#1}].isActive", this.getObid());
           StringUtil.constructSelectParameterPattern(selectPattern, wherePattern, parameterPattern, "sequencesForDivision", "REL.obid.To[" + ApplicationSchemaConstants.RELCLASS_USINGPROJECTPHASES+"{fromObid==#1}].sequences", this.getObid());
           StringUtil.constructWherePattern(wherePattern, parameterPattern, "REL.obid.To[" + ApplicationSchemaConstants.RELCLASS_USINGPROJECTPHASES + "].fromObid",GlobalConstants.OQL_OPERATOR_EQUAL, this.getObid());
           
       }
       StringUtil.constructSelectPattern(selectPattern,"SortBy@REL.[sequences] asc");
       List<ObjectRootVO> list = ObjectRoot.getRelatedObjectSet(lifeCycleVOList, ApplicationSchemaConstants.RELCLASS_DETAILEDPROJECTPHASE, 
               ApplicationSchemaConstants.BIZCLASS_PROJECTPHASE, GlobalConstants.FLAG_TYPE_TO, selectPattern.toString(), wherePattern.toString(), parameterPattern.toString(), false, true);
       return list;
   }
   public final void removeLifeCycle(ProjectLifeCycleVO lifeCycelVO) {
       List<UsingProjectLifeCycleVO> list = BusinessRelationObject.findRelationObjects(ApplicationSchemaConstants.RELCLASS_USINGPROJECTLIFECYCLE,ApplicationSchemaConstants.BIZCLASS_DIVISIONUNIT,this.getObid(),ApplicationSchemaConstants.BIZCLASS_PROJECTLIFECYCLE,lifeCycelVO.getObid());
       for(UsingProjectLifeCycleVO vo : list){
           UsingProjectLifeCycle UsingProjectLifeCycleDom = new UsingProjectLifeCycle(vo);
           UsingProjectLifeCycleDom.deleteObject();
       }
   }
//   public final void applyLifeCycleToMyDivision(ProjectLifeCycleVO lifeCycelVO) {
//       ProjectLifeCycle projectLifeCycleDom = new ProjectLifeCycle(lifeCycelVO);
//       projectLifeCycleDom.applyLifeCycleAndPhase(this.getVo());
//   }
   public final List<ProjectGradeVO> getAppliedProjectGradeList() {
       
       StringBuffer wherePatternBuf = new StringBuffer();
       StringBuffer paramPatternBuf = new StringBuffer();
       StringBuffer selectPatternBuf = new StringBuffer();
       
       StringUtil.constructSelectPattern(selectPatternBuf,"SortBy@REL.[sequences] asc");

       return this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_USINGPROJECTGRADE,
               ApplicationSchemaConstants.BIZCLASS_PROJECTGRADE, GlobalConstants.FLAG_TYPE_TO, selectPatternBuf.toString(),                                   // selectPattern
               wherePatternBuf.toString(),             // wherePattern
               paramPatternBuf.toString(),             // parameterPattern
               false,                                  // bInclude
               false,                                  // bResultUnique
               0,                                      // objectLimit
               1);                                    // findDepth
   }
   
   public final List<ProjectGradeVO> getNotAppliedProjectGradeList() {
       StringBuffer wherePatternBuf = new StringBuffer();
       StringBuffer paramPatternBuf = new StringBuffer();
       StringBuffer selectPatternBuf = new StringBuffer();
       
       StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "!To[" + ApplicationSchemaConstants.RELCLASS_USINGPROJECTGRADE + "].From.names", GlobalConstants.OQL_OPERATOR_EQUAL, this.getNames());
       
       return ObjectRoot.findObjects(ApplicationSchemaConstants.BIZCLASS_PROJECTGRADE,  GlobalConstants.FLAG_TYPE_ALL,
               GlobalConstants.FLAG_TYPE_ALL,
               selectPatternBuf.toString(),
               wherePatternBuf.toString(),
               paramPatternBuf.toString(),
               false,
               0
       );
   }
   public List<ProjectLifeCycleVO> getProjectLfeCycleList(boolean includeInactive){
       StringBuffer wherePattern = new StringBuffer();
       StringBuffer parameterPattern = new StringBuffer();
       if(!includeInactive){
           StringUtil.constructWherePattern(wherePattern, parameterPattern, "@this.[states]", GlobalConstants.OQL_OPERATOR_EQUAL, ApplicationSchemaConstants.STATE_ACTIVE_INACTIVE_ACTIVE);
       }
       return this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_USINGPROJECTLIFECYCLE, ApplicationSchemaConstants.BIZCLASS_PROJECTLIFECYCLE, GlobalConstants.FLAG_TYPE_TO, 
               wherePattern.toString(), parameterPattern.toString(), 1);
   }
   
//   public String getBudgetUnitCode(String divisionCode, String year, List<CodeDetailVO> divisionBUList) {
//       String budgetUnitCode = "";
//
//       if ( NullUtil.isNone(year) ) {
//           year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
//       } 
//       
//       if ( NullUtil.isNone(divisionBUList) ) {
//           CodeDetail codeDetail = DomUtil.toDom(new CodeDetailVO());
//           CodeDetailVO codeDetailVO = codeDetail.getCodeDetail("DIVISION_MGTGROUP", divisionCode);
//           divisionBUList = new ArrayList<CodeDetailVO>();
//           if ( !NullUtil.isNull(codeDetailVO) ) {
//               divisionBUList.add(codeDetailVO);
//           }
//       }
//       
//       Set<String> budgetUnitStrSet = new HashSet<String>();
//       if ( !NullUtil.isNone(divisionBUList) && divisionBUList.size() > 0 ) {
//           for ( CodeDetailVO codeVO : divisionBUList ) {
//               if ( !StrUtil.isEmpty(codeVO.getAttribute01()) ) {
//                   ManagementGroupVO bu1VO = ManagementGroupList.getBudgetUnitVOByName(codeVO.getAttribute01(), year);
//                   if ( !NullUtil.isNull(bu1VO) ) budgetUnitStrSet.add(bu1VO.getNames());
//               }
//               if ( !StrUtil.isEmpty(codeVO.getAttribute02()) ) {
//                   ManagementGroupVO bu2VO = ManagementGroupList.getBudgetUnitVOByName(codeVO.getAttribute02(), year);
//                   if ( !NullUtil.isNull(bu2VO) ) budgetUnitStrSet.add(bu2VO.getNames());
//               }
//               if ( !StrUtil.isEmpty(codeVO.getAttribute03()) ) {
//                   ManagementGroupVO bu3VO = ManagementGroupList.getBudgetUnitVOByName(codeVO.getAttribute03(), year);
//                   if ( !NullUtil.isNull(bu3VO) ) budgetUnitStrSet.add(bu3VO.getNames());
//               }
//           }
//       }
//       
//       if ( budgetUnitStrSet.size() > 0 ) {
//           budgetUnitCode = StrUtil.convertSet2Str(budgetUnitStrSet);
//       }
//       return budgetUnitCode;
//   }
   public static List<DivisionUnitVO> findCommonDivisionForTemplateLibrary(){
       List<DivisionUnitVO> list = new ArrayList<DivisionUnitVO>();
       DivisionUnitVO divisionUnitVO = BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_DIVISIONUNIT,ProjectConstants.PROJECT_BASIS_COMMON_DIVISION);
       if(!NullUtil.isNull(divisionUnitVO)) list.add(divisionUnitVO);
       return list;
   }
   public static Set<String> getVCBusinessUnitDivisionCodeSet(){
       StringBuffer selectPatternBuf = new StringBuffer();
       StringBuffer wherePatternBuf = new StringBuffer();
       StringBuffer paramPatternBuf = new StringBuffer();
   
       StringUtil.addSortByPattern(selectPatternBuf, "@this.[names]");
       StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[businessUnitCode]",     GlobalConstants.OQL_OPERATOR_EQUAL, "VC");
       StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[states]",               GlobalConstants.OQL_OPERATOR_EQUAL, ApplicationSchemaConstants.STATE_ACTIVE_INACTIVE_ACTIVE);
   
       List<DivisionUnitVO> DivisionUnitList = ObjectRoot.findObjects(ApplicationSchemaConstants.BIZCLASS_DIVISIONUNIT,
                                                                      selectPatternBuf.toString(),
                                                                      wherePatternBuf.toString(),
                                                                      paramPatternBuf.toString() );
       Set<String> vcDivisionInSet =  new HashSet<String>();
       for(DivisionUnitVO vo : DivisionUnitList){
           vcDivisionInSet.add(vo.getNames());
           vcDivisionInSet.add(vo.getDivisionCode());
       }
       return vcDivisionInSet;
   }
}

