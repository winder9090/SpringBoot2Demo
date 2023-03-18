package jingweng.demo.springboot2.mapper;

import jingweng.demo.springboot2.entity.User;
import jingweng.demo.springboot2.entity.UserToken;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.mapper
 * @className: User_Token
 * @author:
 * @description: TODO
 * @date: 2023/3/17 9:22
 * @version: 1.0
 */
@Mapper
@Repository
public interface UserTokenMapper {
    UserToken findToken(String token);

    int addUserToken(UserToken usertoken);

    UserToken findID(int id);

    void delToken(int id);
}
