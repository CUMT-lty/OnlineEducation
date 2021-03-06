package com.mooc.service.impl;

import com.mooc.mapper.ClassLogMapper;
import com.mooc.mapper.StuCognitionMapper;
import com.mooc.pojo.Class;
import com.mooc.pojo.ClassLog;
import com.mooc.pojo.Knowledge;
import com.mooc.pojo.StuCognition;
import com.mooc.service.ClassLogService;
import com.mooc.util.SqlSessionFactoryUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.*;

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
    public ClassLog[] selectClassLogByCIds(int[] cIds) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassLogMapper mapper = sqlSession.getMapper(ClassLogMapper.class);
        List<ClassLog> classLogs = mapper.selectClassLogByCIds(cIds);
        sqlSession.close();
        return classLogs.toArray(new ClassLog[classLogs.size()]);
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
        // newScore??????????????????oldScore???????????????
        return (int) Math.ceil(((oldNum * oldScore) + newScore*2 ) / (oldNum+1));
    }



    @Override
    public ClassLog[] recommendClassByStuId(int sId) {
        ClassLogServiceImpl classLogService = new ClassLogServiceImpl();
        KnowledgeServiceImpl knowledgeService = new KnowledgeServiceImpl();
        StuCognitionServiceImpl stuCognitionService = new StuCognitionServiceImpl();
        ClassServiceImpl classService = new ClassServiceImpl();
        Neo4jServiceImpl neo4jService = new Neo4jServiceImpl();
        // ??????????????????????????????????????????????????????
        StuCognition[] stuCognitions = stuCognitionService.selectStuCognitionsBySId(sId);
        // ??????????????????????????????
        List<Class[]> recommends = new ArrayList<>();
        // ????????????????????????????????????
        for (StuCognition stuCognition: stuCognitions) {
            // ?????????????????????
            int kId = stuCognition.getkId();
            String kname = knowledgeService.selectKnameById(kId);
            // ??????????????????
            int level = stuCognition.getCongitionLevel();

            // ??????????????????????????????
            Class[] localKlevel1 = classService.selectByKIdAndLevel(kId, 1);
            Class[] localKlevel2 = classService.selectByKIdAndLevel(kId, 2);
            Class[] localKlevel3 = classService.selectByKIdAndLevel(kId, 3);

            // ?????????????????????
            String[] lastKnames = neo4jService.getLastByKname(kname);
            Knowledge[] lastKnowledges = knowledgeService.selectKnowledgesByKnames(lastKnames);
            // ?????????????????????????????????????????????2??????????????????????????????
            Arrays.sort(lastKnowledges, new Comparator<Knowledge>(){
                @Override
                public int compare(Knowledge k1, Knowledge k2){
                    return k2.getOrder() - k2.getOrder();  // ?????????????????????
                }
            });
            // ?????????????????????????????????3?????????
            Class[] lastLevel3_1 = null;
            Class[] lastLevel3_2 = null;
            int lastKNum = lastKnowledges.length;
            if( lastKNum > 1 || lastKNum == 1 ){
                lastLevel3_1 = classService.selectByKIdAndLevel(lastKnowledges[0].getId(), 3);
            }
            if( lastKNum > 2 || lastKNum == 2 ) {
                lastLevel3_2 = classService.selectByKIdAndLevel(lastKnowledges[1].getId(), 3);
            }

            // ???????????????????????????
            String[] nextKnames = neo4jService.getNextByKname(kname);
            Knowledge[] nextKnowledges = knowledgeService.selectKnowledgesByKnames(nextKnames);
            // ?????????????????????????????????????????????????????????2????????????
            Arrays.sort(nextKnowledges, new Comparator<Knowledge>() {
                @Override
                public int compare(Knowledge k1, Knowledge k2) {
                    return k1.getOrder() - k2.getOrder();   // ?????????????????????
                }
            });
            // ?????????????????????????????????1?????????
            Class[] nextLevel1_1 = null;
            Class[] nextLevel1_2 = null;
            int nextKNum = nextKnowledges.length;
            if (nextKNum > 1 || nextKNum == 1){
                nextLevel1_1 = classService.selectByKIdAndLevel(nextKnowledges[0].getId(), 1);
            }
            if (nextKNum > 2 || nextKNum == 2) {
                nextLevel1_2 = classService.selectByKIdAndLevel(nextKnowledges[1].getId(), 1);
            }

            // ??????????????????????????????????????????
            if (level == 1){
                // ???????????????1???????????????????????????1???2????????? ??? ??????????????????3?????????
                if (localKlevel1 != null) recommends.add(localKlevel1);
                if (localKlevel2 != null) recommends.add(localKlevel2);
                if (lastLevel3_1 != null) recommends.add(lastLevel3_1);
                if (lastLevel3_2 != null) recommends.add(lastLevel3_2);
            } else if (level == 2) {
                // ???????????????2???????????????????????????3????????? ??? ???????????????1?????????
                if (localKlevel3 != null) recommends.add(localKlevel3);
                if (nextLevel1_1 != null) recommends.add(nextLevel1_1);
                if (nextLevel1_2 != null) recommends.add(nextLevel1_2);
            } else {
                // ???????????????3????????????????????????1?????????
                if (nextLevel1_1 != null) recommends.add(nextLevel1_1);
                if (nextLevel1_2 != null) recommends.add(nextLevel1_2);
            }
        }

        // ??????recommends????????????List<Class[]>???????????????Class[]?????????
        List<Integer> resCIdList = new ArrayList<>();
        int size = 0;
        for (Class[] classes: recommends){
            size = size + classes.length;
            for (Class c: classes){
                resCIdList.add(c.getId());
            }
        }
        int[] resCIds = resCIdList.stream().mapToInt(Integer::valueOf).toArray();

        // ?????????????????????ClassLog
        ClassLog[] recClassLogs = classLogService.selectClassLogByCIds(resCIds);
        return recClassLogs;
    }

}
