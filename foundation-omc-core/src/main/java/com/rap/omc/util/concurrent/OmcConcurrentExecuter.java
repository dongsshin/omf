/**
 * ===========================================
 * System Name : LGE PLM Project
 * Program ID : ConcurrentExecuter.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2018. 12. 19.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.util.concurrent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.util.NullUtil;



/**
 * <pre>
 * Class : ConcurrentExecuter
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcConcurrentExecuter {
    private static final Logger LOGGER = LoggerFactory.getLogger(OmcConcurrentExecuter.class);
    private OmcThreadPoolExecutor executor;
    private int threadCount = 1;
    private int maxProcessingMilliSeconds = 1*1000;
    private boolean isExecuted = false;
    private Set<String> keySet = new HashSet<String>();
    private boolean isForceShutdown = false;
    private List<Callable<Map<String,Object>>> callableList  = new ArrayList<Callable<Map<String,Object>>>();
    private List<Future<Map<String,Object>>>   futures = new ArrayList<Future<Map<String,Object>>>();
    private Map<String,OmcFuture>   futureMap = new HashMap<String,OmcFuture>();
    
    public Set<String> keySet(){
        return keySet;
    }
    /**
     * 
     * 
     * @return the isExecuted
     */
    public boolean isExecuted(){
        return isExecuted;
    }
    
    
    /**
     * 
     * 
     * @return the isForceShutdown
     */
    public boolean isForceShutdown(){
        return isForceShutdown;
    }
    /**
     * 
     * 
     * @return the futures
     */
    public List<Future<Map<String, Object>>> getFutures(){
        return futures;
    }
    /**
     * 
     * @param threadCount 작업을 수행할 Thread Count, 작업갯수가 Over되더라도 Queue에 있다가 수행되어짐.
     * @param maxProcessingSeconds 수행 시간이 Over되어지면 Stop되어짐.
     */
    public OmcConcurrentExecuter(int threadCount, int maxProcessingSeconds) {
        super();
        this.threadCount = threadCount;
        this.maxProcessingMilliSeconds = maxProcessingSeconds*1000;
        executor = new OmcThreadPoolExecutor(this.threadCount);
    }
    public void addCallable(OmcConcurrentCallableAbstract callable){
        callableList.add(callable);
        keySet.add(callable.getThreadID());
    }
    public void addCallableAll(List<? extends OmcConcurrentCallableAbstract> callableList){
        for(OmcConcurrentCallableAbstract callable : callableList){
            addCallable(callable);
        }
    }
    /**
     * 실제 작업을 Execute하는 부분
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void execute(){
        long startMillis = System.currentTimeMillis();
        for(Callable callable : callableList){
            Future<Map<String,Object>> result = executor.submit(callable);
            futures.add(result);
            String threadKey = ((OmcConcurrentCallableAbstract)callable).getThreadID();
            futureMap.put(threadKey,new OmcFuture(((OmcConcurrentCallableAbstract)callable).getThreadID(), (OmcConcurrentCallableAbstract)callable, result));
        }
        executor.shutdown();
        boolean isRunning = true;
        while(isRunning){
            LOGGER.info("Process Status : " + executor.getCompletedTaskCount() + "/" + executor.getTaskCount());
            long currenttMillis = System.currentTimeMillis();
            if(maxProcessingMilliSeconds < currenttMillis - startMillis){
                if(executor.getTaskCount() > executor.getCompletedTaskCount()){
                    //Queue에 싸여있는것 먼저 Cancel처리 한다.
                    for(Future<Map<String,Object>> future : futures){
                        if(!future.isDone() && !future.isCancelled()) {
                            future.cancel(true);
                        }
                    }
                    int interrutedCount = shutdownAndForceStop(1);
                    if(interrutedCount > 0) LOGGER.warn("There can be interruted Thread! Count : " + interrutedCount);
                }
                break;
            }
            isRunning = false;
            for(Future future : futures) if(!future.isDone()) {isRunning = true;break;}
            if(isRunning){
                try {
                    TimeUnit.MILLISECONDS.sleep(OmcConcurrentConstants.THREAD_CHECK_INTERVAL_MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        isExecuted = true;
    }
    /**
     * 모든 작업이 수행되었는짐 Check하는 부분
     *
     * @return
     */
    public boolean allCompleted(){
        for(Future<?> future : this.futures){
            if(!future.isDone()) return false;
        }
        return true;
    }
    /**
     * 각작업 상태를 Console에 뿌림
     */
    public void printProcessedStatus(){
        //if(!isExecuted) throw new FoundationException("Execute is not executed.");
        StringBuffer strBuf = new StringBuffer();
        for(Future<?> future : this.futures){
            strBuf.setLength(0);
            strBuf.append("future.isDone(): " + future.isDone());
            strBuf.append(",").append("future.isCancelled(): " + future.isCancelled());
            if(future.isDone() && !future.isCancelled()){
                try {
                    Map<?, ?> returnMap = (Map<?, ?>)future.get();
                    strBuf.append(",").append("status: " + (Boolean)returnMap.get(OmcConcurrentConstants.STATUS));
                } catch (InterruptedException e) {
                    strBuf.append(",").append("get Error: May be InterruptedException");
                    //e.printStackTrace();
                } catch (ExecutionException e) {
                    //e.printStackTrace();
                    strBuf.append(",").append("get Error: May be ExecutionException");
                }catch (Exception e) {
                    //e.printStackTrace();
                    strBuf.append(",").append("get Error: May be Exception");
                }
            }
            LOGGER.debug(strBuf.toString());
        }
    }
    /**
     * 모든 작업이 성공적으로 끝났는지 Check하는 것
     *
     * @return
     */
    public boolean allSuccessfull(){
        if(!allCompleted()) return false;
        for(Future<?> future : this.futures){
            if(future.isCancelled()) return false;
            try {
                Map<?, ?> returnMap = (Map<?, ?>)future.get();
                if(!(Boolean)returnMap.get(OmcConcurrentConstants.STATUS)) return false;
            } catch (InterruptedException e) {
                return false;
            } catch (ExecutionException e) {
                return false;
            }catch (Exception e) {
                return false;
            }
        }
        return true;
    }
    /**
     * 작업결과 전체를 Return함. 실제 성공적으로 작업되어진 것에 대해서 Return함. 비정상 수행되어진것은 Skip함.
     *
     * @return
     */
    @SuppressWarnings({"unchecked" })
    public <T> List<T> getResultAll(){
        List<Object> list = new ArrayList<Object>();
        List<T> result = new ArrayList<T>();
        for(Future<?> future : this.futures){
            try {
                if(future.isDone() && !future.isCancelled()){
                    Map<?, ?> returnMap = (Map<?, ?>)future.get();
                    list = (List<Object>)returnMap.get(OmcConcurrentConstants.RESULT);
                    if(!NullUtil.isNone(list)){
                        for(Object obj : list) result.add((T)obj);
                    }                    
                }
            } catch (InterruptedException e) {
                ;//e.printStackTrace();
            } catch (ExecutionException e) {
                ;//e.printStackTrace();
            }
        }
        return result;
    }
    /**
     * Thread Key값으로 Output을 Return하고 비정상인 경우 null을 Return함.
     *
     * @param threadKey
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> getResult(String threadKey){
        List<Object> list = new ArrayList<Object>();
        List<T> result = new ArrayList<T>();
        String tempThreadKey = "";
        for(Future<?> future : this.futures){
            try {
                if(future.isDone() && !future.isCancelled()){
                    Map<?, ?> returnMap = (Map<?, ?>)future.get();
                    tempThreadKey = (String)returnMap.get(OmcConcurrentConstants.THREAD_KEY);
                    if(threadKey.equals(tempThreadKey)){
                        list = (List<Object>)returnMap.get(OmcConcurrentConstants.RESULT);
                        if(!NullUtil.isNone(list)){
                            for(Object obj : list) result.add((T)obj);
                        }                    
                    }                    
                }
            } catch (InterruptedException e) {
                return null;
            } catch (ExecutionException e) {
                return null;
            }
        }
        return result;
    }
    /**
     * Thread Key값으로 Input Parameter를 Return
     *
     * @param threadKey
     * @return
     */
    
    @SuppressWarnings("rawtypes")
    public Object getInputParameter(String threadKey){
        for(Callable callable : callableList){
            OmcConcurrentCallableAbstract callableAbatract = (OmcConcurrentCallableAbstract)callable;
            if(threadKey.equals(callableAbatract.getThreadID())) return callableAbatract.getInObj();
        }
        return null;
    }
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private <T> List<T> getInputParameterSub(String kind){
        List<T> successList     = new ArrayList<T>();
        List<T> errorList       = new ArrayList<T>();
        List<T> cancelledList   = new ArrayList<T>();
        List<T> notExecutedList = new ArrayList<T>();
        List<T> killedList = new ArrayList<T>();
        for(String key: futureMap.keySet()){
            Future future = futureMap.get(key).getFuture();
            OmcConcurrentCallableAbstract callable = futureMap.get(key).getCallable();
            Object inObj = callable.getInObj();
            boolean status = false;
            if(future.isDone()){
                if(future.isCancelled()){
                    cancelledList.add((T)inObj);
                }else{
                    try {
                        Map<?, ?> returnMap = (Map<?, ?>)future.get();
                        status = (Boolean)returnMap.get(OmcConcurrentConstants.STATUS);
                        if(status){
                            successList.add((T)inObj);
                        }else{
                            errorList.add((T)inObj);
                        }    
                    } catch (InterruptedException e) {
                        killedList.add((T)inObj);
                    } catch (ExecutionException e) {
                        killedList.add((T)inObj);
                    }
                }
            }else{
                notExecutedList.add((T)inObj);
            }
        }
        if(kind.equals(OmcConcurrentConstants.THREAD_EXECUTED_STATUS_Success)) return successList;
        if(kind.equals(OmcConcurrentConstants.THREAD_EXECUTED_STATUS_Error)) return errorList;
        if(kind.equals(OmcConcurrentConstants.THREAD_EXECUTED_STATUS_Cancelled)) return cancelledList;
        if(kind.equals(OmcConcurrentConstants.THREAD_EXECUTED_STATUS_NotExecuted)) return notExecutedList;
        if(kind.equals(OmcConcurrentConstants.THREAD_EXECUTED_STATUS_Killed)) return killedList;
        return null;
    }
    //프로그램 수행중 해당 프로그램에서 에러가 발생한거(작업은 완료)
    public <T> List<T> getSuccesedInputParameter(){
        return getInputParameterSub(OmcConcurrentConstants.THREAD_EXECUTED_STATUS_Success);
    }
    //프로그램 수행중 해당 프로그램에서 에러가 발생한거(Business Logic상 Exception발생한것)
    public <T> List<T> getErrorOccuredInputParameter(){
        return getInputParameterSub(OmcConcurrentConstants.THREAD_EXECUTED_STATUS_Error);
    }
    // Business Logic 수행중 일정 시간 Over가 되어서 강제 종료되어진것
    public <T> List<T> getKilledInputParameter(){
        return getInputParameterSub(OmcConcurrentConstants.THREAD_EXECUTED_STATUS_Killed);
    }
    //전혀 Business Logic이 수행되지 않은 것
    @SuppressWarnings("unchecked")
    public <T> List<T> getNotExecutedInputParameter(){
        List<T> list = new ArrayList<T>();
        list.addAll((List<T>)getInputParameterSub(OmcConcurrentConstants.THREAD_EXECUTED_STATUS_NotExecuted));
        list.addAll((List<T>)getInputParameterSub(OmcConcurrentConstants.THREAD_EXECUTED_STATUS_Cancelled));
        return list;
    }
    private int shutdownAndForceStop(int waitingSeconds) {
        int interrutedCount = 0;
        executor.shutdown();
        isForceShutdown = true;
        try{
            if (!executor.awaitTermination(waitingSeconds, TimeUnit.SECONDS)) {
                List<Runnable> list = executor.shutdownNow();
                interrutedCount = list.size();
            }
        } catch (InterruptedException ie) {
            List<Runnable> list = executor.shutdownNow();
            interrutedCount = list.size();
        }
        return interrutedCount;
      }
}
