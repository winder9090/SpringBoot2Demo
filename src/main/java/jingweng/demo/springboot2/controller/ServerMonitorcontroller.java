package jingweng.demo.springboot2.controller;

import cn.hutool.json.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jingweng.demo.springboot2.annotation.LogOperation;
import jingweng.demo.springboot2.utils.SystemInfoUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.controller
 * @className: ServerMonitorcontroller
 * @author:
 * @description: TODO
 * @date: 2023/3/24 10:17
 * @version: 1.0
 */

@Api(tags = "系统监控模块")
@RestController
public class ServerMonitorcontroller {

    @ApiOperation(value = "获取所有系统信息")
    @LogOperation("获取所有系统信息")
    @GetMapping()
    public JSONObject getInfo() throws Exception
    {
        return SystemInfoUtils.getInfo();
    }
}
