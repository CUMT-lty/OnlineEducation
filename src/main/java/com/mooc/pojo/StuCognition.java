package com.mooc.pojo;

public class StuCognition {

    private int id;

    private int sId;

    private int kId;

    private int congitionLevel;   // 该学生对该知识点的认知等级


    // get & set

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

    public int getkId() {
        return kId;
    }

    public void setkId(int kId) {
        this.kId = kId;
    }

    public int getCongitionLevel() {
        return congitionLevel;
    }

    public void setCongitionLevel(int congitionLevel) {
        this.congitionLevel = congitionLevel;
    }

    // toString

    @Override
    public String toString() {
        return "StuCognition{" +
                "id=" + id +
                ", sId=" + sId +
                ", kId=" + kId +
                ", congitionLevel=" + congitionLevel +
                '}';
    }
}
