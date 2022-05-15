package com.mooc.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@WebServlet("/base")
public class BaseServlet extends HttpServlet {

    /**
     * 根据请求路径进行方法分发
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取请求路径
        String uri = req.getRequestURI();  //   /demo_war/stu/selectAll
        // 2. 获取最后一段请求路径
        int index = uri.lastIndexOf("/");
        String methodName = uri.substring( index+1 );  // selectAll
        // 3. 获取StuServlet的字节码对象Class
        // 谁调用this所在的方法，this就代表谁
        Class<? extends BaseServlet> cls = this.getClass();  // StuServlet调用了service方法，那么cls就是StuServlet.class
        // 4. 获取方法method对象
        try {
            Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            // 5. 执行方法
            method.invoke(this, req, resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
