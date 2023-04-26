package jingweng.demo.springboot2.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.entity
 * @className: ScheduleJobEntity
 * @author:
 * @description: 定时任务
 * @date: 2023/3/20 15:44
 * @version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ScheduleJobEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * spring bean名称
     */
    private String beanName;
    /**
     * 参数
     */
    private String params;
    /**
     * cron表达式
     */
    private String cronExpression;
    /**
     * 任务状态  0：暂停  1：正常
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 更新者
     */

    private Long updater;
    /**
     * 更新时间
     */

    private Date updateDate;
}
