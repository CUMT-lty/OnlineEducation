package com.mooc.pojo;

public class ClassLog {

	private int id;

	private int cId;           // 课程编号

    private String cName;

    private String cDescription;

    private int viewNum;       // 课程被观看次数

    private int score;         // 课程资源得分

    private int likeNum;       // 获点赞数

    private int collectNum;    // 获收藏数

    private int scoreNum;     // 被评分次数


    // set & get

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcDescription() {
        return cDescription;
    }

    public void setcDescription(String cDescription) {
        this.cDescription = cDescription;
    }

    public int getViewNum() {
        return viewNum;
    }

    public void setViewNum(int viewNum) {
        this.viewNum = viewNum;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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

// toString


    @Override
    public String toString() {
        return "ClassLog{" +
                "id=" + id +
                ", cId=" + cId +
                ", viewNum=" + viewNum +
                ", score=" + score +
                ", likeNum=" + likeNum +
                ", collectNum=" + collectNum +
                ", scoreNum=" + scoreNum +
                '}';
    }
}
