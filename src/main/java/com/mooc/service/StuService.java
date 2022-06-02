package com.mooc.service;

import com.mooc.pojo.Stu;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 和 stu 相关的业务逻辑层接口
 */
public interface StuService {

    /**
     * 添加一条新的学生记录
     * @param stu 一个Stu对象
     * @return 返回该条记录的学生id
     */
    int addStu(Stu stu);


    /**
     * 根据学生id删除一个学生用户
     * @param id 学生id
     */
    void deleteById(int id);


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
    Stu selectByUsername(String username);


    /**
     * 根据用户名和密码查询用户
     * @param username 用户名
     * @param password 密码
     * @return 查询到的一条用户记录
     */
    Stu selectByUsernameAndPsw(String username, String password);
//    List<Stu> selectByMap(HashMap hashMap);


    /**
     * 根据id修改密码
     * @param id 学生id
     * @param newPsw 新密码
     */
    void updatePasswordById(int id, String newPsw);


    /**
     * 根据用户名修改密码
     * @param username 用户名
     * @param newPsw 新密码
     */
    void updatePasswordByUsername(String username, String newPsw);


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
    void updateBySIdAndColumnName(int sId, String columnName, String columnValue);


}
