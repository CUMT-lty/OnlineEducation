package com.mooc.service.impl;

import com.mooc.mapper.ClassCollectMapper;
import com.mooc.pojo.ClassCollect;
import com.mooc.service.ClassCollectService;
import com.mooc.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class ClassCollectServiceImpl implements ClassCollectService {

    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public void addClassCollect(ClassCollect classCollect) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassCollectMapper mapper = sqlSession.getMapper(ClassCollectMapper.class);
        mapper.addClassCollect(classCollect);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteByCIdAndSId(int cId, int sId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassCollectMapper mapper = sqlSession.getMapper(ClassCollectMapper.class);
        mapper.deleteByCIdAndSId(cId, sId);
        sqlSession.commit();
        sqlSession.close();

    }

    @Override
    public void deleteRowsBySIdAndCIds(int sId, int[] cIds) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassCollectMapper mapper = sqlSession.getMapper(ClassCollectMapper.class);
        mapper.deleteRowsBySIdAndCIds(sId, cIds);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public int totalRowsByCId(int cId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassCollectMapper mapper = sqlSession.getMapper(ClassCollectMapper.class);
        int rowNum = mapper.totalRowsByCId(cId);
        sqlSession.close();
        return rowNum;
    }

    @Override
    public int totalRowsBySId(int sId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassCollectMapper mapper = sqlSession.getMapper(ClassCollectMapper.class);
        int rowNum = mapper.totalRowsBySId(sId);
        sqlSession.close();
        return rowNum;
    }

    @Override
    public ClassCollect selectByCIdAndSId(int cId, int sId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassCollectMapper mapper = sqlSession.getMapper(ClassCollectMapper.class);
        ClassCollect classCollect = mapper.selectByCIdAndSId(cId, sId);
        sqlSession.close();
        return classCollect;
    }
}
