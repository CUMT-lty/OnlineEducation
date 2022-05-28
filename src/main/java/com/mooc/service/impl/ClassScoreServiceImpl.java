package com.mooc.service.impl;

import com.mooc.mapper.ClassScoreMapper;
import com.mooc.pojo.ClassScore;
import com.mooc.service.ClassScoreSercive;
import com.mooc.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class ClassScoreServiceImpl implements ClassScoreSercive {

    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public void addClassScore(ClassScore classScore) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassScoreMapper mapper = sqlSession.getMapper(ClassScoreMapper.class);
        mapper.addClassScore(classScore);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public ClassScore[] selectBySIdAndCId(int sId, int cId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassScoreMapper mapper = sqlSession.getMapper(ClassScoreMapper.class);
        List<ClassScore> classScores= mapper.selectBySIdAndCId(sId, cId);
        sqlSession.close();
        return classScores.toArray(new ClassScore[classScores.size()]);
    }

    @Override
    public void updateScoreBySIdAndCId(int sId, int cId, int newScore) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassScoreMapper mapper = sqlSession.getMapper(ClassScoreMapper.class);
        mapper.updateScoreBySIdAndCId(sId, cId, newScore);
        sqlSession.commit();
        sqlSession.close();
    }
}
