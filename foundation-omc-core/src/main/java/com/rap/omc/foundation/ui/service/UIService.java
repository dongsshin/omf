package com.rap.omc.foundation.ui.service;

import java.util.HashMap;
import java.util.List;

import com.rap.omc.foundation.menu.model.MenuSubMenuInfo;
import com.rap.omc.foundation.ui.model.CommandButtonAccessVO;
import com.rap.omc.foundation.ui.model.TreeMenuHelper;
public interface UIService {
    
    public TreeMenuHelper getClassPopupMenu(String obid, String contextObid, String callingPosition);
    
    public List<MenuSubMenuInfo> getToolBarMenuListPlat(String toolbarNm, String obid, String contextObid);
    
    public TreeMenuHelper getToolBarMenuList(String toolbarNm, String obid, String contextObid, String callingPosition, String classIcon, String classIconSmall, int wnatedLevel);
    
    public TreeMenuHelper getToolBarMenuList(String toolBarNm, String obid, String contextObid);

    public TreeMenuHelper getToolBarMenuList(String toolbarNm, String obid, String contextObid,String callingPosition);
    
    public TreeMenuHelper getToolBarMenuList(String toolbarNm, String obid);
    
    public TreeMenuHelper getToolBarMenuList(String toolbarNm);
    
    public TreeMenuHelper getClassMenuList(String obid);
    
    public TreeMenuHelper getStructureMenuList(String obid);
    
    public TreeMenuHelper getClassPopupMenuList(String obid, String contextObid, String callingPosition);
    
    public TreeMenuHelper getPopupMenu(String menuName, String obid, String contextObid, String callingPosition);
    
    public TreeMenuHelper getLayoutList(String layout, String obid, String contextObid);
    
    public boolean checkMenuAccess(String mnuNm ,String obid);
    
    public boolean checkMenuAccess(String mnuNm ,String obid, String contextObid);

    public CommandButtonAccessVO resolveCommandButtonAccess ( CommandButtonAccessVO accessVO );
    
    public HashMap<String, Integer> getLeftMenuCommand();
    
    public HashMap<String, Integer> getPdrLeftMenuCommand();
    
    public HashMap<String, Integer> getPdmsLeftMenuCommand();
}
