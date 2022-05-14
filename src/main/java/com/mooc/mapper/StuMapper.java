package com.mooc.mapper;

import java.util.List;

import com.mooc.pojo.Stu;
import org.apache.ibatis.annotations.Param;

public interface StuMapper {

    /* 数据访问层：只负责sql语句 */

    /* 步骤：
    * 1. 方法完成的功能
    * 2. sql语句
    * 3. 方法是否需要参数
    * 4. 返回值
    * */

    /*
     * 参数处理问题：
     * 1. 单一普通参数：直接用参数占位符 #{}
     * 2. 多参数：使用注解声明参数，一般将参数名称设为对应的属性名称
     * 3. 对象参数：将参数占位符中的参数名称设置为对象中对应的属性名即可
     * 4. 集合参数：传一个HashMap集合，集合中的键为参数占位符的参数名称，值设置为对应的值
     * */


    /**
     * 添加一条新的学生记录
     * @param stu 一个Stu对象
     */
    void addStu(Stu stu);


    /**
     * 根据学生id删除一个学生用户
     * @param id 学生id
     */
    void deleteById(@Param("id") int id);


    /**
     * 查询所有学生用户
      * @return 所有学生用户记录项
     */
    List<Stu> selectAllStu();


    /**
     * 根据用户名查询学生用户
     * @param username
     * @return 查询到的一个学生用户
     */
    Stu selectByUsername(@Param("username") String username);


    /**
     * 根据用户名和密码查询用户
     * @param username 用户名
     * @param password 密码
     * @return 查询到的一条用户记录
     */
    Stu selectByUsernameAndPsw(@Param("username") String username,
                               @Param("password") String password);
//    List<Stu> selectByMap(HashMap hashMap);


    /**
     * 根据id修改密码
     * @param id 学生id
     * @param newPsw 新密码
     */
    void updatePasswordById(@Param("id") int id, @Param("newPsw") String newPsw);


    /**
     * 根据用户名修改密码
     * @param username 用户名
     * @param newPsw 新密码
     */
    void updatePasswordByUsername(@Param("username") String username,
                                   @Param("newPsw") String newPsw);

    /**
     * 动态修改任意字段（可用于学生用户信息修改）
     * @param stu 一个Stud对象
     */
    void updateAnyColumn(Stu stu);


    /**
     * 根据学生id和字段名，修改某一字段的值
     * @param sId
     * @param columnName
     */
    void updateBySIdAndColumnName(@Param("sId") int sId,
                                  @Param("columnName") String columnName,
                                  @Param("columnValue") String columnValue);



}