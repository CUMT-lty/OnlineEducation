package com.mooc.mapper;

import com.mooc.pojo.Teacher;
import org.apache.ibatis.annotations.Param;
import org.neo4j.cypher.internal.frontend.v2_3.ast.functions.Str;

public interface TeacherMapper {

    // 添加新教师管理员用户  （多参数情况要用注解标明参数）

    /**
     * 添加新的教师用户
     * @param name 教师用户名名字（不重名情况下使用教师名字）
     * @param password 密码
     */
    void addNewTeacher(@Param("name")String name, @Param("password")String password);


    /**
     * 通过id删除一条教师记录
     * @param id 教师id
     */
    void deleteById(@Param("id") int id);


    /**
     * 通过教师名字删除一条教师记录
     * @param name 教师名字
     */
    void deleteByName(@Param("name") String name);


    /**
     * 通过用户名和密码查询教师用户
     * @param name 教师用户名
     * @param password 密码
     * @return
     */
    Teacher selectByNameAndPsw(@Param("name")String name,
                               @Param("password")String password);


    /**
     * 更新密码，通过id
     * @param id 教师id
     */
    void updatePasswordById(@Param("id") int id, @Param("newPsw") String newPsw);


    /**
     * 更新密码，通过教师用户名
     * @param name 教师用户名
     */
    void updatePasswordByName(@Param("name") String name, @Param("newPsw") String newPsw);



}
