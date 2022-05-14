package demo.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/* loadOnStartup属性设置为负整数：第一次被访问时创建servlet对象。为0或正整数：服务器启动时创建servlet对象 */
@WebServlet(urlPatterns = "/servletdemo1", loadOnStartup = -1)
public class ServletDemo implements Servlet {

    private ServletConfig servletConfig;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        /* 初始化阶段 */
        this.servletConfig = servletConfig;   // 提升局部变量作用域的方式：作用域提升
        System.out.println("init......");
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        /* 服务响应阶段 */
        System.out.println("servlet hello!!!!");
    }

    @Override
    public void destroy() {
        /* 销毁阶段 */
        System.out.println("destroy......");
    }

    @Override
    public ServletConfig getServletConfig() {
        return servletConfig;
    }

    @Override
    public String getServletInfo() {
        return null;
    }

}

