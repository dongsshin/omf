/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectRole.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.dom;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.rap.omc.api.object.dom.BusinessObjectMaster;
import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.api.oql.model.OmcOQLCondition;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.api.util.omc.ThreadLocalUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;

import rap.api.object.project.model.ActivityTemplateMasterVO;
import rap.api.object.project.model.ProjectDefinedRoleVO;
import rap.api.object.project.model.ProjectRoleVO;
import rap.api.object.project.model.WBSItemTemplatesVO;
import rap.application.constants.ApplicationBizConstants;
import rap.application.constants.ApplicationSchemaConstants;


public class ProjectRole extends BusinessObjectMaster {
    public ProjectRole(String obid){
        super(obid);
    }
    public ProjectRole(ProjectRoleVO vo){
        super(vo);
    }
     @Override
    public ProjectRoleVO getVo(){
        return (ProjectRoleVO)super.getVo();
    }
     @Override
    public void initialize(){
        super.initialize();
        initializeProjectRole();
    }
    public void initializeProjectRole(){
    /*code here*/
    }
     @Override
    public String toString() {
        return "ProjectRole[toString()=" + super.toString() + "]";
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
    public List<ActivityTemplateMasterVO> getActivityTemplateMaster(){
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
       
        return  getRelatedObjects(ApplicationSchemaConstants.RELCLASS_ALLOCATEDROLEATACTIVITYTEMPLATE, 
                                 ApplicationSchemaConstants.BIZCLASS_ACTIVITYTEMPLATEMASTER, GlobalConstants.FLAG_TYPE_TO, 
                                 "", wherePatternBuf.toString(), paramPatternBuf.toString(), false, true, 0, 1);
    }

    public List<WBSItemTemplatesVO> getWBSItemTemplates(){
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        
        return  getRelatedObjects(ApplicationSchemaConstants.RELCLASS_ALLOCATEDROLEATACTIVITYTEMPLATE, 
                ApplicationSchemaConstants.BIZCLASS_ACTIVITYTEMPLATEMASTER, GlobalConstants.FLAG_TYPE_TO, 
                "", wherePatternBuf.toString(), paramPatternBuf.toString(), false, true, 0, 1);
    }
    
    public static List<ProjectRoleVO> retrieveProjectRole(ProjectRoleVO searchInfo, boolean isPaging, boolean activeOnly){
        List<ProjectRoleVO> result = null;
        
        StringBuffer selectPatternBuf = new StringBuffer();
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        
        StringUtil.addSortByPattern(selectPatternBuf, "@this.[sequences], @this.[names]");
        if(StrUtil.isNotEmpty(searchInfo.getNames())){
            List<OmcOQLCondition> conditionList = new ArrayList<OmcOQLCondition>();
            conditionList.add(new OmcOQLCondition("UPPER(@this.[roleNameEng])",GlobalConstants.FLAG_TYPE_ALL + searchInfo.getNames().toUpperCase() + GlobalConstants.FLAG_TYPE_ALL,GlobalConstants.OQL_OPERATOR_LIKE));
            conditionList.add(new OmcOQLCondition("UPPER(@this.[roleNameKor])",GlobalConstants.FLAG_TYPE_ALL + searchInfo.getNames().toUpperCase() + GlobalConstants.FLAG_TYPE_ALL,GlobalConstants.OQL_OPERATOR_LIKE));
            conditionList.add(new OmcOQLCondition("UPPER(@this.[roleNameChi])",GlobalConstants.FLAG_TYPE_ALL + searchInfo.getNames().toUpperCase() + GlobalConstants.FLAG_TYPE_ALL,GlobalConstants.OQL_OPERATOR_LIKE));
            StringUtil.constructOrWherePattern(wherePatternBuf, paramPatternBuf, conditionList);
        }
        
        if(StrUtil.isNotEmpty(searchInfo.getRoleType()) && !ApplicationBizConstants.ATTRIBUTE_RANGE_All.equals(searchInfo.getRoleType())){
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[roleType]", GlobalConstants.OQL_OPERATOR_EQUAL, searchInfo.getRoleType());
        }
        if(activeOnly) StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[states]", GlobalConstants.OQL_OPERATOR_EQUAL, ApplicationSchemaConstants.STATE_ACTIVE_INACTIVE_ACTIVE);

        if(isPaging){
            result = findObjectPagingList(ApplicationSchemaConstants.BIZCLASS_PROJECTROLE,
                    GlobalConstants.FLAG_TYPE_ALL, 
                    GlobalConstants.FLAG_TYPE_ALL, 
                    selectPatternBuf.toString(),
                    wherePatternBuf.toString(),
                    paramPatternBuf.toString(),
                    false,
                    searchInfo);
        }else{
            result = findObjects(ApplicationSchemaConstants.BIZCLASS_PROJECTROLE, 
                    GlobalConstants.FLAG_TYPE_ALL, 
                    GlobalConstants.FLAG_TYPE_ALL, 
                    selectPatternBuf.toString(), 
                    wherePatternBuf.toString(), 
                    paramPatternBuf.toString(), 
                    false, 0);
        }
        return result;
    }
    
    public static final ProjectRole getProjectRole(String roleCode){
        if(NullUtil.isNull(roleCode) ) { return null; };
        
        ProjectRoleVO projectRoleVO = ObjectRoot.findObject(ApplicationSchemaConstants.BIZCLASS_PROJECTROLE, roleCode, false);
        if(!NullUtil.isNull(projectRoleVO) ){ return DomUtil.toDom(projectRoleVO); }
        return null;
    }
    
    public static final ProjectRoleVO getProjectRoleVO(String roleCode){
        if(NullUtil.isNull(roleCode) ) { return null; };
        return ObjectRoot.findObject(ApplicationSchemaConstants.BIZCLASS_PROJECTROLE, roleCode, false);
    }
    
    public static final List<ProjectRoleVO> getProjectRoleVOList(String roleCodeList){
        return findObjects(ApplicationSchemaConstants.BIZCLASS_PROJECTROLE, roleCodeList );
    }
    
    public static final List<ProjectRoleVO> getProjectRoleVOList(List<String> nameList){
        return BusinessObjectMaster.getBusinessObjectMasters(ApplicationSchemaConstants.BIZCLASS_PROJECTROLE, nameList);
    }

    public static final List<ProjectRoleVO> getProjectRoleVOListAsDefinedRole(List<ProjectDefinedRoleVO> ProjectDefinedRoleVOList){
        
        List<String> roleCodeNameList = new ArrayList<String>();
        for(ProjectDefinedRoleVO projectDefinedRoleVO : ProjectDefinedRoleVOList){
            roleCodeNameList.add(projectDefinedRoleVO.getRoleCode());
        }
        
        if(roleCodeNameList.isEmpty()){ return new ArrayList<ProjectRoleVO>();}
        
        return BusinessObjectMaster.getBusinessObjectMasters(ApplicationSchemaConstants.BIZCLASS_PROJECTROLE, roleCodeNameList);
    }
    
    public String convertNameListToSave(){
        StringBuffer strBuf = new StringBuffer();
        strBuf.append(getVo().getRoleNameEng()).append("^+^").append(getVo().getRoleNameKor()).append("^+^").append(getVo().getRoleNameChi());
        return strBuf.toString();
    }
    
    public String getDisplayNameAsLocale(){
        String displayName = "";
        String locale = ThreadLocalUtil.getLocale();
        if(locale.equals(OmcSystemConstants.OMC_LOCALE_LANG_KO)){
            displayName = getVo().getRoleNameKor();
        }else if(locale.equals(OmcSystemConstants.OMC_LOCALE_LANG_CH)){
            displayName = getVo().getRoleNameChi();
        }else{
            displayName = getVo().getRoleNameKor();
        }
        return displayName;
    }
}

