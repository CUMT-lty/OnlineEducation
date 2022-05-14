package com.mooc.controller.old;

import com.mooc.util.VerifyUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/verifyServlet")
public class VerifyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletOutputStream os = response.getOutputStream();
        String verifyCode = VerifyUtils.outputVerifyImage(100, 50, 4, os);

        // 用session保存验证码
//        HttpSession session = request.getSession();
//        session.setAttribute("verifyCode", verifyCode);
        // 如果需要验证吗功能的话，在需要的位置获取session就好
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
