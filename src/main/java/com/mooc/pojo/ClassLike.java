package com.mooc.pojo;


public class ClassLike {

	private int id;

    private int cId;

    private int sId;

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

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    // toString
    @Override
    public String toString() {
        return "ClassLike{" +
                "id=" + id +
                ", cId=" + cId +
                ", sId=" + sId +
                '}';
    }
}
