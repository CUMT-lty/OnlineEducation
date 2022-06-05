package com.mooc.controller;

import com.alibaba.fastjson.JSON;
import com.mooc.pojo.Stu;
import com.mooc.pojo.StuLog;
import com.mooc.service.impl.StuLogServiceImpl;
import com.mooc.service.impl.StuServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/stu/*")  // 使用目录式路由管理
public class StuServlet extends BaseServlet {

  StuServiceImpl stuService = new StuServiceImpl();   // service层访问接口
  StuLogServiceImpl stuLogService = new StuLogServiceImpl();

  public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InterruptedException {
    request.setCharacterEncoding("utf-8");   // 设置编码方式,处理POST请求中文乱码问题
    String jsonStr = request.getReader().readLine();
    Stu stu = JSON.parseObject(jsonStr, Stu.class);   // 映射为Stu实体类对象
    String username = stu.getUsername();
    Stu tmpStu = stuService.selectByUsername(username);
    if (tmpStu != null) {   // 如果用户已存在，设置响应状态码500
      response.setStatus(500);
      response.setContentType("text/plaintext;charset=utf-8");
      response.getWriter().write(String.valueOf(false));
    } else {  // 如果是新用户，则继续进行注册，并返回响应状态码200
      // stu表加一条记录，并返回该条记录的stuId
      int stuId = stuService.addStu(stu);
      response.setStatus(200);
      response.setContentType("text/plaintext;charset=utf-8");
      response.getWriter().write(String.valueOf(true));
      // stu_log表添加一条记录
      StuLog stuLog = new StuLog();
      stuLog.setStuId(stuId);
      stuLogService.addStuLog(stuLog);
    }
  }

  public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("utf-8");   // 设置编码方式,处理POST请求中文乱码问题
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    Stu stu = stuService.selectByUsernameAndPsw(username, password);
    if (stu != null) {   // 登录信息正确
      // 设置cookie，在浏览器端保存用户stuId
      Cookie cookie = new Cookie("stuId", String.valueOf(stu.getId()));
      cookie.setPath("/");    // 扩大cookie作用范围至整个项目
      response.addCookie(cookie);
      response.setStatus(200);
    } else {  // 登录信息有误
      response.setStatus(500);
    }
  }

  public void stuLog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int stuId = -1;
    Cookie[] cookies = request.getCookies();
    if (cookies != null && cookies.length != 0) {
      for (Cookie cookie : cookies) {
        if ("stuId".equals(cookie.getName())) {   // 从cookie获取学生id
          stuId = Integer.valueOf(cookie.getValue());
          break;
        }
      }
    }

    if (stuId != -1) {   // 如果用户已登录
      StuLog stuLog = stuLogService.selectBySId(stuId);
      String jsonStr = JSON.toJSONString(stuLog);
      response.setStatus(200);
      response.setContentType("text/json;charset=utf-8");
      response.getWriter().write(jsonStr);
    } else {   // 用户没有登陆或者没拿到stuId
      response.setStatus(500);
      response.setContentType("text/plaintext;charset=utf-8");
      response.getWriter().write(String.valueOf(false));
    }
  }

}
