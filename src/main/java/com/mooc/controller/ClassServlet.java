package com.mooc.controller;

import com.alibaba.fastjson.JSONObject;
import com.mooc.pojo.Class;
import com.mooc.pojo.ClassLog;
import com.mooc.service.impl.ClassLogServiceImpl;
import com.mooc.service.impl.ClassServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/class/*")  // 使用目录式的路径映射
public class ClassServlet extends BaseServlet {

    ClassServiceImpl classService =  new ClassServiceImpl();
    ClassLogServiceImpl classLogService = new ClassLogServiceImpl();

    /**
     * 课程详情页，获取课程日志信息
     */
    public void log(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String className = request.getParameter("name");
        Class aClass = classService.selectByName(className);
        String description = aClass.getDescription();
        ClassLog classLog = classLogService.selectByCId(aClass.getId());
        int viewNum = classLog.getViewNum();
        int score = classLog.getScore();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", className);
        jsonObject.put("description", description);
        jsonObject.put("viewNum", viewNum);
        jsonObject.put("score", score);

        response.setContentType("text/json;charset=utf-8");  // 处理响应头和中文编码问题
        response.getWriter().write(jsonObject.toJSONString());
    }


}
