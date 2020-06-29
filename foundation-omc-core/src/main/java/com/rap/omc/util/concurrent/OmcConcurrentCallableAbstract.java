/**
 * ===========================================
 * System Name : LGE PLM Project
 * Program ID : ConcurrentCallableAbstract.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2018. 12. 19.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.util.concurrent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.schema.util.OmcUniqueIDGenerator;


/**
 * <pre>
 * Class : ConcurrentCallableAbstract
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public abstract class OmcConcurrentCallableAbstract implements Callable<Map<String,Object>>{
    private static final Logger LOGGER = LoggerFactory.getLogger(OmcConcurrentExecuter.class);
    private Object inObj = null;
    private String threadID = OmcUniqueIDGenerator.getObid();
    
    /**
     * 
     * 
     * @return the threadID
     */
    public String getThreadID(){
        return threadID;
    }
    /**
     * 
     * 
     * @return the inObj
     */
    public Object getInObj(){
        return inObj;
    }
    /**
     * @param inObj
     */
    public OmcConcurrentCallableAbstract(Object inObj) {
        super();
        this.inObj = inObj;
    }
    @Override
    public Map<String,Object> call(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put(OmcConcurrentConstants.STATUS, false);
        map.put(OmcConcurrentConstants.MESSAGE, "Started");
        map.put(OmcConcurrentConstants.SYSTEM_MESSAGE, "");
        map.put(OmcConcurrentConstants.THREAD_KEY, this.threadID);
        try{
            List<Object> list = executeProcess();
            map.put(OmcConcurrentConstants.STATUS, true);
            map.put(OmcConcurrentConstants.RESULT, list);
            map.put(OmcConcurrentConstants.MESSAGE, "Successful");
            map.put(OmcConcurrentConstants.SYSTEM_MESSAGE, "Successful");
        }catch(InterruptedException e){
            e.printStackTrace();
            map.put(OmcConcurrentConstants.STATUS, false);
            map.put(OmcConcurrentConstants.MESSAGE, "Interrupted");
            map.put(OmcConcurrentConstants.SYSTEM_MESSAGE, e.getMessage());
            Thread.currentThread().interrupt();
        }catch(Exception e){
            e.printStackTrace();
            map.put(OmcConcurrentConstants.STATUS, true);
            map.put(OmcConcurrentConstants.MESSAGE, "Failed");
            map.put(OmcConcurrentConstants.SYSTEM_MESSAGE, e.getMessage());
        }
        return map;
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private <T> List<T> executeProcess() throws InterruptedException{
        List list = excuteBusinessProcess();
        List<T> result = new ArrayList<T>();
        for(Object obj : list) result.add((T)obj);
        return result;
    }
    abstract protected <T> List<T> excuteBusinessProcess();
}