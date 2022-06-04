package com.mooc.service;

import com.mooc.pojo.Knowledge;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KnowledgeService {

  /**
   * 增加新知识点项
   *
   * @param knowledge
   */
  void addKnowledge(Knowledge knowledge);


  /**
   * 根据知识点id删除知识点
   *
   * @param id 知识点id
   */
  void deleteById(int id);


  /**
   * 根据知识点名称删除知识点
   *
   * @param name 知识点名称
   */
  void deleteByName(String name);


  /**
   * 根据id数组批量删除知识点
   *
   * @param ids id数组
   */
  void deleteRowsByIds(int[] ids);


  /**
   * 根据id获取知识点名称
   *
   * @param id 知识点id
   * @return 知识点名称
   */
  String selectKnameById(int id);

  /**
   * 根据知识点的名称检索知识点id
   *
   * @param kname 知识点名字
   * @return 知识点id
   */
  int selectKIdByKname(@Param("kname") String kname);


  /**
   * 根据知识点名称批量检索知识点记录
   *
   * @param knames 知识点名称数组
   * @return 检索到的所有记录
   */
  Knowledge[] selectKnowledgesByKnames(String[] knames);


  /**
   * 根据知识点名字获取知识点位序
   *
   * @param name 知识点名字
   * @return 位序
   */
  int selectOrderByName(String name);


  /**
   * 根据知识点id获取知识点位序
   *
   * @param id 知识点id
   * @return 位序
   */
  int selectOrderById(int id);


  /**
   * 根据知识点名字更新知识点位序
   *
   * @param name 知识点名字
   */
  void updateOrderByName(String name, int newOrder);


  /**
   * 根据知识点id更新知识点位序
   *
   * @param id 知识点id
   */
  void updateOrderById(int id, int newOrder);

}
