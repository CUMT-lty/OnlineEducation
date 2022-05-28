package com.mooc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mooc.bean.AnsBean;
import com.mooc.pojo.Class;
import com.mooc.pojo.ClassExam;
import com.mooc.pojo.Exam;
import com.mooc.pojo.StuCognition;
import com.mooc.service.StuCognitionService;
import com.mooc.service.impl.ClassExamServiceImpl;
import com.mooc.service.impl.ClassServiceImpl;
import com.mooc.service.impl.ExamServiceImpl;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

import com.mooc.service.impl.StuCognitionServiceImpl;
import com.mooc.util.ExamUtils;

@WebServlet("/exam/*")
public class ExamServlet extends BaseServlet {

    ExamServiceImpl examService = new ExamServiceImpl();
    StuCognitionServiceImpl stuCognitionService = new StuCognitionServiceImpl();
    ClassExamServiceImpl classExamService = new ClassExamServiceImpl();
    ClassServiceImpl classService = new ClassServiceImpl();

    /**
     * 参加课后测试页，获取九道题目
     */
    public void takeExam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");   // 设置编码方式,处理POST请求中文乱码问题

        String cIdStr = request.getParameter("cId");
        int cId = Integer.valueOf(cIdStr);         // 获取课程id
        System.out.println(cId);

        Exam[] exams = examService.randomSelectByCIdAndNum(cId, 9);  // 获取试题
        String examsJsonStr = JSON.toJSONString(exams);   // 将试题列表转为json字符串
        System.out.println(examsJsonStr);

        response.setContentType("text/json;charset=utf-8");
        response.setStatus(200);
        response.getWriter().write(examsJsonStr);
    }


    /**
     * 参加课后测试页，获取本次测试的得分
     */
    public void ans(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");   // 设置编码方式,处理POST请求中文乱码问题
        // 获取课程id和学生id
        int cId = Integer.valueOf(request.getParameter("cId"));
        int sId = -1;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie: cookies){    // 从cookie获取学生id
            if ("stuId".equals(cookie.getName())) sId = Integer.valueOf(cookie.getValue());
            break;
        }

        String s = request.getParameter("ansAndEIds");
        AnsBean ansBean = JSON.parseObject(s, AnsBean.class);  // 转成java对象
        int[] ids = ansBean.getIds();
        int[] checkedAnswers = ansBean.getCheckedAnswers();
        int[] ansList = examService.selectAnswersByIds(ids);
        int newScore = ExamUtils.examScoreResult(checkedAnswers, ansList);   // 计算分数
        JSONObject json = new JSONObject();
        json.put("score", newScore);
        String jsonStr = json.toJSONString();
        response.setStatus(200);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonStr);   // 将本次得分响应回前端

        // 将分数信息保存到数据库
        // 先看该学生是否是第一次考试
        int oldScore = classExamService.selectBySIdAndCId(sId, cId);
        if (oldScore == -1) {    // 如果是第一次考试
            // 增加一条考试记录
            ClassExam classExam = new ClassExam();
            classExam.setcId(cId);
            classExam.setsId(sId);
            classExam.setScore(oldScore);
            classExamService.addClassExam(classExam);
        } else {     // 如果不是第一次考试
            // 更新本条考试记录
            classExamService.updateScoreBySIdAndCId(sId, cId, newScore);
        }

        // 如果学生的考试分数大于等于六十分，更新学生的认知信息
        if (newScore > 60 || newScore == 60) {
            // 查找课程所在知识点
            int kId = classService.selectKnowledgeByCId(cId);
            // 掌握该课程对应的认知水平
            Class aClass = classService.selectById(cId);
            int level = aClass.getLevel();
            // 看学生是否已认知这个知识点
            StuCognition stuCognition = stuCognitionService.selectBySIdAndKId(sId, kId);
            if (stuCognition == null){   // 如果还没有认知，就添加一条认知记录
                StuCognition newStuCognition = new StuCognition();
                newStuCognition.setsId(sId);
                newStuCognition.setkId(kId);
                newStuCognition.setCongitionLevel(level);
                stuCognitionService.addStuCognition(newStuCognition);
            } else {  // 如果已认知，就更新这条认知记录
                int oldLevel = stuCognition.getCongitionLevel();
                if(level > oldLevel) {  // 如果认知水平提高，就更新认知水平
                    stuCognitionService.updateBySIdAndKId(sId, kId, level);
                }
            }
        }
    }
}
