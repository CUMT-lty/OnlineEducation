package com.mooc.test;

import com.alibaba.fastjson.JSON;
import com.mooc.bean.AnsBean;
import com.mooc.bean.ExamIdBean;
import com.mooc.mapper.StuMapper;
import com.mooc.pojo.Exam;
import com.mooc.pojo.Stu;
import com.mooc.service.impl.ExamServiceImpl;
import com.mooc.service.impl.Neo4jServiceImpl;
import com.mooc.service.impl.StuCognitionServiceImpl;
import com.mooc.util.ExamUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import javax.servlet.http.Cookie;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class TryTest {



    @Test
    public void demoTest1() throws IOException {
        String s = "{\"username\":\"uniq\",\"password\":\"admin\",\"name\":\"admin\",\"school\":\"admin\",\"profession\":\"admin\"}";
        Stu stu = JSON.parseObject(s, Stu.class);
//        System.out.println(stu);
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StuMapper mapper = sqlSession.getMapper(StuMapper.class);
        mapper.addStu(stu);
        sqlSession.commit();

        Stu uniq = mapper.selectByUsername("uniq");
        System.out.println(uniq);
        sqlSession.close();

    }

    @Test
    public void demoTest2(){

        Neo4jServiceImpl neo4jService = new Neo4jServiceImpl();
        String[] javas = neo4jService.getLastByKname("c");

        System.out.println(Arrays.toString(javas));
    }

    @Test
    public void demoTest3(){
        StuCognitionServiceImpl stuCognitionService = new StuCognitionServiceImpl();
        int[] ints = stuCognitionService.selectStuCognitionsBySId(1);
        System.out.println(Arrays.toString(ints));
    }

    @Test
    public void ExamTest(){
        ExamServiceImpl examService = new ExamServiceImpl();

        List<Exam> exams = examService.randomSelectByNum(9);  // 获取九道试题
        int[] eIds = examService.getEIdsByExams(exams);     // 获取九道试题的id

        ExamIdBean examIdBean = new ExamIdBean();
        examIdBean.seteIds(eIds);    // 将试题id数组存入examIdBean的eIds属性中

        String  examIdBeanJsonStr = JSON.toJSONString(examIdBean);   // 将examIdBean转为字符串

//        System.out.println(examIdBeanJsonStr);

        Cookie eIdsCookie = new Cookie("eIds", examIdBeanJsonStr);   // 存为cookie

        String examsJsonStr = JSON.toJSONString(exams);   // 将试题列表转为json字符串

        System.out.println(examsJsonStr);
    }


    @Test
    public void AnsTest(){
        ExamServiceImpl examService = new ExamServiceImpl();
        int[] eIds = {1,3,4,5,6,7,8,9,13};

        List<Integer> ansList = examService.selectAnswersByIds(eIds);

        int[] answers = new int[ansList.size()];

        for(int i = 0;i<ansList.size();i++){
            answers[i] = ansList.get(i);      // 根据提交的eIds，获取正确答案的数组
        }

        int[] stuAns = {3,1,3,2,1,4,1,1,4};   // 将stuAnswers答案字符串转为答案数组

        int score = ExamUtils.examScoreResult(stuAns, answers);  // 评分

        System.out.println(score);

    }


    @Test
    public void demoTest(){

        AnsBean ansBean = new AnsBean();
        ansBean.setCheckedAnswer(1);
        ansBean.setId(1);

        System.out.println(ansBean);

    }

}
