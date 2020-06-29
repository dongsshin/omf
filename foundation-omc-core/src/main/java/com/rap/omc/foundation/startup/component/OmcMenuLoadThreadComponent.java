/**
 * ===========================================
 * System Name : LGE PLM Project
 * Program ID : MigThread.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 7. 4.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.foundation.startup.component;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.rap.omc.util.foundation.MenuServiceUtil;

/**
 * <pre>
 * Class : MigThread
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
@Component("omcMenuLoadThreadComponent")
@Scope("prototype")
public class OmcMenuLoadThreadComponent implements Runnable{
    private String menuName;
    
    public OmcMenuLoadThreadComponent(String menuName) {
        super();
        this.menuName = menuName;
    }

    public String getMenuName(){
        return menuName;
    }
    
    public void setMenuName(String menuName){
        this.menuName = menuName;
    }
    public OmcMenuLoadThreadComponent() {
        super();
    }
    @Override
    public void run()    {
        try{
           Thread.sleep(500);
        }catch(Exception e){}
        MenuServiceUtil.getMenuItemCashLoad(menuName);
    }
}
