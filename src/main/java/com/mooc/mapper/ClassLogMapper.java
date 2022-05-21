package com.mooc.mapper;

import java.util.List;

import com.mooc.pojo.Class;
import com.mooc.pojo.ClassLog;
import org.apache.ibatis.annotations.Param;

public interface ClassLogMapper {


    /**
     * 增加一条课程动态信息记录
     * @param classLog 一个ClassLog对象
     */
    void addClassLog(ClassLog classLog);


    /**
     * 删除一条课程动态信息记录（当且仅当课程被删除时才会删除一行记录）
     * @param cId 课程 id
     */
    void deleteByCId(@Param("cId") int cId);


    /**
     * 根据课程 id 检索到一条课程动态记录
     * @param cId 课程 id
     * @return 一条课程动态记录ClassLog
     */
    ClassLog selectByCId(@Param("cId") int cId);


    /**
     * 根据索引字符串模糊检索课程 (用于课程检索)
     * @param cName 索引字符串 (输入原字符串即可)
     * @return 匹配的课程记录集合
     */
    List<ClassLog> selectByNameLike(@Param("cName") String cName);


    /**
     * 根据课程的得分排名获取指定数量的记录
     * @param num 需要的记录数
     * @return 获取到的记录集合
     */
    List<ClassLog> selectByScoreOrderLimNum(@Param("num") int num);


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
    void updateByCIdAndColumn(@Param("cId") int cId,
                              @Param("columnName") String columnName,
                              @Param("columnValue") int columnValue);

    /**
     * 根据课程id将某一字段的值加一
     * @param cId 课程id
     * @param columnName 要修改的字段名称
     */
    void increaseOneByCIdAndColumn(@Param("cId") int cId,
                                   @Param("columnName") String columnName);



}
