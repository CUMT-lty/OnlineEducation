package com.mooc.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.mooc.bean.AnsBean;
import com.mooc.mapper.ClassLikeMapper;
import com.mooc.mapper.ClassLogMapper;
import com.mooc.mapper.ClassMapper;
import com.mooc.mapper.StuMapper;
import com.mooc.pojo.*;
import com.mooc.pojo.Class;
import com.mooc.service.impl.*;

import com.mooc.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import javax.xml.transform.sax.SAXResult;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;


public class TryTest {

  @Test
  public void recommendTest() {

    StuCognitionServiceImpl stuCognitionService = new StuCognitionServiceImpl();
    StuCognition[] stuCognitions = stuCognitionService.selectStuCognitionsBySId(11);
    System.out.println(stuCognitions.length);

  }


  @Test
  public void tryDemo() {

    ExamServiceImpl examService = new ExamServiceImpl();
    Exam exam = new Exam();
    exam.setcId(147);
    exam.settId(1);
    exam.setA("a选项");
    exam.setB("b选项");
    exam.setC("c选项");
    exam.setD("d选项");
    exam.setAnswer(1);
    for (int i = 4; i <= 12; i++){
      exam.setDescription(i + "python爬虫高级开发工程师课程");
      examService.addExam(exam);
    }


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


  @Test
  public void encodeTest() throws NoSuchAlgorithmException {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
    SqlSession sqlSession = sqlSessionFactory.openSession();
    StuMapper mapper = sqlSession.getMapper(StuMapper.class);

    Stu stu = new Stu();
    stu.setUsername("111");
    String password = "111";

    MessageDigest md5 = MessageDigest.getInstance("MD5");
    md5.update(password.getBytes(StandardCharsets.UTF_8));
    String s = new BigInteger(1, md5.digest()).toString(16);
    System.out.println(s);

    stu.setPassword(s);
    stu.setName("111");
    stu.setSchool("111");
    stu.setProfession("111");

    mapper.addStu(stu);
  }


}