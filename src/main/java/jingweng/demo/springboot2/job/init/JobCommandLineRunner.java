package jingweng.demo.springboot2.job.init;

import jingweng.demo.springboot2.entity.ScheduleJobEntity;
import jingweng.demo.springboot2.job.utils.ScheduleUtils;
import jingweng.demo.springboot2.service.ScheduleJobService;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.job.init
 * @className: JobCommandLineRunner
 * @author:
 * @description: 初始化定时任务数据
 * @date: 2023/3/20 14:14
 * @version: 1.0
 */
@Component
public class JobCommandLineRunner implements CommandLineRunner {
    @Autowired
    private Scheduler scheduler;

    @Autowired
    private ScheduleJobService scheduleJob;

    @Override
    public void run(String... args) {
        List<ScheduleJobEntity> scheduleJobList = scheduleJob.selectList();
        for(ScheduleJobEntity scheduleJob : scheduleJobList){
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getId());
            //如果不存在，则创建
            if(cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            }else {
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
        }
    }
}
