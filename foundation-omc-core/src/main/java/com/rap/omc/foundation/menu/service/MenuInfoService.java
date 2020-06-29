/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : MemuInfoService.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 4. 24.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.foundation.menu.service;

import java.util.List;

import com.rap.omc.foundation.menu.model.CommonMenuSearchVO;
import com.rap.omc.foundation.menu.model.MenuItemInfo;
import com.rap.omc.foundation.menu.model.MenuSubMenuInfo;




/**
 * <pre>
 * Class : MemuInfoService
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public interface MenuInfoService {
    public MenuItemInfo getMenuItemInfo(String menuItem);
    public List<MenuItemInfo> getMenuItemInfoList(String menuItemList);
    public List<MenuSubMenuInfo> getMenuCommandEtcList(CommonMenuSearchVO searchVO);
    public List<MenuSubMenuInfo> getToolBarMenuList(String toolbarNm);
    public List<MenuSubMenuInfo> getToolBarMenuList(List<String> toolbarList);
}
