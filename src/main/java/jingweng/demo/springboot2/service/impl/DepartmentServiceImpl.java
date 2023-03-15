package jingweng.demo.springboot2.service.impl;

import jingweng.demo.springboot2.entity.Department;
import jingweng.demo.springboot2.mapper.DepartmentMapper;
import jingweng.demo.springboot2.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.service.impl
 * @className: DepartmentServiceImpl
 * @author:
 * @description: TODO
 * @date: 2023/3/15 9:32
 * @version: 1.0
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> getDepartments() {
        return departmentMapper.getDepartments();
    }
}
