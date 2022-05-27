package com.mooc.mapper;

import com.mooc.pojo.ClassExam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassExamMapper {


    /**
     * 增加一条新的课程测试记录
     * @param classExam 一个ClassExam对象
     */
    void addClassExam(ClassExam classExam);


    /**
     * 删除某学生的所有测试记录（当学生用户注销时）
     * @param sId 学生id
     */
    void deleteBySId(@Param("sId") int sId);


    /**
     * 删除某课程的所有测试记录
     * @param cId 课程id
     */
    void deleteByCId(@Param("cId") int cId);


    /**
     * 查询一个学生用户参加过的所有课程测试项
     * @param sId 学生id
     * @return 获取到的所有课程测试项
     */
    List<ClassExam> selectBySId(@Param("sId") int sId);


    /**
     * 根据学生id和课程id查询学生参加某门课程的成绩
     * @param sId 学生id
     * @param cId 课程id
     * @return 该学生的该课程成绩
     */
    ClassExam selectBySIdAndCId(@Param("sId") int sId, @Param("cId") int cId);


    /**
     * 根据学生id和课程id修改该学生某门课程的成绩
     * @param sId 学生id
     * @param cId 课程id
     * @param newScore 新成绩
     */
    void updateScoreBySIdAndCId(@Param("sId") int sId, @Param("cId") int cId, @Param("newScore") int newScore);

}
