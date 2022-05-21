package com.mooc.controller;

import com.alibaba.fastjson.JSON;
import com.mooc.bean.ExamIdBean;
import com.mooc.controller.BaseServlet;
import com.mooc.pojo.Exam;
import com.mooc.service.impl.ExamServiceImpl;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

import com.mooc.util.ExamUtils;

@WebServlet("/exam/*")
public class ExamServlet extends BaseServlet {

    ExamServiceImpl examService = new ExamServiceImpl();

    /**
     * 参加课后测试页，获取九道题目
     */
    public void takeExam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");   // 设置编码方式,处理POST请求中文乱码问题

        List<Exam> exams = examService.randomSelectByNum(9);  // 获取九道试题

        int[] eIds = examService.getEIdsByExams(exams);     // 获取九道试题的id

        ExamIdBean examIdBean = new ExamIdBean();
        examIdBean.seteIds(eIds);    // 将试题id数组存入examIdBean的eIds属性中
        String examBeanJsonStr = JSON.toJSONString(examIdBean);   // 将examIdBean转为字符串
        Cookie eIdsCookie = new Cookie("eIds", examBeanJsonStr);   // 设为一条cookie

        String examsJsonStr = JSON.toJSONString(exams);   // 将试题列表转为json字符串

        response.setContentType("text/json;charset=utf-8");
        response.addCookie(eIdsCookie);    // 将试题id存入cookie，（cookie值是一个json类型的字符串）
        response.setStatus(200);
        response.getWriter().write(examsJsonStr);
    }


    /**
     * 参加课后测试页，获取本次测试的得分
     */
    public void ans(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");   // 设置编码方式,处理POST请求中文乱码问题

        Cookie[] cookies = request.getCookies();    // 拿到所有的cookie
        String stuAnswers = request.getReader().readLine();   // 拿到答案集合

        String examIdBeanJson = "";   // cookie值是一个对应examIdBean类型的json字符串
        for (Cookie cookie: cookies) {
            if (cookie.getName().equals("eIds"));
            examIdBeanJson = cookie.getValue();
        }

        // 将examIdBean类型的json字符串转为对应的ExamIdBean对象
        ExamIdBean examIdBean = JSON.parseObject(examIdBeanJson, ExamIdBean.class);
        int[] eIds = examIdBean.geteIds();  // 获取到ExamIdBean对象的eIds属性

        List<Integer> ansList = examService.selectAnswersByIds(eIds);
        int[] answers = new int[ansList.size()];
        for(int i = 0;i<ansList.size();i++){
            answers[i] = ansList.get(i);      // 根据提交的eIds，获取正确答案的数组
        }

        int[] stuAns = {0};   // 将stuAnswers答案字符串转为答案数组

        int score = ExamUtils.examScoreResult(stuAns, answers);

        response.setStatus(200);
        response.setContentType("text/plaintext;charset=utf-8");
        response.getWriter().write(score);   // 把最后分数返回
    }
}
