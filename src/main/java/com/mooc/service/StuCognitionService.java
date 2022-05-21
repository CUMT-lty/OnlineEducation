package com.mooc.service;

import com.mooc.pojo.StuCognition;


public interface StuCognitionService {

    /**
     * 增加一条学生掌握知识点记录
     * @param stuCognition 一个StuCognition对象
     */
    void addStuCognition(StuCognition stuCognition);


    /**
     * 根据学生id删除一条认知记录
     * @param sId 学生id
     * @param kId 知识点id
     */
    void deleteBySIdAndKId(int sId, int kId);


    /**
     * 批量删除一个学生的所有认知信息 （当且仅当该学生账号注销）
     * @param sId 学生id
     */
    void deleteStuCognitionsBySId(int sId);


    /**
     * 批量删除与一个知识点有关的所有认知信息（当且仅当一个知识点被删除）
     * @param kId
     */
    void deleteStuCognitionsByKId(int kId);


    /**
     * 根据学生id查找一个学生的所有认知信息
     * @param sId
     * @return 查找到的所有认知信息
     */
    int[] selectStuCognitionsBySId(int sId);

}
