package com.mooc.service.impl;

import com.mooc.mapper.TeacherMapper;
import com.mooc.pojo.Teacher;
import com.mooc.service.TeacherService;
import com.mooc.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class TeacherServiceImpl implements TeacherService {

    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public void addNewTeacher(Teacher teacher) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
        mapper.addNewTeacher(teacher);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
        mapper.deleteById(id);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteByName(String name) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
        mapper.deleteByName(name);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public Teacher selectByNameAndPsw(String name, String password) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
        Teacher teacher = mapper.selectByNameAndPsw(name, password);
        sqlSession.close();
        return teacher;
    }

    @Override
    public void updatePasswordById(int id, String newPsw) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
        mapper.updatePasswordById(id, newPsw);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void updatePasswordByName(String name, String newPsw) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
        mapper.updatePasswordByName(name, newPsw);
        sqlSession.commit();
        sqlSession.close();
    }
}
