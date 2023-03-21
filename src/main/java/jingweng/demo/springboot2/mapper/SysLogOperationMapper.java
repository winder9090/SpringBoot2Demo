package jingweng.demo.springboot2.mapper;

import jingweng.demo.springboot2.entity.SysLogOperationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.mapper
 * @className: sysLogOperationMapper
 * @author:
 * @description: TODO
 * @date: 2023/3/21 14:06
 * @version: 1.0
 */
@Mapper
@Repository
public interface SysLogOperationMapper {
    public void save(SysLogOperationEntity log);
}
