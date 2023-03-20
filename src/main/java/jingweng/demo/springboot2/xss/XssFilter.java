package jingweng.demo.springboot2.xss;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.xss
 * @className: XssFilter
 * @author:
 * @description: XSS过滤
 * @date: 2023/3/20 10:12
 * @version: 1.0
 */
public class XssFilter implements Filter {

    @Override
    public void init(FilterConfig config) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(
                (HttpServletRequest) request);
        chain.doFilter(xssRequest, response);
    }

    @Override
    public void destroy() {
    }

}