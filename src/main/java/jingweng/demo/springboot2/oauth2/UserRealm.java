package jingweng.demo.springboot2.oauth2;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.oauth2
 * @className: UserRealm
 * @author:
 * @description: TODO
 * @date: 2023/3/15 13:36
 * @version: 1.0
 */

import jingweng.demo.springboot2.entity.Role;
import jingweng.demo.springboot2.entity.User;
import jingweng.demo.springboot2.service.PermissionService;
import jingweng.demo.springboot2.service.RoleService;
import jingweng.demo.springboot2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 负责认证用户身份和对用户进行授权
 */
@Component
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    //@Override
    //public boolean supports(AuthenticationToken token) {
    //    return token instanceof Oauth2Token;
    //}

    // 用户授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        List<Role> roleList = roleService.findRoleByUserId(user.getId());
        Set<String> roleSet = new HashSet<>();
        List<Integer> roleIds = new ArrayList<>();
        for (Role role : roleList) {
            roleSet.add(role.getRole());
            roleIds.add(role.getId());
        }
        // 放入角色信息
        authorizationInfo.setRoles(roleSet);
        // 放入权限信息
        List<String> permissionList = permissionService.findByRoleId(roleIds);
        authorizationInfo.setStringPermissions(new HashSet<>(permissionList));

        return authorizationInfo;
    }

    // 用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authToken;
        User user = userService.findByAccount(token.getUsername());
        if (user == null) {
            return null;
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }
}
