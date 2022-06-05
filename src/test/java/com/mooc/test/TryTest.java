package com.mooc.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.mooc.bean.AnsBean;
import com.mooc.mapper.ClassLikeMapper;
import com.mooc.mapper.ClassLogMapper;
import com.mooc.mapper.ClassMapper;
import com.mooc.pojo.*;
import com.mooc.pojo.Class;
import com.mooc.service.impl.*;

import com.mooc.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import javax.xml.transform.sax.SAXResult;
import java.util.List;


public class TryTest {

  @Test
  public void recommendTest() {
    ClassLogServiceImpl classLogService = new ClassLogServiceImpl();
    ClassLog[] classLogs = classLogService.recommendClassByStuId(1);
    for (ClassLog classLog : classLogs) {
      System.out.println(classLog);
    }

  }


  @Test
  public void tryDemo() {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("name", "java语言程序设计");
    jsonObject.put("description", "java入门基础课程");
    jsonObject.put("viewNum", 365);
    jsonObject.put("score", 7);
    jsonObject.put("like", true);
    jsonObject.put("collect", true);
    String s = jsonObject.toJSONString();
    System.out.println(s);
  }

  @Test
  public void classMapperTest() {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
    SqlSession sqlSession = sqlSessionFactory.openSession();

    ClassMapper mapper = sqlSession.getMapper(ClassMapper.class);
    Class aClass = mapper.selectByName("Python入门到实战，3节课化身为Python大神");
//    Class aClass = mapper.selectById(35);
    System.out.println(aClass);
  }

  @Test
  public void classLogMapperTest(){
    String cName = "java语言程序设计";
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
    SqlSession sqlSession = sqlSessionFactory.openSession();
    ClassLogMapper mapper = sqlSession.getMapper(ClassLogMapper.class);
    ClassLog classLog = mapper.selectByCId(35);
    System.out.println(classLog);
  }

  @Test
  public void addClassLog(){
    ClassServiceImpl classService = new ClassServiceImpl();
    ClassLogServiceImpl classLogService = new ClassLogServiceImpl();

    for (int i = 198; i<= 231; i++) {
      Class aClass = classService.selectById(i);
      ClassLog classLog = new ClassLog();
      classLog.setcId(i);
      classLog.setcName(aClass.getName());
      classLogService.addClassLog(classLog);
    }
  }


  @Test
  public void StuServiceImplTest(){

    Stu stu = new Stu();
    stu.setUsername("333");
    stu.setPassword("333");
    stu.setName("333");
    stu.setSchool("333");
    stu.setProfession("333");

    StuServiceImpl stuService = new StuServiceImpl();
    int i = stuService.addStu(stu);

    System.out.println(i);


  }


}