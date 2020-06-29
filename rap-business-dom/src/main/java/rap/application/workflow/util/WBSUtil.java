/**
 * ===========================================
 * System Name : LGE PLM Project
 * Program ID : WBSUtil.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 10. 17.  s_dongsshin   Initial
 * ===========================================
 */
package rap.application.workflow.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import com.rap.omc.api.object.model.BusinessObjectRootVO;
import com.rap.omc.api.object.model.ObjectRootVO;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.omc.SortUtil;
import com.rap.omc.api.util.omc.ThreadLocalUtil;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.TimeServiceUtil;
import com.rap.omc.util.core.DateUtil;

import rap.api.object.project.model.ProjectScheduleVO;
import rap.api.object.project.model.ProjectsVO;
import rap.api.object.project.model.WBSGeneralActivityVO;
import rap.api.object.project.model.WBSItemsVO;
import rap.api.object.project.model.WBSJobActivityVO;
import rap.api.object.relation.model.WBSDependencyVO;
import rap.application.constants.ApplicationSchemaConstants;
import rap.application.constants.ProjectConstants;
import rap.application.workflow.model.ActivityMethodDescVO;
import rap.application.workflow.model.ActivitySimpleChildVO;
import rap.application.workflow.model.ActivitySimpleVO;
import rap.application.workflow.model.DependencySimpleVO;
import rap.application.workflow.model.DependencyVO;
/**
 * <pre>
 * Class : WBSUtil
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class WBSUtil {
    public static int maxCost;
    public static int calledCount = 0;
    public static String format = "%1$-10s %2$-5s %3$-5s %4$-5s %5$-5s %6$-5s %7$-10s\n";
    public static  Map<String,ActivitySimpleVO> convertToSimpleActivity(List<WBSItemsVO> list, Map<String,WBSItemsVO> activityObjectDB){
        Map<String,ActivitySimpleVO> activities = new HashMap<String,ActivitySimpleVO>();
        for(WBSItemsVO vo : list){
            ActivitySimpleVO activitySimpleVO = new ActivitySimpleVO(vo.getUniqueString(),vo.getUniqueStringParent(),vo.getExplodedDepth(),vo.getClassName(),vo.getObid(),vo.getActivityNameKor(),vo.getIsReDoActivity(),vo.getIsSkipped(),vo.getPlanDuration(),vo.getPlanStartDate(),vo.getPlanEndDate(),vo.getActualStartDate(),vo.getActualEndDate());
            List<WBSItemsVO> childList = activityObjectDB.get(vo.getObid()).getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_MAP_DB_STRUCTURE_CHILD_LIST);
            ObjectRootVO parentList = activityObjectDB.get(vo.getObid()).getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_MAP_DB_STRUCTURE_PARENT);
            for(WBSItemsVO childVO : childList){
                activitySimpleVO.addChild( new ActivitySimpleChildVO(childVO.getObid(),childVO.getActivityNameKor()));
            }
            if(!NullUtil.isNull(parentList)){
                if(parentList instanceof WBSItemsVO){
                    activitySimpleVO.setActivityNameParent(((WBSItemsVO)parentList).getActivityNameKor());
                    activitySimpleVO.setActivityObidParent(((WBSItemsVO)parentList).getObid());                
                }         
            }
            List<DependencyVO> previousList = activityObjectDB.get(vo.getObid()).getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_MAP_DB_DEPENDENCY_PREVIOUS_LIST);
            List<DependencyVO> nextList     = activityObjectDB.get(vo.getObid()).getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_MAP_DB_DEPENDENCY_NEXT_LIST);
            for(DependencyVO dependencyVO : previousList){
                activitySimpleVO.addPreviousDependency(new DependencySimpleVO(dependencyVO.getDependencyType(),dependencyVO.getDependentVO().getObid(),dependencyVO.getDependentVO().getActivityNameKor()));
            }
            for(DependencyVO dependencyVO : nextList){
                activitySimpleVO.addNextDependency(new DependencySimpleVO(dependencyVO.getDependencyType(),dependencyVO.getDependentVO().getObid(),dependencyVO.getDependentVO().getActivityNameKor()));
            }
            activities.put(activitySimpleVO.getActivityObid(), activitySimpleVO);
        }
        Map<String,Date> maxMinDateMap = new HashMap<String,Date>();
        Map<String,ActivitySimpleVO> finalActivityMapList = getLatestActivity(activities,maxMinDateMap);
        Map<String,ActivitySimpleVO> criticalPathMap = new HashMap<String,ActivitySimpleVO>();
        for(String key : finalActivityMapList.keySet()){
            findCritaclPath(activities,criticalPathMap,finalActivityMapList.get(key),0);
        }
        for(String key : criticalPathMap.keySet()){
            activities.get(key).setIsCriticalPathItem("Y");
        }
        return activities;
    }
    private static void findCritaclPath(Map<String,ActivitySimpleVO> activities, Map<String,ActivitySimpleVO> criticalPathMap, ActivitySimpleVO simpleActivity, int findLevel){
        criticalPathMap.put(simpleActivity.getActivityObid(), simpleActivity);
        ActivitySimpleVO latestEndActivity = lateEndPreviousActivity(activities,simpleActivity);
        if(latestEndActivity == null) return;
        findCritaclPath(activities,criticalPathMap,latestEndActivity,findLevel+1);
        
    }
    private static ActivitySimpleVO lateEndPreviousActivity(Map<String,ActivitySimpleVO> activities, ActivitySimpleVO simpleActivity){
        if(simpleActivity.getPreviousDependencies().size() == 0) return null;
        ActivitySimpleVO returnObj = null;
        for(int i = 0; i < simpleActivity.getPreviousDependencies().size(); i++){
            if(i==0) {
                returnObj = activities.get(simpleActivity.getPreviousDependencies().get(i).getObid());
            }else{
                if(activities.get(simpleActivity.getPreviousDependencies().get(i).getObid()).getEarlyFinishDate().compareTo(returnObj.getEarlyFinishDate()) > 0) {
                    returnObj = activities.get(simpleActivity.getPreviousDependencies().get(i).getObid());
                }
            }
        }
        return returnObj;
    }
    private static Map<String,ActivitySimpleVO> getLatestActivity(Map<String,ActivitySimpleVO> activities, Map<String,Date> maxMinDateMap){
        Map<String,ActivitySimpleVO> finalActivityMapList = new HashMap<String,ActivitySimpleVO>();
        boolean isFirst = true;
        Date maxEndDate = null;
        Date minStartDate = null;
        for(String key : activities.keySet()){
            
            if(activities.get(key).getChlidList().size() == 0 && activities.get(key).getActivityClassName().equals("WBSGeneralActivity")){
                if(isFirst){
                    maxEndDate   = activities.get(key).getEarlyFinishDate();
                    minStartDate = activities.get(key).getEarlyStartDate();
                    isFirst = false;
                }else{
                    if(activities.get(key).getEarlyFinishDate().compareTo(maxEndDate) > 0){
                        finalActivityMapList = new HashMap<String,ActivitySimpleVO>();
                        finalActivityMapList.put(key, activities.get(key));
                        maxEndDate =activities.get(key).getEarlyFinishDate();
                    }else if(activities.get(key).getEarlyFinishDate() == maxEndDate){
                        finalActivityMapList.put(key, activities.get(key));
                    }
                }
                if(activities.get(key).getEarlyStartDate().compareTo( minStartDate) < 0) minStartDate = activities.get(key).getEarlyStartDate();
            }
        }
        maxMinDateMap.put("minStartDate", minStartDate);
        maxMinDateMap.put("maxEndDate", maxEndDate);
        return finalActivityMapList;
    }
    
    public static Map<String,String> criticalPath(List<WBSItemsVO> list, List<WBSDependencyVO> dependencyLlist) {
        Map<String,String> pathInfoMap   = new HashMap<String,String>();
        Map<String,WBSItemsVO> objectMap = new HashMap<String,WBSItemsVO>();
        //List<WBSItemsVO> copiedList      = (List<WBSItemsVO>)DomUtil.copy(list);
        
        for(WBSItemsVO vo : list){
            pathInfoMap.put(vo.getObid(), "N");
            if(vo instanceof WBSGeneralActivityVO){
                objectMap.put(vo.getObid(), vo);
            }
        }
        Set<OmcActivity> tasks = WBSUtil.convertActivity(list,dependencyLlist,objectMap);
        OmcActivity[] taskArray = WBSUtil.criticalPath(tasks);
        //print(taskArray);
        List<WBSItemsVO> returnList = new ArrayList<WBSItemsVO>();
        
        
        List<String> dkdk = null;
        int length = 0;
        
        for(int i = 0 ; i < taskArray.length; i++){
            if(taskArray[i].pathList.size() > length) dkdk = taskArray[i].pathList;
            System.out.println(taskArray[i].pathList);
            String criticalCond = taskArray[i].earlyStart == taskArray[i].latestStart ? "Y" : "N";
            if(criticalCond.equals("Y")) {
                pathInfoMap.put(taskArray[i].activityObid, "Y");
            }
            WBSItemsVO vo = objectMap.get(taskArray[i].activityObid);
            vo.setIsCriticalPathItem(criticalCond);
            returnList.add(objectMap.get(taskArray[i].activityObid));
        }
        return pathInfoMap;
    }
    public static OmcActivity[] criticalPath(Set<OmcActivity> tasks) {
        List<OmcActivity> completed = new ArrayList<OmcActivity>();
        List<OmcActivity> remaining = new ArrayList<OmcActivity>();
        for(OmcActivity dd : tasks){
            dd.criticalCost = 0;
            remaining.add(dd);
            System.out.println("cost:" + dd.cost);
            System.out.println("criticalCost:" + dd.criticalCost);
        }
        
        while (!remaining.isEmpty()) {
            boolean progress = false;
            for (int i = remaining.size() -1; i >= 0 ; i--) {
                if (completed.containsAll(remaining.get(i).dependencies)) {
                    int critical = 0;
                    System.out.println("remaining.get(" + i + ")             :" + remaining.get(i).activityName);
                    System.out.println("remaining.get(" + i + ").cost        :" + remaining.get(i).cost);
                    System.out.println("remaining.get(" + i + ").criticalCost:" + remaining.get(i).criticalCost);
                    for (OmcActivity dependTask : remaining.get(i).dependencies) {
                        if (dependTask.criticalCost > critical) {
                            critical = dependTask.criticalCost;
                        }
                        dependTask.pathList.addAll(remaining.get(i).pathList);
                        dependTask.pathList.add(remaining.get(i).activityObid);
                    }
                    int calculatedCost = critical + remaining.get(i).cost;
                    remaining.get(i).criticalCost = calculatedCost;
                    remaining.get(i).pathList.add(remaining.get(i).activityObid);
                    System.out.println(StrUtil.convertList2Str(remaining.get(i).pathList));
                    completed.add(remaining.get(i));
                    remaining.remove(i);
                    progress = true;
                }
            }
            if (!progress) throw new FoundationException("Infinite Loop!!!");
        }
        maxCost(tasks);
        HashSet<OmcActivity> initialNodes = WBSUtil.initials(tasks);
        for (OmcActivity initial : initialNodes) {
            System.out.println(initial.activityName);
        }
        //calculateEarly(initialNodes);
        OmcActivity[] ret = completed.toArray(new OmcActivity[0]);
        return ret;
    }
    private static Set<OmcActivity> convertActivity(List<WBSItemsVO> list, List<WBSDependencyVO> dependencyLlist, Map<String,WBSItemsVO> objectMap){
        Set<OmcActivity> tasks = new HashSet<OmcActivity>();
        Set<String> obidSet = new HashSet<String>();
        Map<String,OmcActivity> taskObjectMap = new HashMap<String,OmcActivity>();
        for(WBSItemsVO vo : list){
            if(vo instanceof WBSGeneralActivityVO && StrUtil.isEmpty((String)vo.getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_ACTIVITY_ISCHILD_MAP_ATTR))){
                OmcActivity task = new OmcActivity(vo.getObid(),vo.getActivityNameKor(),vo.getPlanDuration());
                if(!obidSet.contains(task.activityObid)){
                    tasks.add(task);
                    taskObjectMap.put(task.activityObid,task);
                }
                                
            }
        }
        for (OmcActivity task : tasks) {
            List<WBSItemsVO> de = WBSUtil.getDependency(objectMap.get(task.activityObid), dependencyLlist, objectMap);
            for(WBSItemsVO vo : de){
                task.addDependency(taskObjectMap.get(vo.getObid()));
            }                
        }
        return tasks;
    }
    public static void clearDependencyInfo(List<WBSItemsVO> list){
        for(WBSItemsVO vo : list){
            vo.setOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_MAP_DB_DEPENDENCY_PREVIOUS_LIST, null);
            vo.setOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_MAP_DB_DEPENDENCY_NEXT_LIST, null);
            vo.setOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_MAP_DB_STRUCTURE_CHILD_LIST, null);
            vo.setOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_MAP_DB_STRUCTURE_PARENT, null);
            vo.setOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_MAP_DB_STRUCTURE_PARENT, null);
        }
    }
    private static List<WBSItemsVO> getDependency(WBSItemsVO wantedVO, List<WBSDependencyVO> dependencyLlist, Map<String,WBSItemsVO> objectMap){
        List<WBSItemsVO> list = new ArrayList<WBSItemsVO>();
        for(WBSDependencyVO vo : dependencyLlist){
            if(wantedVO.getObid().equals(vo.getFromObid())){
                list.add(objectMap.get(vo.getToObid()));
            }
        }
        return list;
    }
    /**********************************************************************************************************************************************/
    /**********************************************************************************************************************************************/
    /**********************************************************************************************************************************************/
    /**********************************************************************************************************************************************/
    /**********************************************************************************************************************************************/
    /**********************************************************************************************************************************************/
    public static void scheduling(List<WBSItemsVO> list, List<WBSDependencyVO> dependencyLlist) {
        System.out.println("Start = " + TimeServiceUtil.convertDate2Str(new Date(), "yyyy:mm:dd-mm:ss:SSS"));
        
        Map<String,WBSItemsVO> objectMap = new HashMap<String,WBSItemsVO>();
        for(WBSItemsVO vo : list){
            if(vo instanceof WBSGeneralActivityVO && StrUtil.isEmpty((String)vo.getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_ACTIVITY_ISCHILD_MAP_ATTR))){
                objectMap.put(vo.getObid(), vo);
            }
        }
        List<ActivityVO> tasks = new ArrayList<ActivityVO>();
        Map<String,ActivityVO> taskObjectMap = new HashMap<String,ActivityVO>();
        WBSUtil.convertActivityVO(list,dependencyLlist,objectMap,tasks,taskObjectMap);
        setEarlyAndLateDate(tasks,true,null,false);
        Collections.sort(tasks,new SequenceComparator());
        for(ActivityVO vo : tasks){
            System.out.println(vo.toString());
            //if(vo.earlyStartDate.equals(vo.lateStartDate)) System.out.println(vo.toString());
        }
        System.out.println("End = " + TimeServiceUtil.convertDate2Str(new Date(), "yyyy:mm:dd-mm:ss:SSS"));
        System.out.println("End = " + TimeServiceUtil.convertDate2Str(new Date(), "yyyy:mm:dd-mm:ss:SSS"));
    }
    public static class SequenceComparator implements Comparator<ActivityVO> {
        @Override
        public int compare(ActivityVO s, ActivityVO t) {
           int i = s.earlyStartDate.compareTo(t.earlyStartDate);
           if(i != 0) return i;
           return s.uniqueStr.compareTo(t.uniqueStr);
        }
    }
    public static void setEarlyAndLateDate(List<ActivityVO> tasks, boolean forward, List<ActivityVO> initials, boolean alreadyPriority) {
        List<ActivityVO> completed = new ArrayList<ActivityVO>();
        if(!NullUtil.isNone(initials)) completed.addAll(initials);
        List<ActivityVO> remaining = new ArrayList<ActivityVO>();
        remaining.addAll(tasks);
        if(forward){
            while (!remaining.isEmpty()) {
                boolean progress = false;
                for (int i = remaining.size() -1; i >= 0 ; i--) {
                    if (remaining.get(i).allPreviousCompleted(completed)) {
                        Date maxDate = null;
                        if(!NullUtil.isNone(remaining.get(i).previousDependencies)){
                            for (DependencyActivityVO dependTask : remaining.get(i).previousDependencies) {
                                if(maxDate == null){
                                    if(dependTask.dependencyType.equals(ProjectConstants.WBS_DEPENDENCY_TYPE_FINISH_TO_START)) maxDate = dependTask.dependentVO.get(0).earlyFinishDate;
                                    if(dependTask.dependencyType.equals(ProjectConstants.WBS_DEPENDENCY_TYPE_START_TO_START)) maxDate = dependTask.dependentVO.get(0).earlyStartDate;
                                    if(dependTask.dependencyType.equals(ProjectConstants.WBS_DEPENDENCY_TYPE_FINISH_TO_FINISH)) maxDate = dependTask.dependentVO.get(0).earlyFinishDate;
                                    if(dependTask.dependencyType.equals(ProjectConstants.WBS_DEPENDENCY_TYPE_START_TO_FINISH)) maxDate = dependTask.dependentVO.get(0).earlyFinishDate;
                                }
                                if(dependTask.dependencyType.equals(ProjectConstants.WBS_DEPENDENCY_TYPE_FINISH_TO_START)){
                                    if (dependTask.dependentVO.get(0).earlyFinishDate.compareTo(maxDate) > 0) maxDate = dependTask.dependentVO.get(0).earlyFinishDate;
                                }else if(dependTask.dependencyType.equals(ProjectConstants.WBS_DEPENDENCY_TYPE_START_TO_START)){
                                    if (dependTask.dependentVO.get(0).earlyStartDate.compareTo(maxDate) > 0) maxDate = dependTask.dependentVO.get(0).earlyStartDate;
                                }else if(dependTask.dependencyType.equals(ProjectConstants.WBS_DEPENDENCY_TYPE_FINISH_TO_FINISH)){
                                    if (dependTask.dependentVO.get(0).earlyFinishDate.compareTo(maxDate) > 0) maxDate = dependTask.dependentVO.get(0).earlyFinishDate;
                                }else if(dependTask.dependencyType.equals(ProjectConstants.WBS_DEPENDENCY_TYPE_START_TO_FINISH)){
                                    if (dependTask.dependentVO.get(0).earlyFinishDate.compareTo(maxDate) > 0) maxDate = dependTask.dependentVO.get(0).earlyFinishDate;
                                }
                            }
                            if(alreadyPriority){
                                if(remaining.get(i).earlyStartDate.compareTo(maxDate) < 0) remaining.get(i).earlyStartDate = maxDate;
                            }else{
                                remaining.get(i).earlyStartDate = maxDate;
                            }
                            remaining.get(i).earlyFinishDate = TimeServiceUtil.addDays(remaining.get(i).earlyStartDate, remaining.get(i).duration);
                        }
                        completed.add(remaining.get(i));
                        remaining.remove(i);
                        progress = true;
                    }
                }
                if (!progress) throw new FoundationException("Infinite Loop!!!");
            }            
        }else{
            while (!remaining.isEmpty()) {
                boolean progress = false;
                for (int i = remaining.size() -1; i >= 0 ; i--) {
                    if (remaining.get(i).allNextCompleted(completed)) {
                        Date maxDate = null;
                        if(!NullUtil.isNone(remaining.get(i).nextDependencies)){
                            for (DependencyActivityVO dependTask : remaining.get(i).nextDependencies) {
                                if(maxDate == null){
                                    if(dependTask.dependencyType.equals(ProjectConstants.WBS_DEPENDENCY_TYPE_FINISH_TO_START)) maxDate = dependTask.dependentVO.get(0).lateStartDate;
                                    if(dependTask.dependencyType.equals(ProjectConstants.WBS_DEPENDENCY_TYPE_START_TO_START)) maxDate = dependTask.dependentVO.get(0).lateStartDate;
                                    if(dependTask.dependencyType.equals(ProjectConstants.WBS_DEPENDENCY_TYPE_FINISH_TO_FINISH)) maxDate = dependTask.dependentVO.get(0).lateFinishDate;
                                    if(dependTask.dependencyType.equals(ProjectConstants.WBS_DEPENDENCY_TYPE_START_TO_FINISH)) maxDate = dependTask.dependentVO.get(0).lateStartDate;
                                }
                                if(dependTask.dependencyType.equals(ProjectConstants.WBS_DEPENDENCY_TYPE_FINISH_TO_START)){
                                    if (dependTask.dependentVO.get(0).lateStartDate.compareTo(maxDate) < 0) maxDate = dependTask.dependentVO.get(0).lateStartDate;
                                }else if(dependTask.dependencyType.equals(ProjectConstants.WBS_DEPENDENCY_TYPE_START_TO_START)){
                                    if (dependTask.dependentVO.get(0).lateStartDate.compareTo(maxDate) < 0) maxDate = dependTask.dependentVO.get(0).lateStartDate;
                                }else if(dependTask.dependencyType.equals(ProjectConstants.WBS_DEPENDENCY_TYPE_FINISH_TO_FINISH)){
                                    if (dependTask.dependentVO.get(0).lateFinishDate.compareTo(maxDate) < 0) maxDate = dependTask.dependentVO.get(0).lateFinishDate;
                                }else if(dependTask.dependencyType.equals(ProjectConstants.WBS_DEPENDENCY_TYPE_START_TO_FINISH)){
                                    if (dependTask.dependentVO.get(0).lateStartDate.compareTo(maxDate) < 0) maxDate = dependTask.dependentVO.get(0).lateStartDate;
                                }
                            }                            
                            remaining.get(i).lateFinishDate = maxDate;
                            remaining.get(i).lateStartDate = TimeServiceUtil.addDays(remaining.get(i).lateFinishDate, remaining.get(i).duration*-1);
                        }
                        completed.add(remaining.get(i));
                        remaining.remove(i);
                        progress = true;
                    }
                }
                if (!progress) throw new FoundationException("Infinite Loop!!!");
            }            
        }
    }
    public static void leafActivities(List<ActivityVO> tasks, List<ActivityVO> completed,List<ActivityVO> remaining) {
        for (ActivityVO vo : tasks) {
            if(NullUtil.isNone(vo.nextDependencies)){
                vo.lateFinishDate = vo.earlyFinishDate;
                vo.lateStartDate = TimeServiceUtil.addDays(vo.lateFinishDate, vo.duration*-1);
                completed.add(vo);
            }else{
                remaining.add(vo);
            }
        }
    }
    private static void convertActivityVO(List<WBSItemsVO> list,List<WBSDependencyVO> dependencyLlist, Map<String,WBSItemsVO> objectMap, List<ActivityVO> tasks, Map<String,ActivityVO> taskObjectMap){
        Date date = TimeServiceUtil.getDBLocalTime();
        for(WBSItemsVO vo : list){
            if(vo instanceof WBSGeneralActivityVO && StrUtil.isEmpty((String)vo.getOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_ACTIVITY_ISCHILD_MAP_ATTR))){
                if(!taskObjectMap.containsKey(vo.getObid())){
                    ActivityVO task = new ActivityVO(vo.getUniqueString(),vo.getObid(),vo.getActivityNameKor(),vo.getPlanDuration(),vo.getPlanStartDate(),vo.getPlanEndDate(),date,date);
                    tasks.add(task);
                    taskObjectMap.put(task.activityObid,task);     
                }  
            }
        }
        for (ActivityVO task : tasks) {
            task.nextDependencies     = getNextDependency(task,dependencyLlist,taskObjectMap);
            task.previousDependencies = getPreviousDependency(task,dependencyLlist,taskObjectMap);
        }
    }
    private static List<DependencyActivityVO> getPreviousDependency(ActivityVO wantedVO, List<WBSDependencyVO> dependencyLlist, Map<String,ActivityVO> taskObjectMap){
        List<DependencyActivityVO> list = new ArrayList<DependencyActivityVO>();
        for(WBSDependencyVO vo : dependencyLlist){
            if(wantedVO.activityObid.equals(vo.getFromObid())){
                list.add((new DependencyActivityVO(vo.getDependencyType(),taskObjectMap.get(vo.getToObid()))));
            }
        }
        return list;
    }
    private static List<DependencyActivityVO> getNextDependency(ActivityVO wantedVO, List<WBSDependencyVO> dependencyLlist, Map<String,ActivityVO> taskObjectMap){
        List<DependencyActivityVO> list = new ArrayList<DependencyActivityVO>();
        for(WBSDependencyVO vo : dependencyLlist){
            if(wantedVO.activityObid.equals(vo.getToObid())){
                //DependencyActivityVO de = new DependencyActivityVO(vo.getDependencyType(),taskObjectMap.get(vo.getFromObid()));
                list.add((new DependencyActivityVO(vo.getDependencyType(),taskObjectMap.get(vo.getFromObid()))));
                //list.get(list.size()-1).dependentVO = taskObjectMap.get(vo.getFromObid());
            }
        }
        return list;
    }
    /**********************************************************************************************************************************************/
    /**********************************************************************************************************************************************/
    /**********************************************************************************************************************************************/
    /**********************************************************************************************************************************************/
    /**********************************************************************************************************************************************/
    /**********************************************************************************************************************************************/
    
    public static class ActivityVO {
        public String uniqueStr = "";
        public int duration = 0;
        public int totalDuration = 0;
        
        public String activityObid;
        public String activityName;
        
        public boolean isCompleted = false;
        
        Date earlyStartDate = null;
        Date earlyFinishDate = null;
        Date lateStartDate = null;
        Date lateFinishDate = null;
        
        public int totalDurationForaward = 0;
        public int totalDurationBackward = 0;
        
        public int allPathCountForward  = 0;
        public int allPathCountBackward = 0;
        
        public List<String> pathList = new ArrayList<String>();
        public List<DependencyActivityVO> previousDependencies = new ArrayList<DependencyActivityVO>();
        public List<DependencyActivityVO> nextDependencies = new ArrayList<DependencyActivityVO>();
        
        public ActivityVO(String uniqueStr,String activityObid, String activityName, int duration, Date earlyStartDate, Date earlyFinishDate, Date lateStartDate,Date lateFinishDate) {
            this.uniqueStr = uniqueStr;
            this.activityObid = activityObid;
            this.activityName = activityName;
            this.duration = duration;
            this.earlyStartDate = earlyStartDate;
            this.earlyFinishDate = earlyFinishDate;
            this.lateStartDate = lateStartDate;
            this.lateFinishDate = lateFinishDate;
        }
        
        public boolean allPreviousCompleted(List<ActivityVO> completedList){
            if(NullUtil.isNone(previousDependencies)) return true;
            if(NullUtil.isNone(completedList)) return false;
            for(DependencyActivityVO dependency : previousDependencies){
                boolean isExists = false;
                for(ActivityVO activityVO : completedList){
                    if(dependency.dependentVO.get(0).activityObid.equals(activityVO.activityObid)) {isExists = true;break;}
                }
                if(!isExists) return false;
            }
            return true;
        }
        public boolean allNextCompleted(List<ActivityVO> completedList){
            if(NullUtil.isNone(nextDependencies)) return true;
            if(NullUtil.isNone(completedList)) return false;
            for(DependencyActivityVO dependency : nextDependencies){
                boolean isExists = false;
                for(ActivityVO activityVO : completedList){
                    if(dependency.dependentVO.get(0).activityObid.equals(activityVO.activityObid)) {isExists = true;break;}
                }
                if(!isExists) return false;
            }
            return true;
        }
        public void addPreviousDependencies(DependencyActivityVO vo){
            previousDependencies.add(vo);
        }
        public void addNextDependencies(DependencyActivityVO vo){
            nextDependencies.add(vo);
        }
        /**
         * 
         * @return
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString(){
            String str = "ActivityVO [activityName=" + activityName.replace(",", "") + ",duration=" + duration + ", totalDuration=" + totalDuration + ", activityObid="
                    + activityObid + ", earlyStartDate=" + convertDate(earlyStartDate)
                    + ", earlyFinishDate=" + convertDate(earlyFinishDate) + ", lateStartDate=" + convertDate(lateStartDate) + ", lateFinishDate="
                    + convertDate(lateFinishDate) + ", totalDurationForaward=" + totalDurationForaward + ", totalDurationBackward="
                    + totalDurationBackward + ", allPathCountForward=" + allPathCountForward + ", allPathCountBackward="
                    + allPathCountBackward + "]";
            StringBuffer sb = new StringBuffer("\nPrevious === ");
            for(DependencyActivityVO vo : previousDependencies){
                sb.append(vo.dependentVO.get(0).activityName).append(",");
            }
            str = str + sb.toString() + "\nNext === ";
            sb.setLength(0);
            for(DependencyActivityVO vo : nextDependencies){
                sb.append(vo.dependentVO.get(0).activityName).append(",");
            }
            str = str + sb.toString();
            return str;
            
        }
        private String convertDate(Date date){
            try {
                return DateUtil.getDate(date,"yyyy-MM-dd");
            } catch (Exception e) {
                e.printStackTrace();
                return "";
               
            }
        }
    }
    public static class DependencyActivityVO {
        public String dependencyType;
        public HashMap<Integer,ActivityVO> dependentVO = new HashMap<Integer,ActivityVO>();
        /**
         * @param dependencyType
         * @param dependentVO
         */
        public DependencyActivityVO(String dependencyType, ActivityVO dependentVO) {
            super();
            this.dependencyType = dependencyType;
            this.dependentVO.put(0, dependentVO);
        }
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static class OmcActivity {
       public int cost;
       public int criticalCost;
       public String activityObid;
       public String activityName;
       public int earlyStart;
       public int earlyFinish;
       public int latestStart;
       public int latestFinish;
       public List<String> pathList = new ArrayList<String>();
       
       public HashSet<OmcActivity> dependencies = new HashSet<OmcActivity>();

       public OmcActivity(String activityObid,String activityName, int cost, OmcActivity... dependencies) {
           this.activityObid = activityObid;
           this.activityName = activityName;
           this.cost = cost;
           for (OmcActivity t : dependencies) {
               this.dependencies.add(t);
           }
           this.earlyFinish = -1;
       }
       public OmcActivity(String activityObid,String activityName, int cost) {
           this.activityObid = activityObid;
           this.activityName = activityName;
           this.cost = cost;
           this.earlyFinish = -1;
       }
       
       public void setLatest() {
           latestStart = maxCost - criticalCost;
           latestFinish = latestStart + cost;
       }

       public String[] toStringArray() {
           String criticalCond = earlyStart == latestStart ? "Yes" : "No";
           String[] toString = { activityObid, earlyStart + "", earlyFinish + "", latestStart + "", latestFinish + "",
                   latestStart - earlyStart + "", criticalCond };
           return toString;
       }
       
       public void addDependency(OmcActivity dependency) {
           this.dependencies.add(dependency);
       }
       public boolean isDependent(OmcActivity t) {
           if (dependencies.contains(t)) {
               return true;
           }
           for (OmcActivity dep : dependencies) {
               if (dep.isDependent(t)) {
                   return true;
               }
           }
           return false;
       }
    }
    public static void calculateEarly(HashSet<OmcActivity> initials) {
        for (OmcActivity initial : initials) {
            initial.earlyStart = 0;
            initial.earlyFinish = initial.cost;
            setEarly(initial);
        }
    }
    public static void setEarly(OmcActivity initial) {
        calledCount++;
        int completionTime = initial.earlyFinish;
        for (OmcActivity t : initial.dependencies) {
            if (completionTime >= t.earlyStart) {
                t.earlyStart  = completionTime;
                t.earlyFinish = completionTime + t.cost;
            }
            setEarly(t);
        }
    }
    public static HashSet<OmcActivity> initials(Set<OmcActivity> tasks) {
        HashSet<OmcActivity> remaining = new HashSet<OmcActivity>(tasks);
        for (OmcActivity t : tasks) {
            for (OmcActivity td : t.dependencies) {
                remaining.remove(td);
            }
        }
        return remaining;
    }
    public static void maxCost(Set<OmcActivity> tasks) {
        int max = -1;
        for (OmcActivity t : tasks) {
            if (t.criticalCost > max) max = t.criticalCost;
        }
        maxCost = max;
        for (OmcActivity t : tasks) {t.setLatest();}
    }
    public static void print(OmcActivity[] tasks) {
        System.out.format(format, "Task", "ES", "EF", "LS", "LF", "Slack", "Critical?");
        for (OmcActivity t : tasks)
            System.out.format(format, (Object[]) t.toStringArray());
    }
    
    public static String getDisplayActivityNameAsLoc(WBSItemsVO wbsItemVO){
        String locale = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.locale, OmcSystemConstants.OMC_LOCALE_LANG_EN);
        return getDisplayActivityNameAsLoc(wbsItemVO, locale);
    }
    public static String getDisplayActivityNameAsLoc(WBSItemsVO wbsItemVO, String locale){
        String displayName = "";
        if(locale.equals(OmcSystemConstants.OMC_LOCALE_LANG_KO)){
            displayName = wbsItemVO.getActivityNameKor();
        }else if(locale.equals(OmcSystemConstants.OMC_LOCALE_LANG_CH)){
            displayName = wbsItemVO.getActivityNameChi();
        }else{
            displayName = wbsItemVO.getActivityNameEng();
        }
        return displayName;
    }
    public static String getDisplayActivityNameAsLoc(WBSJobActivityVO wbsJobActivityVO, String locale){
        String displaName = "(J) ";
        if(locale.equals(OmcSystemConstants.OMC_LOCALE_LANG_KO)){
            displaName += wbsJobActivityVO.getActivityNameKor();
        }else if(locale.equals(OmcSystemConstants.OMC_LOCALE_LANG_CH)){
            displaName += wbsJobActivityVO.getActivityNameChi();
        }else{
            displaName += wbsJobActivityVO.getActivityNameEng();
        }
        return displaName;
    }
    public static String getDisplayActivityNameAsLoc(List<WBSItemsVO> list, String locale){
        StringBuffer strBuf = new StringBuffer();
        if(NullUtil.isNone(list)) return "";
        for(WBSItemsVO vo : list){
            strBuf.append(",").append(getDisplayActivityNameAsLoc(vo,locale)).append("(").append((String)vo.getOutDataAttributeValue("makePreviousActivityDisplayName")).append(")");
        }
        return strBuf.substring(1);
    }

    public static String getDisplayRoleNameAsLoc(WBSItemsVO wbsItemVO, String locale){
        String roleName = "";
        if(NullUtil.isNone(wbsItemVO.getRoleList()) || "None".equals(wbsItemVO.getRoleList()))  return roleName;
        String[] arrRoleList = wbsItemVO.getRoleList().split(Pattern.quote("^~^"));
        String[] arrRoleInfo = {};
        for(String role : arrRoleList){
            arrRoleInfo = role.split(Pattern.quote("^+^"));
            if(locale.equals(OmcSystemConstants.OMC_LOCALE_LANG_KO)){
                roleName = arrRoleInfo[1];
            }else if(locale.equals(OmcSystemConstants.OMC_LOCALE_LANG_CH)){
                roleName = arrRoleInfo[2];
            }else{
                roleName = arrRoleInfo[0];
            };
        }
        return roleName;
    }
    
    public static String getActtivityOwnerForDisplay(WBSItemsVO wbsItemVO){
        if(NullUtil.isNone(wbsItemVO.getActivityOwnerList()) || "None".equals(wbsItemVO.getActivityOwnerList()))  return "";
        StringBuffer strBuf = new StringBuffer();
        String[] arrOwnerList = wbsItemVO.getActivityOwnerList().split(Pattern.quote("^~^"));
        for(String owner : arrOwnerList){
            strBuf.append(",").append(owner.split(Pattern.quote("^+^"))[0]);
        }
        return strBuf.substring(1);
    }
    
    public static String getActtivityTypeForDisplay(WBSItemsVO wbsItemVO){
        if(wbsItemVO.getClassName().equals(ApplicationSchemaConstants.BIZCLASS_WBSPHASES)){
            
        }else if(wbsItemVO.getClassName().equals(ApplicationSchemaConstants.BIZCLASS_WBSSUBPROJECTS)){
        }else if(wbsItemVO.getClassName().equals(ApplicationSchemaConstants.BIZCLASS_WBSGENERALACTIVITY)){
        }else if(wbsItemVO.getClassName().equals(ApplicationSchemaConstants.BIZCLASS_WBSPHASES)){
            
        }
        return "";
    }
    
    public static void setDisplayValues(List<BusinessObjectRootVO> wbsItemList){
        String locale = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.locale, OmcSystemConstants.OMC_LOCALE_LANG_EN);
        for(BusinessObjectRootVO vo : wbsItemList){
            if(vo instanceof WBSItemsVO){
                vo.setOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_ACTIVITY_NAME_MAP_ATTR, getDisplayActivityNameAsLoc((WBSItemsVO)vo, locale));
                vo.setOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_ACTIVITY_ROLELIST_MAP_ATTR, getDisplayRoleNameAsLoc((WBSItemsVO)vo, locale));
                vo.setOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_ACTIVITY_OWNERLIST_MAP_ATTR, getActtivityOwnerForDisplay((WBSItemsVO)vo));
            }else if(vo instanceof WBSJobActivityVO){
                vo.setOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_ACTIVITY_NAME_MAP_ATTR, getDisplayActivityNameAsLoc((WBSJobActivityVO)vo, locale));
                vo.setOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_ACTIVITY_OWNERLIST_MAP_ATTR, vo.getOutDataAttributeValue("userIdUserTitles"));
            }else if(vo instanceof ProjectScheduleVO){
                vo.setOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_ACTIVITY_NAME_MAP_ATTR, (String)vo.getTitles());
                vo.setOutDataAttributeValue(ProjectConstants.WBS_SCHEDULE_ACTIVITY_OWNERLIST_MAP_ATTR, vo.getOutDataAttributeValue("this_ownerName"));
                
            }
        }
        
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
    @SuppressWarnings("rawtypes")
    public static Object getActivityRunClass(String classNames,ProjectsVO projectVO, ProjectScheduleVO scheduleVO, WBSItemsVO activityVO, String phaseCode, String phaseTitles, String phaseCodeSystem, String phaseTitlesSystem){
        Object runClass = null;
        try {
            Class<?> cls = Class.forName(classNames);
            Constructor constructor =cls.getDeclaredConstructor(ProjectsVO.class,ProjectScheduleVO.class,WBSItemsVO.class,String.class,String.class,String.class,String.class);
            runClass = constructor.newInstance(projectVO,scheduleVO,activityVO,phaseCode,phaseTitles,phaseCodeSystem,phaseTitlesSystem);
        } catch (ClassNotFoundException | IllegalArgumentException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            //LOGGER.error(e.getMessage());
            throw new FoundationException("[Foundtion]New Instance Error", new Object[] {classNames},e);
        }
        return runClass;
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
        String className = ProjectConstants.PROJECT_WBSSTANDARD_SPEC_CLASS_NAME;
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
                String methodDesc = WBSAbstractSpecSpecForActivity.methodDescMap.get(methodList[i].getName());
                if(!StrUtil.isEmpty(methodDesc)) {
                    methodDescList.add(new ActivityMethodDescVO(className + "." + methodList[i].getName(),methodList[i].getName(),methodDesc));
                }
            }
        }
        return methodDescList;
    }
}
