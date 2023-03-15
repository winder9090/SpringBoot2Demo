package jingweng.demo.springboot2.mapper;

import jingweng.demo.springboot2.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.mapper
 * @className: UserMapper
 * @author:
 * @description: TODO
 * @date: 2023/3/15 13:16
 * @version: 1.0
 */
@Mapper
@Repository
public interface UserMapper {
    User findByAccount(@Param("account") String account);
}
