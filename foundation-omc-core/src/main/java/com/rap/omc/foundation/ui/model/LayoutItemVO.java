/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : LayoutItemVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 2. 9.  kwanghyui.choi   Initial
 * ===========================================
 */
package com.rap.omc.foundation.ui.model;


/**
 * <pre>
 * Class : LayoutItemVO
 * Description : TODO
 * </pre>
 * 
 * @author kwanghyui.choi
 */
public class LayoutItemVO extends DefaultUIItemVO{
    private int dataLevel;
    private String kindName;
    private String layName;
    private String layHref;
    private String layAlt;
    private int layHeight;
    private String layLabel;
    

    /**
     * 
     * 
     * @return the dataLevl
     */
    public int getDataLevel(){
        return dataLevel;
    }
    
    /**
     * 
     * 
     * @param dataLevl the dataLevl to set
     */
    public void setDataLevel(int dataLevel){
        this.dataLevel = dataLevel;
    }
    
    /**
     * 
     * 
     * @return the kindName
     */
    public String getKindName(){
        return kindName;
    }
    
    /**
     * 
     * 
     * @param kindName the kindName to set
     */
    public void setKindName(String kindName){
        this.kindName = (null == kindName || "null".equals(kindName))?"": kindName;
    }
    
    /**
     * 
     * 
     * @return the layName
     */
    public String getLayName(){
        return layName;
    }
    
    /**
     * 
     * 
     * @param layName the layName to set
     */
    public void setLayName(String layName){
        this.layName = (null == layName || "null".equals(layName))?"": layName;
    }
    
    /**
     * 
     * 
     * @return the layHref
     */
    public String getLayHref(){
        return layHref;
    }
    
    /**
     * 
     * 
     * @param layHref the layHref to set
     */
    public void setLayHref(String layHref){
        this.layHref = (null == layHref || "null".equals(layHref))?"": layHref;
    }
    
    /**
     * 
     * 
     * @return the layAlt
     */
    public String getLayAlt(){
        return layAlt;
    }
    
    /**
     * 
     * 
     * @param layAlt the layAlt to set
     */
    public void setLayAlt(String layAlt){
        this.layAlt = (null == layAlt || "null".equals(layAlt))?"": layAlt;
    }
    
    /**
     * 
     * 
     * @return the layHeight
     */
    public int getLayHeight(){
        return layHeight;
    }
    
    /**
     * 
     * 
     * @param layHeight the layHeight to set
     */
    public void setLayHeight(int layHeight){
        this.layHeight = layHeight;
    }
    
    /**
     * 
     * 
     * @return the layLabel
     */
    public String getLayLabel(){
        return layLabel;
    }
    
    /**
     * 
     * 
     * @param layLabel the layLabel to set
     */
    public void setLayLabel(String layLabel){
        this.layLabel = (null == layLabel || "null".equals(layLabel))?"": layLabel;
    }
}
