package com.mooc.service;

import com.mooc.pojo.ClassLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassLogService {

    /**
     * 增加一条课程动态信息记录
     * @param classLog 一个ClassLog对象
     */
    void addClassLog(ClassLog classLog);


    /**
     * 删除一条课程动态信息记录（当且仅当课程被删除时才会删除一行记录）
     * @param cId 课程 id
     */
    void deleteByCId(int cId);


    /**
     * 根据课程 id 检索到一条课程动态记录
     * @param cId 课程 id
     * @return 一条课程动态记录ClassLog
     */
    ClassLog selectByCId(int cId);


    /**
     * 根据索引字符串模糊检索课程 (用于课程检索)
     * @param cName 索引字符串 (输入原字符串即可)
     * @return 匹配的课程记录集合
     */
    ClassLog[] selectByNameLike(String cName);


    /**
     * 根据课程的得分排名获取指定数量的记录
     * @param num 需要的记录数
     * @return 获取到的记录集合
     */
    ClassLog[] selectByScoreOrderLimNum(int num);


    /**
     * 根据课程id批量查询class_log记录
     * @param cIds
     * @return
     */
    ClassLog[] selectClassLogByCIds(int[] cIds);


    /**
     * 修改一条课程动态信息记录
     * @param classLog 一个ClassLog对象
     */
    void updateRow(ClassLog classLog);


    /**
     * 根据课程id动态修改某一字段的值为指定值
     * @param cId 课程id
     * @param columnName 要修改的字段名
     * @param columnValue 字段值
     */
    void updateByCIdAndColumn(int cId, String columnName, int columnValue);

    /**
     * 根据课程id将某一字段的值加一
     * @param cId 课程id
     * @param columnName 要修改的字段名称
     */
    void increaseOneByCIdAndColumn(int cId, String columnName);


    /**
     * 重新计算课程平均分（默认增加了一个人）
     * @param oldNum 原有参加评分人数
     * @param oldScore 原平均分
     * @param newScore 新增加的一个分数
     * @return 重新打分后的平均分
     */
    int reAveScore(int oldNum, int oldScore, int newScore);


    /**
     * 根据学生用户id获取个性化的课程推荐
     * @param sId 学生用户ID
     * @return 要推荐的 ClassLog 数组
     */
    ClassLog[] recommendClassByStuId(int sId);


}
