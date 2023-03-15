package jingweng.demo.springboot2.service;

import jingweng.demo.springboot2.entity.Role;

import java.util.List;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.service.impl
 * @className: RoleService
 * @author:
 * @description: TODO
 * @date: 2023/3/15 13:18
 * @version: 1.0
 */
public interface RoleService {
    public List<Role> findRoleByUserId(Integer id);
}
