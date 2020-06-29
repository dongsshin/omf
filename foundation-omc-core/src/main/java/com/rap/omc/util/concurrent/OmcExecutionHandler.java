package com.rap.omc.util.concurrent;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class OmcExecutionHandler implements RejectedExecutionHandler{
    @Override
    public void rejectedExecution(Runnable arg0, ThreadPoolExecutor arg1){
        System.out.println("rejectedExecution called");
    }

}
