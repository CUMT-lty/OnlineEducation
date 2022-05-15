package com.mooc.util;

public class ExamUtils {

    /**
     * 对试卷判分
     * @param sAnswers 提交的答案列表
     * @param rAnswers 正确答案列表
     * @return 最终得分（满分100，最后得分向上取整）
     */
    public static int examScoreResult(int[] sAnswers, int[] rAnswers){
        if (sAnswers.length != rAnswers.length ) return -1;  // 如果提交答案数和正确答案数不照，就返回-1
        int num = sAnswers.length;        // 题目数量
        double total = 0;            // 9道题中答对的题目数
        for (int i = 0; i<num; i++){
            if (sAnswers[i] == rAnswers[i]) total = total + 1;
        }
        int resScore = (int) Math.ceil((total/9)*100);
        return resScore;
    }

}
