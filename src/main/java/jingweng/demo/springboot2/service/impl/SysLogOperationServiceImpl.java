package jingweng.demo.springboot2.service.impl;

import jingweng.demo.springboot2.entity.SysLogOperationEntity;
import jingweng.demo.springboot2.mapper.SysLogOperationMapper;
import jingweng.demo.springboot2.service.SysLogOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.service.impl
 * @className: sysLogOperationServiceImpl
 * @author:
 * @description: TODO
 * @date: 2023/3/21 14:04
 * @version: 1.0
 */
@Service
public class SysLogOperationServiceImpl implements SysLogOperationService {
    @Autowired
    private SysLogOperationMapper sysLogOperationMapper;

    @Override
    public void save(SysLogOperationEntity log){

        sysLogOperationMapper.save(log);
    }
}
