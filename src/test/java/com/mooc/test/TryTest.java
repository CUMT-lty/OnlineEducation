package com.mooc.test;

import com.alibaba.fastjson.JSON;
import com.mooc.pojo.ClassLog;
import com.mooc.pojo.Exam;
import com.mooc.service.impl.ClassLogServiceImpl;
import com.mooc.service.impl.ExamServiceImpl;
import com.mooc.service.impl.Neo4jServiceImpl;
import com.mooc.service.impl.StuCognitionServiceImpl;
import com.mooc.util.ExamUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TryTest {

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
    public void ExamTest() {
        ExamServiceImpl examService = new ExamServiceImpl();

        int cId = 1;

        Exam[] exams = examService.randomSelectByCIdAndNum(cId, 9);  // 获取九道试题
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
    public void intListDemoTest(){

        List<Integer> intList  = new ArrayList<>();
        intList.add(1);
        intList.add(2);

        int[] ints = intList.stream().mapToInt(Integer::valueOf).toArray();

        System.out.println(Arrays.toString(ints));
    }


    @Test
    public void recClaByScoreTest(){
        ClassLogServiceImpl classLogService = new ClassLogServiceImpl();
        ClassLog[] classLogs = classLogService.selectByScoreOrderLimNum(2);
        String s = JSON.toJSONString(classLogs);
        System.out.println(s);
    }



}
