package com.mooc.service;

import com.mooc.pojo.ClassLike;

public interface ClassLikeService {

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
    void deleteByCIdAndSId(int cId, int sId);


    /**
     * 根据学生id和课程id列表批量删除点赞记录
     * @param sId 学生id
     * @param cIds 课程id列表
     */
    void deleteRowsBySIdAndCIds(int sId, int[] cIds);


    /**
     * 统计某课程被多少人点赞过
     * @param cId 课程id
     * @return 点赞该课程的人数
     */
    int totalRowsByCId(int cId);


    /**
     * 统计某学生点赞过多少课程
     * @param sId 学生id
     * @return 该学生点赞的课程数
     */
    int totalRowsBySId(int sId);


    /**
     * 根据课程 id和学生 id检索一条点赞记录
     * @param cId 课程 id
     * @param sId 学生 id
     * @return 检索到的一条点赞记录
     */
    ClassLike selectByCIdAndSId(int cId, int sId);

}
