package com.mooc.pojo;

public class ClassExam {

    private int id;

    private int cId;

    private int sId;

    private int score;


    // get & set

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

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    // toString

    @Override
    public String toString() {
        return "ClassExam{" +
                "id=" + id +
                ", cId=" + cId +
                ", sId=" + sId +
                ", score=" + score +
                '}';
    }
}
