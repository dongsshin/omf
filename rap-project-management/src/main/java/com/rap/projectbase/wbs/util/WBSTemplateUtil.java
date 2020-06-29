/**
 * ===========================================
 * System Name : LGE PLM Project
 * Program ID : WBSTemplateUtil.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 7. 20.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.projectbase.wbs.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.api.object.model.BusinessObjectVO;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.api.util.omc.SortUtil;
import com.rap.omc.api.util.omc.ThreadLocalUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.api.util.spring.SpringFactoryUtil;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.projectbase.wbs.service.WBSTemplateService;
import com.rap.projectbase.wbs.util.model.AnychartTreeDataItemDataVO;
import com.rap.projectbase.wbs.util.model.DHTMLGanttActivityVO;
import com.rap.projectbase.wbs.util.model.DHTMLGanttTemplateActivityVO;
import com.rap.projectbase.wbs.util.model.DHTMLGanttWBSActivityVO;

import rap.api.object.project.model.ProjectScheduleVO;
import rap.api.object.project.model.ProjectsVO;
import rap.api.object.project.model.WBSItemTemplatesVO;
import rap.api.object.project.model.WBSItemsVO;
import rap.api.object.relation.model.TemplateDependencyVO;
import rap.application.constants.ApplicationSchemaConstants;
import rap.application.constants.ProjectConstants;
import rap.application.workflow.model.ActivityMethodDescVO;

/**
 * <pre>
 * Class : WBSTemplateUtil
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class WBSTemplateUtil {
    static final Logger LOGGER = LoggerFactory.getLogger(WBSTemplateUtil.class);
   
    public static enum GANTT_VIEWING_TYPE {
        planVsActual, baselineVsPlan, baselineVsActual
    }
    private WBSTemplateService wbsTemplateService;

    private static WBSTemplateUtil sInstance;
    
    private synchronized static WBSTemplateUtil getInstance(){
        if (sInstance == null) {
            sInstance = new WBSTemplateUtil();
            sInstance.wbsTemplateService    = (WBSTemplateService)SpringFactoryUtil.getBean("wbsTemplateService");
        }
        return sInstance;
    }
    public static List<ActivityMethodDescVO> getActivityStandardStartValidationMethodList(){
        return getActivityStandardMethodList("validateForStart");
    }
    public static List<ActivityMethodDescVO> getActivityStandardCompleteValidationMethodList(){
        return getActivityStandardMethodList("validateForComplete");
    }
    public static List<ActivityMethodDescVO> getActivityStandardStartMethodList(){
        return getActivityStandardMethodList("startProcessFor");
    }
    public static List<ActivityMethodDescVO> getActivityStandardPostMethodList(){
        return getActivityStandardMethodList("postProcessFor");
    }
    public static List<ActivityMethodDescVO> getActivityStandardMethodAllList(){
        List<ActivityMethodDescVO> allList = new ArrayList<ActivityMethodDescVO>();
        allList.addAll(getActivityStandardStartValidationMethodList());
        allList.addAll(getActivityStandardCompleteValidationMethodList());
        allList.addAll(getActivityStandardStartMethodList());
        allList.addAll(getActivityStandardPostMethodList());
        
        List<ActivityMethodDescVO> returnList = new ArrayList<ActivityMethodDescVO>();
        for(ActivityMethodDescVO vo: allList){
            boolean isExists = false;
            for(ActivityMethodDescVO subVo :returnList){
                if(vo.getMethod().equals(subVo.getMethod())) {isExists = true;break;}
            }
            if(!isExists){
                returnList.add(vo);
            }
        }
        return allList;
    }
    private static List<ActivityMethodDescVO> getActivityStandardMethodList(String methodPrefix){
        List<ActivityMethodDescVO> methodDescList = new ArrayList<ActivityMethodDescVO>();
        methodDescList.add(new ActivityMethodDescVO("None","None","None"));
        String className = "lge.plm.project.wbs.StandardSpecForActivity";
        Class<?> cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            //LOGGER.error(className + " load Error. Error Message : " + e.getMessage());
            e.printStackTrace();
        }
        Method[] methodList = cls.getDeclaredMethods();
        for(int i = 0; i < methodList.length; i++){
            if(methodList[i].getName().startsWith(methodPrefix)) {
                String methodDesc = StandardSpecForActivity.methodDescMap.get(methodList[i].getName());
                if(!StrUtil.isEmpty(methodDesc)) {
                    methodDescList.add(new ActivityMethodDescVO(className + "." + methodList[i].getName(),methodList[i].getName(),methodDesc));
                }
            }
        }
        return methodDescList;
    }
//    public static void deleteWBSItemTemplate(String wbsTemplateMasterObid, String obid){
//        WBSTemplateMaster wbsTemplateMasterDom = new WBSTemplateMaster(wbsTemplateMasterObid);
//        getInstance().wbsTemplateService.txnDeleteWBSItemTemplate(wbsTemplateMasterDom,obid);
//    }
    public static List<TemplateDependencyVO> getWBSTemplateDependency(List<WBSItemTemplatesVO> list){
        ObjectRoot dom = new ObjectRoot("");
        List<TemplateDependencyVO>  dd = dom.getRelationship();
        List<TemplateDependencyVO> dependencyList = ObjectRoot.getRelationshipSet(list, ApplicationSchemaConstants.RELCLASS_TEMPLATEDEPENDENCY, ApplicationSchemaConstants.BIZCLASS_WBSITEMTEMPLATES, GlobalConstants.FLAG_TYPE_TO, "", "", "");
        return dependencyList;
    }
    public static void setFirstAndLastFlag(List<WBSItemsVO> workWBSItemsList,boolean isInitial){
        SortUtil.sort(workWBSItemsList, "uniqueString", false);
        String savePhase = "initial";
        boolean isFirstSetted = false;
        int seq = 0;
        Set<Integer> firstSet = new HashSet<Integer>();
        Set<Integer> lastSet  = new HashSet<Integer>();
        if(isInitial){
            for(WBSItemsVO vo : workWBSItemsList){
                vo.setIsFirstActivity("N");
                vo.setIsLastActivity("N");
                if((vo.getClassName().equals(ApplicationSchemaConstants.BIZCLASS_WBSPHASES)||vo.getClassName().equals(ApplicationSchemaConstants.BIZCLASS_WBSSUBPROJECTS)) 
                        && !vo.getActivityNameEng().equals(savePhase)){
                    isFirstSetted = false;
                    if(seq > 0) lastSet.add(seq-1);
                }
                if(!isFirstSetted && StrUtil.isEmpty((String)vo.getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_ACTIVITY_ISCHILD_MAP_ATTR))){
                    firstSet.add(seq);isFirstSetted = true;
                }
                seq++;
                savePhase = vo.getActivityNameEng();
            }
            for(Integer seq1 : firstSet){
                workWBSItemsList.get(seq1).setIsFirstActivity("Y");
            }
            if(seq > 0){lastSet.add(seq -1);}
            for(Integer seq1 : lastSet){
                workWBSItemsList.get(seq1).setIsLastActivity("Y");
            }
        }else{
            for(WBSItemsVO vo : workWBSItemsList){
                vo.setIsFirstActivity("N");
                vo.setIsLastActivity("N");
                if((vo.getClassName().equals(ApplicationSchemaConstants.BIZCLASS_WBSPHASES)||vo.getClassName().equals(ApplicationSchemaConstants.BIZCLASS_WBSSUBPROJECTS)) 
                        && !vo.getPhaseName().equals(savePhase)){
                    isFirstSetted = false;
                    if(seq > 0) lastSet.add(seq-1);
                }
                if(!isFirstSetted && StrUtil.isEmpty((String)vo.getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_ACTIVITY_ISCHILD_MAP_ATTR))){
                    firstSet.add(seq);isFirstSetted = true;
                }
                seq++;
                savePhase = vo.getPhaseName();
            }
            for(Integer seq1 : firstSet){
                workWBSItemsList.get(seq1).setIsFirstActivity("Y");
            }
            if(seq > 0){lastSet.add(seq -1);}
            for(Integer seq1 : lastSet){
                workWBSItemsList.get(seq1).setIsLastActivity("Y");
            }   
        }
    }
    public static List<WBSItemTemplatesVO> getIndependentActivityList(List<WBSItemTemplatesVO> list, List<TemplateDependencyVO> dependencyList, List<DHTMLGanttTemplateActivityVO> calculatedList){
        List<WBSItemTemplatesVO> rslt = new ArrayList<WBSItemTemplatesVO>();
        for(WBSItemTemplatesVO templateVO : list){
            boolean isExists  = false;
            for(TemplateDependencyVO dependencyVO : dependencyList){
                if(dependencyVO.getFromObid().equals(templateVO.getObid())){
                    if(!isAlreadyCalculated(dependencyVO.getToObid(), calculatedList)){
                        isExists = true; break;
                    }
                }
            }
            if(!isExists) {
                boolean canBeAdd = true;
                for(DHTMLGanttActivityVO vo : calculatedList){
                    if(vo.getId().equals(templateVO.getObid())) {canBeAdd = false; break;}
                }
                if(canBeAdd) rslt.add((WBSItemTemplatesVO)DomUtil.copy(templateVO));
            }
        }
        return rslt;
    }
    private static boolean isAlreadyCalculated(String toObid, List<DHTMLGanttTemplateActivityVO> calculatedList){
        for(DHTMLGanttTemplateActivityVO templateVO : calculatedList){
            if(toObid.equals(templateVO.getId())){
                return true;
            }
        }
        return false;
    }
    public static List<WBSItemTemplatesVO> getTopActivityList(List<WBSItemTemplatesVO> list){
        List<WBSItemTemplatesVO> rslt = new ArrayList<WBSItemTemplatesVO>();
        for(WBSItemTemplatesVO vo : list){
            if(vo.getExplodedDepth() == 1){
                rslt.add((WBSItemTemplatesVO)DomUtil.copy(vo));
            }
        }
        return rslt;
    }
    public static List<AnychartTreeDataItemDataVO> convertActivityToAnychartItemList(Date startBaseDate, List<WBSItemTemplatesVO> list){
        List<AnychartTreeDataItemDataVO> anychartTreeDataItemList = new ArrayList<AnychartTreeDataItemDataVO>();
        for(WBSItemTemplatesVO vo : list){
            AnychartTreeDataItemDataVO anychartTreeDataItem = convertToAnychartTreeDataItemVO(startBaseDate,vo);
            anychartTreeDataItemList.add(anychartTreeDataItem);
        }
        return anychartTreeDataItemList;
    }
    public static List<WBSItemTemplatesVO> getChildActivityList(List<WBSItemTemplatesVO> list,WBSItemTemplatesVO activityVO){
        List<WBSItemTemplatesVO> rslt = new ArrayList<WBSItemTemplatesVO>();
        for(WBSItemTemplatesVO vo : list){
            if(activityVO.getUniqueString().equals(vo.getUniqueStringParent())){
                rslt.add((WBSItemTemplatesVO)DomUtil.copy(vo));
            }
        }
        return rslt;
    }

    public static DHTMLGanttTemplateActivityVO convertToDHTMLGanttActivityVO(WBSItemTemplatesVO templateVO,
                                                                             List<WBSItemTemplatesVO> list,
                                                                             Date startDate, 
                                                                             Date endDate,
                                                                             Integer duration,
                                                                             boolean isOpen){
        DHTMLGanttTemplateActivityVO activityVO = new DHTMLGanttTemplateActivityVO(templateVO);
        activityVO.setId(templateVO.getObid());
        
        String locale = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.locale, OmcSystemConstants.OMC_LOCALE_LANG_EN);
        
        if(locale.equals(OmcSystemConstants.OMC_LOCALE_LANG_KO)){
            activityVO.setText(templateVO.getActivityNameKor());
        }else if(locale.equals(OmcSystemConstants.OMC_LOCALE_LANG_CH)){
            activityVO.setText(templateVO.getActivityNameChi());
        }else{
            activityVO.setText(templateVO.getActivityNameEng());
        }
        if(StrUtil.isEmpty(activityVO.getText())) activityVO.setText(templateVO.getActivityNameEng());

        activityVO.setOrder((int)templateVO.getOutDataAttributeValue(ProjectConstants.GANTT_SORT_ORDER_KEY));
        activityVO.setDuration(duration);
        activityVO.setEndDate(endDate);
        activityVO.setStartDate(startDate);
        activityVO.setOpen(isOpen);
        activityVO.setType(getDHTMLType(templateVO,list));
        activityVO.setProgress(0f);
        activityVO.setPlannedEndDate(endDate);
        activityVO.setPlannedStartDate(startDate);
        activityVO.setParent(getDHTMLParentId(templateVO));

        return activityVO;
    }

    public static DHTMLGanttWBSActivityVO convertToDHTMLGanttActivityVO(WBSItemsVO wbsItemsVO,List<WBSItemsVO> list, GANTT_VIEWING_TYPE viewingType){

        DHTMLGanttWBSActivityVO activityVO = new DHTMLGanttWBSActivityVO(wbsItemsVO);
        activityVO.setId(wbsItemsVO.getObid());
        
        String locale = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.locale, OmcSystemConstants.OMC_LOCALE_LANG_EN);
        
        if(locale.equals(OmcSystemConstants.OMC_LOCALE_LANG_KO)){
            activityVO.setText(wbsItemsVO.getActivityNameKor());
        }else if(locale.equals(OmcSystemConstants.OMC_LOCALE_LANG_CH)){
            activityVO.setText(wbsItemsVO.getActivityNameChi());
        }else{
            activityVO.setText(wbsItemsVO.getActivityNameEng());
        }
        if(StrUtil.isEmpty(activityVO.getText())) activityVO.setText(wbsItemsVO.getActivityNameEng());
        
        activityVO.setOrder((int)wbsItemsVO.getOutDataAttributeValue(ProjectConstants.GANTT_SORT_ORDER_KEY));
        
        Integer duration = getDuration(wbsItemsVO,viewingType);
        activityVO.setDuration(getDuration(wbsItemsVO,viewingType));
        activityVO.setDuration(duration);

        Date startDate = getStartDate(wbsItemsVO,viewingType);
        activityVO.setStartDate(startDate);
        activityVO.setEndDate(getEndDate(wbsItemsVO,viewingType,startDate,duration));
        
        activityVO.setPlannedStartDate(getPlannedStartDate(wbsItemsVO,viewingType));
        activityVO.setPlannedEndDate(getPlannedEndDate(wbsItemsVO,viewingType));
        
        activityVO.setOpen(getOpen(wbsItemsVO,viewingType));
        activityVO.setType(getDHTMLType(wbsItemsVO,list));
        activityVO.setProgress(getProgress(wbsItemsVO,viewingType));
        activityVO.setParent(getDHTMLParentId(wbsItemsVO));
        return activityVO;
    }
    private static Integer getDuration(WBSItemsVO wbsItemsVO,GANTT_VIEWING_TYPE viewingType){
        return wbsItemsVO.getPlanDuration();
    }
    private static Date getStartDate(WBSItemsVO wbsItemsVO,GANTT_VIEWING_TYPE viewingType){
        if(WBSTemplateUtil.GANTT_VIEWING_TYPE.baselineVsPlan == viewingType && wbsItemsVO.getPlanStartDate() != null){
            return wbsItemsVO.getPlanStartDate();
        }else if(WBSTemplateUtil.GANTT_VIEWING_TYPE.baselineVsActual == viewingType && wbsItemsVO.getActualStartDate() != null){
            return wbsItemsVO.getActualStartDate();
        }
        return wbsItemsVO.getActualStartDate();
    }
    private static Date getEndDate(WBSItemsVO wbsItemsVO,GANTT_VIEWING_TYPE viewingType, Date startDate, Integer duration){
        Date endDate = null;
        if(WBSTemplateUtil.GANTT_VIEWING_TYPE.baselineVsPlan == viewingType && wbsItemsVO.getPlanEndDate() != null){
            return wbsItemsVO.getPlanEndDate();
        }else if(WBSTemplateUtil.GANTT_VIEWING_TYPE.baselineVsActual == viewingType && wbsItemsVO.getActualEndDate() != null){
            return wbsItemsVO.getActualEndDate();
        }
        return wbsItemsVO.getActualEndDate();
    }
    private static Boolean getOpen(WBSItemsVO wbsItemsVO,GANTT_VIEWING_TYPE viewingType){
        return true;
    }
    private static Float getProgress(WBSItemsVO wbsItemsVO,GANTT_VIEWING_TYPE viewingType){
        return 0f;
    }
    private static Date getPlannedStartDate(WBSItemsVO wbsItemsVO,GANTT_VIEWING_TYPE viewingType){
        if(WBSTemplateUtil.GANTT_VIEWING_TYPE.baselineVsPlan == viewingType && wbsItemsVO.getBaselinedStartDate() != null){
            return wbsItemsVO.getBaselinedStartDate();
        }else if(WBSTemplateUtil.GANTT_VIEWING_TYPE.baselineVsActual == viewingType && wbsItemsVO.getBaselinedStartDate() != null){
            return wbsItemsVO.getBaselinedStartDate();
        }
        return wbsItemsVO.getPlanStartDate();
    }
    private static Date getPlannedEndDate(WBSItemsVO wbsItemsVO,GANTT_VIEWING_TYPE viewingType){
        if(WBSTemplateUtil.GANTT_VIEWING_TYPE.baselineVsPlan == viewingType && wbsItemsVO.getPlanEndDate() != null){
            return wbsItemsVO.getBaselinedEndDate();
        }else if(WBSTemplateUtil.GANTT_VIEWING_TYPE.baselineVsActual == viewingType && wbsItemsVO.getActualEndDate() != null){
            return wbsItemsVO.getBaselinedEndDate();
        }
        return wbsItemsVO.getPlanEndDate();
    }

    private static String getDHTMLParentId(BusinessObjectVO wbsItemTemplatesVO) {
        String fromClass = (String)wbsItemTemplatesVO.getOutData().get("rel_fromClass");
        String parentId = "0";
        if(!fromClass.equals(ApplicationSchemaConstants.BIZCLASS_WBSTEMPLATEMASTER)
            && !fromClass.equals(ApplicationSchemaConstants.BIZCLASS_PROJECTSCHEDULE)){
            parentId = (String)wbsItemTemplatesVO.getOutData().get("rel_fromObid");
        }
        return parentId;
    }
    private static String getDHTMLType(WBSItemTemplatesVO wbsItemTemplatesVO,List<WBSItemTemplatesVO> list) {
        for(WBSItemTemplatesVO vo : list){
            String parentObid = (String)vo.getOutData().get("rel_fromObid");
            if(!StrUtil.isEmpty(parentObid)){
                if(wbsItemTemplatesVO.getIsMilestone().equals("Y")) return ProjectConstants.WBS_GANTT_ACTIVITY_TYPE_MILESTONE;
                if(parentObid.equals(wbsItemTemplatesVO.getObid())) return ProjectConstants.WBS_GANTT_ACTIVITY_TYPE_PROJECT;
            }
        }
        return "";
    }
    private static String getDHTMLType(WBSItemsVO wbsItemTemplatesVO,List<WBSItemsVO> list) {
        
        for(WBSItemsVO vo : list){
            String parentObid = (String)vo.getOutData().get("rel_fromObid");
            if(!StrUtil.isEmpty(parentObid)){
                if(wbsItemTemplatesVO.getIsMilestone().equals("Y")) return ProjectConstants.WBS_GANTT_ACTIVITY_TYPE_MILESTONE;
                if(parentObid.equals(wbsItemTemplatesVO.getObid())) return ProjectConstants.WBS_GANTT_ACTIVITY_TYPE_PROJECT;
            }
        }
        return "";
    }
    public static Date addDays(Date startBaseDate, int days){
        Calendar calendar = Calendar.getInstance(); 
        calendar.setTime(startBaseDate); 
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }
    public static JsonNode convertToJsonObject(Object obj){
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.convertValue(obj, JsonNode.class);
        return jsonNode;
    }
    public static String convertToJsonString(Object obj){
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.convertValue(obj, JsonNode.class);
        String json = "";
        try {
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
    public static String convertJsonNodeToString(JsonNode jsonNode){
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    
    public static Map<String,Object> makeBounds(BigDecimal left, BigDecimal top, BigDecimal width,  BigDecimal height){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("left", left);
        map.put("top", top);
        map.put("width", width);
        map.put("height", height);
        return map;
    }
    public static AnychartTreeDataItemDataVO convertToAnychartTreeDataItemVO(Date startBaseDate, WBSItemTemplatesVO templateVO){
        
        Random rand = new Random();

        AnychartTreeDataItemDataVO anychartTreeDataItemDataVO = new AnychartTreeDataItemDataVO();
        anychartTreeDataItemDataVO.setId(templateVO.getUniqueString());
        anychartTreeDataItemDataVO.setName(templateVO.getActivityNameEng());
        
        long millis = new java.util.Date().getTime();
        
        Date newDateStart = addDays(startBaseDate,templateVO.getExplodedDepth()+rand.nextInt(15));    
        Date newDateEnd   = addDays(startBaseDate,templateVO.getExplodedDepth()+(rand.nextInt(100) + 20));    
        anychartTreeDataItemDataVO.setActualStart(newDateStart.getTime());
        anychartTreeDataItemDataVO.setActualEnd(newDateEnd.getTime());
        anychartTreeDataItemDataVO.setBaselineStart(newDateStart.getTime());
        anychartTreeDataItemDataVO.setBaselineEnd(newDateEnd.getTime());
        anychartTreeDataItemDataVO.setProgressValue("50%");
        anychartTreeDataItemDataVO.setRowHeight(20);
        anychartTreeDataItemDataVO.setParent(templateVO.getUniqueStringParent());
        anychartTreeDataItemDataVO.setObid(templateVO.getObid());
        return anychartTreeDataItemDataVO;
    }

    
    public static String getActivityMethodDescription(List<ActivityMethodDescVO> metohdList, String method){
        String methodDescription = "";
        for(ActivityMethodDescVO activityMethodDescVO : metohdList){
            if(activityMethodDescVO.getMethod().equals(method)){
                methodDescription = activityMethodDescVO.getMethodDesc();
                break;
            }
        }
        return methodDescription;
    }
    
    public static String getActivityStandardStartValidationMethodDescription(String method){
        return getActivityMethodDescription(getActivityStandardMethodList("validateForStart"), method);
    }
    public static String getActivityStandardCompleteValidationMethodDescription(String method){
        return getActivityMethodDescription(getActivityStandardMethodList("validateForComplete"), method);
    }
    public static String getActivityStandardStartMethodDescription(String method){
        return getActivityMethodDescription(getActivityStandardMethodList("startProcessFor"), method);
    }
    public static String getActivityStandardPostMethodDescription(String method){
        return getActivityMethodDescription(getActivityStandardMethodList("postProcessFor"), method);
    }
    
}
