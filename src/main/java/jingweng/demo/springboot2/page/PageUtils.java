package jingweng.demo.springboot2.page;

import com.github.pagehelper.PageInfo;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.page
 * @className: PageUtils
 * @author:
 * @description: TODO
 * @date: 2023/3/23 10:00
 * @version: 1.0
 */
public class PageUtils {

    public static PageResult getPageResult(PageRequest pageRequest, PageInfo<?> pageInfo) {
        PageResult pageResult = new PageResult();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setTotalPages(pageInfo.getPages());
        pageResult.setContent(pageInfo.getList());
        return pageResult;
    }
}
