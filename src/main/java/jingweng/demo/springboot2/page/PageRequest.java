package jingweng.demo.springboot2.page;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.page
 * @className: PageRequest
 * @author:
 * @description: 分页请求
 * @date: 2023/3/23 9:58
 * @version: 1.0
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PageRequest {
    /**
     * 当前页码
     */
    private int pageNum;
    /**
     * 每页数量
     */
    private int pageSize;

}

