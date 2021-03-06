package com.mooc.service.impl;

import com.mooc.mapper.StuCognitionMapper;
import com.mooc.pojo.StuCognition;
import com.mooc.service.StuCognitionService;
import com.mooc.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class StuCognitionServiceImpl implements StuCognitionService {

    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public void addStuCognition(StuCognition stuCognition) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StuCognitionMapper mapper = sqlSession.getMapper(StuCognitionMapper.class);
        mapper.addStuCognition(stuCognition);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteBySIdAndKId(int sId, int kId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StuCognitionMapper mapper = sqlSession.getMapper(StuCognitionMapper.class);
        mapper.deleteBySIdAndKId(sId, kId);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteStuCognitionsBySId(int sId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StuCognitionMapper mapper = sqlSession.getMapper(StuCognitionMapper.class);
        mapper.deleteStuCognitionsBySId(sId);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteStuCognitionsByKId(int kId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StuCognitionMapper mapper = sqlSession.getMapper(StuCognitionMapper.class);
        mapper.deleteStuCognitionsByKId(kId);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public StuCognition[] selectStuCognitionsBySId(int sId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StuCognitionMapper mapper = sqlSession.getMapper(StuCognitionMapper.class);
        List<StuCognition> stuCognitions = mapper.selectStuCognitionsBySId(sId);
        sqlSession.close();
        return stuCognitions.toArray(new StuCognition[stuCognitions.size()]);
    }


    @Override
    public StuCognition selectBySIdAndKId(int sId, int kId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StuCognitionMapper mapper = sqlSession.getMapper(StuCognitionMapper.class);
        StuCognition stuCognition = mapper.selectBySIdAndKId(sId, kId);
        sqlSession.close();
        return stuCognition;
    }

    @Override
    public int selectCognitionLevelBySIdAndKId(int sId, int kId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StuCognitionMapper mapper = sqlSession.getMapper(StuCognitionMapper.class);
        int level = mapper.selectCognitionLevelBySIdAndKId(sId, kId);
        sqlSession.close();
        return level;
    }

    @Override
    public void updateBySIdAndKId(int sId, int kId, int newLevel) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StuCognitionMapper mapper = sqlSession.getMapper(StuCognitionMapper.class);
        mapper.updateBySIdAndKId(sId, kId, newLevel);
        sqlSession.commit();
        sqlSession.close();
    }

}
