package com.mooc.service.impl;

import com.mooc.mapper.ClassLogMapper;
import com.mooc.pojo.ClassLog;
import com.mooc.service.ClassLogService;
import com.mooc.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class ClassLogServiceImpl implements ClassLogService {

    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public void addClassLog(ClassLog classLog) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassLogMapper mapper = sqlSession.getMapper(ClassLogMapper.class);
        mapper.addClassLog(classLog);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteByCId(int cId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassLogMapper mapper = sqlSession.getMapper(ClassLogMapper.class);
        mapper.deleteByCId(cId);
        sqlSession.commit();
        sqlSession.close();

    }

    @Override
    public ClassLog selectByCId(int cId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassLogMapper mapper = sqlSession.getMapper(ClassLogMapper.class);
        ClassLog classLog = mapper.selectByCId(cId);
        sqlSession.close();
        return classLog;
    }

    @Override
    public void updateRow(ClassLog classLog) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassLogMapper mapper = sqlSession.getMapper(ClassLogMapper.class);
        mapper.updateRow(classLog);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void updateByCIdAndColumn(int cId, String columnName, int columnValue) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassLogMapper mapper = sqlSession.getMapper(ClassLogMapper.class);
        mapper.updateByCIdAndColumn(cId, columnName, columnValue);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void increaseOneByCIdAndColumn(int cId, String columnName) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassLogMapper mapper = sqlSession.getMapper(ClassLogMapper.class);
        mapper.increaseOneByCIdAndColumn(cId, columnName);
        sqlSession.commit();
        sqlSession.close();
    }
}
