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

  ClassServiceImpl classService = new ClassServiceImpl();
  ClassLogServiceImpl classLogService = new ClassLogServiceImpl();
  ClassLikeServiceImpl classLikeService = new ClassLikeServiceImpl();
  ClassCollectServiceImpl classCollectService = new ClassCollectServiceImpl();
  ClassScoreServiceImpl classScoreService = new ClassScoreServiceImpl();


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
    if (cookies != null && cookies.length != 0) {
      for (Cookie cookie : cookies) {
        if ("stuId".equals(cookie.getName())) {
          stuId = Integer.parseInt(cookie.getValue());
          break;
        }
      }
    }
    ClassLog[] classLogs;
    // stuId都为正数，如果stuId为负，说没没获取到cookie，此时只为用户推荐高分课程
    if (stuId == -1) classLogs = classLogService.selectByScoreOrderLimNum(9);
      // 如果获取到了stuId说明用户状态为已登录，此时进行个性化课程推荐
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
    int sId = -1;     // 用户登录状态前端也会检查
    Cookie[] cookies = request.getCookies();
    if (cookies != null && cookies.length != 0) {
      for (Cookie cookie : cookies) {    // 从cookie中或缺stuId同时检查登陆状态
        if ("stuId".equals(cookie.getName())) {
          sId = Integer.valueOf(cookie.getValue());
          break;
        }
      }
    }
    // 获取课程相关信息
    ClassLog classLog = classLogService.selectByCId(cId);   // 获取课程动态信息
    String name = classLog.getcName();
    String description = classLog.getcDescription();
    int viewNum = classLog.getViewNum();   // 课程被观看次数
    int score = classLog.getScore();       // 课程得分
    // 将课程信息封装进json
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("name", name);
    jsonObject.put("description", description);
    jsonObject.put("viewNum", viewNum);
    jsonObject.put("score", score);
    // 获取学生相关信息，并封装进json
    if (sId != -1 && classLikeService.selectByCIdAndSId(cId, sId) != null) {
      // 已登录且点过赞
      jsonObject.put("like", true);
    } else {
      jsonObject.put("like", false);
    }
    if (sId != -1 && classCollectService.selectByCIdAndSId(cId, sId) != null) {
      // 已登陆且收藏过
      jsonObject.put("collect", true);
    } else {
      jsonObject.put("collect", false);
    }
    if (sId != -1) {
      // 如果已登陆，该学生对该课程的当前打分
      int stuClassScore = 0;
      ClassScore[] classScores = classScoreService.selectBySIdAndCId(sId, cId);
      if (classScores != null) stuClassScore = classScores[0].getScore();
      jsonObject.put("stuClassScore", stuClassScore);
    }
    // 将结果响应给前端
    response.setStatus(200);
    response.setContentType("text/json;charset=utf-8");  // 处理响应头和中文编码问题
    response.getWriter().write(jsonObject.toJSONString());
  }

  /**
   * 点赞
   */
  public void like(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String jsonStr = request.getReader().readLine();
    System.out.println("like: " + jsonStr);

    JSONObject jsonObject = JSON.parseObject(jsonStr);
    int cId = jsonObject.getInteger("cId");
    int sId = -1;     // 用户登录状态前端也会检查，避免冗余请求
    Cookie[] cookies = request.getCookies();
    if (cookies != null && cookies.length != 0) {
      for (Cookie cookie : cookies) {    // 从cookie中或缺stuId同时检查登陆状态
        if ("stuId".equals(cookie.getName())) {
          sId = Integer.valueOf(cookie.getValue());
          break;
        }
      }
    }
    // 查询class_like中是否已有点赞记录
    ClassLike cl1 = classLikeService.selectByCIdAndSId(cId, sId);
    if (cl1 != null) {   // 如果已有该点赞记录，则删除该记录
      classLikeService.deleteByCIdAndSId(cId, sId);
      response.setStatus(200);
      response.setContentType("text/plaintext;charset=utf-8");  // 处理响应头和中文编码问题
      response.getWriter().write(String.valueOf(false)); // 响应
    } else {          // 如果没有相应点赞记录，则添加该记录
      ClassLike classLike = new ClassLike();
      classLike.setsId(sId);
      classLike.setcId(cId);
      classLikeService.addClassLike(classLike);
      // 并且class_log 中点赞人数加一
      classLogService.increaseOneByCIdAndColumn(cId, "cl_like_num");
      // 响应
      response.setStatus(200);
      response.setContentType("text/plaintext;charset=utf-8");  // 处理响应头和中文编码问题
      response.getWriter().write(String.valueOf(true));  // 响应
    }
  }

  /**
   * 收藏
   */
  public void collect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String jsonStr = request.getReader().readLine();
    System.out.println("collect: " + jsonStr);

    JSONObject jsonObject = JSON.parseObject(jsonStr);
    int cId = jsonObject.getInteger("cId");
    int sId = -1;
    Cookie[] cookies = request.getCookies();
    if (cookies != null && cookies.length != 0) {
      for (Cookie cookie : cookies) {
        if ("stuId".equals(cookie.getName())) {
          sId = Integer.valueOf(cookie.getValue());
          break;
        }
      }
    }
    // 查询class_collect中是否已有收藏记录
    ClassCollect cc1 = classCollectService.selectByCIdAndSId(cId, sId);
    if (cc1 != null) {  // 如果已有收藏记录，删除该记录
      classCollectService.deleteByCIdAndSId(cId, sId);
      // 响应
      response.setStatus(200);
      response.setContentType("text/plaintext;charset=utf-8");  // 处理响应头和中文编码问题
      response.getWriter().write(String.valueOf(false));
    } else {  // 如果没有该收藏记录，添加一条记录
      ClassCollect classCollect = new ClassCollect();
      classCollect.setcId(cId);
      classCollect.setsId(sId);
      classCollectService.addClassCollect(classCollect);
      // 并且class_log 中收藏人数加一
      classLogService.increaseOneByCIdAndColumn(cId, "cl_collect_num");
      // 响应
      response.setStatus(200);
      response.setContentType("text/plaintext;charset=utf-8");  // 处理响应头和中文编码问题
      response.getWriter().write(String.valueOf(true));
    }
  }

  /**
   * 评分
   */
  public void score(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // get : score & cId
    String jsonStr = request.getReader().readLine();
    System.out.println("score: " + jsonStr);

    JSONObject jsonObject = JSON.parseObject(jsonStr);
    int cId = jsonObject.getInteger("cId");
    int score = jsonObject.getInteger("score");
    int sId = -1;
    Cookie[] cookies = request.getCookies();
    if (cookies != null && cookies.length != 0) {
      for (Cookie cookie : cookies) {
        if ("stuId".equals(cookie.getName())) {
          sId = Integer.valueOf(cookie.getValue());
          break;
        }
      }
    }

    // class_log 中评分人数加一
    classLogService.increaseOneByCIdAndColumn(cId, "cl_score_num");
    // class_log 中的新的平均分更新（10分制）
    ClassLog classLog = classLogService.selectByCId(cId);
    int newAveScore = classLogService.reAveScore(classLog.getScoreNum(), classLog.getScore(), score);
    classLogService.updateByCIdAndColumn(cId, "cl_score", newAveScore);


    // ClassScore中某学生对某课程的打分更新（5分制）
    ClassScore[] classScores = classScoreService.selectBySIdAndCId(sId, cId);
    if (classScores != null) {   // 如果已有打分记录，则更新
      classScoreService.updateScoreBySIdAndCId(sId, cId, score);
    } else {  // 如果没有评分记录，添加一条记录
      ClassScore classScore = new ClassScore();
      classScore.setsId(sId);
      classScore.setcId(cId);
      classScore.setScore(score);
      classScoreService.addClassScore(classScore);
    }

    // 响应
    response.setStatus(200);
    response.setContentType("text/plaintext;charset=utf-8");  // 处理响应头和中文编码问题
    response.getWriter().write(String.valueOf(true));
  }
}
