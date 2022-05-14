package com.mooc.test;

import com.mooc.mapper.ClassLikeMapper;
import com.mooc.mapper.ClassLogMapper;
import com.mooc.mapper.ClassMapper;
import com.mooc.mapper.ExamMapper;
import com.mooc.pojo.*;
import com.mooc.pojo.Class;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MabatisTest {

    @Test
    public void testMethods() throws IOException{
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        ExamMapper mapper = sqlSession.getMapper(ExamMapper.class);

        int[] ids = {1,3};

        List<Integer> list = mapper.selectAnswersByIds(ids);

        System.out.println(list);


//        sqlSession.commit();  // 插入要提交事务
        sqlSession.close();   // 关闭会话

    }

}
