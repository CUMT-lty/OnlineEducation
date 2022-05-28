package com.mooc.test;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mooc.mapper.ClassExamMapper;
import com.mooc.pojo.ClassExam;
import com.mooc.pojo.ClassLog;
import com.mooc.service.impl.ClassLogServiceImpl;
import com.mooc.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.util.Arrays;

public class TryTest {

    @Test
    public void recommendTest(){
        ClassLogServiceImpl classLogService = new ClassLogServiceImpl();
        ClassLog[] classLogs = classLogService.recommendClassByStuId(1);
        System.out.println(Arrays.toString(classLogs));

    }


    @Test
    public void classExamTest(){
        int sId = 1;
        int cId = 2;
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassExamMapper mapper = sqlSession.getMapper(ClassExamMapper.class);
        System.out.println(mapper.selectBySIdAndCId(sId, cId));

    }


    @Test
    public void tryDemo(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "java语言程序设计");
        jsonObject.put("description", "java入门基础课程");
        jsonObject.put("viewNum", 365);
        jsonObject.put("score", 7);
        jsonObject.put("like", true);
        jsonObject.put("collect", true);
        String s = jsonObject.toJSONString();
        System.out.println(s);
    }




}
