package jingweng.demo.springboot2.job.task;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.job.task
 * @className: TestTask
 * @author:
 * @description: 测试定时任务(演示Demo，可删除),testTask为spring bean的名称
 * @date: 2023/3/20 16:19
 * @version: 1.0
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component("testTask")
public class TestTask implements ITask{
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void run(String params){
        logger.debug("TestTask定时任务正在执行，参数为：{}", params);
    }

    //@Bean("testTask")
    //public void testTask(){
    //    logger.debug("testTask run!!!");
    //}

}

