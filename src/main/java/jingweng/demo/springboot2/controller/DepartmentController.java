package jingweng.demo.springboot2.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jingweng.demo.springboot2.entity.Department;
import jingweng.demo.springboot2.excel.DepartmentsExcel;
import jingweng.demo.springboot2.page.PageRequest;
import jingweng.demo.springboot2.service.DepartmentService;
import jingweng.demo.springboot2.utils.ExcelUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.controller
 * @className: DepartmentController
 * @author:
 * @description: TODO
 * @date: 2023/3/15 9:20
 * @version: 1.0
 */
@Api(tags = "部门查询模块")
@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentServer;

    // 查询全部部门
    @ApiOperation(value = "查询全部部门")
    @GetMapping("/getDepartments")
    public List<Department> getDepartments(){
        return departmentServer.getDepartments();
    }

    @ApiOperation("导出")
    @GetMapping("/ExpDepartments")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<Department> list = departmentServer.getDepartments();

        ExcelUtils.exportExcelToTarget(response, null, list, DepartmentsExcel.class);
    }

    @ApiOperation("poi导出")
    @GetMapping("/poiExport")
    public void poiExport(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        String[] tableHeaders = {"id", "name", };

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Sheet1");
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);


        Font font = workbook.createFont();
        font.setColor(HSSFColor.RED.index);
        //font.setBold(true);
        cellStyle.setFont(font);

        // 将第一行的三个单元格给合并
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
        HSSFRow row = sheet.createRow(0);
        HSSFCell beginCell = row.createCell(0);
        beginCell.setCellValue("Department");
        beginCell.setCellStyle(cellStyle);

        row = sheet.createRow(1);
        // 创建表头
        for (int i = 0; i < tableHeaders.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(tableHeaders[i]);
            cell.setCellStyle(cellStyle);
        }

        List<Department> list = departmentServer.getDepartments();

        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i + 2);

            Department user = list.get(i);
            row.createCell(0).setCellValue(user.getId());
            row.createCell(1).setCellValue(user.getDepartmentName());
        }

        OutputStream outputStream = response.getOutputStream();
        response.reset();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=template.xls");

        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    @PostMapping(value="/findPage")
    @ApiOperation(value = "分页查询")
    public Object findPage(@RequestBody PageRequest pageQuery) {
        return departmentServer.findPage(pageQuery);
    }

}
