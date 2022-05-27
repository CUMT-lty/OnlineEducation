package com.mooc.mapper;

import java.util.List;

import com.mooc.pojo.Class;
import org.apache.ibatis.annotations.Param;

public interface ClassMapper {


    /**
     * 添加一条新的课程记录
     * @param c 一个Class对象
     */
    void addClass(Class c);


    /**
     * 根据课程 id 删除一条课程记录
     * @param id 课程 id
     */
    void deleteById(@Param("id") int id);


    /**
     * 根据课程名字删除一条课程记录
     * @param name 课程名字
     */
    void deleteByName(@Param("name") String name);


    /**
     * 根据课程id批量删除课程记录
     * @param ids 课程 id 数组
     */
    void deleteClassesByIds(@Param("ids") int[] ids);


    /**
     * 根据课程名字批量删除课程
     * @param names 课程名字数组
     */
    void deleteClassesByNames(String[] names);


    /**
     * 根据课程id检索一条课程记录
     * @param id 课程id
     * @return 检索到的一条课程记录
     */
    Class selectById(@Param("id") int id);


    /**
     * 根据课程名字检索一条课程记录
     * @param name 课程名字
     * @return 检索到的一条课程信息
     */
    Class selectByName(@Param("name") String name);


    /**
     * 查询指定区间内的课程信息 (用于分页查询)
     * @param startIndex 开始索引
     * @param size 需要查询的记录数
     * @return 查询到的课程记录集合
     */
    List<Class> selectClassesByLimit(@Param("startIndex") int startIndex, @Param("size") int size);


    /**
     * 检索属于某一知识点的全部课程
     * @param kId 知识点id
     * @return 检索到的所有课程记录
     */
    List<Class> selectClassesByKId(@Param("kId") int kId);


    /**
     * 根据知识点和课程难度等级检索课程
     * @param kId 知识点id
     * @param level 课程难度等级
     * @return 检索到的所有课程记录
     */
    List<Class> selectByKIdAndLevel(@Param("kId") int kId, @Param("level") int level);


    /**
     * 检索某一课程所属的知识点
     * @param cId 课程id
     * @return 知识点id
     */
    int selectKnowledgeByCId(@Param("cId") int cId);


    /**
     * 批量检索课程所在的知识点
     * @param cIds 课程id数组
     * @return 检索到的知识点id数组
     */
    int[] selectKnowledgesByCIds(int[] cIds);



    /**
     * 通过教师 id 检索该教师上传的所有课程
     * @param tId
     * @return
     */
    List<Class> selectClassesByTId(@Param("tId") int tId);


    /**
     * 根据索引字符串模糊检索课程 (用于课程检索)
     * @param name 索引字符串 (输入原字符串即可)
     * @return 匹配的课程记录集合
     */
    List<Class> selectByNameLike(@Param("name") String name);


    /* 不提供对class表的update操作 */


}