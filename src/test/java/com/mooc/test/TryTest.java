package com.mooc.test;
import com.mooc.pojo.ClassLog;
import com.mooc.service.impl.ClassLogServiceImpl;
import org.junit.Test;

import java.util.Arrays;

public class TryTest {

    @Test
    public void recommendTest(){
        ClassLogServiceImpl classLogService = new ClassLogServiceImpl();
        ClassLog[] classLogs = classLogService.recommendClassByStuId(1);
        System.out.println(Arrays.toString(classLogs));

    }


}
