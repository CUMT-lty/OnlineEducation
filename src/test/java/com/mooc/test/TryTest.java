package com.mooc.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.mooc.pojo.StuLog;
import com.mooc.service.impl.ClassScoreServiceImpl;

import com.mooc.service.impl.StuLogServiceImpl;
import org.junit.Test;


public class TryTest {

    @Test
    public void recommendTest(){
        StuLogServiceImpl stuLogService = new StuLogServiceImpl();
        StuLog stuLog = stuLogService.selectBySId(1);
        String s = JSON.toJSONString(stuLog);
        System.out.println(s);

    }


    @Test
    public void tryDemo(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "java语言程序设计");
        jsonObject.put("description", "java入门基础课程");
        jsonObject.put("viewNum", 365);
        jsonObject.put("score", 7);
        jsonObject.put("like", true);
        jsonObject.put("collect", true);
        String s = jsonObject.toJSONString();
        System.out.println(s);
    }




}
