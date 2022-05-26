package com.mooc.service.impl;

import com.mooc.mapper.ExamMapper;
import com.mooc.pojo.Exam;
import com.mooc.service.ExamService;
import com.mooc.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class ExamServiceImpl implements ExamService {

    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public void addExam(Exam exam) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ExamMapper mapper = sqlSession.getMapper(ExamMapper.class);
        mapper.addExam(exam);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ExamMapper mapper = sqlSession.getMapper(ExamMapper.class);
        mapper.deleteById(id);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteExamsByIds(int[] ids) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ExamMapper mapper = sqlSession.getMapper(ExamMapper.class);
        mapper.deleteExamsByIds(ids);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public Exam[] selectByCId(int cId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ExamMapper mapper = sqlSession.getMapper(ExamMapper.class);
        List<Exam> exams = mapper.selectByCId(cId);
        sqlSession.close();
        return exams.toArray(new Exam[exams.size()]);
    }

    @Override
    public List<Exam> selectByTId(int tId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ExamMapper mapper = sqlSession.getMapper(ExamMapper.class);
        List<Exam> exams = mapper.selectByTId(tId);
        sqlSession.close();
        return exams;
    }

    @Override
    public int selectAnswerById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ExamMapper mapper = sqlSession.getMapper(ExamMapper.class);
        int ans = mapper.selectAnswerById(id);
        sqlSession.close();
        return ans;
    }

    @Override
    public int[] selectAnswersByIds(int[] ids) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ExamMapper mapper = sqlSession.getMapper(ExamMapper.class);
        List<Integer> answers = mapper.selectAnswersByIds(ids);
        sqlSession.close();
        return answers.stream().mapToInt(Integer::valueOf).toArray();
    }

    @Override
    public Exam[] randomSelectByNum(int num) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ExamMapper mapper = sqlSession.getMapper(ExamMapper.class);
        List<Exam> exams = mapper.randomSelectByNum(num);
        sqlSession.close();
        return exams.toArray(new Exam[num]);
    }

    @Override
    public Exam[] randomSelectByCIdAndNum(int cId, int num) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ExamMapper mapper = sqlSession.getMapper(ExamMapper.class);
        List<Exam> exams = mapper.randomSelectByCIdAndNum(cId, num);
        sqlSession.close();
        return exams.toArray(new Exam[num]);
    }

    @Override
    public void updateAnswerById(int id, int newAnswer) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ExamMapper mapper = sqlSession.getMapper(ExamMapper.class);
        mapper.updateAnswerById(id, newAnswer);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public int[] getEIdsByExams(List<Exam> exams) {
        Exam[] es = exams.toArray(new Exam[exams.size()]);
        int num = es.length;
        int[] eIds = new int[num];
        for (int i = 0; i<num; i++) {
            eIds[i] = es[i].getId();
        }
        return eIds;
    }
}
