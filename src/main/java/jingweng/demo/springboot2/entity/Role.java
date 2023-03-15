package jingweng.demo.springboot2.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.entity
 * @className: Role
 * @author:
 * @description: TODO
 * @date: 2023/3/15 13:12
 * @version: 1.0
 */
@Data
@ToString
public class Role implements Serializable {

    private static final long serialVersionUID = -1767327914553823741L;

    private Integer id;

    private String role;

    private String desc;
}