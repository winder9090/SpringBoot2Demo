package jingweng.demo.springboot2.service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.service
 * @className: CaptchaService
 * @author:
 * @description: TODO
 * @date: 2023/3/17 17:41
 * @version: 1.0
 */
public interface CaptchaService {

    /**
     * 图片验证码
     */
    void create(HttpServletResponse response, String uuid) throws IOException;

    /**
     * 验证码效验
     * @param uuid  uuid
     * @param code  验证码
     * @return  true：成功  false：失败
     */
    boolean validate(String uuid, String code);
}
