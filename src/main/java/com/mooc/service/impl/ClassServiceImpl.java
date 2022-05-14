package com.mooc.service.impl;

import com.mooc.mapper.ClassMapper;
import com.mooc.pojo.Class;
import com.mooc.service.ClassService;
import com.mooc.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class ClassServiceImpl implements ClassService {

    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public void addClass(Class c) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassMapper mapper = sqlSession.getMapper(ClassMapper.class);
        mapper.addClass(c);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassMapper mapper = sqlSession.getMapper(ClassMapper.class);
        mapper.deleteById(id);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteByName(String name) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassMapper mapper = sqlSession.getMapper(ClassMapper.class);
        mapper.deleteByName(name);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteClassesByIds(int[] ids) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassMapper mapper = sqlSession.getMapper(ClassMapper.class);
        mapper.deleteClassesByIds(ids);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteClassesByNames(String[] names) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassMapper mapper = sqlSession.getMapper(ClassMapper.class);
        mapper.deleteClassesByNames(names);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public Class selectById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassMapper mapper = sqlSession.getMapper(ClassMapper.class);
        Class res = mapper.selectById(id);
        sqlSession.close();
        return res;
    }

    @Override
    public Class selectByName(String name) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassMapper mapper = sqlSession.getMapper(ClassMapper.class);
        Class res = mapper.selectByName(name);
        sqlSession.close();
        return res;
    }

    @Override
    public List<Class> selectClassesByLimit(int startIndex, int size) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassMapper mapper = sqlSession.getMapper(ClassMapper.class);
        List<Class> classes = mapper.selectClassesByLimit(startIndex, size);
        sqlSession.close();
        return classes;
    }

    @Override
    public List<Class> selectClassesByKId(int kId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassMapper mapper = sqlSession.getMapper(ClassMapper.class);
        List<Class> classes = mapper.selectClassesByKId(kId);
        sqlSession.close();
        return classes;
    }

    @Override
    public List<Class> selectClassesByTId(int tId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassMapper mapper = sqlSession.getMapper(ClassMapper.class);
        List<Class> classes = mapper.selectClassesByTId(tId);
        sqlSession.close();
        return classes;
    }

    @Override
    public List<Class> selectByNameLike(String name) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassMapper mapper = sqlSession.getMapper(ClassMapper.class);
        List<Class> classes = mapper.selectByNameLike(name);
        sqlSession.close();
        return null;
    }

}
