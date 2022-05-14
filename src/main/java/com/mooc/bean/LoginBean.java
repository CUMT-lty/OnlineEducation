package com.mooc.bean;

import com.mooc.pojo.Stu;

public class LoginBean<T> {

    private boolean loginFlag;

    private Stu stu;

    // get & set

    public boolean getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(boolean loginFlag) {
        this.loginFlag = loginFlag;
    }

    public Stu getStu() {
        return stu;
    }

    public void setStu(Stu stu) {
        this.stu = stu;
    }

    // toString

    @Override
    public String toString() {
        return "LoginBean{" +
                "loginFlag=" + loginFlag +
                ", stu=" + stu +
                '}';
    }
}
