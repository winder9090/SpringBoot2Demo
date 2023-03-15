package jingweng.demo.springboot2.exception;

import jingweng.demo.springboot2.enums.ServerResponseEnum;
import jingweng.demo.springboot2.vo.ServerResponseVO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.exception
 * @className: UnauthorizedException
 * @author:
 * @description: TODO
 * @date: 2023/3/15 13:23
 * @version: 1.0
 */
@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ServerResponseVO UnAuthorizedExceptionHandler(UnauthorizedException e) {
        return ServerResponseVO.error(ServerResponseEnum.UNAUTHORIZED);
    }
}
