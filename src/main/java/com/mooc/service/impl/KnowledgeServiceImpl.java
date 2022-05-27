package com.mooc.service.impl;

import com.mooc.mapper.KnowledgeMapper;
import com.mooc.pojo.Knowledge;
import com.mooc.service.KnowledgeService;
import com.mooc.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class KnowledgeServiceImpl implements KnowledgeService {

    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public void addKnowledge(Knowledge knowledge) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        KnowledgeMapper mapper = sqlSession.getMapper(KnowledgeMapper.class);
        mapper.addKnowledge(knowledge);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        KnowledgeMapper mapper = sqlSession.getMapper(KnowledgeMapper.class);
        mapper.deleteById(id);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteByName(String name) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        KnowledgeMapper mapper = sqlSession.getMapper(KnowledgeMapper.class);
        mapper.deleteByName(name);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteRowsByIds(int[] ids) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        KnowledgeMapper mapper = sqlSession.getMapper(KnowledgeMapper.class);
        mapper.deleteRowsByIds(ids);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public String selectKnameById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        KnowledgeMapper mapper = sqlSession.getMapper(KnowledgeMapper.class);
        String kname = mapper.selectKnameById(id);
        sqlSession.close();
        return kname;
    }

    @Override
    public Knowledge[] selectKnowledgesByKnames(String[] knames) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        KnowledgeMapper mapper = sqlSession.getMapper(KnowledgeMapper.class);
        List<Knowledge> knowledges = mapper.selectKnowledgesByKnames(knames);
        sqlSession.close();
        return knowledges.toArray(new Knowledge[knowledges.size()]);
    }

    @Override
    public int selectOrderByName(String name) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        KnowledgeMapper mapper = sqlSession.getMapper(KnowledgeMapper.class);
        int order = mapper.selectOrderByName(name);
        sqlSession.close();
        return order;
    }

    @Override
    public int selectOrderById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        KnowledgeMapper mapper = sqlSession.getMapper(KnowledgeMapper.class);
        int order = mapper.selectOrderById(id);
        sqlSession.close();
        return order;
    }

    @Override
    public void updateOrderByName(String name, int newOrder) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        KnowledgeMapper mapper = sqlSession.getMapper(KnowledgeMapper.class);
        mapper.updateOrderByName(name, newOrder);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void updateOrderById(int id, int newOrder) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        KnowledgeMapper mapper = sqlSession.getMapper(KnowledgeMapper.class);
        mapper.updateOrderById(id, newOrder);
        sqlSession.commit();
        sqlSession.close();
    }
}
