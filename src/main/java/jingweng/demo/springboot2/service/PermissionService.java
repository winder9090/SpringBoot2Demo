package jingweng.demo.springboot2.service;

import java.util.List;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.service
 * @className: PermissionService
 * @author:
 * @description: TODO
 * @date: 2023/3/15 13:17
 * @version: 1.0
 */
public interface PermissionService {
    public List<String> findByRoleId(List<Integer> roleIds);
}
