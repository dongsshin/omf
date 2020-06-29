/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : WBSActivities.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.dom;


import java.util.List;
import java.util.Map;

import com.rap.omc.api.object.model.BusinessObjectRootVO;
import com.rap.omc.api.util.omc.ThreadLocalUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.schema.util.OmcSystemConstants;

import rap.api.object.project.model.WBSActivitiesVO;
import rap.api.object.project.model.WBSJobActivityVO;
import rap.application.constants.ApplicationSchemaConstants;
import rap.application.constants.ProjectConstants;


public class WBSActivities extends WBSItems {
    public WBSActivities(String obid){
        super(obid);
    }
    public WBSActivities(WBSActivitiesVO vo){
        super(vo);
    }
    @Override
    public WBSActivitiesVO getVo(){
        return (WBSActivitiesVO)super.getVo();
    }
    @Override
    public void initialize(){
        super.initialize();
        initializeWBSActivities();
    }
    public void initializeWBSActivities(){
    /*code here*/
    }
    @Override
    public String toString() {
        return "WBSActivities[toString()=" + super.toString() + "]";
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

   @Override
    protected void validateForRevise(Map<String, Object> map){
        super.validateForRevise(map);
        /*code below*/

    }

   @Override
    protected void preProcessForRevise(Map<String, Object> map){
        super.preProcessForRevise(map);
        /*code below*/

    }

   @Override
    protected void postProcessForRevise(Map<String, Object> map){
        super.postProcessForRevise(map);
        /*code below*/

    }
   @Override
   public void completeWBSActivity(BusinessObjectRootVO inboxTaskVO){
       this.completeWBSActivity(inboxTaskVO,false);
   }
   @Override
   public void completeWBSActivity(BusinessObjectRootVO inboxTaskVO, boolean skipWarning){
       super.completeWBSActivity(inboxTaskVO,skipWarning);
   }
   /**
    * 
    * @param generateName
    * @see lge.plm.api.object.project.dom.WBSItems#setDefault(boolean)
    */
   @Override
   public void setDefault(boolean generateName){
       super.setDefault(generateName);
       getVo().setLifeCycle(ApplicationSchemaConstants.LIFECYCLE_WBS_ACTIVITY);
   }
   public List<WBSJobActivityVO> getJobActivity(){
       StringBuffer selectPatternBuf = new StringBuffer();
       StringBuffer wherePatternBuf = new StringBuffer();
       StringBuffer paramPatternBuf = new StringBuffer();
       List<WBSJobActivityVO> relatedObjects = getRelatedObjects(ApplicationSchemaConstants.RELCLASS_ALLOCATEDTOMEMBER, 
                                                                  ApplicationSchemaConstants.BIZCLASS_WBSJOBACTIVITY, 
                                                                  GlobalConstants.FLAG_TYPE_TO, selectPatternBuf.toString(),
                                                                  wherePatternBuf.toString(), paramPatternBuf.toString(), 
                                                                  false, true, 0, 1);
       String locale = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.locale, OmcSystemConstants.OMC_LOCALE_LANG_EN);
       for(WBSJobActivityVO vo: relatedObjects){
           vo.setOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_ACTIVITY_NAME_MAP_ATTR, new WBSJobActivity(vo).getCommonTitlesForDisplay(locale));
       }
       return relatedObjects;
   }
   
}

