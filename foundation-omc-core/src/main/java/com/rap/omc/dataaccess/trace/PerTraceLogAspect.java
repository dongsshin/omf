package com.rap.omc.dataaccess.trace;

import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import com.rap.omc.dataaccess.mybatis.interceptor.TraceContext;
import com.rap.omc.framework.refresh.BeanRefreshSupport;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.core.StopWatch;

@Aspect
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Component
public class PerTraceLogAspect implements BeanFactoryAware, BeanRefreshSupport{

    private static final Logger LOGGER = LoggerFactory.getLogger(PerTraceLogAspect.class);
    
    private boolean enabled = true;
    private String prefix = "▶[PER]";
    
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
    @Around("within(@org.springframework.stereotype.Repository *) ")
    public Object doPersistentTraceLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        
        if (enabled){
            String className = joinPoint.getSignature().getDeclaringTypeName();
            String methodName = joinPoint.getSignature().toLongString();
            
            StringBuffer beforeMethodLog = new StringBuffer();
            beforeMethodLog.append("\n");
            beforeMethodLog.append(prefix + " Class : " + className + "\n");
            beforeMethodLog.append(prefix + " Method : " + methodName + "\n");
            
            Object[] obj = joinPoint.getArgs();
            beforeMethodLog.append(prefix + " Query Id : " + obj[0] + "\n");
            LOGGER.trace(beforeMethodLog.toString());
            StopWatch watch = new StopWatch();
            
            result = joinPoint.proceed();

            StringBuffer afterMethodLog = new StringBuffer();
            afterMethodLog.append("\n");
            afterMethodLog.append(prefix + " Query Lap Time : " + watch.getElapsed() + " ms\n");
            Map<?, ?> map = TraceContext.local.get();
            if(NullUtil.isNone(map)){
                LOGGER.debug("[PerTraceLogAspect]map is null");
            }else{ 
                if(!NullUtil.isNull(map.get("sql"))){
                    afterMethodLog.append(prefix + " Query : \n\t" + map.get("sql") + "\n");
                }
                if(!NullUtil.isNull(map.get("paramInfo"))){
                    afterMethodLog.append(prefix + " Binding Params : \n\t" + map.get("paramInfo") + "\n");
                }
            }
            LOGGER.trace(afterMethodLog.toString());
            LOGGER.info(afterMethodLog.toString());
        } else {
            result = joinPoint.proceed();
        }
        return result;
    }
    @AfterThrowing(pointcut = "within(@org.springframework.stereotype.Repository *)" , throwing = "exception")
    public void doPersistentErrorTraceLogging(JoinPoint thisJoinPoint, Exception exception) throws Exception {
        if(enabled){
            if(!NullUtil.isNull(TraceContext.local.get())){
                String sql = (String) TraceContext.local.get().get("sql");
                LOGGER.trace("\n" + prefix + " Error Query : \n\t" + sql);
            }
            LOGGER.trace("\n" + prefix + " Error Log : " + exception.getMessage());
        }
        throw exception;
    }
    
    /**
     * 사용한 ThreadLocal에 대한 자원 해제  
     *
     * @param thisJoinPoint
     */
    @After("within(@org.springframework.stereotype.Repository *)" )
    public void doPersistentFinallyTraceLogging(JoinPoint thisJoinPoint){
        if (this.enabled) {
            if (TraceContext.local != null) {
                TraceContext.local.remove();
            }
        }
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
    }
}