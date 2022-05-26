package com.mooc.test;

import com.mooc.mapper.StuCognitionMapper;
import com.mooc.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MabatisTest {

    @Test
    public void testMethods() throws IOException{
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StuCognitionMapper mapper = sqlSession.getMapper(StuCognitionMapper.class);

    }

}
