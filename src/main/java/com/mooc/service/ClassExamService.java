package com.mooc.service;

import com.mooc.pojo.ClassExam;
import java.util.List;

public interface ClassExamService {

    /**
     * 增加一条新的课程测试记录
     * @param classExam 一个ClassExam对象
     */
    void addClassExam(ClassExam classExam);


    /**
     * 删除某学生的所有测试记录（当学生用户注销时）
     * @param sId 学生id
     */
    void deleteBySId(int sId);


    /**
     * 删除某课程的所有测试记录
     * @param cId 课程id
     */
    void deleteByCId(int cId);


    /**
     * 查询一个学生用户参加过的所有课程测试项
     * @param sId 学生id
     * @return 获取到的所有课程测试项
     */
    List<ClassExam> selectBySId(int sId);


    /**
     * 根据学生id和课程id查询学生参加某门课程的成绩
     * @param sId 学生id
     * @param cId 课程id
     * @return 如果是正数，表示该学生的成绩，否则表示没有这条记录
     */
    int selectBySIdAndCId(int sId, int cId);


    /**
     * 根据学生id和课程id修改该学生某门课程的成绩
     * @param sId 学生id
     * @param cId 课程id
     * @param newScore 新成绩
     */
    void updateScoreBySIdAndCId(int sId, int cId, int newScore);

}
