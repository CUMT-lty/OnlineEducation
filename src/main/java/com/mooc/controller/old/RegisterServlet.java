package com.mooc.controller.old;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mooc.pojo.Stu;
import com.mooc.service.impl.StuServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    // 1. 调用对应的UserService中的方法来完成需要的操作
    private StuServiceImpl stuService = new StuServiceImpl();
    /* 将service的创建提到成员部分 */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /* 额外：处理POST请求中文乱码问题 */
        request.setCharacterEncoding("utf-8");

        /*// 2. 接受前台传来的数据，getParameter中的参数为传递的参数名称
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String school = request.getParameter("school");
        String profession = request.getParameter("profession");*/


        // 2. 接收前台传来的数据，数据是以json格式传来的
        BufferedReader reader = request.getReader();
        String jsonStr = reader.readLine();    // json字符串
        // 3. 将json字符串封装为一个Stu对象
        Stu stu = JSON.parseObject(jsonStr, Stu.class);
        // 4. 调用stuService中的register方法完成注册操作（和业务逻辑层交互）
        boolean registerFlag = stuService.register(stu);
        // 如果注册成功，则直接跳转到登录页面，否则给原注册页面返回失败信息
        if (registerFlag==true) {  // 注册成果，直接跳转到登录页面
            response.sendRedirect("/login.html");   // 重定向
        } else {  // 注册失败，给原注册页面响应失败信息
            // 5. 封装json对象
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("registerRes", false);  // false说明用户名重复
            // 6. 将json对象转为json字符串
            String s = JSON.toJSONString(jsonObject);
            // 7. 响应回前端
            response.setContentType("text/json;charset=utf-8");  // 处理响应头和中文编码问题
            response.getWriter().write(s);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
