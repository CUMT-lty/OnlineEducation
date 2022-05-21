package com.mooc.controller;

import com.alibaba.fastjson.JSON;
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
     * 通过课程的平均排名获取推荐课程
     */
    public void recommend(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cookie[] cookies = request.getCookies();
        boolean flag = false;  // true标识用户登陆过，false表示用户没有登陆过
        for (Cookie cookie: cookies){
            if ("loginFlag".equals(cookie.getName())) flag = true;
            break;
        }


        String classLogsJsonStr;  // 转为json字符串

        response.setContentType("text/json;charset=utf-8");
        response.setStatus(200);
//        response.getWriter().write(classLogsJsonStr);
    }

    /**
     * 课程详情页，获取课程日志信息
     */
    public void log(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int cId = Integer.valueOf(request.getParameter("cId"));   // 获取课程id

        Class aClass = classService.selectById(cId);
        String description = aClass.getDescription();  // 获取课程描述
        ClassLog classLog = classLogService.selectByCId(aClass.getId());   // 获取课程动态信息
        int viewNum = classLog.getViewNum();   // 课程被观看次数
        int score = classLog.getScore();     // 课程得分

        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("description", description);
        jsonObject.put("viewNum", viewNum);
        jsonObject.put("score", score);

        response.setStatus(200);
        response.setContentType("text/json;charset=utf-8");  // 处理响应头和中文编码问题
        response.getWriter().write(jsonObject.toJSONString());
    }


}
