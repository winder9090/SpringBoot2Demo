package jingweng.demo.springboot2.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Redis切面处理类
 *
 */
@Aspect     // 标注增强处理类（切面类）
@Component  // 交由Spring容器管理
public class RedisAspect {
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 是否开启redis缓存  true开启   false关闭
     */
    @Value("${renren.redis.open: false}")
    private boolean open;

    /**
     * 在切面类中使用execution给批量方法添加切面
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("execution(* jingweng.demo.springboot2.redis.RedisUtils.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        if(open){
            try{
                result = point.proceed();
            }catch (Exception e){
                logger.error("redis error", e);
            }
        }
        return result;
    }
}
