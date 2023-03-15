package jingweng.demo.springboot2.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jingweng.demo.springboot2.enums.ServerResponseEnum;
import jingweng.demo.springboot2.vo.ServerResponseVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.controller
 * @className: LoginController
 * @author:
 * @description: TODO
 * @date: 2023/3/15 14:42
 * @version: 1.0
 */
@RestController
@RequestMapping("")
@Api(tags = "登录模块")
public class LoginController {

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public ServerResponseVO login(@RequestParam(value = "account") String account,
                                  @RequestParam(value = "password") String password) {
        Subject userSubject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(account, password);
        try {
            // 登录验证
            userSubject.login(token);
            return ServerResponseVO.success();
        } catch (UnknownAccountException e) {
            return ServerResponseVO.error(ServerResponseEnum.ACCOUNT_NOT_EXIST);
        } catch (DisabledAccountException e) {
            return ServerResponseVO.error(ServerResponseEnum.ACCOUNT_IS_DISABLED);
        } catch (IncorrectCredentialsException e) {
            return ServerResponseVO.error(ServerResponseEnum.INCORRECT_CREDENTIALS);
        } catch (Throwable e) {
            e.printStackTrace();
            return ServerResponseVO.error(ServerResponseEnum.ERROR);
        }
    }

    @PostMapping("logout")
    @ApiOperation(value = "退出")
    public ServerResponseVO logout(HttpServletRequest request) {
        Subject userSubject = SecurityUtils.getSubject();

        //退出
        userSubject.logout();

        return ServerResponseVO.success();
    }
}