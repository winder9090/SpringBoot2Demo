package jingweng.demo.springboot2.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.entity
 * @className: SysLogOperationEntity
 * @author:
 * @description: 操作日志
 * @date: 2023/3/21 13:58
 * @version: 1.0
 */
@Data
@ToString
public class SysLogOperationEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 用户操作
     */
    private String operation;
    /**
     * 请求URI
     */
    private String requestUri;
    /**
     * 请求方式
     */
    private String requestMethod;
    /**
     * 请求参数
     */
    private String requestParams;
    /**
     * 请求时长(毫秒)
     */
    private Integer requestTime;
    /**
     * 用户代理
     */
    private String userAgent;
    /**
     * 操作IP
     */
    private String ip;
    /**
     * 状态  0：失败   1：成功
     */
    private Integer status;
    /**
     * 用户名
     */
    private String creatorName;

    private Date createdate;
}
