package com.mooc.pojo;

public class ClassCollect {

    private int id;

    private int cId;

    private int sId;

    // get和set方法


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

    // toString方法

    @Override
    public String toString() {
        return "ClassCollect{" +
                "id=" + id +
                ", cId=" + cId +
                ", sId=" + sId +
                '}';
    }
}
