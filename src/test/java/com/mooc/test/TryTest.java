package com.mooc.test;
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




}
