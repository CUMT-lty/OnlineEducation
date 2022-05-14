package com.mooc.service.impl;

import com.mooc.mapper.StuLogMapper;
import com.mooc.pojo.StuLog;
import com.mooc.service.StuLogService;
import com.mooc.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class StuLogServiceImpl implements StuLogService {

    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public void addStuLog(StuLog stuLog) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StuLogMapper mapper = sqlSession.getMapper(StuLogMapper.class);
        mapper.addStuLog(stuLog);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteBySId(int sId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StuLogMapper mapper = sqlSession.getMapper(StuLogMapper.class);
        mapper.deleteBySId(sId);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public StuLog selectBySId(int sId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StuLogMapper mapper = sqlSession.getMapper(StuLogMapper.class);
        StuLog stuLog = mapper.selectBySId(sId);
        sqlSession.close();
        return stuLog;
    }

    @Override
    public void updateStudyTimeBySId(int sId, int newStudyTime) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StuLogMapper mapper = sqlSession.getMapper(StuLogMapper.class);
        mapper.updateStudyTimeBySId(sId, newStudyTime);
        sqlSession.commit();
        sqlSession.close();    }

    @Override
    public void increaseOneBySIdAndColumnName(int sId, String columnName) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StuLogMapper mapper = sqlSession.getMapper(StuLogMapper.class);
        mapper.increaseOneBySIdAndColumnName(sId, columnName);
        sqlSession.commit();
        sqlSession.close();
    }
}
