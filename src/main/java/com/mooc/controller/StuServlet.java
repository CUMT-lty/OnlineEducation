package com.mooc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mooc.pojo.Stu;
import com.mooc.service.impl.StuServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/stu/*")  // 使用目录式路由管理
public class StuServlet extends BaseServlet {

    StuServiceImpl stuService = new StuServiceImpl();   // service层访问接口

    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");   // 设置编码方式,处理POST请求中文乱码问题
        String jsonStr = request.getReader().readLine();
        Stu stu = JSON.parseObject(jsonStr, Stu.class);   // 映射为Stu实体类对象
        Stu tmpStu = stuService.selectByUsername(stu.getUsername());
        if (tmpStu!=null) {   // 如果用户已存在，设置响应状态码500
            response.setStatus(500);
            response.setContentType("text/plaintext;charset=utf-8");
            response.getWriter().write(String.valueOf(false));
        } else {  // 如果是新用户，则继续进行注册，并返回响应状态码200
            stuService.addStu(stu);  // 注册
            response.setStatus(200);
            response.setContentType("text/plaintext;charset=utf-8");
            response.getWriter().write(String.valueOf(true));
        }
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");   // 设置编码方式,处理POST请求中文乱码问题
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Stu stu = stuService.selectByUsernameAndPsw(username, password);
        if (stu!=null) {   // 登录信息正确
            // 设置cookie，在浏览器端保存用户stuId
            Cookie cookie = new Cookie("stuId",  stu.getId() + "");
            response.addCookie(cookie);
            response.setStatus(200);
        } else {  // 登录信息有误
            response.setStatus(500);
        }
    }


}
