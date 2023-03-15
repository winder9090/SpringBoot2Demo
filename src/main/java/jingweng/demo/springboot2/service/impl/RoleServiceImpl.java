package jingweng.demo.springboot2.service.impl;

import jingweng.demo.springboot2.entity.Role;
import jingweng.demo.springboot2.mapper.RoleMapper;
import jingweng.demo.springboot2.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.service.impl
 * @className: RoleServiceImpl
 * @author:
 * @description: TODO
 * @date: 2023/3/15 13:19
 * @version: 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findRoleByUserId(Integer id) {
        return roleMapper.findRoleByUserId(id);
    }
}
