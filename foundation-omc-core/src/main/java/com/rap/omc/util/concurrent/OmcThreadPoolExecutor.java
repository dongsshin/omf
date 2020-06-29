package com.rap.omc.util.concurrent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OmcThreadPoolExecutor extends ThreadPoolExecutor{
    private static final Logger LOGGER = LoggerFactory.getLogger(OmcConcurrentExecuter.class);
    
    public OmcThreadPoolExecutor(int arg0, int arg1, long arg2, TimeUnit arg4, BlockingQueue<Runnable> arg5) {
        super(arg0, arg1, arg2, arg4, arg5);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public OmcThreadPoolExecutor(int arg0) {
        this(arg0, arg0, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
        this.setRejectedExecutionHandler(new OmcExecutionHandler());
    }
    private Map<Runnable, Thread> executingThreads = new HashMap<Runnable, Thread>();
    @Override
    protected synchronized void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        executingThreads.put(r, t);
    }
    @Override
    protected synchronized void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        if(executingThreads.containsKey(r)) {
            executingThreads.remove(r);
        }
    }
    @SuppressWarnings("deprecation")
    @Override
    public synchronized List<Runnable> shutdownNow() {
        List<Runnable> runnables = super.shutdownNow();
        for(Thread t : executingThreads.values()) {
            try{
                LOGGER.warn("Thread : " + t.getName() + " is Killed");
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                t.stop();
            }
            catch (final ThreadDeath ex) {
                System.out.println("Ugh I'm dead");
                throw ex;
            }
        }
        return runnables;
    }
}
