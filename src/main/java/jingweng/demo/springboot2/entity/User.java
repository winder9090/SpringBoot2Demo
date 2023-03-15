package jingweng.demo.springboot2.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.entity
 * @className: User
 * @author:
 * @description: TODO
 * @date: 2023/3/15 13:11
 * @version: 1.0
 */
@Data
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = -6056125703075132981L;

    private Integer id;

    private String account;

    private String password;

    private String username;
}
