/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : UIServiceImpl.java
 * ===========================================
 * Modify Date    Modifier    Description
 * -------------------------------------------
 * 2015. 1. 26.  kwanghyui.choi   Initial
 * ===========================================
 */
package com.rap.omc.foundation.ui.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rap.omc.api.object.dom.BusinessObjectMaster;
import com.rap.omc.api.object.dom.BusinessObjectRoot;
import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import com.rap.omc.api.object.model.BusinessObjectRootVO;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.omc.ThreadLocalUtil;
import com.rap.omc.foundation.classes.model.ClassInfo;
import com.rap.omc.foundation.common.model.KeyInfo;
import com.rap.omc.foundation.layout.LayoutInfoService;
import com.rap.omc.foundation.layout.model.LayoutInfo;
import com.rap.omc.foundation.layout.model.TabInfo;
import com.rap.omc.foundation.menu.AccessConstants;
import com.rap.omc.foundation.menu.model.MenuItemInfo;
import com.rap.omc.foundation.menu.model.MenuSubMenuInfo;
import com.rap.omc.foundation.menu.service.MenuInfoSubService;
import com.rap.omc.foundation.ui.model.CheckboxItemVO;
import com.rap.omc.foundation.ui.model.CommandButtonAccessVO;
import com.rap.omc.foundation.ui.model.LayoutItemVO;
import com.rap.omc.foundation.ui.model.MenuItemVO;
import com.rap.omc.foundation.ui.model.RadioItemVO;
import com.rap.omc.foundation.ui.model.TreeMenuHelper;
import com.rap.omc.foundation.user.model.UserSession;
import com.rap.omc.framework.exception.ApplicationException;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.util.BaseFoundationUtil;
import com.rap.omc.util.DynamicInvoker;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.expression.OmcExpressionResolverUtil;
import com.rap.omc.util.foundation.ClassInfoUtil;
import com.rap.omc.util.foundation.MenuServiceUtil;

/**
 * <pre>
 * Class : UIServiceImpl
 * Description : TODO
 * </pre>
 *
 * @author kwanghyui.choi
 */
@Service("uiService")
public class UIServiceImpl implements UIService{
    static final Logger LOGGER = LoggerFactory.getLogger(UIServiceImpl.class);

    private static final int RETRIEVE_SUCESS_VALUE = 0;
    
    @Resource(name = "layoutInfoService")
    private LayoutInfoService layoutInfoService;
    
    @Resource(name = "menuInfoSubService")
    private MenuInfoSubService menuInfoSubService;
    

    @Autowired
    UserSession userSession;

    @Override
    public List<MenuSubMenuInfo> getToolBarMenuListPlat(String toolbarNm, String obid, String contextObid){
        return(getToolBarMenuListPlat(toolbarNm,obid,contextObid,null,null,null,10));
    }
    @Override
    public TreeMenuHelper getToolBarMenuList(String toolbarNm, String obid, String contextObid){
        return(getToolBarMenuList(toolbarNm,obid,contextObid,null,null,null,10));
    }
    @Override
    public TreeMenuHelper getToolBarMenuList(String toolbarNm, String obid, String contextObid,String callingPosition){
        return(getToolBarMenuList(toolbarNm,obid,contextObid,callingPosition,null,null,10));
    }
    @Override
    public TreeMenuHelper getToolBarMenuList(String toolbarNm, String obid){
        return(getToolBarMenuList(toolbarNm,obid,null,null,null,null,10));
    }
    @Override
    public TreeMenuHelper getToolBarMenuList(String toolbarNm){
        return(getToolBarMenuList(toolbarNm,null,null,null,null,null,10));
    }
    @SuppressWarnings("unchecked")
    @Override
    public CommandButtonAccessVO resolveCommandButtonAccess ( CommandButtonAccessVO accessVO ) {
        Map<String,Object> contextMap = getContextMap(accessVO.getObid(),accessVO.getContextObid(),accessVO.getCallingPosition(),"","");
        Map<String, Object> objectMap = convertToObject(ThreadLocalUtil.getUserId(), accessVO.getObid(), accessVO.getContextObid());
        BusinessObjectMasterVO   currentUserObj  = (BusinessObjectMasterVO)objectMap.get("currentUserObj");
        List<BusinessObjectRootVO>     bizObjVOList        = (List<BusinessObjectRootVO>)objectMap.get("businessObjectList");
        BusinessObjectRootVO     contextObjVO    = (BusinessObjectRootVO)objectMap.get("contextObject");
        Map<String,Boolean>     resolvedMap      = new HashMap<String,Boolean>();
        MenuItemInfo menuItem = menuInfoSubService.getMenuItemInfo(accessVO.getCommandName());
        CommandButtonAccessVO rtnAccessVO =  (CommandButtonAccessVO)DomUtil.cloneBean(accessVO);
        
        if(NullUtil.isNull(menuItem)) throw new FoundationException("Command(" + accessVO.getCommandName() + ") Not Found.");
        rtnAccessVO.setIsAccessable("FALSE");
        
        boolean resolved = false;
        if(NullUtil.isNone(bizObjVOList)){
            resolved = OmcExpressionResolverUtil.resolveAccessExpression(userSession, menuItem.getMnuAccessExpression(),currentUserObj, null, contextObjVO, contextMap, menuItem.getMnuName(),resolvedMap);

        }else{
            for(BusinessObjectRootVO bizObjVO : bizObjVOList){
                resolved = OmcExpressionResolverUtil.resolveAccessExpression(userSession, menuItem.getMnuAccessExpression(),currentUserObj, bizObjVO, contextObjVO, contextMap, menuItem.getMnuName(),resolvedMap);
                if(!resolved) break;
            }            
        }
        
        if(resolved) rtnAccessVO.setIsAccessable("TRUE");
        if(Bit.isInclude(menuItem.getMnuFlags(),OmcSystemConstants.SYSMNU_FLAG_Hidden)) {
            accessVO.setIsVisible("FALSE");
        }else{
            rtnAccessVO.setIsVisible("TRUE");
        }
        return rtnAccessVO;
    }
    /**
     *
     * @param obid
     * @param contextObid
     * @param callingPosition
     * @return
     * @see lge.prm.common.ui.service.UIService#getClassPopupMenuList(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public TreeMenuHelper getClassPopupMenuList(String obid, String contextObid, String callingPosition){
        //추가 수정 필요함.
        KeyInfo keyInfo = BaseFoundationUtil.getKeyInfo(obid);
        ClassInfo classInfo = ClassInfoUtil.getClassInfo(keyInfo.getClassName());

        String menuName = classInfo.getClassMenu();
        MenuItemInfo menuItem = menuInfoSubService.getMenuItemInfo(menuName);
        if(menuItem == null) return null;
        return(getToolBarMenuList(menuName,obid,contextObid,callingPosition,classInfo.getClassIconReal(),classInfo.getClassIconSmallReal(),5));
    }
    /**
     *
     * @param menuName
     * @param obid
     * @param contextObid
     * @param callingPosition
     * @return
     * @see lge.prm.common.ui.service.UIService#getPopupMenu(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public TreeMenuHelper getPopupMenu(String menuName, String obid, String contextObid, String callingPosition){
        //추가 수정 필요함.
        MenuItemInfo menuItem = menuInfoSubService.getMenuItemInfo(menuName);
        if(menuItem == null) return null;
        return(getToolBarMenuList(menuName,obid,contextObid,callingPosition,null,null,5));
    }

    /**
     * Retrieve Class Menu List()
     *
     * @param obid
     * @return
     * @see lge.prm.common.ui.service.UIService#getClassMenuList(java.lang.String)
     */
    @Override
    public TreeMenuHelper getClassMenuList(String obid) {
        KeyInfo keyInfo = BaseFoundationUtil.getKeyInfo(obid);
   
        ClassInfo classInfo = ClassInfoUtil.getClassInfo(keyInfo.getClassName());
        String menuName = classInfo.getClassMenu();
        
        TreeMenuHelper treeHelper = new TreeMenuHelper(RETRIEVE_SUCESS_VALUE);
        MenuItemInfo menuItem = menuInfoSubService.getMenuItemInfo(menuName);
        
        if(menuItem == null) return treeHelper;
        treeHelper = getToolBarMenuList(menuName,obid,null,null,classInfo.getClassIconReal(),classInfo.getClassIconSmallReal(),1);
        return treeHelper;
    }

    /**
     * Retrieve Structure Menu List()
     *
     * @param obid
     * @return
     * @see lge.prm.common.ui.service.UIService#getStructureMenuList(java.lang.String)
     */
    @Override
    public TreeMenuHelper getStructureMenuList(String obid) {
        String menuName = "";
        BusinessObjectRoot dom = new BusinessObjectRoot(obid);
        ClassInfo classInfo = ClassInfoUtil.getClassInfo(dom.getVo().getClassName());
        List<String> upperClassList = classInfo.getUpperClassList();
        MenuItemInfo menuItem = null;
        for(String className : upperClassList){
            String mnuName = "structure_" + className;
            menuItem = menuInfoSubService.getMenuItemInfo(mnuName);
            if(!NullUtil.isNull(menuItem)) break;
        }
        TreeMenuHelper treeHelper = new TreeMenuHelper(RETRIEVE_SUCESS_VALUE);
        
        if(menuItem == null) return treeHelper;
        menuName = menuItem.getMnuName();
        treeHelper = getToolBarMenuList(menuName,obid,null,null,null,null,1);
        return treeHelper;
    }
    @Override
    public TreeMenuHelper getClassPopupMenu(String obid, String contextObid, String callingPosition) {
        String menuName = "";
        BusinessObjectRoot dom = new BusinessObjectRoot(obid);
        ClassInfo classInfo = ClassInfoUtil.getClassInfo(dom.getVo().getClassName());
        List<String> upperClassList = classInfo.getUpperClassList();
        MenuItemInfo menuItem = null;
        for(String className : upperClassList){
            String mnuName = "mnu_" + className;
            menuItem = menuInfoSubService.getMenuItemInfo(mnuName);
            if(!NullUtil.isNull(menuItem)) break;
        }
        TreeMenuHelper treeHelper = new TreeMenuHelper(RETRIEVE_SUCESS_VALUE);
        if(menuItem == null) return treeHelper;
        menuName = menuItem.getMnuName();
        treeHelper = getToolBarMenuList(menuName,obid,contextObid,callingPosition,null,null,5);
        
        return treeHelper;
    }
     
    /**
     * Retrieve Layout list
     *
     * @param layout
     * @param obid
     * @return
     * @see lge.prm.common.ui.service.UIService#getLayoutList(java.lang.String, java.lang.String)
     */
    @Override
    public TreeMenuHelper getLayoutList(String layout, String obid, String contextObid) {
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("layout", layout);
        input.put("obid", obid);
        input.put("loginUserId", userSession.getUserId());
        input.put("contextObid", StringUtils.isEmpty(contextObid) ? "OBID" : contextObid);
        
        LayoutInfo layoutInfo = layoutInfoService.getLayoutInfo(layout);
        List<LayoutItemVO> result = convertLayoutToLayoutItemList(layoutInfo,obid,contextObid,"",ThreadLocalUtil.getUserId());
        TreeMenuHelper treeHelper = new TreeMenuHelper(RETRIEVE_SUCESS_VALUE);
        for(LayoutItemVO layoutItemVO: result) {
           treeHelper.addMenuItem(layoutItemVO.getParentId(), layoutItemVO);
        }
        return treeHelper;
    }

    @Override
    public boolean checkMenuAccess(String mnuNm ,String obid){
        return checkMenuAccess(mnuNm ,obid, "");
    }
    @SuppressWarnings("unchecked")
    @Override
    public boolean checkMenuAccess(String mnuNm ,String obid, String contextObid){
        Map<String,Object> contextMap = getContextMap(obid,contextObid,"","","");
        Map<String, Object> objectMap = convertToObject(ThreadLocalUtil.getUserId(), obid, contextObid);
        BusinessObjectMasterVO   currentUserObj  = (BusinessObjectMasterVO)objectMap.get("currentUserObj");
        List<BusinessObjectRootVO>     bizObjVOList        = (List<BusinessObjectRootVO>)objectMap.get("businessObjectList");
        BusinessObjectRootVO     contextObjVO    = (BusinessObjectRootVO)objectMap.get("contextObject");
        Map<String,Boolean>     resolvedMap      = new HashMap<String,Boolean>();
        MenuItemInfo menuItem = menuInfoSubService.getMenuItemInfo(mnuNm);
        
        if(NullUtil.isNull(menuItem)) throw new FoundationException("Command(" + mnuNm+ ") Not Found.");
        boolean resolved = false;
        if(NullUtil.isNone(bizObjVOList)){
            resolved = OmcExpressionResolverUtil.resolveAccessExpression(userSession, menuItem.getMnuAccessExpression(),currentUserObj, null, contextObjVO, contextMap, menuItem.getMnuName(),resolvedMap);
        }else{
            for(BusinessObjectRootVO bizObjVO : bizObjVOList){
                resolved = OmcExpressionResolverUtil.resolveAccessExpression(userSession, menuItem.getMnuAccessExpression(),currentUserObj, bizObjVO, contextObjVO, contextMap, menuItem.getMnuName(),resolvedMap);
                if(!resolved) break;
            }            
        }
        return resolved;
    }
    private List<MenuItemVO> setCheckboxGroupData (List<MenuItemVO>result , String obid, String contextObid, UserSession userSession) throws ApplicationException {
        if(NullUtil.isNone(result)) return result;
        for ( int i=0; i<result.size(); i++) {
            MenuItemVO vo = result.get(i);
            if ( vo.getMnuKindName().endsWith("Radio Group") ) {    //// In current, Same Checkbox Group, but will be diff.
                if ( vo.getCallingType().startsWith("PLSQLFunction") && StringUtils.isNotEmpty (vo.getExtraData())) {
                    String extraData = vo.getExtraData();
                    String[] rows = extraData.split (":■:" );
                    for ( String row : rows ) {
                        String[] cols = row.split( ":□:" );
                        RadioItemVO radioVO = new RadioItemVO( cols[0], cols[1], cols[2], cols[3]);
                        vo.addRadioItems(radioVO);
                    }
                    result.set(i, vo);
                } else if ( vo.getCallingType().startsWith("JavaMethod") ) {
                    String href         = vo.getMnuHref();
                    String progName     = href.substring(0, href.indexOf("(")).trim();
                    String className    = progName.substring(0, progName.lastIndexOf("."));
                    String methodName   = progName.substring( progName.lastIndexOf(".")+1, progName.length());
                    String strParams    = href.substring( href.indexOf("(")+1, href.length()-1).trim();
                    Object[] paramObj   = convertStrToParams(obid, contextObid, userSession, strParams);
                    Object obj = DynamicInvoker.getNewInstanceDefault(this, className);
                    vo.setRadioItems(  (List<RadioItemVO>) DynamicInvoker.invokeMethod(obj, methodName, paramObj) );
                } else {
//                    throw new Exception ("Menu Build Error : " + vo.getMnuName() + " is Rdaio Group but not matched CallingType");
                }

            } else
            if ( vo.getMnuKindName().endsWith(" Group") ) {
                if ( vo.getCallingType().startsWith("PLSQLFunction") && StringUtils.isNotEmpty (vo.getExtraData())) {
                    String extraData = vo.getExtraData();
                    String[] rows = extraData.split (":■:" );
                    for ( String row : rows ) {
                        String[] cols = row.split( ":□:" );
                        CheckboxItemVO checkboxVO = new CheckboxItemVO( cols[0], cols[1], cols[2], cols[3]);
                        vo.addCheckboxItems(checkboxVO);
                    }
                    result.set(i, vo);
                } else if ( vo.getCallingType().startsWith("JavaMethod") ) {
                    String href         = vo.getMnuHref();
                    String progName     = href.substring(0, href.indexOf("(")).trim();
                    String className    = progName.substring(0, progName.lastIndexOf("."));
                    String methodName   = progName.substring( progName.lastIndexOf(".")+1, progName.length());
                    String strParams    = href.substring( href.indexOf("(")+1, href.length()-1).trim();
                    Object[] paramObj   = convertStrToParams(obid, contextObid, userSession, strParams);
                    Object obj = DynamicInvoker.getNewInstanceDefault(this, className);
                    vo.setCheckboxItems(  (List<CheckboxItemVO>) DynamicInvoker.invokeMethod(obj, methodName, paramObj) );
                } else {
//                    throw new Exception ("Menu Build Error : " + vo.getMnuName() + " is Checkbox Group but not matched CallingType");
                }
            }
        }
        return result;
    }
    private Object[] convertStrToParams(String obid, String contextObid, UserSession userSession, String strParams) {
        ArrayList<Object> listParams = new ArrayList();
        listParams.add ( obid );
        listParams.add ( contextObid );
        listParams.add ( userSession );

        String[] arr = strParams.split(",");
        String tempStr = "";
        String delim = "";
        for ( String str : arr ) {
            tempStr += "," + str;
            if ( delim.length() < 1 && (str.startsWith("'") || str.startsWith("\""))) {
                delim = str.substring(0, 1);
            }
            if ( str.endsWith(delim)) {
                listParams.add ( convertStringToObject(tempStr.substring(1)));
                tempStr = "";
                delim = "";
            }
        }

        if ( tempStr.length()>0) {
            Object obj =  convertStringToObject(tempStr.substring(1));
            listParams.add ( obj );
        }
        return listParams.toArray();
    }
    public Object convertStringToObject ( String str ) {
        if (str.startsWith("'") || str.startsWith("\"")) {
            return str.substring(1, str.length() - 1);
        } else {
            try { return Integer.parseInt(str); } catch (Exception ex1) {
                try { return Long.parseLong(str); }  catch (Exception ex2) {
                    try { return Double.parseDouble(str); } catch (Exception ex3) {
                        try { return Float.parseFloat(str); } catch (Exception ex4) {
                            return str;
                        }
                    }
                }
            }
        }
    }
    private List<MenuSubMenuInfo>  getToolBarMenuListPlat(String toolbarNm, String obid, String contextObid, String callingPosition, String classIcon, String classIconSmall, int wnatedLevel){
        Map<String,Object> contextMap = getContextMap(obid,contextObid,callingPosition,classIcon,classIconSmall);
        List<MenuSubMenuInfo> subMenuList = new ArrayList<MenuSubMenuInfo>();
        this.getToolBarMenuListSub(toolbarNm,userSession.getUserId(),obid,contextObid,contextMap,subMenuList);
       
        return subMenuList; 
    }
    @Override
    public TreeMenuHelper getToolBarMenuList(String toolbarNm, 
                                                   String obid, 
                                                   String contextObid, 
                                                   String callingPosition, 
                                                   String classIcon, 
                                                   String classIconSmall, 
                                                   int wnatedLevel){
        
        Map<String,Object> contextMap = getContextMap(obid,contextObid,callingPosition,classIcon,classIconSmall);
        List<MenuSubMenuInfo> subMenuList = new ArrayList<MenuSubMenuInfo>();
        this.getToolBarMenuListSub(toolbarNm,userSession.getUserId(),obid,contextObid,contextMap,subMenuList);
        List<MenuItemVO> result = this.convertSubMenuInfoToMenuItemVO(subMenuList);
        try {
            result = this.setCheckboxGroupData (result , obid, contextObid, userSession );
        } catch ( Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        TreeMenuHelper treeHelper = new TreeMenuHelper(RETRIEVE_SUCESS_VALUE);
        if(!NullUtil.isNone(result)){
            for(MenuItemVO menuItemVO: result) {
                treeHelper.addMenuItem(menuItemVO.getParentId(), menuItemVO);
            }
        }
        return treeHelper;
    }

    @SuppressWarnings({ "unchecked" })
    private void getToolBarMenuListSub(String             toolbarNm,
                                            String             userId,
                                            String             obid,
                                            String             contextObid,
                                            Map<String,Object> contextMap,
                                            List<MenuSubMenuInfo> subMenuList){
        Map<String, Object> objectMap = convertToObject(userId, obid, contextObid);
        BusinessObjectMasterVO   currentUserObj  = (BusinessObjectMasterVO)objectMap.get("currentUserObj");
        List<BusinessObjectRootVO> bizObjVOList  = (List<BusinessObjectRootVO>)objectMap.get("businessObjectList");
        BusinessObjectRootVO     contextObjVO    = (BusinessObjectRootVO)objectMap.get("contextObject");
        Map<String,Boolean> resolvedMap = new HashMap<String,Boolean>();
        MenuItemInfo menuItem = menuInfoSubService.getMenuItemInfo(toolbarNm);
        if(NullUtil.isNull(menuItem)) throw new FoundationException("Object(" + toolbarNm + ") Not Found.");
        getToolBarMenuListSub(menuItem,null,0,"00000",userSession,currentUserObj,bizObjVOList,contextObjVO,contextMap,true,0,subMenuList,resolvedMap);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void getToolBarMenuListSub(MenuItemInfo             menuItem        ,
                                            MenuSubMenuInfo          subMenuItem     ,
                                            int                      currentLevel    ,
                                            String                   uniqueStrParent ,
                                            UserSession              userSession     ,
                                            BusinessObjectMasterVO   currentUserObj  ,
                                            List<BusinessObjectRootVO> bizObjVOList  ,
                                            BusinessObjectRootVO     contextObj      ,
                                            Map                      contextMap      ,
                                            boolean                  isFirst         ,
                                            int                      level           ,
                                            List<MenuSubMenuInfo>    subMenuList     ,
                                            Map<String,Boolean>     resolvedMap){
        boolean resolved = true;
        if(Bit.isInclude(menuItem.getMnuFlags(),OmcSystemConstants.SYSMNU_FLAG_IsAccessControlObject)){
            Set<String>  menuSet = (Set<String>)contextMap.get(AccessConstants.CONTEXT_USER_MENU_LIST);
            if(NullUtil.isNull(menuSet)) resolved = false;
            if(!menuSet.contains(menuItem.getMnuName())) resolved = false;
        }
        if(resolved){
            LOGGER.debug("Menu Label                      :{}, Exppression :{}", menuItem.getMnuLabel(),menuItem.getMnuAccessExpression());
            if(NullUtil.isNone(bizObjVOList)){
                resolved = OmcExpressionResolverUtil.resolveAccessExpression(userSession, menuItem.getMnuAccessExpression(),currentUserObj, null, contextObj, contextMap, menuItem.getMnuName(),resolvedMap);
            }else{
                for(BusinessObjectRootVO bizObj :bizObjVOList){
                    resolved = OmcExpressionResolverUtil.resolveAccessExpression(userSession, menuItem.getMnuAccessExpression(),currentUserObj, bizObj, contextObj, contextMap, menuItem.getMnuName(),resolvedMap);
                    if(!resolved) break;
                }                
            }
            LOGGER.debug("Resolved                        :{}", resolved);
        }
        if(!resolved && Bit.isInclude(menuItem.getMnuFlags(), OmcSystemConstants.SYSMNU_FLAG_Hidden)) return;
        if(resolved){
            menuItem.setMnuResolvedExpression("TRUE");
        }else
        {
            menuItem.setMnuResolvedExpression("FALSE");
        }
        if(isFirst){
            menuItem.setUniqueStr(uniqueStrParent);
            menuItem.setMnuLevel(currentLevel);
            if(Bit.isInclude(menuItem.getMnuFlags(), OmcSystemConstants.SYSMNU_KIND_CheckBoxGroup) ||
               Bit.isInclude(menuItem.getMnuFlags(), OmcSystemConstants.SYSMNU_KIND_RadioGroup)    ||
               Bit.isInclude(menuItem.getMnuFlags(), OmcSystemConstants.SYSMNU_KIND_FilterGroup)){
                menuItem.setExtraData(menuItem.getMnuHref());
            }
            subMenuList.add(MenuServiceUtil.convertMenuVO(menuItem));
        }else{
            subMenuItem.setMnuResolvedExpression(menuItem.getMnuResolvedExpression());
            subMenuList.add(subMenuItem);
        }
        if(resolved){
            List<MenuSubMenuInfo> tempSubMenuList = menuItem.getChildItemList();
            if(!NullUtil.isNone(tempSubMenuList)){
                int seq = 1;
                String uniqueStr = "";
                for (MenuSubMenuInfo subMenu : tempSubMenuList){
                    uniqueStr = uniqueStrParent + StrUtil.LPAD(String.valueOf(seq++), 5, "0");
                    subMenu.setUniqueStr(uniqueStr);
                    subMenu.setUniqueStrParent(uniqueStrParent);
                    subMenu.setMnuLevel(currentLevel+1);
                    if(Bit.isInclude(subMenu.getMnuFlags(), OmcSystemConstants.SYSMNU_KIND_CheckBoxGroup) ||
                       Bit.isInclude(subMenu.getMnuFlags(), OmcSystemConstants.SYSMNU_KIND_RadioGroup)    ||
                       Bit.isInclude(subMenu.getMnuFlags(), OmcSystemConstants.SYSMNU_KIND_FilterGroup)){
                        subMenu.setExtraData(subMenu.getMnuHref());
                    }
                    MenuItemInfo tempMenuItem = menuInfoSubService.getMenuItemInfo(subMenu.getMnuName());
                    getToolBarMenuListSub(tempMenuItem,subMenu, currentLevel+1,uniqueStr,userSession,currentUserObj,bizObjVOList,contextObj,contextMap,false,level+1,subMenuList,resolvedMap);
                }
            }
        }
    }
    
    private List<MenuItemVO> convertSubMenuInfoToMenuItemVO(List<MenuSubMenuInfo> subMenuList){
        String locale = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.locale,OmcSystemConstants.OMC_LOCALE_LANG_DEFAULT);
        if(NullUtil.isNone(subMenuList)) return new ArrayList<MenuItemVO>();
        List<MenuItemVO> result = new ArrayList<MenuItemVO>();
        MenuItemVO menuItemVO = new MenuItemVO();
        for(MenuSubMenuInfo subMenu: subMenuList) {
            menuItemVO = new MenuItemVO();
            menuItemVO.setCallingType(subMenu.getMnuCallingType());
            menuItemVO.setControlProperties(subMenu.getControlProperties());
            menuItemVO.setExtraData(subMenu.getExtraData());
            menuItemVO.setId(subMenu.getUniqueStr());
            menuItemVO.setMnuAccess(subMenu.getMnuAccess());
            menuItemVO.setMnuAlt(subMenu.getMnuAlt());
            menuItemVO.setMnuDescriptions(subMenu.getMnuDescriptions());
            menuItemVO.setMnuHref(subMenu.getMnuHref());
            menuItemVO.setMnuImage(subMenu.getMnuImage());
            menuItemVO.setMnuKindName(subMenu.getMnuKindName());
            if(locale.equals(OmcSystemConstants.OMC_LOCALE_LANG_KO)){
                menuItemVO.setMnuLabel(subMenu.getMnuLabelKr());
            }else{
                menuItemVO.setMnuLabel(subMenu.getMnuLabel());
            }
            menuItemVO.setMnuLevel(String.valueOf(subMenu.getMnuLevel()));
            menuItemVO.setMnuModule(subMenu.getMnuModule());
            menuItemVO.setMnuName(subMenu.getMnuName());
            menuItemVO.setMnuPstring01(subMenu.getMnuPstring01());
            menuItemVO.setMnuPstring02(subMenu.getMnuPstring02());
            menuItemVO.setMnuPstring03(subMenu.getMnuPstring03());
            menuItemVO.setMnuPstring04(subMenu.getMnuPstring04());
            menuItemVO.setMnuPstring05(subMenu.getMnuPstring05());
            menuItemVO.setMnuResolvedExpression(subMenu.getMnuResolvedExpression());
            
            menuItemVO.setMnuSort(String.valueOf(subMenu.getMnuSort()));
            menuItemVO.setMnuTarget(subMenu.getMnuTarget());
            menuItemVO.setParentId(subMenu.getUniqueStrParent());
            menuItemVO.setWindowHeight(subMenu.getWindowHeight());
            menuItemVO.setWindowWidth(subMenu.getWindowWidth());
            result.add(menuItemVO);
        }
        return result;
    }
    @SuppressWarnings("unchecked")
    private List<LayoutItemVO> convertLayoutToLayoutItemList(LayoutInfo layoutInfo,String obid,String contextObid,String callingPosition,String userId){
        String locale = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.locale,OmcSystemConstants.OMC_LOCALE_LANG_DEFAULT);
        List<LayoutItemVO> layoutItemList = new ArrayList<LayoutItemVO>();
        if(!NullUtil.isNull(layoutInfo)){
            LayoutItemVO layoutItem = new LayoutItemVO();
            layoutItem.setId("00000");
            layoutItem.setDataLevel(0);
            layoutItem.setKindName("Layout");
            layoutItem.setLayName(layoutInfo.getLayoutName());
            layoutItem.setLayHref(layoutInfo.getLinkHerf());
            layoutItem.setLayAlt(layoutInfo.getLinkUrl());
            if(locale.equals(OmcSystemConstants.OMC_LOCALE_LANG_KO)){
                layoutItem.setLayLabel(layoutInfo.getLabelsKr());
            }else{
                layoutItem.setLayLabel(layoutInfo.getLabels());
            }
            layoutItem.setLayHeight(0);
            layoutItem.setMnuAccess("");
            layoutItem.setMnuResolvedExpression("TRUE");
            layoutItemList.add(layoutItem);
            List<TabInfo> tabInfoList = layoutInfo.getSubObjectList();
            int tabSeq = 1;
            if(!NullUtil.isNone(tabInfoList)){
                for(TabInfo tabInfo : tabInfoList){
                    LayoutItemVO layoutItemTab = new LayoutItemVO();
                    layoutItemTab.setParentId(layoutItem.getId());
                    layoutItemTab.setId(layoutItem.getId() + StrUtil.LPAD(tabSeq, 5, "0"));
                    layoutItemTab.setDataLevel(1);
                    layoutItemTab.setKindName("Tab");
                    layoutItemTab.setLayName(tabInfo.getTabName());
                    layoutItemTab.setLayHref(tabInfo.getLinkHerf());
                    layoutItemTab.setLayAlt(tabInfo.getLinkUrl());
                    if(locale.equals(OmcSystemConstants.OMC_LOCALE_LANG_KO)){
                        layoutItemTab.setLayLabel(tabInfo.getLabelsKr());
                    }else{
                        layoutItemTab.setLayLabel(tabInfo.getLabels());
                    }
                    layoutItemTab.setLayHeight(tabInfo.getHeights());
                    layoutItemTab.setMnuAccess("");
                    layoutItemTab.setMnuResolvedExpression("TRUE");
                    layoutItemList.add(layoutItemTab);
                    List<MenuItemInfo> menuItemInfoList = tabInfo.getSubObjectList();
                    int cmdSeq = 1;
                    if(!NullUtil.isNone(menuItemInfoList)){
                        Map<String,Object> contextMap = getContextMap(obid,contextObid,callingPosition,"","");
                        Map<String, Object> objectMap = convertToObject(userId, obid, contextObid);
                        BusinessObjectMasterVO   currentUserObj  = (BusinessObjectMasterVO)objectMap.get("currentUserObj");
                        List<BusinessObjectRootVO> bizObjVOList  = (List<BusinessObjectRootVO>)objectMap.get("businessObjectList");
                        BusinessObjectRootVO     contextObjVO    = (BusinessObjectRootVO)objectMap.get("contextObject");
                        Map<String,Boolean> resolvedMap = new HashMap<String,Boolean>();
                        for(MenuItemInfo menuItemInfo : menuItemInfoList){
                            boolean resolved = false;
                            if(Bit.isInclude(menuItemInfo.getMnuFlags(),OmcSystemConstants.SYSMNU_FLAG_IsAccessControlObject)){
                                Set<String>  menuSet = (Set<String>)contextMap.get(AccessConstants.CONTEXT_USER_MENU_LIST);
                                if(NullUtil.isNull(menuSet)) resolved = false;
                                if(!menuSet.contains(menuItemInfo.getMnuName())) resolved = false;
                            }
                            if(NullUtil.isNone(bizObjVOList)){
                                resolved = OmcExpressionResolverUtil.resolveAccessExpression(userSession, menuItemInfo.getMnuAccessExpression(),currentUserObj, null, contextObjVO, contextMap, menuItemInfo.getMnuName(),resolvedMap);
                            }else{
                                for(BusinessObjectRootVO bizObjVO : bizObjVOList){
                                    resolved = OmcExpressionResolverUtil.resolveAccessExpression(userSession, menuItemInfo.getMnuAccessExpression(),currentUserObj, bizObjVO, contextObjVO, contextMap, menuItemInfo.getMnuName(),resolvedMap);
                                    if(!resolved) break;
                                }
                            }
                            if(resolved || !Bit.isInclude(menuItemInfo.getMnuFlags(),OmcSystemConstants.SYSMNU_FLAG_Hidden)) {
                                LayoutItemVO layoutItemCmd = new LayoutItemVO();
                                layoutItemCmd.setParentId(layoutItemTab.getId());
                                layoutItemCmd.setId(layoutItemTab.getId() + StrUtil.LPAD(cmdSeq, 5, "0"));
                                layoutItemCmd.setDataLevel(2);
                                layoutItemCmd.setKindName("Command");
                                layoutItemCmd.setLayName(menuItemInfo.getMnuName());
                                layoutItemCmd.setLayHref(menuItemInfo.getMnuHref());
                                layoutItemCmd.setLayAlt(menuItemInfo.getMnuAlt());
                                if(locale.equals(OmcSystemConstants.OMC_LOCALE_LANG_KO)){
                                    layoutItemCmd.setLayLabel(menuItemInfo.getMnuLabelKr());
                                }else{
                                    layoutItemCmd.setLayLabel(menuItemInfo.getMnuLabel());
                                }
                                layoutItemCmd.setLayHeight(0);
                                layoutItemCmd.setMnuAccess("");
                                if(resolved){
                                    layoutItemCmd.setMnuResolvedExpression("TRUE");
                                }else{
                                    layoutItemCmd.setMnuResolvedExpression("FALSE");
                                }
                                layoutItemList.add(layoutItemCmd);
                            }
                        }
                    }
                }
            }
        }
        return layoutItemList;
    }
    private Map<String,Object> getContextMap(String obid, 
                                             String contextObid, 
                                             String callingPosition, 
                                             String classIcon, 
                                             String classIconSmall){
        Map<String,Object> contextMap = new HashMap<String,Object>();
        if(!StrUtil.isEmpty(callingPosition)) contextMap.put(AccessConstants.CONTEXT_CALLING_POSITION, callingPosition);
        if(!StrUtil.isEmpty(classIcon))       contextMap.put(AccessConstants.CONTEXT_CLASS_ICON, classIcon);
        if(!StrUtil.isEmpty(classIconSmall))  contextMap.put(AccessConstants.CONTEXT_CLASS_ICON_SMALL, classIconSmall);

        contextMap.put(AccessConstants.CONTEXT_USER_PROPERTY, userSession.getPropertyMap());
        contextMap.put(AccessConstants.CONTEXT_USER_ROLE_LIST, userSession.getRoleSet());
        contextMap.put(AccessConstants.CONTEXT_USER_GROUP_LIST, userSession.getGroupSet());
        contextMap.put(AccessConstants.CONTEXT_USER_MENU_LIST, userSession.getAccessibleMenuSet());
        contextMap.put(AccessConstants.CONTEXT_USER_MANAGEMENT_ROLE_LIST, userSession.getManagementRoleSet());

        if(!StrUtil.isEmpty(obid)){
            String[] strArray = obid.split(",");
            obid = strArray[0];
            if(strArray.length > 1){
                for(int i = 1; i < strArray.length; i++){
                    String key = AccessConstants.CONTEXT_USER_DEFINED_HEADER + StrUtil.LPAD(i, 2, "0");
                    contextMap.put(key, strArray[i]);
                }
            }
        }
        return contextMap;
    }
    private Map<String, Object> convertToObject(String userId, String obid, String contextObid){
        
        BusinessObjectMasterVO   currentUserObj  = BusinessObjectMaster.findBusinessObjectMaster("Users", userId);
        List<BusinessObjectRootVO>     bizObjVOList     = new ArrayList<BusinessObjectRootVO>();
        BusinessObjectRootVO     contextObjVO = null;

        if(NullUtil.notNone(obid) && !obid.toUpperCase().equals("OBID")){
            try{
                String parm[] = obid.split(",");
                System.out.println(parm[0]);
                String obidArray[] = parm[0].split(Pattern.quote("|"));
                for(int i = 0; i < obidArray.length; i++){
                    BusinessObjectRoot bizObj  = new BusinessObjectRoot(obidArray[i]);
                    bizObjVOList.add(bizObj.getVo());
                }

            }catch(Exception e){
                LOGGER.debug("[Foundation.UIServiceImpl.convertToObject][Warning]Can be Skipped.Error. Maybe Object(Object obid:" + obid + ") Not found: " + e.getMessage());
                throw e;
            }
        }
        if(NullUtil.notNone(contextObid) && !contextObid.toUpperCase().equals("OBID")){
            try{
                BusinessObjectRoot contextObj  = new BusinessObjectRoot(contextObid);
                contextObjVO = contextObj.getVo();                
            }catch(Exception e){
                LOGGER.debug("[Foundation.UIServiceImpl.convertToObject][Warning]Can be Skipped.Error. Maybe Object(Context obid:" + contextObid + ") Not found: " + e.getMessage());
                throw e;
            }
        }
        Map<String, Object> objectMap = new HashMap<String, Object>();
        objectMap.put("currentUserObj", currentUserObj);
        objectMap.put("businessObjectList", bizObjVOList);
        objectMap.put("contextObject", contextObjVO);
        return objectMap;
    }
	@Override
	public HashMap<String, Integer> getLeftMenuCommand() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public HashMap<String, Integer> getPdrLeftMenuCommand() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public HashMap<String, Integer> getPdmsLeftMenuCommand() {
		// TODO Auto-generated method stub
		return null;
	}

}
