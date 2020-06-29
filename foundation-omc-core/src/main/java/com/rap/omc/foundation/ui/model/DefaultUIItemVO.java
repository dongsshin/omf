/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : DefaultUIItemVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 2. 9.  kwanghyui.choi   Initial
 * ===========================================
 */
package com.rap.omc.foundation.ui.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * <pre>
 * Class : DefaultUIItemVO
 * Description : TODO
 * </pre>
 * 
 * @author kwanghyui.choi
 */
public class DefaultUIItemVO implements UIItemVO{
    private String id;
    private String parentId;
    private String mnuAccess;
    private String mnuResolvedExpression;
    
    private UIItemVO parent = null;
//    private List<UIItemVO> records = new ArrayList<UIItemVO>();
    private List<UIItemVO> children = new ArrayList<UIItemVO>();
    
    /**
     * 
     * 
     * @return the id
     */
    public String getId(){
        return id;
    }
    
    /**
     * 
     * 
     * @param id the id to set
     */
    public void setId(String id){
        this.id = id;
    }
    
    /**
     * 
     * 
     * @return the parentId
     */
    public String getParentId(){
        return parentId;
    }
    
    /**
     * 
     * 
     * @param parentId the parentId to set
     */
    public void setParentId(String parentId){
        this.parentId = (null == parentId || "null".equals(parentId))?"": parentId;
    }
    
    /**
     * 
     * 
     * @return the mnuClass
     */
    public String getMnuAccess(){
        return mnuAccess;
    }
    
    /**
     * 
     * 
     * @param mnuClass the mnuClass to set
     */
    public void setMnuAccess(String mnuAccess){
        this.mnuAccess = (null == mnuAccess || "null".equals(mnuAccess))?"": mnuAccess;
    }
    
    /**
     * 
     * 
     * @return the mnuResolvedExpression
     */
    public String getMnuResolvedExpression(){
        return mnuResolvedExpression;
    }
    
    /**
     * 
     * 
     * @param mnuResolvedExpression the mnuResolvedExpression to set
     */
    public void setMnuResolvedExpression(String mnuResolvedExpression){
        this.mnuResolvedExpression = (null == mnuResolvedExpression || "null".equals(mnuResolvedExpression))?"": mnuResolvedExpression;
    }
    
    public void setParent(UIItemVO parent) {
        this.parent = parent;
    }
    
    public void addRecord(UIItemVO child) {
        children.add(child);
    }
    
    public void removeRecord(UIItemVO uiItemVO) {
        if (children != null) {
            children.remove(uiItemVO);
        }
    }

    public List<UIItemVO> getChildren() {
        return this.children;
    }
    
    public UIItemVO getRecord(String id) {
        if (children != null) {
            for (Iterator<UIItemVO> it = children.iterator(); it.hasNext();) {
                UIItemVO uiItemVO = (UIItemVO) it.next();
                if (uiItemVO.getId().equals(id)) {
                    return uiItemVO;
                }
            }
        }
        return null;
    }
}
