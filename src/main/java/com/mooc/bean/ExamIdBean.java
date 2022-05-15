package com.mooc.bean;

import java.util.Arrays;

public class ExamIdBean {

    private int[] eIds;  // 一个试题id列表

    public int[] geteIds() {
        return eIds;
    }

    public void seteIds(int[] eIds) {
        this.eIds = eIds;
    }

    @Override
    public String toString() {
        return "ExamIdBean{" +
                "eIds=" + Arrays.toString(eIds) +
                '}';
    }
}
