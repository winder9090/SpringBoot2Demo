package jingweng.demo.springboot2.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.utils
 * @className: SecurityUser
 * @author:
 * @description: 用户
 * @date: 2023/3/21 14:00
 * @version: 1.0
 */
public class SecurityUser {
    public static Subject getSubject() {
        try {
            return SecurityUtils.getSubject();
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 获取用户信息
     */
    public static Object getUser() {
        Subject subject = getSubject();
        if(subject == null){
            return null;
        }

        Object obj  = subject.getPrincipal();
        if(obj == null){
            return null;
        }

        return obj;
    }
}
