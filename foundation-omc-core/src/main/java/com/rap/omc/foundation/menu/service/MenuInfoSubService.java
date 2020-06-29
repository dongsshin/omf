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

import com.rap.omc.foundation.menu.model.MenuItemInfo;

/**
 * <pre>
 * Class : MemuInfoService
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public interface MenuInfoSubService {
    public MenuItemInfo getMenuItemInfo(String menuItem);
}
