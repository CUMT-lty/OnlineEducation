package com.mooc.mapper;

import com.mooc.pojo.ClassCollect;
import org.apache.ibatis.annotations.Param;

public interface ClassCollectMapper {

    /**
     * 添加一条新的收藏记录
     * @param classCollect 一个ClassCollect对象
     */
    void addClassCollect(ClassCollect classCollect);


    /**
     * 根据课程id和学生id删除一条收藏记录
     * @param cId 课程id
     * @param sId 学生id
     */
    void deleteByCIdAndSId(@Param("cId") int cId, @Param("sId") int sId);


    /**
     * 根据学生id和id列表批量删除收藏记录项
     * @param sId 学生id
     * @param cIds 课程id列表
     */
    void deleteRowsBySIdAndCIds(@Param("sId") int sId,@Param("cIds") int[] cIds);


    /**
     * 统计某课程被多少人收藏过
     * @param cId 课程id
     * @return 收藏该课程的人数
     */
    int totalRowsByCId(@Param("cId") int cId);


    /**
     * 统计某学生收藏过多少课程
     * @param sId 学生id
     * @return 该学生收藏的课程数
     */
    int totalRowsBySId(@Param("sId") int sId);


    /**
     * 根据课程 id和学生 id检索一条收藏记录
     * @param cId 课程 id
     * @param sId 学生 id
     * @return 检索到的一条记录
     */
    ClassCollect selectByCIdAndSId(@Param("cId") int cId, @Param("sId") int sId);



    /* 没有必要的update操作，当学生想取消收藏时，只需删除一条对应的记录 */



}
