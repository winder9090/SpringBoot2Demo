package jingweng.demo.springboot2.xss;

import jingweng.demo.springboot2.exception.UnauthorizedException;
import org.apache.commons.lang3.StringUtils;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.xss
 * @className: sqlInject
 * @author:
 * @description: TODO
 * @date: 2023/3/21 15:09
 * @version: 1.0
 */
public class sqlInject {
    /**
     * SQL注入过滤
     * @param str  待验证的字符串
     */
    public static String sqlInject(String str){
        if(StringUtils.isBlank(str)){
            return null;
        }
        //去掉'|"|;|\字符
        str = StringUtils.replace(str, "'", "");
        str = StringUtils.replace(str, "\"", "");
        str = StringUtils.replace(str, ";", "");
        str = StringUtils.replace(str, "\\", "");

        //转换成小写
        str = str.toLowerCase();

        //非法字符
        String[] keywords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alter", "drop"};

        //判断是否包含非法字符
        for(String keyword : keywords){
            if(str.indexOf(keyword) != -1){
                throw new UnauthorizedException(10029);
            }
        }

        return str;
    }
}
