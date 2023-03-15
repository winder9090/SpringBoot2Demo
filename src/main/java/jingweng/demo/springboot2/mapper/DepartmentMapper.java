package jingweng.demo.springboot2.mapper;

import jingweng.demo.springboot2.entity.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.mapper
 * @className: DepartmentMapper
 * @author:
 * @description: TODO
 * @date: 2023/3/15 9:32
 * @version: 1.0
 */
@Mapper
public interface DepartmentMapper {
    List<Department> getDepartments();
}

