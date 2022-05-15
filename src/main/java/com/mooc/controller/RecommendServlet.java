package com.mooc.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * 处理自适应学习路径推荐请求
 */

@WebServlet("/recommend")
public class RecommendServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 需要的前端数据：学生id
        // 2. 返回的数据，一个列表，里面存放着推荐的课程的id和课程名字
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
