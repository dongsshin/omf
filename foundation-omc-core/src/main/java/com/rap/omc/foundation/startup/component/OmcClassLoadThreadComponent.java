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

import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.rap.omc.util.foundation.ClassInfoUtil;

/**
 * <pre>
 * Class : MigThread
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
@Component("omcClassLoadThreadComponent")
@Scope("prototype")
public class OmcClassLoadThreadComponent implements Runnable{
    private Set<String> classSet;
    
    
    public Set<String> getClassSet(){
        return classSet;
    }
    
    public void setClassSet(Set<String> classSet){
        this.classSet = classSet;
    }
    public OmcClassLoadThreadComponent(Set<String> classSet) {
        super();
        this.classSet = classSet;
    }
    public OmcClassLoadThreadComponent() {
        super();
    }
    @Override
    public void run()    {
        try{
           Thread.sleep(500);
        }catch(Exception e){}
        for(String className : this.classSet){
            ClassInfoUtil.getClassInfo(className);
        }
    }
}
