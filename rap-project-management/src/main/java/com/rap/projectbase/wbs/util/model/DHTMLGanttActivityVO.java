/**
 * ===========================================
 * System Name : LGE PLM Project
 * Program ID : DHTMLGanttActivityVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 7. 24.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.projectbase.wbs.util.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.rap.omc.schema.util.OmcSystemConstants;

/**
 * <pre>
 * Class : DHTMLGanttActivityVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public abstract class DHTMLGanttActivityVO extends DTMTLObjectRootVO{
    private String  id;
    private String  parent;
    private String  text;
    private String  type;
    private int  order;
    private Float   progress;
    private Boolean open;

    private Integer duration;
    
    private String  start_date;
    private String  end_date;
    private String  planned_start;
    private String  planned_end;
    
    private Date    startDate;
    private Date    endDate;
    private Date    plannedStartDate;
    private Date    plannedEndDate;
    
    private String convertDHTMLDate(Date date){
        if(date == null) return "";
        SimpleDateFormat format = new SimpleDateFormat(OmcSystemConstants.OMC_JAVA_DATE_FORMAT_GANTT);
        return format.format(date);  
    }
    public Date getStartDate(){
        return startDate;
    }

    public void setStartDate(Date startDate){
        this.startDate = startDate;
        this.start_date = convertDHTMLDate(startDate);
    }

    
    public Date getEndDate(){
        return endDate;
    }

    
    public void setEndDate(Date endDate){
        this.endDate = endDate;
        this.end_date = convertDHTMLDate(endDate);
    }

    
    public Date getPlannedStartDate(){
        return plannedStartDate;
    }

    
    public void setPlannedStartDate(Date plannedStartDate){
        this.plannedStartDate = plannedStartDate;
        this.planned_start = convertDHTMLDate(plannedStartDate);
    }

    
    public Date getPlannedEndDate(){
        return plannedEndDate;
    }

    
    public void setPlannedEndDate(Date plannedEndDate){
        this.plannedEndDate = plannedEndDate;
        this.planned_end = convertDHTMLDate(plannedEndDate);
    }

    public String getId(){
        return id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public String getText(){
        return text;
    }
    
    public void setText(String text){
        this.text = text;
    }
    
    public String getType(){
        return type;
    }
    
    public void setType(String type){
        this.type = type;
    }
    
    public int getOrder(){
        return order;
    }
    
    public void setOrder(int order){
        this.order = order;
    }
    
    public Float getProgress(){
        return progress;
    }
    
    public void setProgress(Float progress){
        this.progress = progress;
    }
    
    public Boolean getOpen(){
        return open;
    }
    
    public void setOpen(Boolean open){
        this.open = open;
    }
    
    public String getStart_date(){
        return start_date;
    }

    public Integer getDuration(){
        return duration;
    }
    
    public void setDuration(Integer duration){
        this.duration = duration;
    }
    
    public String getEnd_date(){
        return end_date;
    }
    public String getParent(){
        return parent;
    }
    public void setParent(String parent){
        this.parent = parent;
    }
    public String getPlanned_start(){
        return planned_start;
    }
    public String getPlanned_end(){
        return planned_end;
    }
}
