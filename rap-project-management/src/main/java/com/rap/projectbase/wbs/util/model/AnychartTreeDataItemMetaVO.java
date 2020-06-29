/**
 * ===========================================
 * System Name : LGE PLM Project
 * Program ID : AnychartTreeDataItemMetaVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 7. 20.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.projectbase.wbs.util.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * Class : AnychartTreeDataItemMetaVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class AnychartTreeDataItemMetaVO {
    private Boolean nc;
    private Integer depth;
    private Long index;
    
    private Long actualStart;
    private Long actualEnd;
    private Long baselineStart;
    private Long baselineEnd;
    private Long autoStart;
    private Long autoEnd;
    private BigDecimal autoProgress;
    
    private Map<String,Object> relBounds;
    private Map<String,Object> labelBounds;
    
    public Boolean getNc(){
        return nc;
    }
    
    public void setNc(Boolean nc){
        this.nc = nc;
    }
    
    public Integer getDepth(){
        return depth;
    }
    
    public void setDepth(Integer depth){
        this.depth = depth;
    }
    
    public Long getIndex(){
        return index;
    }
    
    public void setIndex(Long index){
        this.index = index;
    }
    
    public Long getActualStart(){
        return actualStart;
    }
    
    public void setActualStart(Long actualStart){
        this.actualStart = actualStart;
    }
    
    public Long getActualEnd(){
        return actualEnd;
    }
    
    public void setActualEnd(Long actualEnd){
        this.actualEnd = actualEnd;
    }
    
    public Long getBaselineStart(){
        return baselineStart;
    }
    
    public void setBaselineStart(Long baselineStart){
        this.baselineStart = baselineStart;
    }
    
    public Long getBaselineEnd(){
        return baselineEnd;
    }
    
    public void setBaselineEnd(Long baselineEnd){
        this.baselineEnd = baselineEnd;
    }
    
    public Long getAutoStart(){
        return autoStart;
    }
    
    public void setAutoStart(Long autoStart){
        this.autoStart = autoStart;
    }
    
    public Long getAutoEnd(){
        return autoEnd;
    }
    
    public void setAutoEnd(Long autoEnd){
        this.autoEnd = autoEnd;
    }
    
    public BigDecimal getAutoProgress(){
        return autoProgress;
    }
    
    public void setAutoProgress(BigDecimal autoProgress){
        this.autoProgress = autoProgress;
    }
    
    public Map<String, Object> getRelBounds(){
        return relBounds;
    }
    
    public void setRelBounds(Map<String, Object> relBounds){
        this.relBounds = relBounds;
    }
    
    public Map<String, Object> getLabelBounds(){
        return labelBounds;
    }
    
    public void setLabelBounds(Map<String, Object> labelBounds){
        this.labelBounds = labelBounds;
    }
    
}
