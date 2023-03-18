package jingweng.demo.springboot2.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

@Data
public class DepartmentsExcel {

    @Excel(name = "编号")
    private Integer id;

    @Excel(name = "部门名称")
    private String departmentName;
}
