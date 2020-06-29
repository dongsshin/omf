/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : WBSJobActivity.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.dom;


import java.util.List;
import java.util.Map;

import com.rap.omc.api.object.dom.BusinessObjectMaster;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.general.NameGeneratorUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;

import rap.api.object.organization.dom.Users;
import rap.api.object.organization.model.UsersVO;
import rap.api.object.project.model.ProjectScheduleVO;
import rap.api.object.project.model.WBSActivitiesVO;
import rap.api.object.project.model.WBSItemTemplatesVO;
import rap.api.object.project.model.WBSJobActivityLogVO;
import rap.api.object.project.model.WBSJobActivityVO;
import rap.api.object.relation.dom.AssignedProjectPerson;
import rap.api.object.relation.model.AllocatedToMemberVO;
import rap.application.constants.ApplicationBizConstants;
import rap.application.constants.ApplicationSchemaConstants;


public class WBSJobActivity extends BusinessObjectMaster {
    public WBSJobActivity(String obid){
        super(obid);
    }
    public WBSJobActivity(WBSJobActivityVO vo){
        super(vo);
    }
     @Override
    public WBSJobActivityVO getVo(){
        return (WBSJobActivityVO)super.getVo();
    }
     @Override
    public void initialize(){
        super.initialize();
        initializeWBSJobActivity();
    }
    public void initializeWBSJobActivity(){
    /*code here*/
    }
     @Override
    public String toString() {
        return "WBSJobActivity[toString()=" + super.toString() + "]";
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
        WBSActivities wbsActivities = new WBSActivities((String)map.get("activityObid"));
        addFromObject(ApplicationSchemaConstants.RELCLASS_ALLOCATEDTOMEMBER, wbsActivities.getVo(), null);
        addToObject(ApplicationSchemaConstants.RELCLASS_ASSIGNEDPROJECTPERSON, Users.getUsers(getVo().getUserId()).getVo(), null);
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
        UsersVO assignedProjectPerson = getAssignedProjectPerson();
        boolean assignProjectPerson = false;
        if(!NullUtil.isNone(getVo().getUserId())){assignProjectPerson = true;}
        if(!NullUtil.isNull(assignedProjectPerson)){
            if(!assignedProjectPerson.getNames().equals(getVo().getUserId())){
                new AssignedProjectPerson((String)assignedProjectPerson.getOutData().get("rel_obid")).deleteObject();
            }else{
                assignProjectPerson = false;
            }
        }
        if(assignProjectPerson){
            addToObject(ApplicationSchemaConstants.RELCLASS_ASSIGNEDPROJECTPERSON, Users.getUsers(getVo().getUserId()).getVo(), null);
        }
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
     * 
     * @param locale
     * @return
     * @see omc.api.object.dom.BusinessObjectRoot#getCommonTitlesForDisplay(java.lang.String)
     */
    @Override
    public String getCommonTitlesForDisplay(String locale){
        String displayedTitles = "";
        if(StrUtil.isEmpty(locale)) locale = ApplicationBizConstants.DEFAULT_LANG;
        if(!StrUtil.in(locale, ApplicationBizConstants.LANG_VALID_SET)) locale = ApplicationBizConstants.DEFAULT_LANG;
        if(locale.equals(ApplicationBizConstants.LANG_EN)) displayedTitles = this.getVo().getActivityNameEng();
        if(locale.equals(ApplicationBizConstants.LANG_KO)) displayedTitles = this.getVo().getActivityNameKor();
        if(locale.equals(ApplicationBizConstants.LANG_CH)) displayedTitles = this.getVo().getActivityNameChi();
        return displayedTitles;
    }
    public void setDefault(boolean generateName){
        if(generateName){
            getVo().setNames(NameGeneratorUtil.generateUniqueName(GlobalConstants.IDGEN_IDKEY_WBS_JOB_ACTIVITY));
        }
        getVo().setTitles(getVo().getActivityNameEng());
        getVo().setLifeCycle(ApplicationSchemaConstants.LIFECYCLE_WBS_JOB_ACTIVITY);
        getVo().setStates(ApplicationSchemaConstants.STATE_WBS_JOB_ACTIVITY_ACTIVE);
    }
    public void updateProgress(String yearWeek, String descriptions, int progress){
        String logName =  getVo().getNames() + "-" + yearWeek;
        WBSJobActivityLogVO wbsJobActivityLogVO = BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_WBSJOBACTIVITYLOG, logName );
        
        if(NullUtil.isNull(wbsJobActivityLogVO)){
            WBSJobActivityLogVO logVO = new WBSJobActivityLogVO();
            logVO.setClassName(ApplicationSchemaConstants.BIZCLASS_WBSJOBACTIVITYLOG);
            logVO.setNames(logName);
            logVO.setTitles(getVo().getNames());
            logVO.setFromObid(getVo().getObid());
            logVO.setProgress(progress);
            new WBSJobActivityLog(logVO).createObject();
        }else{
            wbsJobActivityLogVO.setProgress(progress);
            new WBSJobActivityLog(wbsJobActivityLogVO).modifyObject();
        }
        getVo().setProgress(progress);
        getVo().setDescriptions(descriptions);
        modifyObject();
    }
    public final boolean isConnectedOtherContext(ProjectScheduleVO masterVO){
        return isConnectedOtherContext(masterVO.getObid());
    }
    public final boolean isConnectedOtherContext(String masterObid){
        
        StringBuffer wherePattern     = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();
        
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "From["+ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT + "].toObid",GlobalConstants.OQL_OPERATOR_NOT_EQUAL, masterObid);
        List<WBSItemTemplatesVO> list = this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_ALLOCATEDTOMEMBER, ApplicationSchemaConstants.BIZCLASS_WBSACTIVITIES, 
                GlobalConstants.FLAG_TYPE_FROM, wherePattern.toString(), parameterPattern.toString(), 1);
        if(NullUtil.isNone(list)) return false;
        return true;
    }
    public WBSActivitiesVO getWBSActivity(String masterObid){
        StringBuffer wherePattern     = new StringBuffer();     
        StringBuffer parameterPattern = new StringBuffer();
        
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "From["+ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT + "].toObid",GlobalConstants.OQL_OPERATOR_EQUAL, masterObid);
        List<WBSActivitiesVO> list = this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_ALLOCATEDTOMEMBER, ApplicationSchemaConstants.BIZCLASS_WBSACTIVITIES, 
                GlobalConstants.FLAG_TYPE_FROM, wherePattern.toString(), parameterPattern.toString(), 1);
        if(NullUtil.isNone(list)) return null;
        if(list.size() > 1) throw new FoundationException("Schedule context is invalid.");
        return  list.get(0);
    }
    public final boolean isConnectedOtherActivity(String activityObid){
        
        StringBuffer wherePattern     = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();
        
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "@this.[obid]",GlobalConstants.OQL_OPERATOR_NOT_EQUAL, activityObid);
        List<WBSItemTemplatesVO> list = this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_ALLOCATEDTOMEMBER, ApplicationSchemaConstants.BIZCLASS_WBSACTIVITIES, 
                GlobalConstants.FLAG_TYPE_FROM, wherePattern.toString(), parameterPattern.toString(), 1);
        if(NullUtil.isNone(list)) return false;
        return true;
    }
    public AllocatedToMemberVO getAllocatedToMember(String activityObid){
        
        StringBuffer selectPattern     = new StringBuffer();
        StringBuffer wherePattern     = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();
        
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "@this.[fromObid]",GlobalConstants.OQL_OPERATOR_EQUAL, activityObid);
        List<AllocatedToMemberVO> list = this.getRelationship(ApplicationSchemaConstants.RELCLASS_ALLOCATEDTOMEMBER, ApplicationSchemaConstants.BIZCLASS_WBSACTIVITIES, 
                GlobalConstants.FLAG_TYPE_FROM, selectPattern.toString(), wherePattern.toString(), parameterPattern.toString());
        if(NullUtil.isNone(list)) return null;
        if(list.size() > 1) throw new FoundationException("AllocatedToMember is invalid.");
        return  list.get(0);
    }
    public UsersVO getAssignedProjectPerson(){
        return getRelatedObject(ApplicationSchemaConstants.RELCLASS_ASSIGNEDPROJECTPERSON, ApplicationSchemaConstants.BIZCLASS_USERS, GlobalConstants.FLAG_TYPE_TO);
    }
}

