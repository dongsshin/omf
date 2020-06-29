package com.rap.omc.foundation.startup;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.rap.omc.api.util.spring.SpringFactoryUtil;
import com.rap.omc.foundation.menu.model.MenuItemInfo;
import com.rap.omc.foundation.menu.model.MenuSubMenuInfo;
import com.rap.omc.foundation.startup.component.OmcClassLoadThreadComponent;
import com.rap.omc.foundation.startup.component.OmcMenuLoadThreadComponent;
import com.rap.omc.util.foundation.ClassInfoUtil;
import com.rap.omc.util.foundation.MenuServiceUtil;

public class OmcLoaderExecuter {
    static public void load(){
        ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) SpringFactoryUtil.getBean("taskExecutor");
        List<OmcClassLoadThreadComponent> classLoadThreadComponentList = new ArrayList<OmcClassLoadThreadComponent>();
        List<String> allClassList = ClassInfoUtil.getChildClassList("ObjectRoot");
        int i = 0;
        Set<String> classSet = new HashSet<String>();
        for(String className : allClassList){
            if(i > 0 && i%10 == 0) {
                OmcClassLoadThreadComponent classLoadThreadComponent = (OmcClassLoadThreadComponent) SpringFactoryUtil.getBean(OmcClassLoadThreadComponent.class);
                classLoadThreadComponent.setClassSet(classSet);
                classLoadThreadComponentList.add(classLoadThreadComponent);
                classSet = new HashSet<String>();
            }
            classSet.add(className);
            i++;
        }
        if(classSet.size() > 0){
            OmcClassLoadThreadComponent classLoadThreadComponent = (OmcClassLoadThreadComponent) SpringFactoryUtil.getBean(OmcClassLoadThreadComponent.class);
            classLoadThreadComponent.setClassSet(classSet);
            classLoadThreadComponentList.add(classLoadThreadComponent);
        }
        List<OmcMenuLoadThreadComponent> menuLoadThreadComponentList = new ArrayList<OmcMenuLoadThreadComponent>();
        MenuItemInfo mainMenu = MenuServiceUtil.getMenuItemInfo("tbarPLMMain");
        List<MenuSubMenuInfo> childMenuList = mainMenu.getChildItemList();
        for(MenuSubMenuInfo subMenu : childMenuList){
            OmcMenuLoadThreadComponent menuLoadThreadComponent = (OmcMenuLoadThreadComponent) SpringFactoryUtil.getBean(OmcMenuLoadThreadComponent.class);
            menuLoadThreadComponent.setMenuName(subMenu.getMnuName());
            menuLoadThreadComponentList.add(menuLoadThreadComponent);
        }
        for(OmcClassLoadThreadComponent classLoadThreadComponent : classLoadThreadComponentList){
            taskExecutor.execute(classLoadThreadComponent);
        }
        for(OmcMenuLoadThreadComponent menuLoadThreadComponent : menuLoadThreadComponentList){
            taskExecutor.execute(menuLoadThreadComponent);
        }
        while(true) {
            int activeCnt = taskExecutor.getActiveCount();
//            try{
//                Thread.sleep(100);
//            }catch(Exception e){}
            if(activeCnt == 0) {
                taskExecutor.shutdown();
                break;
            }
        }
    }
}
