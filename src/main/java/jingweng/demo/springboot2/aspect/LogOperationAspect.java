package jingweng.demo.springboot2.aspect;

import jingweng.demo.springboot2.annotation.LogOperation;
import jingweng.demo.springboot2.entity.SysLogOperationEntity;
import jingweng.demo.springboot2.enums.OperationStatusEnum;
import jingweng.demo.springboot2.service.SysLogOperationService;
import jingweng.demo.springboot2.utils.HttpContextUtils;
import jingweng.demo.springboot2.utils.IpUtils;
import jingweng.demo.springboot2.utils.JsonUtils;
import jingweng.demo.springboot2.utils.SecurityUser;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.aspect
 * @className: LogOperationAspect
 * @author:
 * @description: 操作日志，切面处理类
 * @date: 2023/3/21 13:51
 * @version: 1.0
 */
@Aspect
@Component
public class LogOperationAspect {
    @Autowired
    private SysLogOperationService sysLogOperationService;

    @Pointcut("@annotation(jingweng.demo.springboot2.annotation.LogOperation)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        try {
            //执行方法
            Object result = point.proceed();

            //执行时长(毫秒)
            long time = System.currentTimeMillis() - beginTime;
            //保存日志
            saveLog(point, time, OperationStatusEnum.SUCCESS.value());

            return result;
        }catch(Exception e) {
            //执行时长(毫秒)
            long time = System.currentTimeMillis() - beginTime;
            //保存日志
            saveLog(point, time, OperationStatusEnum.FAIL.value());

            throw e;
        }
    }

    private void saveLog(ProceedingJoinPoint joinPoint, long time, Integer status) throws Exception {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = joinPoint.getTarget().getClass().getDeclaredMethod(signature.getName(), signature.getParameterTypes());
        LogOperation annotation = method.getAnnotation(LogOperation.class);

        SysLogOperationEntity log = new SysLogOperationEntity();
        if(annotation != null){
            //注解上的描述
            log.setOperation(annotation.value());
        }

        log.setId(System.currentTimeMillis());

        //登录用户信息
        Object obj = SecurityUser.getUser();
        if(obj != null){
            log.setCreatorName("user");
        }

        log.setStatus(status);
        log.setRequestTime((int)time);

        //请求相关信息
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        log.setIp(IpUtils.getIpAddr(request));
        log.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
        log.setRequestUri(request.getRequestURI());
        log.setRequestMethod(request.getMethod());

        //请求参数
        Object[] args = joinPoint.getArgs();
        try{
            String params = JsonUtils.toJsonString(args[0]);
            log.setRequestParams(params);
        }catch (Exception e){

        }

        log.setCreatedate(new Date());

        //保存到DB
        sysLogOperationService.save(log);
    }
}
