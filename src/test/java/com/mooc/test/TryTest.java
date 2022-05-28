package com.mooc.test;

import com.alibaba.fastjson.JSONObject;

import com.mooc.service.impl.ClassScoreServiceImpl;

import org.junit.Test;


public class TryTest {

    @Test
    public void recommendTest(){
        ClassScoreServiceImpl classScoreService = new ClassScoreServiceImpl();
        classScoreService.updateScoreBySIdAndCId(1,2,10);

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
