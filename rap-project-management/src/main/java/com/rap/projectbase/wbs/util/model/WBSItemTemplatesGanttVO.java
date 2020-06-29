/**
 * ===========================================
 * System Name : LGE PLM Project
 * Program ID : WBSItemTemplatesGanttVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 7. 25.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.projectbase.wbs.util.model;

import java.util.Date;

import rap.api.object.project.model.WBSItemTemplatesVO;

/**
 * <pre>
 * Class : WBSItemTemplatesGanttVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class WBSItemTemplatesGanttVO extends WBSItemTemplatesVO{
    private Date planStarDate;
    private Date planEndDate;
    private Date baselinStarDate;
    private Date baselineEndDate;
    
    public Date getPlanStarDate(){
        return planStarDate;
    }
    
    public void setPlanStarDate(Date planStarDate){
        this.planStarDate = planStarDate;
    }
    
    public Date getPlanEndDate(){
        return planEndDate;
    }
    
    public void setPlanEndDate(Date planEndDate){
        this.planEndDate = planEndDate;
    }
    
    public Date getBaselinStarDate(){
        return baselinStarDate;
    }
    
    public void setBaselinStarDate(Date baselinStarDate){
        this.baselinStarDate = baselinStarDate;
    }
    
    public Date getBaselineEndDate(){
        return baselineEndDate;
    }
    
    public void setBaselineEndDate(Date baselineEndDate){
        this.baselineEndDate = baselineEndDate;
    }

}
