package com.mooc.service.impl;

import com.mooc.mapper.StuMapper;
import com.mooc.pojo.Stu;
import com.mooc.service.StuService;
import com.mooc.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;


/**
 * StuService接口的实现类
 */
public class StuServiceImpl implements StuService {

    /* 位于业务逻辑层，只处理业务逻辑 */
    /* 在控制器层和数据访问层之间 */

    // 1. 获取SqlSessionFactory工厂对象
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
    /* 将这句话提到成员位置的原因是，只希望这个初始化语句执行一次 */

    /**
     * 查询所有学生用户
     * @return
     */
    @Override
    public List<Stu> selectAllUser(){
        // 2. 获取sqlSession对象（SqlSession不需要单独抽出来，因为每次访问使用的是不同的session）
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 3. 获取UserMapper对象
        StuMapper mapper = sqlSession.getMapper(StuMapper.class);
        // 4. 调用 StuMapper 中的 selectAllStu() 方法
        List<Stu> users = mapper.selectAllStu();
        // 5. 关闭sqlSession，释放资源
        sqlSession.close();
        // 6. 返回 List<Stu> 集合
        return users;
    }


    /**
     * 注册，添加新的学生用户
     * @param stu
     */
    @Override
    public boolean register(Stu stu){    // 接收一个User类型的对象参数
        // 2. 获取sqlSession对象（SqlSession不需要单独抽出来，因为每次访问使用的是不同的session）
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 3. 获取UserMapper对象
        StuMapper mapper = sqlSession.getMapper(StuMapper.class);

        // 不允许用户名重复，所以要判断该用户名是否已经存在
        Stu s = mapper.selectByUsername(stu.getUsername());
        if (s == null) {  // 如果查不到这个用户名，说明该用户不存在，可以注册
            // 4. 调用 StuMapper 中的 addStu() 方法
            mapper.addStu(stu);
            // 5. 增删改操作需要提交事务
            sqlSession.commit();
        }
        // 6. 关闭sqlSession，释放资源
        sqlSession.close();

        // 如果返回true说明正常注册，返回false说明用户名已存在，需要修改用户名
        return s==null;
    }

    /**
     * 根据用户名和密码登录
     * @param username
     * @param password
     * @return  返回查询到的用户
     */
    @Override
    public Stu login(String username, String password){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StuMapper mapper = sqlSession.getMapper(StuMapper.class);
        Stu stu = mapper.selectByUsernameAndPsw(username, password);
        sqlSession.close();
        return stu;
    }

}