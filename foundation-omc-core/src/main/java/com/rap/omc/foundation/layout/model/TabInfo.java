package com.rap.omc.foundation.layout.model;

import java.util.List;

import com.rap.omc.foundation.common.model.ModelRootVO;
import com.rap.omc.foundation.menu.model.MenuItemInfo;



public class TabInfo extends ModelRootVO{
    private String obid;
    private long flags;
    private String tabName;
    private String labels;
    private String labelsKr;
    private String linkHerf;
    private String linkUrl;
    private int heights;
    private String subObjectListStr;
    private List<MenuItemInfo> subObjectList;
    private int displaySequence;
    
    public int getDisplaySequence(){
        return displaySequence;
    }

    
    public void setDisplaySequence(int displaySequence){
        this.displaySequence = displaySequence;
    }

    public String getObid(){
        return obid;
    }
    
    public void setObid(String obid){
        this.obid = obid;
    }
    
    public long getFlags(){
        return flags;
    }
    
    public void setFlags(long flags){
        this.flags = flags;
    }
    
    public String getTabName(){
        return tabName;
    }
    
    public void setTabName(String tabName){
        this.tabName = tabName;
    }
    
    public String getLabels(){
        return labels;
    }
    
    public void setLabels(String labels){
        this.labels = labels;
    }
    
    public String getLabelsKr(){
        return labelsKr;
    }
    
    public void setLabelsKr(String labelsKr){
        this.labelsKr = labelsKr;
    }
    
    public String getLinkHerf(){
        return linkHerf;
    }
    
    public void setLinkHerf(String linkHerf){
        this.linkHerf = linkHerf;
    }
    
    public String getLinkUrl(){
        return linkUrl;
    }
    
    public void setLinkUrl(String linkUrl){
        this.linkUrl = linkUrl;
    }
    
    public int getHeights(){
        return heights;
    }
    
    public void setHeights(int heights){
        this.heights = heights;
    }
    
    public String getSubObjectListStr(){
        return subObjectListStr;
    }
    
    public void setSubObjectListStr(String subObjectListStr){
        this.subObjectListStr = subObjectListStr;
    }
    
    public List<MenuItemInfo> getSubObjectList(){
        return subObjectList;
    }
    
    public void setSubObjectList(List<MenuItemInfo> subObjectList){
        this.subObjectList = subObjectList;
    }
    
}
