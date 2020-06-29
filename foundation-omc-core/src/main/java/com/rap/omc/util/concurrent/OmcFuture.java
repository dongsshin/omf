package com.rap.omc.util.concurrent;

import java.util.concurrent.Future;

@SuppressWarnings("rawtypes")
public class OmcFuture{
    private String threadID;
    private Future future;
    private OmcConcurrentCallableAbstract callable;
    
    
    /**
     * @param threadID
     * @param future
     */
    public OmcFuture(String threadID, OmcConcurrentCallableAbstract callable, Future future) {
        super();
        this.threadID = threadID;
        this.callable = callable;
        this.future = future;
    }

    
    /**
     * 
     * 
     * @return the callable
     */
    public OmcConcurrentCallableAbstract getCallable(){
        return callable;
    }

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
     * @return the future
     */
    public Future getFuture(){
        return future;
    }
}
