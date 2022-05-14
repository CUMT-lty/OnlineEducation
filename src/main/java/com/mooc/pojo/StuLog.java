package com.mooc.pojo;

public class StuLog {

    private int id;

    private int stuId;

    private int studyNum;

    private int classNum;

    private int studyTime;

    private int likeNum;

    private int collectNum;

    private int scoreNum;

    private int examNum;

    // set和get方法


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public int getStudyNum() {
        return studyNum;
    }

    public void setStudyNum(int studyNum) {
        this.studyNum = studyNum;
    }

    public int getClassNum() {
        return classNum;
    }

    public void setClassNum(int classNum) {
        this.classNum = classNum;
    }

    public int getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(int studyTime) {
        this.studyTime = studyTime;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public int getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(int collectNum) {
        this.collectNum = collectNum;
    }

    public int getScoreNum() {
        return scoreNum;
    }

    public void setScoreNum(int scoreNum) {
        this.scoreNum = scoreNum;
    }

    public int getExamNum() {
        return examNum;
    }

    public void setExamNum(int examNum) {
        this.examNum = examNum;
    }


    // toString方法


    @Override
    public String toString() {
        return "StuLog{" +
                "id=" + id +
                ", stuId=" + stuId +
                ", studyNum=" + studyNum +
                ", classNum=" + classNum +
                ", studyTime=" + studyTime +
                ", likeNum=" + likeNum +
                ", collectNum=" + collectNum +
                ", scoreNum=" + scoreNum +
                ", examNum=" + examNum +
                '}';
    }
}

