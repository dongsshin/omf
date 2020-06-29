/**
 * ===========================================
 * System Name : LGE PLM Project
 * Program ID : AnychartTreeDataItemDataVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 7. 20.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.projectbase.wbs.util.model;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 * Class : AnychartTreeDataItemDataVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class AnychartTreeDataItemDataVO {
    private String obid;
    private String id;
    private String name;
    private Long actualStart;
    private String parent;
    private Long actualEnd;
    private Long baselineStart;
    private Long baselineEnd;
    private String progressValue;
    private Integer rowHeight;
    ///private String connectTo;
    //private String connectorType;
    private List<String> previousIdList;
    private List<String> nextIdList;
    
    
    
    public String getObid(){
        return obid;
    }


    
    public void setObid(String obid){
        this.obid = obid;
    }


    public List<String> getPreviousIdList(){
        return previousIdList;
    }

    
    public void setPreviousIdList(List<String> previousIdList){
        this.previousIdList = previousIdList;
    }

    
    public List<String> getNextIdList(){
        return nextIdList;
    }

    
    public void setNextIdList(List<String> nextIdList){
        this.nextIdList = nextIdList;
    }

    public String getId(){
        return id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public Long getActualStart(){
        return actualStart;
    }
    
    public void setActualStart(Long actualStart){
        this.actualStart = actualStart;
    }
    
    public String getParent(){
        return parent;
    }
    
    public void setParent(String parent){
        this.parent = parent;
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
    
    public String getProgressValue(){
        return progressValue;
    }
    
    public void setProgressValue(String progressValue){
        this.progressValue = progressValue;
    }
    
    public Integer getRowHeight(){
        return rowHeight;
    }
    
    public void setRowHeight(Integer rowHeight){
        this.rowHeight = rowHeight;
    }
//    
//    public String getConnectTo(){
//        return connectTo;
//    }
//    
//    public void setConnectTo(String connectTo){
//        this.connectTo = connectTo;
//    }
//    
//    public String getConnectorType(){
//        return connectorType;
//    }
//    
//    public void setConnectorType(String connectorType){
//        this.connectorType = connectorType;
//    }
    
    
}
