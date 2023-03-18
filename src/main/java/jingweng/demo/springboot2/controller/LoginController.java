package jingweng.demo.springboot2.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import jingweng.demo.springboot2.entity.User;
import jingweng.demo.springboot2.entity.UserToken;
import jingweng.demo.springboot2.enums.ServerResponseEnum;
import jingweng.demo.springboot2.oauth2.TokenGenerator;
import jingweng.demo.springboot2.service.CaptchaService;
import jingweng.demo.springboot2.service.UserService;
import jingweng.demo.springboot2.utils.Result;
import jingweng.demo.springboot2.vo.ServerResponseVO;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
    private final static int EXPIRE = 3600 * 12;

    @Autowired
    private UserService userService;

    @Autowired
    private CaptchaService captchaService;

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public ServerResponseVO login(@RequestParam(value = "account") String account,
                                  @RequestParam(value = "password") String password,
                                  @RequestParam(value = "uuid") String uuid,
                                  @RequestParam(value = "captcha") String captcha) {
        //验证码是否正确
        boolean flag = captchaService.validate(uuid, captcha);
        if(!flag){
            return ServerResponseVO.error(ServerResponseEnum.CAPTCHA_ERROR);
        }

        try {
            String  token = TokenGenerator.generateValue();

            User user =  userService.findByAccount(account);

            UserToken userToken = new UserToken();
            userToken.setId(user.getId());
            userToken.setToken(token);
            userService.addUserToken(userToken);

            Map<String, Object> map = new HashMap<>(2);
            map.put("token", token);
            map.put("expire", EXPIRE);
            return ServerResponseVO.success(map);
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

        return ServerResponseVO.success();
    }

    @GetMapping("captcha")
    @ApiOperation(value = "验证码", produces="application/octet-stream")
    @ApiImplicitParam(paramType = "query", dataType="string", name = "uuid", required = true)
    public void captcha(HttpServletResponse response, String uuid)throws IOException {

        //生成验证码
        captchaService.create(response, uuid);
    }
}