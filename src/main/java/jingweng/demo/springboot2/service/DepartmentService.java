package jingweng.demo.springboot2.service;

import jingweng.demo.springboot2.entity.Department;
import jingweng.demo.springboot2.page.PageRequest;
import jingweng.demo.springboot2.page.PageResult;

import java.util.List;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.service
 * @className: DepartmentService
 * @author:
 * @description: TODO
 * @date: 2023/3/15 9:32
 * @version: 1.0
 */
public interface DepartmentService {
    List<Department> getDepartments();
    PageResult findPage(PageRequest pageRequest);
}
