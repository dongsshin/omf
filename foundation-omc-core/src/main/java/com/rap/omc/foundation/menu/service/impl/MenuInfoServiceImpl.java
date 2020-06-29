/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : MemuInfoServiceImpl.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 4. 24.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.foundation.menu.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rap.omc.api.util.StrUtil;
import com.rap.omc.dataaccess.paging.model.PagingList;
import com.rap.omc.foundation.menu.model.CommonMenuSearchVO;
import com.rap.omc.foundation.menu.model.MenuItemInfo;
import com.rap.omc.foundation.menu.model.MenuSubMenuInfo;
import com.rap.omc.foundation.menu.service.MenuInfoService;
import com.rap.omc.foundation.menu.service.MenuInfoSubService;
import com.rap.omc.schema.object.model.OmcSchemaMenuVO;
import com.rap.omc.schema.util.OmcSchemaServiceUtils;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.foundation.MenuServiceUtil;

/**
 * <pre>
 * Class : MemuInfoServiceImpl
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
@Service("menuInfoService")
public class MenuInfoServiceImpl implements MenuInfoService {
    
    
    @Resource(name = "menuInfoSubService")
    private MenuInfoSubService menuInfoSubService;
    
    @Override
    public List<MenuItemInfo> getMenuItemInfoList(String menuItemList){
        List<MenuItemInfo> menuList = new ArrayList<MenuItemInfo>();
        String[] strList = menuItemList.split(",");
        int displayOrder = 1;
        for(int i = 0; i < strList.length; i++){
            MenuItemInfo tmpMenuItem = getMenuItemInfo(strList[i]);
            if(!NullUtil.isNull(tmpMenuItem)){
                tmpMenuItem.setMnuSort(displayOrder);
                menuList.add(tmpMenuItem);
                displayOrder++;
            }
        }
        return menuList;
    }
    @Override
    public MenuItemInfo getMenuItemInfo(String menuItem){
        return(menuInfoSubService.getMenuItemInfo(menuItem));
    }
    @Override
    public List<MenuSubMenuInfo> getMenuCommandEtcList(CommonMenuSearchVO searchVO){
        List<OmcSchemaMenuVO> tempResult = OmcSchemaServiceUtils.getMenuCommandEtcList(searchVO);
        
        List<String> strList= new ArrayList<String>();
        for(OmcSchemaMenuVO vo : tempResult ){
            strList.add(vo.getNames());
        }
        List<MenuSubMenuInfo> explodedMenuList  = MenuServiceUtil.getToolBarMenuList(strList);
        PagingList<MenuSubMenuInfo> pagedList = new PagingList<MenuSubMenuInfo>();
        for (int inx = 0; inx < explodedMenuList.size(); inx++) {
            Object obj = explodedMenuList.get(inx);
            pagedList.add((MenuSubMenuInfo)obj);
        }
        pagedList.setRowSize(searchVO.getRowSize());
        pagedList.setTargetRow(searchVO.getTargetRow());
        pagedList.setCurrentPage(searchVO.getTargetRow(), searchVO.getRowSize());
        pagedList.setRows(((PagingList)tempResult).getRows());
        explodedMenuList = pagedList;
        return explodedMenuList;
    }
    @Override
    public List<MenuSubMenuInfo> getToolBarMenuList(String toolbarNm){
        List<String> toolbarList = new ArrayList<String>();
        toolbarList.add(toolbarNm);
        return(getToolBarMenuList(toolbarList));
    }
    @Override
    public List<MenuSubMenuInfo> getToolBarMenuList(List<String> toolbarList){
        List<MenuSubMenuInfo> menuList = new ArrayList<MenuSubMenuInfo>();
        int seq = 0;
        for(String toolbar : toolbarList){
            MenuItemInfo menuItem = menuInfoSubService.getMenuItemInfo(toolbar);
            if(!NullUtil.isNull(menuItem)){
                getToolBarMenuListSub(menuItem,null,0,StrUtil.LPAD(String.valueOf(seq++), 5, "0"),true,0,menuList);
            }
        }
        return menuList;
    }
    private void getToolBarMenuListSub(MenuItemInfo             menuItem        ,
                                       MenuSubMenuInfo          subMenuItem     ,
                                       int                      currentLevel    ,
                                       String                   uniqueStrParent ,
                                       boolean                  isFirst         ,
                                       int                      level           ,
                                       List<MenuSubMenuInfo>    subMenuList     ){
        if(isFirst){
            menuItem.setUniqueStr(uniqueStrParent);
            menuItem.setMnuLevel(currentLevel);
            subMenuList.add(MenuServiceUtil.convertMenuVO(menuItem));
        }else{
            subMenuList.add(subMenuItem);
        }
        List<MenuSubMenuInfo> tempSubMenuList = menuItem.getChildItemList();
        if(!NullUtil.isNone(tempSubMenuList)){
            int seq = 1;
            String uniqueStr = "";
            for (MenuSubMenuInfo subMenu : tempSubMenuList){
                uniqueStr = uniqueStrParent + StrUtil.LPAD(String.valueOf(seq++), 5, "0");
                subMenu.setUniqueStr(uniqueStr);
                subMenu.setUniqueStrParent(uniqueStrParent);
                subMenu.setMnuLevel(currentLevel+1);
                MenuItemInfo tempMenuItem = menuInfoSubService.getMenuItemInfo(subMenu.getMnuName());
                getToolBarMenuListSub(tempMenuItem,subMenu, currentLevel+1,uniqueStr,false,level+1,subMenuList);
            }
        }
    }
}
