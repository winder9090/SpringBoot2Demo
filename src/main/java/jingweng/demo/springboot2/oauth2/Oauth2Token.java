package jingweng.demo.springboot2.oauth2;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.oauth2
 * @className: Oauth2Token
 * @author:
 * @description: token
 * @date: 2023/3/16 17:18
 * @version: 1.0
 */

import org.apache.shiro.authc.AuthenticationToken;

public class Oauth2Token implements AuthenticationToken {
    private String token;

    public Oauth2Token(String token){
        this.token = token;
    }

    @Override
    public String getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
