package demo.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/httpservlet")
public class HttpServletDemo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* Request对象用来获取请求数据，Response用来设置响应数据 */

        // 1.获取请求行
        // 获取请求方式
        String method = req.getMethod();
        System.out.println(method);
        // 获取项目访问路径
        String contextPath = req.getContextPath();
        System.out.println(contextPath);
        // 获取URL（统一资源定位符）
        StringBuffer url = req.getRequestURL();
        System.out.println(url);
        // 获取URI（统一资源标识符）
        String uri = req.getRequestURI();
        System.out.println(uri);
        // 获取请求参数
        String queryString = req.getQueryString();
        System.out.println(queryString);

        // 2.获取请求头
        // 获取浏览器的版本信息
        String agent = req.getHeader("user-agent");
        System.out.println(agent);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求体
        // 1.获取字符输入流
        BufferedReader bufferedReader = req.getReader();
        // 2.获取数据（获取到的是提交的信息）
        String dataLine = bufferedReader.readLine();
        System.out.println(dataLine);

        // 以上获取的是请求体，那么请求行和请求头只需要把doGet方法中的逻辑拿过来用就行了
        this.doGet(req, resp);
    }
}
