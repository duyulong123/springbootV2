package com.springboot.v1.configure.log;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import com.springboot.v1.common.ApplicationContextRegister;
import com.springboot.v1.util.StringUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: OpLogAspect
 * @Description: 日志切面类
 * @author dyl
 * @date 2019年12月15日 下午8:23:05
 */
@Slf4j
@Aspect
@Component
public class OpLogAspect {

    /**
     * @Title: createOpLog     
     * @Description: 创建操作日志      
     * @param joinPoint
     * @param opLog
     * @return
     * @throws Throwable       
     */
    @Around(value = "@annotation(opLog)")
    public Object createOpLog(ProceedingJoinPoint joinPoint, OpLog opLog) throws Throwable {
        Object object = null;
        HttpServletRequest request;
        String isOperationLog = null;
        try {
            request = ApplicationContextRegister.getHttpServletRequest();
            isOperationLog = request.getParameter("isOperationLog");
            if (StringUtil.isEmptyString(isOperationLog)) {
                object = joinPoint.proceed();
                return object;
            }
            object = joinPoint.proceed();
            this.methodOpLog(joinPoint, object, 1, null);
        } catch (Exception e) {
            e.printStackTrace();
            if (StringUtil.isEmptyString(isOperationLog)) {
                throw new RuntimeException(e.getMessage());
            }
            this.methodOpLog(joinPoint, object, 0, e);
            throw new RuntimeException(e.getMessage());
        }
        return object;
    }

    /**
     * @Title: methodOpLog     
     * @Description: 日志拦截执行逻辑    
     * @param joinPoint
     * @param status
     * @param e
     * @return
     * @throws Throwable       
     */
    private void methodOpLog(JoinPoint joinPoint, Object object, int status, Throwable e) {
        try {
            if (log.isDebugEnabled()) {
                log.debug("进入操作日志方法{}", joinPoint.getThis().toString());
            }
            OperateLog operateLog = new OperateLog();
            /**获取目标类名*/
            String targetName = joinPoint.getTarget().getClass().getName();
            /**获取目标方法名*/
            String methodName = joinPoint.getSignature().getName();
            /**获取传入目标方法的参数*/
            Object[] arguments = joinPoint.getArgs();
            String logMsg = "";
            Class<?> targetClass = Class.forName(targetName);
            /**获取httpServletRequest */
            @SuppressWarnings("unused")
            HttpServletRequest request = ApplicationContextRegister.getHttpServletRequest();
            Method[] methods = targetClass.getMethods();
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    operateLog.setSysCode((method.getAnnotation(OpLog.class)).system());
                    operateLog.setModuleCode((method.getAnnotation(OpLog.class)).module());
                    operateLog.setMenuCode((method.getAnnotation(OpLog.class)).menu());
                    operateLog.setFuncId((method.getAnnotation(OpLog.class)).function());
                    String logExp = (method.getAnnotation(OpLog.class)).content();
                    EvaluationContext evlContext = new StandardEvaluationContext();
                    for (int i = 0; i < arguments.length; i++) {
                        evlContext.setVariable("arg" + i, arguments[i]);
                    }
                    SpelExpressionParser spelExpressionParser = new SpelExpressionParser();
                    logMsg = spelExpressionParser.parseExpression(logExp).getValue(evlContext).toString();
                    break;
                }
            }
            if (status == 1) {
                operateLog.setStatus("1");
                operateLog.setMessage("[执行成功] " + logMsg);
            } else {
                operateLog.setStatus("0");
                operateLog.setMessage("[执行失败] " + logMsg + " [错误信息] " + e.getMessage());
            }
            /**插入操作*/
            // opLogService.saveOpLog(operateLog, request, paramBean);
            if (log.isDebugEnabled()) {
                log.debug("成功拦截并添加日志-->{}", operateLog);
            }
        } catch (Exception ex) {
            log.error("日志记录失败-->{}", ex.getMessage());
        }
    }

}
