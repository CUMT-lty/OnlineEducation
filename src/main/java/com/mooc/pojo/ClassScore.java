package com.mooc.pojo;

public class ClassScore {
    private int id;
    private int sId;
    private int cId;
    private int score;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "ClassScore{" +
                "id=" + id +
                ", sId=" + sId +
                ", cId=" + cId +
                ", score=" + score +
                '}';
    }
}
