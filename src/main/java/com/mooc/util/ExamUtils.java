package com.mooc.util;

public class ExamUtils {

    /**
     * 对试卷判分
     * @param sAnswers 提交的答案列表
     * @param levels 试题难度列表
     * @param rAnswers 正确答案列表
     * @return 最终得分（满分100，最后得分向上取整）
     */
    public static int examinationScore(int[] sAnswers, int[] levels, int[] rAnswers){
        if (sAnswers.length != rAnswers.length ) return -1;  // 如果提交答案数和正确答案数不照，就返回-1
        int num = sAnswers.length;        // 题目数量
        int sumLevel = 0;                 // level总值，计算每题权重时要用
        double totalScore = 0;            // 学生本次考试总分
        for (int level : levels) {
            sumLevel = sumLevel + level;
        }
        for (int i = 0; i<num; i++){
            if (sAnswers[i] == rAnswers[i]){  // 如果本题作答正确，则按题目权重加分
                totalScore = totalScore + (10 * (levels[i] / sumLevel));
            }
        }
        int resScore = (int) Math.ceil(totalScore);
        return resScore;
    }

}
