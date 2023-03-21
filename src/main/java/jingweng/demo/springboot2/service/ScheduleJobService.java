package jingweng.demo.springboot2.service;

import jingweng.demo.springboot2.entity.ScheduleJobEntity;

import java.util.List;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.service
 * @className: ScheduleJob
 * @author:
 * @description: TODO
 * @date: 2023/3/20 15:46
 * @version: 1.0
 */
public interface ScheduleJobService {
    public List<ScheduleJobEntity> selectList();

    public void run(Long[] ids);
}
