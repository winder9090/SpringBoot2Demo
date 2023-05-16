package jingweng.demo.springboot2.config;

import jingweng.demo.springboot2.oauth2.Oauth2Filter;
import jingweng.demo.springboot2.oauth2.UserRealm;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.config
 * @className: ShiroConfig
 * @author:
 * @description: TODO
 * @date: 2023/3/15 13:50
 * @version: 1.0
 */
@Configuration
public class ShiroConfig {

    //@Bean
    //public UserRealm userRealm() {
    //    return new UserRealm();
    //}

    @Bean
    public DefaultWebSessionManager sessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionValidationSchedulerEnabled(false);
        sessionManager.setSessionIdUrlRewritingEnabled(false);

        return sessionManager;
    }

    @Bean("securityManager")
    public SecurityManager securityManager(UserRealm userRealm, SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        securityManager.setSessionManager(sessionManager);
        securityManager.setRememberMeManager(null);
        return securityManager;
    }

    /**
     * 路径过滤规则
     * @return
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //oauth过滤
        Map<String, Filter> filters = new HashMap<>();
        filters.put("oauth2", new Oauth2Filter());
        shiroFilterFactoryBean.setFilters(filters);

        // 配置拦截规则
        Map<String, String> map = new LinkedHashMap<>();
        // 有先后顺序
        map.put("/login", "anon");      // 允许匿名访问
        map.put("/swagger/**", "anon");
        map.put("/v2/api-docs", "anon");
        map.put("/doc.html", "anon");      // 允许匿名访问
        map.put("/swagger-resources/**", "anon");
        map.put("/webjars/**/**", "anon");
        map.put("/captcha", "anon");
        map.put("/api/websocket/**", "oauth2");
        map.put("/hello", "oauth2");        // 进行身份认证后才能访问
        map.put("/getDepartments", "oauth2");        // 进行身份认证后才能访问
        map.put("/**", "oauth2");        // 进行身份认证后才能访问
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    /**
     * 在spring容器处在某一声明周期时时，为shiro中的相关bean执行对应的bean生命周期方法
     * @return
     */
    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启Shiro注解模式，可以在Controller中的方法上添加注解
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
