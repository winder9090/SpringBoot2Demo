package jingweng.demo.springboot2.service;

import jingweng.demo.springboot2.entity.User;
import jingweng.demo.springboot2.entity.UserToken;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.service
 * @className: UserService
 * @author:
 * @description: TODO
 * @date: 2023/3/15 13:19
 * @version: 1.0
 */
public interface UserService {
    public User findByAccount(String account);

    public UserToken findByToken(String token);

    public int addUserToken(UserToken userToken);
}
