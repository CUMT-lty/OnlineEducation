package com.mooc.mapper;

import com.mooc.pojo.Knowledge;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KnowledgeMapper {


    /**
     * 增加新知识点项
     * @param knowledge
     */
    void addKnowledge(Knowledge knowledge);


    /**
     * 根据知识点id删除知识点
     * @param id 知识点id
     */
    void deleteById(@Param("id") int id);


    /**
     * 根据知识点名称删除知识点
     * @param name 知识点名称
     */
    void deleteByName(@Param("name") String name);


    /**
     * 根据id数组批量删除知识点
     * @param ids id数组
     */
    void deleteRowsByIds(@Param("ids") int[] ids);


    /**
     * 根据id获取知识点名称
     * @param id 知识点id
     * @return 知识点名称
     */
    String selectKnameById(@Param("id") int id);


  /**
   * 根据知识点的名称检索知识点id
   * @param kname 知识点名字
   * @return 知识点id
   */
  int selectKIdByKname(@Param("kname") String kname);


    /**
     * 根据知识点名称批量检索知识点记录
     * @param knames 知识点名称数组
     * @return 检索到的所有记录
     */
    List<Knowledge> selectKnowledgesByKnames(@Param("knames") String[] knames);


    /**
     * 根据知识点名字获取知识点位序
     * @param name 知识点名字
     * @return 位序
     */
    int selectOrderByName(@Param("name") String name);


    /**
     * 根据知识点id获取知识点位序
     * @param id 知识点id
     * @return 位序
     */
    int selectOrderById(@Param("id") int id);


    /**
     * 根据知识点名字更新知识点位序
     * @param name 知识点名字
     */
    void updateOrderByName(@Param("name") String name, @Param("newOrder") int newOrder);


    /**
     * 根据知识点id更新知识点位序
     * @param id 知识点id
     */
    void updateOrderById(@Param("id") int id, @Param("newOrder") int newOrder);

}
