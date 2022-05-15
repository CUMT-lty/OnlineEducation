package com.mooc.controller;

import com.alibaba.fastjson.JSON;
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
        request.setCharacterEncoding("utf-8");   // 设置编码方式,处理POST请求中文乱码问题
        String jsonStr = request.getReader().readLine();
        System.out.println(jsonStr);
        Stu stu = JSON.parseObject(jsonStr, Stu.class);
        stuService.addStu(stu);  // 注册
        response.setStatus(200);
        response.setContentType("text/plaintext;charset=utf-8");
        response.getWriter().write(String.valueOf(true));
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");   // 设置编码方式,处理POST请求中文乱码问题
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Stu stu = stuService.selectByUsernameAndPsw(username, password);

        if (stu!=null) {
            Cookie cookie = new Cookie("stuId",  stu.getId() + "");
            response.addCookie(cookie);
            response.setStatus(200);
        } else {
            response.setStatus(500);
        }
    }


}
