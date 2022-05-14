package com.mooc.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionFactoryUtils {

    private static SqlSessionFactory sqlSessionFactory;  // 变量作用域提升

    // 静态代码块会随着类的加载自动执行，且只执行一次
    /* 抽取SqlSessionFactoryUtils工具类的原因是，只需要一个SqlSessionFactory对象 */
    static {
        /* ctrl+alt+t 代码包裹*/
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 返回一个 SqlSessionFactory 对象
    public static SqlSessionFactory getSqlSessionFactory(){
        return sqlSessionFactory;
    }

}
