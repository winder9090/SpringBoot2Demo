package jingweng.demo.springboot2.service.impl;

import jingweng.demo.springboot2.entity.User;
import jingweng.demo.springboot2.entity.UserToken;
import jingweng.demo.springboot2.mapper.UserMapper;
import jingweng.demo.springboot2.mapper.UserTokenMapper;
import jingweng.demo.springboot2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.service.impl
 * @className: UserServiceImpl
 * @author:
 * @description: TODO
 * @date: 2023/3/15 13:20
 * @version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserTokenMapper userTokenMapper;

    @Override
    public User findByAccount(String account) {
        return userMapper.findByAccount(account);
    }

    @Override
    public UserToken findByToken(String token){return userTokenMapper.findToken(token);};

    @Override
    public int addUserToken(UserToken userToken){return  userTokenMapper.addUserToken(userToken);};

    @Override
    public UserToken findByID(int id){return userTokenMapper.findID(id);};

    @Override
    public void delToken(int id){userTokenMapper.delToken(id);};
}
