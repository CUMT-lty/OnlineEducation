package com.mooc.controller.old;

import com.alibaba.fastjson.JSONObject;
import com.mooc.pojo.Stu;
import com.mooc.service.impl.StuServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    StuServiceImpl stuService = new StuServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        request.setCharacterEncoding("utf-8");   // 设置编码方式,处理POST请求中文乱码问题


        String username = request.getParameter("username");
        String password = request.getParameter("password");
//        Stu stu = stuService.login(username, password);

        // 处理查询结果逻辑


        // 封装json对象返回登录结果信息
        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("")
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
