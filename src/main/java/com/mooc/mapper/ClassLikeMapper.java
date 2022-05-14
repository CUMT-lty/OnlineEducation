package com.mooc.mapper;

import com.mooc.pojo.ClassCollect;
import com.mooc.pojo.ClassLike;
import org.apache.ibatis.annotations.Param;

public interface ClassLikeMapper {


    /**
     * 添加一条新的点赞记录
     * @param classLike 一个ClassCollect对象
     */
    void addClassLike(ClassLike classLike);


    /**
     * 根据课程id和学生id删除一条点赞记录
     * @param cId 课程id
     * @param sId 学生id
     */
    void deleteByCIdAndSId(@Param("cId") int cId, @Param("sId") int sId);


    /**
     * 根据学生id和课程id列表批量删除点赞记录
     * @param sId 学生id
     * @param cIds 课程id列表
     */
    void deleteRowsBySIdAndCIds(@Param("sId") int sId,@Param("cIds") int[] cIds);


    /**
     * 统计某课程被多少人点赞过
     * @param cId 课程id
     * @return 点赞该课程的人数
     */
    int totalRowsByCId(@Param("cId") int cId);


    /**
     * 统计某学生点赞过多少课程
     * @param sId 学生id
     * @return 该学生点赞的课程数
     */
    int totalRowsBySId(@Param("sId") int sId);


    /**
     * 根据课程 id和学生 id检索一条点赞记录
     * @param cId 课程 id
     * @param sId 学生 id
     * @return 检索到的一条点赞记录
     */
    ClassLike selectByCIdAndSId(@Param("cId") int cId, @Param("sId") int sId);




    /* 没有必要的update操作，当学生想取消点赞时，只需删除一条对应的记录 */


}
