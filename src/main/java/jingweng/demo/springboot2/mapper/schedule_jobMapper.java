package jingweng.demo.springboot2.mapper;

import jingweng.demo.springboot2.entity.Role;
import jingweng.demo.springboot2.entity.ScheduleJobEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.mapper
 * @className: schedule_jobMapper.xml
 * @author:
 * @description: TODO
 * @date: 2023/3/21 11:30
 * @version: 1.0
 */
@Mapper
@Repository
public interface schedule_jobMapper {
    List<ScheduleJobEntity> selectList();

    ScheduleJobEntity findScheduleJob(Long id);
}
