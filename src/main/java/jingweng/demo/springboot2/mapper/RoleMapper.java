package jingweng.demo.springboot2.mapper;

import jingweng.demo.springboot2.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.mapper
 * @className: RoleMapper
 * @author:
 * @description: TODO
 * @date: 2023/3/15 13:14
 * @version: 1.0
 */
@Mapper
@Repository
public interface RoleMapper {

    List<Role> findRoleByUserId(@Param("userId") Integer userId);
}