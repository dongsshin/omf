/**
 * ===========================================
 * System Name : LGE PLM Project
 * Program ID : ActivitySimpleVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2018. 9. 7.  s_dongsshin   Initial
 * ===========================================
 */
package rap.application.workflow.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import com.rap.omc.framework.exception.FoundationBaseException;
import com.rap.omc.schema.util.OmcSystemConstants;



/**
 * <pre>
 * Class : ActivitySimpleVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class ActivitySimpleVO {
    private int duration = 0;
    private String activityObid;
    private String activityName;
    
    private String activityObidParent;
    private String activityNameParent;
    
    private String isReDoActivity = "N";
    private String isSkipped = "N";
    
    
    private String activityClassName;
    private String isCriticalPathItem = "N";
    
    
    private Date actualStarted   = null;
    private Date actualCompleted = null;
    
    private Date earlyStartDate  = null;
    private Date earlyFinishDate = null;
    
    private String earlyStartDateStr  = null;
    private String earlyFinishDateStr = null;
    
    
    /**
     * 
     * 
     * @return the earlyStartDateStr
     */
    public String getEarlyStartDateStr(){
        return earlyStartDateStr;
    }
    
    /**
     * 
     * 
     * @param earlyStartDateStr the earlyStartDateStr to set
     */
    public void setEarlyStartDateStr(String earlyStartDateStr){
        this.earlyStartDateStr = earlyStartDateStr;
    }
    
    /**
     * 
     * 
     * @return the earlyFinishDateStr
     */
    public String getEarlyFinishDateStr(){
        return earlyFinishDateStr;
    }
    
    /**
     * 
     * 
     * @param earlyFinishDateStr the earlyFinishDateStr to set
     */
    public void setEarlyFinishDateStr(String earlyFinishDateStr){
        this.earlyFinishDateStr = earlyFinishDateStr;
    }

    private String uniqueString;
    private String uniqueStringParent;
    private int    explodedDepth = 0;
        
    private List<DependencySimpleVO>    previousDependencies = new ArrayList<DependencySimpleVO>();
    private List<DependencySimpleVO>    nextDependencies     = new ArrayList<DependencySimpleVO>();
    private List<ActivitySimpleChildVO> chlidList            = new ArrayList<ActivitySimpleChildVO>();
    public ActivitySimpleVO(){
        super();
    }
    public ActivitySimpleVO(String uniqueString, String uniqueStringParent, int explodedDepth, String activityClassName,String activityObid, String activityName, String isReDoActivity, String isSkipped, int duration, Date earlyStartDate, Date earlyFinishDate,Date actualStarted, Date actualCompleted) {
        super();
        SimpleDateFormat transFormat = new SimpleDateFormat(OmcSystemConstants.OMC_JAVA_DATE_FORMAT_DATE);
        this.uniqueString = uniqueString;
        this.uniqueStringParent = uniqueStringParent;
        this.explodedDepth = explodedDepth;
        this.activityObid = activityObid;
        this.activityName = activityName;
        this.activityClassName = activityClassName;
        this.isReDoActivity = isReDoActivity;
        this.isSkipped = isSkipped;
        this.duration = duration;
        this.earlyStartDate = earlyStartDate;
        this.earlyFinishDate = earlyFinishDate;
        
        this.earlyStartDateStr = transFormat.format(this.earlyStartDate);
        this.earlyFinishDateStr = transFormat.format(this.earlyFinishDate);;
        
        this.actualStarted = actualStarted;
        this.actualCompleted = actualCompleted;
    }
    public void addChild(ActivitySimpleChildVO childVO) {
        this.chlidList.add(childVO);
    }
    public void addPreviousDependency(DependencySimpleVO dependencyVO) {
        this.previousDependencies.add(dependencyVO);
    }
    public void addNextDependency(DependencySimpleVO dependencyVO) {
        this.nextDependencies.add(dependencyVO);
    }
    
    /**
     * 
     * 
     * @return the isCriticalPathItem
     */
    public String getIsCriticalPathItem(){
        return isCriticalPathItem;
    }
    
    /**
     * 
     * 
     * @param isCriticalPathItem the isCriticalPathItem to set
     */
    public void setIsCriticalPathItem(String isCriticalPathItem){
        this.isCriticalPathItem = isCriticalPathItem;
    }
    /**
     * 
     * 
     * @return the isSkipped
     */
    public String getIsSkipped(){
        return isSkipped;
    }
    
    /**
     * 
     * 
     * @param isSkipped the isSkipped to set
     */
    public void setIsSkipped(String isSkipped){
        this.isSkipped = isSkipped;
    }
    /**
     * 
     * 
     * @return the uniqueString
     */
    public String getUniqueString(){
        return uniqueString;
    }
    
    /**
     * 
     * 
     * @param uniqueString the uniqueString to set
     */
    public void setUniqueString(String uniqueString){
        this.uniqueString = uniqueString;
    }
    
    /**
     * 
     * 
     * @return the uniqueStringParent
     */
    public String getUniqueStringParent(){
        return uniqueStringParent;
    }
    
    /**
     * 
     * 
     * @param uniqueStringParent the uniqueStringParent to set
     */
    public void setUniqueStringParent(String uniqueStringParent){
        this.uniqueStringParent = uniqueStringParent;
    }
    
    /**
     * 
     * 
     * @return the explodedDepth
     */
    public int getExplodedDepth(){
        return explodedDepth;
    }
    
    /**
     * 
     * 
     * @param explodedDepth the explodedDepth to set
     */
    public void setExplodedDepth(int explodedDepth){
        this.explodedDepth = explodedDepth;
    }
    /**
     * 
     * 
     * @return the isReDoActivity
     */
    public String getIsReDoActivity(){
        return isReDoActivity;
    }
    
    /**
     * 
     * 
     * @param isReDoActivity the isReDoActivity to set
     */
    public void setIsReDoActivity(String isReDoActivity){
        this.isReDoActivity = isReDoActivity;
    }
    
    /**
     * 
     * 
     * @return the activityClassName
     */
    public String getActivityClassName(){
        return activityClassName;
    }
    
    /**
     * 
     * 
     * @param activityClassName the activityClassName to set
     */
    public void setActivityClassName(String activityClassName){
        this.activityClassName = activityClassName;
    }
    /**
     * 
     * 
     * @return the actualStarted
     */
    public Date getActualStarted(){
        return actualStarted;
    }
    
    /**
     * 
     * 
     * @param actualStarted the actualStarted to set
     */
    public void setActualStarted(Date actualStarted){
        this.actualStarted = actualStarted;
    }
    
    /**
     * 
     * 
     * @return the actualCompleted
     */
    public Date getActualCompleted(){
        return actualCompleted;
    }
    
    /**
     * 
     * 
     * @param actualCompleted the actualCompleted to set
     */
    public void setActualCompleted(Date actualCompleted){
        this.actualCompleted = actualCompleted;
    }
    /**
     * 
     * 
     * @return the duration
     */
    public int getDuration(){
        return duration;
    }
    
    /**
     * 
     * 
     * @param duration the duration to set
     */
    public void setDuration(int duration){
        this.duration = duration;
    }
    
    /**
     * 
     * 
     * @return the activityObid
     */
    public String getActivityObid(){
        return activityObid;
    }
    
    /**
     * 
     * 
     * @param activityObid the activityObid to set
     */
    public void setActivityObid(String activityObid){
        this.activityObid = activityObid;
    }
    
    /**
     * 
     * 
     * @return the activityName
     */
    public String getActivityName(){
        return activityName;
    }
    
    /**
     * 
     * 
     * @param activityName the activityName to set
     */
    public void setActivityName(String activityName){
        this.activityName = activityName;
    }
    
    /**
     * 
     * 
     * @return the activityObidParent
     */
    public String getActivityObidParent(){
        return activityObidParent;
    }
    
    /**
     * 
     * 
     * @param activityObidParent the activityObidParent to set
     */
    public void setActivityObidParent(String activityObidParent){
        this.activityObidParent = activityObidParent;
    }
    
    /**
     * 
     * 
     * @return the activityNameParent
     */
    public String getActivityNameParent(){
        return activityNameParent;
    }
    
    /**
     * 
     * 
     * @param activityNameParent the activityNameParent to set
     */
    public void setActivityNameParent(String activityNameParent){
        this.activityNameParent = activityNameParent;
    }
   
    /**
     * 
     * 
     * @return the earlyStartDate
     */
    public Date getEarlyStartDate(){
        return earlyStartDate;
    }
    
    /**
     * 
     * 
     * @param earlyStartDate the earlyStartDate to set
     */
    public void setEarlyStartDate(Date earlyStartDate){
        this.earlyStartDate = earlyStartDate;
    }
    public void    setEarlyStartDate(String    earlyStartDate){
        this.earlyStartDate = this.omcConvertStr2Date(earlyStartDate);
    }
    /**
     * 
     * 
     * @return the earlyFinishDate
     */
    public Date getEarlyFinishDate(){
        return earlyFinishDate;
    }
    
    /**
     * 
     * 
     * @param earlyFinishDate the earlyFinishDate to set
     */
    public void setEarlyFinishDate(Date earlyFinishDate){
        this.earlyFinishDate = earlyFinishDate;
    }
    public void    setEarlyFinishDate(String    earlyFinishDate){
        this.earlyFinishDate = this.omcConvertStr2Date(earlyFinishDate);
    }
    /**
     * 
     * 
     * @return the previousDependencies
     */
    public List<DependencySimpleVO> getPreviousDependencies(){
        return previousDependencies;
    }
    
    /**
     * 
     * 
     * @param previousDependencies the previousDependencies to set
     */
    public void setPreviousDependencies(List<DependencySimpleVO> previousDependencies){
        this.previousDependencies = previousDependencies;
    }
    
    /**
     * 
     * 
     * @return the nextDependencies
     */
    public List<DependencySimpleVO> getNextDependencies(){
        return nextDependencies;
    }
    
    /**
     * 
     * 
     * @param nextDependencies the nextDependencies to set
     */
    public void setNextDependencies(List<DependencySimpleVO> nextDependencies){
        this.nextDependencies = nextDependencies;
    }
    
    /**
     * 
     * 
     * @return the chlidList
     */
    public List<ActivitySimpleChildVO> getChlidList(){
        return chlidList;
    }
    
    /**
     * 
     * 
     * @param chlidList the chlidList to set
     */
    public void setChlidList(List<ActivitySimpleChildVO> chlidList){
        this.chlidList = chlidList;
    }    
    private Date omcConvertStr2Date(String strDate) {
        Date date = null;
        if(strDate == null || strDate.length() == 0 ) return date;

        StringBuffer format = new StringBuffer();
        
        if(strDate.length() >= 10) {
            String prefix = strDate.substring(0,10);
            
            if( Pattern.matches("[0-9][0-9][0-9][0-9]\\-[0-9][0-9]\\-[0-9][0-9]",prefix)){
                format.append("yyyy-MM-dd");
                if(strDate.length() == 19) format.append(" HH:mm:ss");
                if(strDate.length() == 16) format.append(" HH:mm");
            }else if(Pattern.matches("[0-9][0-9][0-9][0-9]\\/[0-9][0-9]\\/[0-9][0-9]",prefix)){
                format.append("yyyy/MM/dd");
                if(strDate.length() == 19) format.append(" HH:mm:ss");
                if(strDate.length() == 16) format.append(" HH:mm");
            }else if(Pattern.matches("[0-9][0-9]\\-[0-9][0-9]\\-[0-9][0-9][0-9][0-9]",prefix)){
                format.append("MM-dd-yyyy");
                if(strDate.length() == 19) format.append(" HH:mm:ss");
                if(strDate.length() == 16) format.append(" HH:mm");
            }else if(Pattern.matches("[0-9][0-9]\\/[0-9][0-9]\\/[0-9][0-9][0-9][0-9]",prefix)){
                format.append("MM/dd/yyyy");
                if(strDate.length() == 19) format.append(" HH:mm:ss");
                if(strDate.length() == 16) format.append(" HH:mm");
            }else{
                prefix = strDate.substring(1,9);
                if(Pattern.matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]",prefix)){
                    format.append("yyyyMMdd");
                    if(strDate.length() == 17) format.append(" HH:mm:ss");
                    if(strDate.length() == 14) format.append(" HH:mm");
                }
            }
        } else if(strDate.length() == 8) {
            format.append("yyyyMMdd");
        }
        
        SimpleDateFormat transFormat = new SimpleDateFormat(format.toString());
        try {
            date = transFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new FoundationBaseException("[Foundation]Date Format does not support for '" + strDate + "' format.");
        }
        return date;
    }
}
