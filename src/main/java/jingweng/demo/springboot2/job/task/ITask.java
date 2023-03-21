package jingweng.demo.springboot2.job.task;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.job.task
 * @className: ITask
 * @author:
 * @description: 定时任务接口，所有定时任务都要实现该接口
 * @date: 2023/3/20 16:18
 * @version: 1.0
 */
public interface ITask {

    /**
     * 执行定时任务接口
     *
     * @param params   参数，多参数使用JSON数据
     */
    void run(String params);
}
