/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : MenuTree1.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 2. 2.  kwanghyui.choi   Initial
 * ===========================================
 */
package com.rap.omc.foundation.ui.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * <pre>
 * Class : ToolbarVO
 * Description : TODO
 * </pre>
 * 
 * @author kwanghyui.choi
 */
public class TreeMenuHelper {
    /**
     * 
     */
    private int statusCode = 0;
    private List<UIItemVO> data;
    private HashMap<String, UIItemVO> menuMap;
    
    public TreeMenuHelper(int statusCode) {
        this.statusCode = statusCode;
        this.data = new ArrayList<UIItemVO>();
        menuMap = new HashMap<String, UIItemVO>();
    }
    
    public int getStatusCode() {
        return this.statusCode;
    }
    
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    
    public List<UIItemVO> getData() {
        return this.data;
    }
    
    public void addMenuItem(String uniqueStrParent, UIItemVO menuItem) {
        UIItemVO parent = uniqueStrParent == null ? null : (UIItemVO) menuMap.get(uniqueStrParent);
        if (parent != null) {
            menuItem.setParent(parent);
            parent.addRecord(menuItem);
        } else {
            data.add(menuItem);
        }
        menuMap.put(menuItem.getId(), menuItem);
    }
    
    public void removeMenuItem(String unigueStrParent, String uniqueStr) {
        MenuItemVO parent = unigueStrParent == null ? null : (MenuItemVO) menuMap.get(unigueStrParent);
        MenuItemVO item = getMenuItem(uniqueStr);
        if (parent != null) {
            parent.removeRecord(parent.getRecord(uniqueStr));
        }
        else {
            if(item != null) {
                data.remove(item);
            }
        }
        menuMap.remove(uniqueStr);
    }

    public MenuItemVO getMenuItem(String uniqueStr) {
        return (MenuItemVO) menuMap.get(uniqueStr);
    }
    
}
