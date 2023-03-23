package jingweng.demo.springboot2.service.impl;

import cn.hutool.system.UserInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jingweng.demo.springboot2.entity.Department;
import jingweng.demo.springboot2.mapper.DepartmentMapper;
import jingweng.demo.springboot2.page.PageRequest;
import jingweng.demo.springboot2.page.PageResult;
import jingweng.demo.springboot2.page.PageUtils;
import jingweng.demo.springboot2.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.service.impl
 * @className: DepartmentServiceImpl
 * @author:
 * @description: TODO
 * @date: 2023/3/15 9:32
 * @version: 1.0
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> getDepartments() {
        return departmentMapper.getDepartments();
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest));
    }

    /**
     * 调用分页插件完成分页
     * @return
     */
    private PageInfo<Department> getPageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Department> departmentList = departmentMapper.selectPage();
        return new PageInfo<Department>(departmentList);
    }
}
