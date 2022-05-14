package com.mooc.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/stu/*")  // 使用目录式的路径映射
public class StuServlet extends BaseServlet {

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("stu selectAll......");
    }

    public void addNewStu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("stu addNewStu......");
    }

}
