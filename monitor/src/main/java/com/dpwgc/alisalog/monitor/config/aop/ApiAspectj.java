package com.dpwgc.alisalog.monitor.config.aop;

import com.dpwgc.alisalog.common.util.JsonUtil;
import com.dpwgc.alisalog.common.util.LogUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 切面拦截-请求响应情况获取
 */
@Aspect
@Component
public class ApiAspectj {

    /**
     * 切入点 console LogController
     */
    @Pointcut("execution(public * com.dpwgc.alisalog.monitor.controller.LogController.*(..))")
    public void logAspect() {
    }

    /**
     * 记录请求日志
     */
    @Around("logAspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        // 获取请求参数
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

        // 记录执行时间
        long startTime = System.currentTimeMillis();
        //处理请求
        Object result = joinPoint.proceed();

        //记录日志
        try {
            RequestLog requestLog = RequestLog.builder()
                    //访问人的ip
                    .ip(request.getRemoteAddr())
                    //请求路径
                    .url(request.getRequestURL().toString())
                    //处理的类
                    .classMethod(String.format("%s.%s", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName()))
                    //处理的方法类型
                    .httpMethod(request.getMethod())
                    //请求参数
                    .requestParams(getRequestParam(joinPoint))
                    //耗时
                    .timeCost(System.currentTimeMillis() - startTime).build();

            LogUtil.info("console request", JsonUtil.toJson(requestLog));
        } catch (Exception e) {
            LogUtil.error("console request error", e.toString());
        }
        return result;
    }

    /**
     * 获取请求参数
     */
    private Object[] getRequestParam(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Object[] arguments = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest || args[i] instanceof ServletResponse || args[i] instanceof MultipartFile) {
                //ServletRequest，ServletResponse,MultipartFile不能序列化
                continue;
            }
            arguments[i] = args[i];
        }
        return arguments;
    }
}
