package com.rap.omc.foundation.menu.model;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import com.rap.omc.api.object.model.BusinessObjectRootVO;
import com.rap.omc.foundation.menu.AccessConstants;



public abstract class MenuAccess {
    BusinessObjectMasterVO  userObjVO;
    BusinessObjectRootVO    businessObjVO;
    BusinessObjectRootVO    contextObjVO;
    HashMap<String,Object>  contextMap;
    String                  menuName;
    
    private String contextCallingPostion; 
    private String contextUserDefined01;
    private String contextUserDefined02;
    private String contextUserDefined03;
    private String contextUserDefined04;
    private String contextUserDefined05;
    private String contextUserDefined06;
    private String contextUserDefined07;
    private String contextUserDefined08;
    private String contextUserDefined09;
    private String contextUserDefined10;
    
    public MenuAccess(BusinessObjectMasterVO userObjVO, BusinessObjectRootVO businessObjVO,
            BusinessObjectRootVO contextObjVO, HashMap<String, Object> contextMap, String menuName) {
        super();
        this.userObjVO = userObjVO;
        this.businessObjVO = businessObjVO;
        this.contextObjVO = contextObjVO;
        this.contextMap = contextMap;
        this.menuName = menuName;
        
        this.contextCallingPostion = (String)contextMap.get(AccessConstants.CONTEXT_CALLING_POSITION);
        this.contextUserDefined01  = (String)contextMap.get(AccessConstants.CONTEXT_USER_DEFINED01);
        this.contextUserDefined02  = (String)contextMap.get(AccessConstants.CONTEXT_USER_DEFINED02);
        this.contextUserDefined03  = (String)contextMap.get(AccessConstants.CONTEXT_USER_DEFINED03);
        this.contextUserDefined04  = (String)contextMap.get(AccessConstants.CONTEXT_USER_DEFINED04);
        this.contextUserDefined05  = (String)contextMap.get(AccessConstants.CONTEXT_USER_DEFINED05);
        this.contextUserDefined06  = (String)contextMap.get(AccessConstants.CONTEXT_USER_DEFINED06);
        this.contextUserDefined07  = (String)contextMap.get(AccessConstants.CONTEXT_USER_DEFINED07);
        this.contextUserDefined08  = (String)contextMap.get(AccessConstants.CONTEXT_USER_DEFINED08);
        this.contextUserDefined09  = (String)contextMap.get(AccessConstants.CONTEXT_USER_DEFINED09);
        this.contextUserDefined10  = (String)contextMap.get(AccessConstants.CONTEXT_USER_DEFINED10);
    }

    
    public BusinessObjectRootVO getBusinessObjVO(){
        return businessObjVO;
    }

    
    public void setBusinessObjVO(BusinessObjectRootVO businessObjVO){
        this.businessObjVO = businessObjVO;
    }

    
    public BusinessObjectMasterVO getUserObjVO(){
        return userObjVO;
    }

    
    public BusinessObjectRootVO getContextObjVO(){
        return contextObjVO;
    }

    
    public HashMap<String, Object> getContextMap(){
        return contextMap;
    }

    
    public String getMenuName(){
        return menuName;
    }

    public String getContextCallingPostion(){
        return contextCallingPostion;
    }
    
    public String getContextUserDefined01(){
        return contextUserDefined01;
    }
    
    public String getContextUserDefined02(){
        return contextUserDefined02;
    }
    
    public String getContextUserDefined03(){
        return contextUserDefined03;
    }
    
    public String getContextUserDefined04(){
        return contextUserDefined04;
    }
    
    public String getContextUserDefined05(){
        return contextUserDefined05;
    }
    
    public String getContextUserDefined06(){
        return contextUserDefined06;
    }
    
    public String getContextUserDefined07(){
        return contextUserDefined07;
    }
    
    public String getContextUserDefined08(){
        return contextUserDefined08;
    }
    
    public String getContextUserDefined09(){
        return contextUserDefined09;
    }
    
    public String getContextUserDefined10(){
        return contextUserDefined10;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuAccess.class);
    public abstract boolean validateForCheck(); 
    public abstract boolean checkProcess(); 
    public final boolean checkAccess(){
        LOGGER.debug("checkAccess Started");
        boolean isSuccess = false;
        try{
            LOGGER.debug("validateForCheck Pre");
            
            isSuccess = validateForCheck();
            if(!isSuccess) return isSuccess;
            LOGGER.debug("checkProcess Pre");
            isSuccess = checkProcess();
            LOGGER.debug("checkProcess After");
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return isSuccess;
    }
}
