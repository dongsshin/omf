/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ActivityTemplateMaster.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.dom;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rap.omc.api.object.dom.BusinessObjectMaster;
import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import com.rap.omc.api.oql.model.OmcOQLCondition;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.framework.exception.ApplicationException;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;

import rap.api.object.organization.dom.DivisionUnit;
import rap.api.object.organization.model.DivisionUnitVO;
import rap.api.object.organization.model.OrganizationsVO;
import rap.api.object.project.model.ActivityTemplateMasterVO;
import rap.api.object.project.model.ProjectRoleVO;
import rap.api.object.relation.dom.AllocatedRoleAtActivityTemplate;
import rap.application.constants.ApplicationBizConstants;
import rap.application.constants.ApplicationSchemaConstants;
import rap.application.constants.ProjectConstants;
import rap.application.workflow.model.ActivityMethodDescVO;
import rap.application.workflow.util.WBSUtil;


public class ActivityTemplateMaster extends BusinessObjectMaster {
    public ActivityTemplateMaster(String obid){
        super(obid);
    }
    public ActivityTemplateMaster(ActivityTemplateMasterVO vo){
        super(vo);
    }
     @Override
    public ActivityTemplateMasterVO getVo(){
        return (ActivityTemplateMasterVO)super.getVo();
    }
     @Override
    public void initialize(){
        super.initialize();
        initializeActivityTemplateMaster();
    }
    public void initializeActivityTemplateMaster(){
    /*code here*/
    }
     @Override
    public String toString() {
        return "ActivityTemplateMaster[toString()=" + super.toString() + "]";
    }


     @Override
    protected void validateForChange(String newClassName, String newName, String newLifeCycle, String newStates, Map<String, Object> map){
        super.validateForChange(newClassName, newName, newLifeCycle, newStates, map);
        /*code below*/
        if(!canBeModified()) throw new FoundationException("Cannot change. current state is" + this.getStates());
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
        if(!canBeModified()) throw new FoundationException("Cannot change. current state is" + this.getStates());
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
        if(!canBeModified()) throw new FoundationException("Cannot change. current state is" + this.getStates());
        resetActivityNameForEmpty();
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
    private void resetActivityNameForEmpty(){
        if(StrUtil.isEmpty(this.getVo().getActivityNameKor())){
            if(!StrUtil.isEmpty(this.getVo().getActivityNameEng())){
                this.getVo().setActivityNameChi(this.getVo().getActivityNameEng());
            }
        }
        if(StrUtil.isEmpty(this.getVo().getActivityNameEng())){
            if(!StrUtil.isEmpty(this.getVo().getActivityNameKor())){
                this.getVo().setActivityNameChi(this.getVo().getActivityNameKor());
            }
        }
        if(StrUtil.isEmpty(this.getVo().getActivityNameChi())){
            if(!StrUtil.isEmpty(this.getVo().getActivityNameEng())){
                this.getVo().setActivityNameChi(this.getVo().getActivityNameEng());
            }else{
                this.getVo().setActivityNameChi(this.getVo().getActivityNameKor());
            }
        }
        if(StrUtil.isEmpty(this.getVo().getActivityNameKor()) || StrUtil.isEmpty(this.getVo().getActivityNameEng()) || StrUtil.isEmpty(this.getVo().getActivityNameChi())){
            throw new ApplicationException("Activity Name is Empty!!!");
        }
    }
    public List<ProjectRoleVO> retrieveUsedRoleAtWBSTemplate(){
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        
        return  getRelatedObjects(ApplicationSchemaConstants.RELCLASS_USEDROLEATWBSTEMPLATE, 
                                  ApplicationSchemaConstants.BIZCLASS_PROJECTROLE, GlobalConstants.FLAG_TYPE_TO, 
                                  "", wherePatternBuf.toString(), paramPatternBuf.toString(), false, true, 0, 1);
    }
    
    public static List<ActivityTemplateMasterVO> retrieveActivityTemplateMasterList(String divisionUnit){
    	ActivityTemplateMasterVO searchInfo = new ActivityTemplateMasterVO();
        return retrieveActivityTemplateMasterList(searchInfo,divisionUnit,false);
    }
    public static List<ActivityTemplateMasterVO> retrieveActivityTemplateMasterList(ActivityTemplateMasterVO searchInfo, String divisionUnit, boolean isPaging){
        List<ActivityTemplateMasterVO> result = null;
        StringBuffer selectPatternBuf = new StringBuffer();
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        
        if(!NullUtil.isNone(searchInfo.getNames())){
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "UPPER(@this.[names])", GlobalConstants.OQL_OPERATOR_LIKE, GlobalConstants.FLAG_TYPE_ALL + searchInfo.getNames().toUpperCase() + GlobalConstants.FLAG_TYPE_ALL);
        }

        if(!NullUtil.isNone(searchInfo.getDefaultRoleList()) && !ProjectConstants.NONE.equals(searchInfo.getDefaultRoleList())){
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "UPPER(@this.[defaultRoleList])", GlobalConstants.OQL_OPERATOR_LIKE, GlobalConstants.FLAG_TYPE_ALL + searchInfo.getDefaultRoleList().toUpperCase() + GlobalConstants.FLAG_TYPE_ALL);
        }
        
        if(!NullUtil.isNone(searchInfo.getTitles())){
            List<OmcOQLCondition> conditionList = new ArrayList<OmcOQLCondition>();
            conditionList.add(new OmcOQLCondition("UPPER(@this.[activityNameEng])",GlobalConstants.FLAG_TYPE_ALL + searchInfo.getTitles().toUpperCase() + GlobalConstants.FLAG_TYPE_ALL,GlobalConstants.OQL_OPERATOR_LIKE));
            conditionList.add(new OmcOQLCondition("UPPER(@this.[activityNameKor])",GlobalConstants.FLAG_TYPE_ALL + searchInfo.getTitles().toUpperCase() + GlobalConstants.FLAG_TYPE_ALL,GlobalConstants.OQL_OPERATOR_LIKE));
            conditionList.add(new OmcOQLCondition("UPPER(@this.[activityNameChi])",GlobalConstants.FLAG_TYPE_ALL + searchInfo.getTitles().toUpperCase() + GlobalConstants.FLAG_TYPE_ALL,GlobalConstants.OQL_OPERATOR_LIKE));
            StringUtil.constructOrWherePattern(wherePatternBuf, paramPatternBuf, conditionList);
        }
        
        if(!NullUtil.isNone(divisionUnit)){
            DivisionUnitVO divisionUnitVO = DivisionUnit.getVOByName(divisionUnit);
            List<OmcOQLCondition> conditionList = new ArrayList<OmcOQLCondition>();
            conditionList.add(new OmcOQLCondition("@this.[appliedOrganizationList]", "*^"+ ApplicationBizConstants.COMPANY_LGE +"^*", GlobalConstants.OQL_OPERATOR_LIKE));
            conditionList.add(new OmcOQLCondition("@this.[appliedOrganizationList]", "*^"+ divisionUnitVO.getNames()+"^*", GlobalConstants.OQL_OPERATOR_LIKE));
            conditionList.add(new OmcOQLCondition("@this.[appliedOrganizationList]", "*^"+ divisionUnitVO.getBusinessUnitCode()+"^*", GlobalConstants.OQL_OPERATOR_LIKE));
            StringUtil.constructOrWherePattern(wherePatternBuf, paramPatternBuf, conditionList);
        }
        
        if(!NullUtil.isNone(searchInfo.getStates()) && searchInfo.getStates().equals(ApplicationSchemaConstants.STATE_ACTIVE_INACTIVE_ACTIVE)){
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[states]", GlobalConstants.OQL_OPERATOR_EQUAL, searchInfo.getStates() );
        }
        
        if(isPaging){
            result = findObjectPagingList(ApplicationSchemaConstants.BIZCLASS_ACTIVITYTEMPLATEMASTER,
                                          GlobalConstants.FLAG_TYPE_ALL, 
                                          GlobalConstants.FLAG_TYPE_ALL, 
                                          selectPatternBuf.toString(),
                                          wherePatternBuf.toString(),
                                          paramPatternBuf.toString(),
                                          false,
                                          searchInfo);
        }else{
            result = findObjects(ApplicationSchemaConstants.BIZCLASS_ACTIVITYTEMPLATEMASTER, 
                    GlobalConstants.FLAG_TYPE_ALL, 
                    GlobalConstants.FLAG_TYPE_ALL, 
                    selectPatternBuf.toString(), 
                    wherePatternBuf.toString(), 
                    paramPatternBuf.toString(), 
                    false, 0);
        }
        Map<String,ActivityMethodDescVO> methodMap = new HashMap<String,ActivityMethodDescVO>();
        
        List<ActivityMethodDescVO> list = WBSUtil.getActivityStandardMethodAllList();
        
        for(ActivityMethodDescVO vo : list){
            methodMap.put(vo.getMethod(), vo);
        }
        for(ActivityTemplateMasterVO vo: result){
            ActivityMethodDescVO methodVo = methodMap.get(vo.getCompleteValidationMethod());
            if(!NullUtil.isNull(methodVo)) {
                vo.setOutDataAttributeValue("completeValidationMethodDesc", methodVo.getMethodDesc());
            }else{
                vo.setOutDataAttributeValue("completeValidationMethodDesc", "None");
            }
            methodVo = methodMap.get(vo.getPostExecutionMethod());
            if(!NullUtil.isNull(methodVo)) {
                vo.setOutDataAttributeValue("postExecutionMethodDesc", methodVo.getMethodDesc());
            }else{
                vo.setOutDataAttributeValue("postExecutionMethodDesc", "None");
            }
            methodVo = methodMap.get(vo.getStartExecutionMethod());
            if(!NullUtil.isNull(methodVo)) {
                vo.setOutDataAttributeValue("startExecutionMethodDesc", methodVo.getMethodDesc());
            }else{
                vo.setOutDataAttributeValue("startExecutionMethodDesc", "None");
            }
            methodVo = methodMap.get(vo.getStartValidationMethod());
            if(!NullUtil.isNull(methodVo)) {
                vo.setOutDataAttributeValue("startValidationMethodDesc", methodVo.getMethodDesc());
            }else{
                vo.setOutDataAttributeValue("startExecutionMethodDesc", "None");
            }
        }
        
        
        
        return result;
    }
    
    public List<ProjectRoleVO> retrieveAllocatedRoleATActivityTemplateItems(){
        StringBuffer selectPatternBuf = new StringBuffer();
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        return getRelatedObjects(ApplicationSchemaConstants.RELCLASS_ALLOCATEDROLEATACTIVITYTEMPLATE, 
                                 ApplicationSchemaConstants.BIZCLASS_PROJECTROLE, 
                                 GlobalConstants.FLAG_TYPE_FROM, selectPatternBuf.toString(),
                                 wherePatternBuf.toString(), paramPatternBuf.toString(), 
                                 false, true, 0, 1);
    }
    
    public void updateProjectRole(String defaultRoleNameList){
        
        Set<String> projectRoleNameList =  new HashSet<String>();
        if(StrUtil.isNotEmpty(defaultRoleNameList)){
            projectRoleNameList = StrUtil.convertArrayToSet(defaultRoleNameList.split(","));
        }
        List<ProjectRoleVO> allocatedRoleVOList = retrieveAllocatedRoleATActivityTemplateItems();
        for(ProjectRoleVO allocatedProjectRoleVO : allocatedRoleVOList){
            boolean isExists = false;
            for(String roleName : projectRoleNameList){
                if(allocatedProjectRoleVO.getObid().equals(roleName)){
                    projectRoleNameList.remove(roleName);
                    isExists = true;
                    break;
                }
            }
            if(!isExists){
                new AllocatedRoleAtActivityTemplate((String)allocatedProjectRoleVO.getOutData().get("rel_obid")).deleteObject();
            }
        }
        
        for(String roleCode : projectRoleNameList){
            addFromObject(ApplicationSchemaConstants.RELCLASS_ALLOCATEDROLEATACTIVITYTEMPLATE, ProjectRole.getProjectRoleVO(roleCode), null);
        }
    }
    
    public final List<BusinessRelationObjectVO> retrieveRelatedObjectList() {
        String filter = null;
        if("Common".equals(getVo().getAppliedScope())) {
            filter = ApplicationSchemaConstants.BIZCLASS_COMPANY;
        }else if("Business Unit".equals(getVo().getAppliedScope())) {
            filter = ApplicationSchemaConstants.BIZCLASS_BUSINESSUNIT;
        }else if("Divison Unit".equals(getVo().getAppliedScope())) {
            filter = ApplicationSchemaConstants.BIZCLASS_DIVISIONUNIT;
        }
        return this.getRelationship( ApplicationSchemaConstants.RELCLASS_ACTIVITYAPPLIEDORGANIZATION, filter, GlobalConstants.FLAG_TYPE_TO);
    }
    public final boolean canBeRevisible() {
        if(this.getStates().equals(ApplicationSchemaConstants.STATE_ACTIVITY_TEMPLATE_MASTER_ACTIVE)) return true;
        return false;
    }
    public final List<OrganizationsVO> retrieveAppliedOrganizationList() {
        
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        StringBuffer selectPatternBuf = new StringBuffer();
        
        StringUtil.constructSelectPattern(selectPatternBuf,"SortBy@REL.[created] asc");
        String filterPattern = null;
        if("Division Unit".equals(this.getVo().getAppliedScope())) {
            filterPattern = ApplicationSchemaConstants.BIZCLASS_DIVISIONUNIT;
        }else if("Business Unit".equals(getVo().getAppliedScope())) {
            filterPattern = ApplicationSchemaConstants.BIZCLASS_BUSINESSUNIT;
        }else if("Common".equals(getVo().getAppliedScope())) {
            filterPattern = ApplicationSchemaConstants.BIZCLASS_COMPANY;
        }

        return this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_ACTIVITYAPPLIEDORGANIZATION,
                filterPattern, GlobalConstants.FLAG_TYPE_TO, selectPatternBuf.toString(),                                   // selectPattern
                wherePatternBuf.toString(),             // wherePattern
                paramPatternBuf.toString(),             // parameterPattern
                false,                                  // bInclude
                false,                                  // bResultUnique
                0,                                      // objectLimit
                1);                                    // findDepth
    }
    private boolean canBeModified(){
        ActivityTemplateMaster activityTemplateMasterDom = new ActivityTemplateMaster(this.getObid());
        //if (activityTemplateMasterDom.getStates().equals(ApplicationSchemaConstants.STATE_ACTIVITY_TEMPLATE_MASTER_WORKING)) return true;
        return true;
    }
}

