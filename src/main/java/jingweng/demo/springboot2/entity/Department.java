package jingweng.demo.springboot2.entity;

import lombok.*;

import java.io.Serializable;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.entity
 * @className: Department
 * @author:
 * @description: TODO
 * @date: 2023/3/15 9:20
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department implements Serializable {

    private Integer id;
    private String departmentName;

}
