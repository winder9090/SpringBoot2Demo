package jingweng.demo.springboot2.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.mapper
 * @className: PermissionMapper
 * @author:
 * @description: TODO
 * @date: 2023/3/15 13:13
 * @version: 1.0
 */
@Mapper
@Repository
public interface PermissionMapper {

    List<String> findByRoleId(@Param("roleIds") List<Integer> roleIds);
}
