package com.mooc.service.impl;

import com.mooc.mapper.ClassExamMapper;
import com.mooc.pojo.ClassExam;
import com.mooc.service.ClassExamService;
import com.mooc.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class ClassExamServiceImpl implements ClassExamService {

    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public void addClassExam(ClassExam classExam) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassExamMapper mapper = sqlSession.getMapper(ClassExamMapper.class);
        mapper.addClassExam(classExam);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteBySId(int sId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassExamMapper mapper = sqlSession.getMapper(ClassExamMapper.class);
        mapper.deleteBySId(sId);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteByCId(int cId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassExamMapper mapper = sqlSession.getMapper(ClassExamMapper.class);
        mapper.deleteByCId(cId);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public List<ClassExam> selectBySId(int sId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassExamMapper mapper = sqlSession.getMapper(ClassExamMapper.class);
        List<ClassExam> classExams = mapper.selectBySId(sId);
        sqlSession.close();
        return classExams;
    }

    @Override
    public int selectBySIdAndCId(int sId, int cId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassExamMapper mapper = sqlSession.getMapper(ClassExamMapper.class);
        ClassExam classExam = mapper.selectBySIdAndCId(sId, cId);
        sqlSession.close();
        if (classExam!=null) return classExam.getScore();
        else return -1;
    }

    @Override
    public void updateScoreBySIdAndCId(int sId, int cId, int newScore) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassExamMapper mapper = sqlSession.getMapper(ClassExamMapper.class);
        mapper.updateScoreBySIdAndCId(sId, cId, newScore);
        sqlSession.commit();
        sqlSession.close();
    }
}
