package com.mooc.controller.old;

import com.alibaba.fastjson.JSON;
import com.mooc.pojo.Stu;
import com.mooc.service.impl.StuServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/selectAllUserServlet")
public class SelectAllUserServlet extends HttpServlet {

    // 1. 调用对应的UserService中的方法来完成需要的操作
    private StuServiceImpl userService = new StuServiceImpl();
    /* 将service的创建提到成员部分 */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 2. 调用UserService中的selectAllUser方法完成查询操作（和业务逻辑层交互）
//        List<Stu> users = userService.selectAllUser();

        // 3. 将结果响应或转发到另一个资源中

        // 3.1 将结果转发：将结果存入request域中 (参数名,参数值)，并转发
        /*request.setAttribute("users", users);
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        request.getRequestDispatcher("a.html").forward(request, response);*/

        // 3.2 将结果以json格式响应到前端
//        String usersJSONString = JSON.toJSONString(users);
        response.setContentType("text/json;charset=utf-8");  // 处理响应头和中文编码问题
//        response.getWriter().write(usersJSONString);   // 响应到前端

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
