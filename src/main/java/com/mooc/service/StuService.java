package com.mooc.service;

import com.mooc.pojo.Stu;

import java.util.List;


/**
 * 和 stu 相关的业务逻辑层接口
 */
public interface StuService {

    List<Stu> selectAllUser();

    boolean register(Stu stu);

    Stu login(String username, String password);

    /*
    * 1，添加新的学生用户
    * 2. 删除学生用户
    *
    *
    *
    *
    *
    *
    * */

}
