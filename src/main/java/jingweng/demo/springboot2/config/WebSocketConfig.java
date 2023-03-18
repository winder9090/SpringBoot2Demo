package jingweng.demo.springboot2.config;

import jingweng.demo.springboot2.entity.UserToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.config
 * @className: WebSocketConfig
 * @author:
 * @description: TODO
 * @date: 2023/3/14 15:10
 * @version: 1.0
 */
@Configuration
public class WebSocketConfig extends ServerEndpointConfig.Configurator{

    /**
     * @param sec:
     * @param request:
     * @param response:
     * @return void
     * @author
     * @description 修改握手,就是在握手协议建立之前修改其中携带的内容
     * @date 2023/3/9 14:35
     */
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        // 将http会话的用户信息绑定到websocket会话的用户信息中
        //sec.getUserProperties().put("user", SecurityUser.getUser());
        Subject subject = SecurityUtils.getSubject();
        sec.getUserProperties().put("user", subject);
        if(subject == null){
            return ;
        }
        Object obj  = subject.getPrincipal();
        super.modifyHandshake(sec, request, response);
    }

    @Bean
    public ServerEndpointExporter serverEndpoint(){
        ServerEndpointExporter serverEndpointExporter = new ServerEndpointExporter();

        return serverEndpointExporter;
    }

}
