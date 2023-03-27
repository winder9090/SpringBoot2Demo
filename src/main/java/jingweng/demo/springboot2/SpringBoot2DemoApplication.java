package jingweng.demo.springboot2;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@Slf4j
public class SpringBoot2DemoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		log.info("spring boot App is starting.......");
		SpringApplication.run(SpringBoot2DemoApplication.class, args);
		log.info("spring boot App is running........");
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBoot2DemoApplication.class);
	}
}
