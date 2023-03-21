package jingweng.demo.springboot2.service.impl;

import jingweng.demo.springboot2.entity.ScheduleJobEntity;
import jingweng.demo.springboot2.job.utils.ScheduleUtils;
import jingweng.demo.springboot2.mapper.UserMapper;
import jingweng.demo.springboot2.mapper.schedule_jobMapper;
import jingweng.demo.springboot2.service.ScheduleJobService;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.service.impl
 * @className: ScheduleJobImpl
 * @author:
 * @description: TODO
 * @date: 2023/3/20 15:46
 * @version: 1.0
 */
@Service
public class ScheduleJobServiceImpl implements ScheduleJobService {
    @Autowired
    private Scheduler scheduler;

    @Autowired
    private schedule_jobMapper schedulejobMapper;

    @Override
    public List<ScheduleJobEntity> selectList(){

        List<ScheduleJobEntity> list  = schedulejobMapper.selectList();
        //List<ScheduleJobEntity> list = new ArrayList<ScheduleJobEntity>();
        //ScheduleJobEntity entity = new ScheduleJobEntity();
        //entity.setId(1067246875800000076L);
        //entity.setStatus(1);
        //entity.setRemark("有参测试，多个参数使用json");
        //entity.setParams("renren");
        //entity.setUpdater(System.currentTimeMillis());
        //entity.setBean_Name("testTask");
        //entity.setUpdateDate(new Date());
        //entity.setCron_Expression("0 0/1 * * * ?");
        //list.add(entity);
        return list;

    };

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void run(Long[] ids) {
        //ScheduleJobEntity entity = new ScheduleJobEntity();
        //entity.setId(1067246875800000076L);
        //entity.setStatus(1);
        //entity.setRemark("有参测试，多个参数使用json");
        //entity.setParams("renren");
        //entity.setUpdater(System.currentTimeMillis());
        //entity.setBeanName("testTask");
        //entity.setUpdateDate(new Date());
        //entity.setCron_Expression("0 0/1 * * * ?");
        //ScheduleUtils.run(scheduler, entity);
        for(Long id : ids){
            ScheduleJobEntity entity = schedulejobMapper.findScheduleJob(id);
            ScheduleUtils.run(scheduler, entity);
        }



    }

}
