package jingweng.demo.springboot2.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jingweng.demo.springboot2.entity.Department;
import jingweng.demo.springboot2.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.controller
 * @className: DepartmentController
 * @author:
 * @description: TODO
 * @date: 2023/3/15 9:20
 * @version: 1.0
 */
@Api(tags = "部门查询模块")
@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentServer;

    // 查询全部部门
    @ApiOperation(value = "查询全部部门")
    @GetMapping("/getDepartments")
    public List<Department> getDepartments(){
        return departmentServer.getDepartments();
    }
}
