package com.mooc.service.impl;

import com.mooc.mapper.ClassLikeMapper;
import com.mooc.pojo.ClassLike;
import com.mooc.service.ClassLikeService;
import com.mooc.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class ClassLikeServiceImpl implements ClassLikeService {

    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public void addClassLike(ClassLike classLike) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassLikeMapper mapper = sqlSession.getMapper(ClassLikeMapper.class);
        mapper.addClassLike(classLike);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteByCIdAndSId(int cId, int sId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassLikeMapper mapper = sqlSession.getMapper(ClassLikeMapper.class);
        mapper.deleteByCIdAndSId(cId, sId);
        sqlSession.commit();
        sqlSession.close();

    }

    @Override
    public void deleteRowsBySIdAndCIds(int sId, int[] cIds) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassLikeMapper mapper = sqlSession.getMapper(ClassLikeMapper.class);
        mapper.deleteRowsBySIdAndCIds(sId, cIds);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public int totalRowsByCId(int cId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassLikeMapper mapper = sqlSession.getMapper(ClassLikeMapper.class);
        int rowNum = mapper.totalRowsByCId(cId);
        sqlSession.close();
        return rowNum;
    }

    @Override
    public int totalRowsBySId(int sId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassLikeMapper mapper = sqlSession.getMapper(ClassLikeMapper.class);
        int rowNum = mapper.totalRowsBySId(sId);
        sqlSession.close();
        return rowNum;
    }

    @Override
    public ClassLike selectByCIdAndSId(int cId, int sId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassLikeMapper mapper = sqlSession.getMapper(ClassLikeMapper.class);
        ClassLike classLike = mapper.selectByCIdAndSId(cId, sId);
        sqlSession.close();
        return classLike;
    }
}
