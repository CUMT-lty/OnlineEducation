package com.mooc.pojo;

public class StuCognition {

    private int id;

    private int sId;

    private int kId;


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


    // toString

    @Override
    public String toString() {
        return "StuCognition{" +
                "id=" + id +
                ", sId=" + sId +
                ", kId=" + kId +
                '}';
    }
}
