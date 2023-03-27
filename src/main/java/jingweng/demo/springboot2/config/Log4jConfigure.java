package jingweng.demo.springboot2.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.config
 * @className: Log4jConfigure
 * @author:
 * @description: TODO
 * @date: 2023/3/27 8:42
 * @version: 1.0
 */
@ComponentScan
@ConfigurationProperties("classpath:log4j.properties")
public class Log4jConfigure {
}
