package com.mooc.service.impl;

import com.mooc.mapper.ClassLogMapper;
import com.mooc.pojo.ClassLog;
import com.mooc.service.ClassLogService;
import com.mooc.util.SqlSessionFactoryUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Arrays;
import java.util.List;

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
    public ClassLog[] selectByNameLike(String cName){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassLogMapper mapper = sqlSession.getMapper(ClassLogMapper.class);
        List<ClassLog> classLogs = mapper.selectByNameLike(cName);
        sqlSession.close();
        return classLogs.toArray(new ClassLog[classLogs.size()]);
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
    public ClassLog[] selectByScoreOrderLimNum(int num) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassLogMapper mapper = sqlSession.getMapper(ClassLogMapper.class);
        List<ClassLog> classLogs = mapper.selectByScoreOrderLimNum(num);
        sqlSession.close();
        return classLogs.toArray(new ClassLog[num]);
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

    @Override
    public int reAveScore(int oldNum, int oldScore, int newScore) {
        return (int) Math.ceil(((oldNum * oldScore) + newScore) / (oldNum+1));
    }

    @Override
    public ClassLog[] recommendClassByStuId(int sId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassLogMapper mapper = sqlSession.getMapper(ClassLogMapper.class);

        // 个性化课程推荐
        List<ClassLog> classLogs = mapper.selectByScoreOrderLimNum(9);


        sqlSession.close();
        return classLogs.toArray(new ClassLog[9]);
    }

}
