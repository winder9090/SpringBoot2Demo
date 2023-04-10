package jingweng.demo.springboot2.oauth2;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.oauth2
 * @className: UserRealm
 * @author:
 * @description: 负责认证用户身份和对用户进行授权
 * @date: 2023/3/15 13:36
 * @version: 1.0
 */

import jingweng.demo.springboot2.entity.Role;
import jingweng.demo.springboot2.entity.User;
import jingweng.demo.springboot2.entity.UserToken;
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

@Component
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;



    /**
     * @param principalCollection:
     * @return AuthorizationInfo
     * @author
     * @description 用户授权
     * @date 2023/3/17 8:46
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //User user = (User) principalCollection.getPrimaryPrincipal();
        UserToken user =  (UserToken)principalCollection.getPrimaryPrincipal();
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

    /**
     * @param authToken:
     * @return AuthenticationInfo
     * @author
     * @description 认证(登录时调用)
     * @date 2023/3/17 8:47
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
        String accessToken = (String) authToken.getPrincipal();
        if (accessToken == null) {
            return null;
        }

        UserToken userToken = userService.findByToken(accessToken);
        return new SimpleAuthenticationInfo(userToken, accessToken, getName());
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof Oauth2Token;
    }
}
