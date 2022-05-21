package com.mooc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mooc.bean.AnsBean;
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

        String cIdStr = request.getParameter("cId");
        int cId = Integer.valueOf(cIdStr);         // 获取课程id

        Exam[] exams = examService.randomSelectByCIdAndNum(cId, 9);  // 获取九道试题
        String examsJsonStr = JSON.toJSONString(exams);   // 将试题列表转为json字符串

        response.setContentType("text/json;charset=utf-8");
        response.setStatus(200);
        response.getWriter().write(examsJsonStr);
    }


    /**
     * 参加课后测试页，获取本次测试的得分
     */
    public void ans(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");   // 设置编码方式,处理POST请求中文乱码问题
        String s = request.getReader().readLine();
//        System.out.println("json:" + "   "+s);
        AnsBean ansBean = JSON.parseObject(s, AnsBean.class);  // 转成java对象
//        System.out.println(ansBean);

        int[] ids = ansBean.getIds();
        int[] checkedAnswers = ansBean.getCheckedAnswers();

        List<Integer> ansList = examService.selectAnswersByIds(ids);
        int[] ansInts = ansList.stream().mapToInt(Integer::valueOf).toArray();  // 转为整形数组

        int score = ExamUtils.examScoreResult(checkedAnswers, ansInts);   // 计算分数

        System.out.println(score);

        JSONObject json = new JSONObject();
        json.put("score", score);
        String jsonStr = json.toJSONString();

        response.setStatus(200);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonStr);   // 返回分数

    }
}
