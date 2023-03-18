package jingweng.demo.springboot2.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "Hello模块")
@RestController
public class HelloController {

    @ApiOperation(value = "向客人问好")
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}
