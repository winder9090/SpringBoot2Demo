package jingweng.demo.springboot2.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.entity
 * @className: UserToken
 * @author:
 * @description: TODO
 * @date: 2023/3/17 9:24
 * @version: 1.0
 */
@Data
@ToString
public class UserToken {
    private Integer id;

    private String token;
}
