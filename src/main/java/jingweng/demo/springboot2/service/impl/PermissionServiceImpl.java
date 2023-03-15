package jingweng.demo.springboot2.service.impl;

import jingweng.demo.springboot2.mapper.PermissionMapper;
import jingweng.demo.springboot2.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.service.impl
 * @className: PermissionServiceImpl
 * @author:
 * @description: TODO
 * @date: 2023/3/15 13:18
 * @version: 1.0
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<String> findByRoleId(List<Integer> roleIds) {
        return permissionMapper.findByRoleId(roleIds);
    }
}
