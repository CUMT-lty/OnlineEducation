package com.mooc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mooc.pojo.*;
import com.mooc.pojo.Class;
import com.mooc.service.impl.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/class/*")  // 使用目录式的路径映射
public class ClassServlet extends BaseServlet {

    ClassServiceImpl classService =  new ClassServiceImpl();
    ClassLogServiceImpl classLogService = new ClassLogServiceImpl();
    ClassLikeServiceImpl classLikeService = new ClassLikeServiceImpl();
    ClassCollectServiceImpl classCollectService = new ClassCollectServiceImpl();


    public void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nameLike = request.getParameter("nameLike");
        ClassLog[] classLogs = classLogService.selectByNameLike(nameLike);
        String classLogsJsonStr = JSON.toJSONString(classLogs);
        response.setContentType("text/json;charset=utf-8");
        response.setStatus(200);
        response.getWriter().write(classLogsJsonStr);

        /*
        * [{"cDescription":"java入门基础课程","cId":1,"cName":"java语言程序设计","collectNum":196,"id":1,"likeNum":211,"score":7,"scoreNum":89,"viewNum":365},
        * {"cDescription":"java网络编程，需要先具备java基础知识，和网络相关基础知识","cId":2,"cName":"java网络编程","collectNum":187,"id":5,"likeNum":200,"score":6,"scoreNum":105,"viewNum":244},
        * {"cDescription":"ssm课程的先导课，整套web开发入门，包含前后端的基础课程","cId":3,"cName":"javaWeb课程","collectNum":165,"id":6,"likeNum":387,"score":8,"scoreNum":155,"viewNum":401},
        * {"cDescription":"深入刨析Servlet的实现","cId":6,"cName":"javaServlet","collectNum":357,"id":9,"likeNum":357,"score":9,"scoreNum":357,"viewNum":366}]
         */
    }


    /**
     * 获取推荐课程
     */
    public void recommend(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cookie[] cookies = request.getCookies();
        int stuId = -1;
        for (Cookie cookie: cookies){
            if ("stuId".equals(cookie.getName())) stuId = Integer.valueOf(cookie.getValue());
            break;
        }

        ClassLog[] classLogs = null;

        // 如果stuId为-1说明没有登录，否则说明登录了
        if (stuId == -1) classLogs = classLogService.selectByScoreOrderLimNum(6);
        else classLogs = classLogService.recommendClassByStuId(stuId);

        String classLogsJsonStr = JSON.toJSONString(classLogs);

        response.setContentType("text/json;charset=utf-8");
        response.setStatus(200);
        response.getWriter().write(classLogsJsonStr);
    }

    /**
     * 课程详情页，获取课程详细信息
     */
    public void log(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int cId = Integer.valueOf(request.getParameter("cId"));   // 获取课程id

        Class aClass = classService.selectById(cId);

        ClassLog classLog = classLogService.selectByCId(aClass.getId());   // 获取课程动态信息
        String name = classLog.getcName();
        String description = classLog.getcDescription();
        int viewNum = classLog.getViewNum();   // 课程被观看次数
        int score = classLog.getScore();     // 课程得分

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("description", description);
        jsonObject.put("viewNum", viewNum);
        jsonObject.put("score", score);

        response.setStatus(200);
        response.setContentType("text/json;charset=utf-8");  // 处理响应头和中文编码问题
        response.getWriter().write(jsonObject.toJSONString());
    }

    /**
     * 点赞
     */
    public void like(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cId = Integer.valueOf(request.getParameter("cId"));
        int sId = -1;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie: cookies) {
            if ("stuId".equals(cookie.getName())) sId = Integer.valueOf(cookie.getValue());
            break;
        }
        // class_log 中点赞人数加一
        classLogService.increaseOneByCIdAndColumn(cId, "cl_like_num");
        // class_like 中增加一条记录
        ClassLike classLike = new ClassLike();
        classLike.setsId(sId);
        classLike.setcId(cId);
        classLikeService.addClassLike(classLike);
    }

    /**
     * 收藏
     */
    public void collect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cId = Integer.valueOf(request.getParameter("cId"));
        int sId = -1;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie: cookies) {
            if ("stuId".equals(cookie.getName())) sId = Integer.valueOf(cookie.getValue());
            break;
        }
        // class_log 中收藏人数加一
        classLogService.increaseOneByCIdAndColumn(cId, "cl_collect_num");
        // class_collect 中增加一条记录
        ClassCollect classCollect = new ClassCollect();
        classCollect.setsId(sId);
        classCollect.setcId(cId);
        classCollectService.addClassCollect(classCollect);
    }

    /**
     * 评分
     */
    public void score(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get : score & cId
        int score = Integer.valueOf(request.getParameter("score"));
        int cId = Integer.valueOf(request.getParameter("cId"));
        // class_log 中评分人数加一
        classLogService.increaseOneByCIdAndColumn(cId, "cl_score_num");
        // class_log 中的新的平均分更新
        ClassLog classLog = classLogService.selectByCId(cId);
        int newScore = classLogService.reAveScore(classLog.getScoreNum(), classLog.getScore(), score);
        classLogService.updateByCIdAndColumn(cId, "cl_score", newScore);
    }

}
