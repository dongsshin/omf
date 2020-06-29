package com.rap.omc.foundation.classes.service;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.rap.omc.foundation.startup.OmcLoaderExecuter;


public class OmcFoundationInitialCashLoader implements ServletContextListener  {
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        OmcLoaderExecuter.load();
//        List<String> allClassList = ClassInfoUtil.getChildClassList("ObjectRoot");
//        for(String className : allClassList){
//            ClassInfoUtil.getClassInfo(className);
//        }
//        MenuServiceUtil.getMenuItemCashLoad("tbarPLMMain");
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce){
        // TODO Auto-generated method stub
    }
   
}
