package com.mooc.controller;

import com.alibaba.fastjson.JSONObject;
import com.mooc.pojo.Stu;
import com.mooc.service.impl.StuServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/stu/*")  // 使用目录式的路径映射
public class StuServlet extends BaseServlet {

    StuServiceImpl stuService = new StuServiceImpl();   // 提到成员位置

    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");   // 设置编码方式,处理POST请求中文乱码问题
        String username = request.getParameter("username");
        String password = request.getParameter("password");


//        Stu stu = stuService.login(username, password);



        JSONObject jsonObject = new JSONObject();
    }


}
