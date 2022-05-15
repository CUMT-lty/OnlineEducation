package com.mooc.test;

import com.alibaba.fastjson.JSON;
import com.mooc.mapper.ExamMapper;
import com.mooc.mapper.StuMapper;
import com.mooc.pojo.Stu;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class TryTest {



    @Test
    public void demoTest() throws IOException {
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



}
