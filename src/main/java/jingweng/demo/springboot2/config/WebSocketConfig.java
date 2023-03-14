package jingweng.demo.springboot2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

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
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpoint(){
        ServerEndpointExporter serverEndpointExporter = new ServerEndpointExporter();

        return serverEndpointExporter;
    }

}
