package com.mooc.mapper;

import com.mooc.pojo.Exam;
import org.apache.ibatis.annotations.Param;
import org.neo4j.cypher.internal.frontend.v2_3.ast.functions.Str;

import java.util.List;

public interface ExamMapper {


    /**
     * 增加一条试题记录
     * @param exam 一个Exam对象
     */
    void addExam(Exam exam);


    /**
     * 根据 试题id 删除一条试题记录
     * @param id 试题id
     */
    void deleteById(@Param("id") int id);


    /**
     * 根据 id 数组批量删除试题记录
     * @param ids 试题id数组
     */
    void deleteExamsByIds(@Param("ids") int[] ids);


    /**
     * 检索某一课程的所有试题
     * @param cId 课程的id
     * @return 检索到的所有试题记录
     */
    List<Exam> selectByCId(@Param("cId") int cId);


    /**
     * 检索某教师上传的所有试题
     * @param tId 教师的id
     * @return 检索到的所有试题记录
     */
    List<Exam> selectByTId(@Param("tId") int tId);


    /**
     * 根据试题id检索该试题的答案序号
     * @param id 试题id
     * @return 试题答案序号，一个1-4的整数 (1234分别对应ABCD)
     */
    int selectAnswerById(@Param("id") int id);


    /**
     * 批量检索试题答案序号
     * @param ids 试题id数组
     * @return 对应的试题答案序号数组
     */
    List<Integer> selectAnswersByIds(@Param("ids") int[] ids);


    /**
     * 随机抽取指定数目的不重复试题记录
     * @param num 需要抽取的试题数目
     * @return 抽取到的试题记录
     */
    List<Exam> randomSelectByNum(@Param("num") int num);


    /**
     * 根据试题id修改试题的答案
     * @param id 试题id
     * @param newAnswer 新答案
     */
    void updateAnswerById(@Param("id") int id,@Param("newAnswer") int newAnswer);

}
