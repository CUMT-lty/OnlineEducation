package com.mooc.mapper;

import com.mooc.pojo.StuLog;
import org.apache.ibatis.annotations.Param;

public interface StuLogMapper {


    /**
     * 添加一条新纪录
     * @param stuLog 一个StuLog对象
     */
    void addStuLog(StuLog stuLog);


    /**
     * 删除一条学生日志记录（当且仅当学生用户被删除时才执行此操作）
     * @param sId 学生id
     */
    void deleteBySId(@Param("sId") int sId);


    /**
     * 根据学生id查询该学生的日志信息
     * @param sId 学生id
     * @return 查询到的一条日志记录
     */
    StuLog selectBySId(@Param("sId") int sId);


    /**
     * 根据学生id更新学习时间
     * @param sId 学生id
     * @param newStudyTime 新学习时间
     */
    void updateStudyTimeBySId(@Param("sId") int sId,
                              @Param("newStudyTime") int newStudyTime);


    /**
     * 根据学生id，使某一统计字段的值加一
     * @param sId 学生id
     * @param columnName 要更新的字段
     */
    void increaseOneBySIdAndColumnName(@Param("sId") int sId,
                                       @Param("columnName") String columnName);



}
