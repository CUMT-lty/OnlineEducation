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
import org.neo4j.register.Register;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MabatisTest {

    @Test
    public void testMethods() throws IOException{
        int[] a = {1,2,3,4,5};
        System.out.println(String.valueOf(a));
    }

}
