package com.mooc.test;

import com.alibaba.fastjson.JSON;
import com.mooc.mapper.ClassLogMapper;
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


    @Test
    public void recommendTest(){
        ClassLogServiceImpl classLogService = new ClassLogServiceImpl();
        ClassLog[] classLogs = classLogService.selectByScoreOrderLimNum(6);

        String s = JSON.toJSONString(classLogs);
        System.out.println(s);

    }

    @Test
    public void classLogTest(){

        ClassLogServiceImpl classLogService = new ClassLogServiceImpl();


        // get : score & cId
        int score = 7;
        int cId = 1;
        // class_log 中评分人数加一
        classLogService.increaseOneByCIdAndColumn(cId, "cl_score_num");
        // class_log 中的新的平均分更新
        ClassLog classLog = classLogService.selectByCId(cId);
        int newScore = classLogService.reAveScore(classLog.getScoreNum(), classLog.getScore(), score);
        System.out.println(newScore);
        classLogService.updateByCIdAndColumn(cId, "cl_score", newScore);
    }


    @Test
    public void searchTest(){
        String nameLike = "java";
        ClassLogServiceImpl classLogService = new ClassLogServiceImpl();
        ClassLog[] classLogs = classLogService.selectByNameLike(nameLike);
        String classLogsJsonStr = JSON.toJSONString(classLogs);
        System.out.println(classLogsJsonStr);
    }

}
