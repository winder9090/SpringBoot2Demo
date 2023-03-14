package jingweng.demo.springboot2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.controller
 * @className: HelloController
 * @author:
 * @description: TODO
 * @date: 2023/3/14 15:06
 * @version: 1.0
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}

