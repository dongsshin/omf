/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : CacheUtil.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2014. 12. 24. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.util.foundation;

import java.util.List;

import com.rap.omc.foundation.menu.model.CommonMenuSearchVO;
import com.rap.omc.foundation.menu.model.MenuItemInfo;
import com.rap.omc.foundation.menu.model.MenuSubMenuInfo;
import com.rap.omc.foundation.menu.service.MenuInfoService;
import com.rap.omc.foundation.menu.service.MenuInfoSubService;
import com.rap.omc.api.util.spring.SpringFactoryUtil;


/**
 * 
 * <pre>
 * Class : MenuServiceUtil
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class MenuServiceUtil{

    private MenuInfoService menuInfoService;
    private MenuInfoSubService menuInfoSubService;

    private static MenuServiceUtil sInstance;

    /**
     * Singleton을 구성된 getInstance() method
     */
    private synchronized static MenuServiceUtil getInstance(){
        if (sInstance == null) {
            sInstance = new MenuServiceUtil();
            sInstance.menuInfoService    = (MenuInfoService)SpringFactoryUtil.getBean("menuInfoService");
            sInstance.menuInfoSubService = (MenuInfoSubService)SpringFactoryUtil.getBean("menuInfoSubService");
        }
        return sInstance;
    }
    
    public static MenuItemInfo getMenuItemInfo(String menuItem){
        MenuItemInfo menuItemInfo = getInstance().menuInfoSubService.getMenuItemInfo(menuItem);
        return menuItemInfo;
    }
    public static List<MenuItemInfo> getMenuItemInfoList(String menuItemList){
        return getInstance().menuInfoService.getMenuItemInfoList(menuItemList);
    }
    public static void getMenuItemCashLoad(String menuItem){
        MenuItemInfo menuItemInfo = getMenuItemInfo(menuItem);
        List<MenuSubMenuInfo> tempSubMenuList = menuItemInfo.getChildItemList();
        for (MenuSubMenuInfo subMenu : tempSubMenuList){
            getMenuItemCashLoad(  subMenu.getMnuName());
        }
    }
    public static List<MenuSubMenuInfo> getMenuCommandEtcList(CommonMenuSearchVO searchVO){
        return getInstance().menuInfoService.getMenuCommandEtcList(searchVO);
    }
    public static List<MenuSubMenuInfo> getToolBarMenuList(String toolbarNm){
        return getInstance().menuInfoService.getToolBarMenuList(toolbarNm);
    }
    public static List<MenuSubMenuInfo> getToolBarMenuList(List<String> toolbarList){
        return getInstance().menuInfoService.getToolBarMenuList(toolbarList);
    }
    public static MenuSubMenuInfo convertMenuVO(MenuItemInfo menuItem){
        MenuSubMenuInfo menuItemVO = new MenuSubMenuInfo();
        menuItemVO.setObid                   (menuItem.getObid                 ());
        menuItemVO.setMnuLevel               (menuItem.getMnuLevel             ());
        menuItemVO.setUniqueStrParent        (menuItem.getUniqueStrParent      ());
        menuItemVO.setUniqueStr              (menuItem.getUniqueStr            ());
        menuItemVO.setMnuFlags               (menuItem.getMnuFlags             ());
        menuItemVO.setMnuKind                (menuItem.getMnuKind              ());
        menuItemVO.setMnuKindName            (menuItem.getMnuKindName          ());
        menuItemVO.setMnuName                (menuItem.getMnuName              ());
        menuItemVO.setMnuCallingType         (menuItem.getMnuCallingType       ());
        menuItemVO.setMnuDescriptions        (menuItem.getMnuDescriptions      ());
        menuItemVO.setMnuLabel               (menuItem.getMnuLabel             ());
        menuItemVO.setMnuLabelKr             (menuItem.getMnuLabelKr           ());
        menuItemVO.setMnuHref                (menuItem.getMnuHref              ());
        menuItemVO.setMnuAlt                 (menuItem.getMnuAlt               ());
        menuItemVO.setMnuImage               (menuItem.getMnuImage             ());
        menuItemVO.setMnuSort                (menuItem.getMnuSort              ());
        menuItemVO.setMnuAccess              (menuItem.getMnuAccess            ());
        menuItemVO.setMnuAccessExpression    (menuItem.getMnuAccessExpression  ());
        menuItemVO.setMnuResolvedExpression  (menuItem.getMnuResolvedExpression());
        menuItemVO.setMnuModule              (menuItem.getMnuModule            ());
        menuItemVO.setMnuPstring01           (menuItem.getMnuPstring01         ());
        menuItemVO.setMnuPstring02           (menuItem.getMnuPstring02         ());
        menuItemVO.setMnuPstring03           (menuItem.getMnuPstring03         ());
        menuItemVO.setMnuPstring04           (menuItem.getMnuPstring04         ());
        menuItemVO.setMnuPstring05           (menuItem.getMnuPstring05         ());
        menuItemVO.setTreeFlags              (menuItem.getTreeFlags            ());
        menuItemVO.setTreeAttribute01        (menuItem.getTreeAttribute01      ());
        menuItemVO.setTreeAttribute02        (menuItem.getTreeAttribute02      ());
        menuItemVO.setClassIcon              (menuItem.getClassIcon            ());
        menuItemVO.setClassIconSmall         (menuItem.getClassIconSmall       ());
        menuItemVO.setExtraData              (menuItem.getExtraData            ());
        menuItemVO.setMnuTarget              (menuItem.getMnuTarget            ());
        menuItemVO.setWindowWidth            (menuItem.getWindowWidth          ());
        menuItemVO.setWindowHeight           (menuItem.getWindowHeight         ());
        menuItemVO.setControlProperties      (menuItem.getControlProperties    ());
        return menuItemVO;
    }
}
