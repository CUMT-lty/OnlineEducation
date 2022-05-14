package com.mooc.service;

import com.mooc.mapper.TeacherMapper;
import com.mooc.pojo.Teacher;

public interface TeacherService {

    /**
     * 添加新的教师用户
     * @param teacher 一个Teacher对象
     */
    void addNewTeacher(Teacher teacher);


    /**
     * 通过id删除一条教师记录
     * @param id 教师id
     */
    void deleteById(int id);


    /**
     * 通过教师名字删除一条教师记录
     * @param name 教师名字
     */
    void deleteByName(String name);


    /**
     * 通过用户名和密码查询教师用户
     * @param name 教师用户名
     * @param password 密码
     * @return
     */
    Teacher selectByNameAndPsw(String name, String password);


    /**
     * 更新密码，通过id
     * @param id 教师id
     */
    void updatePasswordById(int id, String newPsw);


    /**
     * 更新密码，通过教师用户名
     * @param name 教师用户名
     */
    void updatePasswordByName(String name, String newPsw);

}
