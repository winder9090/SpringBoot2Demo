package jingweng.demo.springboot2.page;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.page
 * @className: PageResult
 * @author:
 * @description: 分页返回结果
 * @date: 2023/3/23 9:59
 * @version: 1.0
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PageResult {
    /**
     * 当前页码
     */
    private int pageNum;
    /**
     * 每页数量
     */
    private int pageSize;
    /**
     * 记录总数
     */
    private long totalSize;
    /**
     * 页码总数
     */
    private int totalPages;
    /**
     * 数据模型
     */
    private List<?> content;

}
