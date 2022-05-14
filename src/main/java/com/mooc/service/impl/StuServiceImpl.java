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


    @Override
    public boolean addStu(Stu stu) {
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

    @Override
    public void deleteById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StuMapper mapper = sqlSession.getMapper(StuMapper.class);
        mapper.deleteById(id);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public List<Stu> selectAllStu() {

        // 2. 获取sqlSession对象（SqlSession不需要单独抽出来，因为每次访问使用的是不同的session）
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 3. 获取UserMapper对象
        StuMapper mapper = sqlSession.getMapper(StuMapper.class);
        // 4. 调用 StuMapper 中的 selectAllStu() 方法
        List<Stu> stus = mapper.selectAllStu();
        // 5. 关闭sqlSession，释放资源
        sqlSession.close();
        // 6. 返回 List<Stu> 集合
        return stus;

    }

    @Override
    public Stu selectByUsername(String username) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StuMapper mapper = sqlSession.getMapper(StuMapper.class);
        Stu stu = mapper.selectByUsername(username);
        sqlSession.close();
        return stu;
    }

    @Override
    public Stu selectByUsernameAndPsw(String username, String password) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StuMapper mapper = sqlSession.getMapper(StuMapper.class);
        Stu stu = mapper.selectByUsernameAndPsw(username, password);
        sqlSession.close();
        return stu;
    }

    @Override
    public void updatePasswordById(int id, String newPsw) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StuMapper mapper = sqlSession.getMapper(StuMapper.class);
        mapper.updatePasswordById(id, newPsw);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void updatePasswordByUsername(String username, String newPsw) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StuMapper mapper = sqlSession.getMapper(StuMapper.class);
        mapper.updatePasswordByUsername(username, newPsw);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void updateAnyColumn(Stu stu) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StuMapper mapper = sqlSession.getMapper(StuMapper.class);
        mapper.updateAnyColumn(stu);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void updateBySIdAndColumnName(int sId, String columnName, String columnValue) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StuMapper mapper = sqlSession.getMapper(StuMapper.class);
        mapper.updateBySIdAndColumnName(sId, columnName, columnValue);
        sqlSession.commit();
        sqlSession.close();
    }

}