package com.mooc.bean;


/**
 * 注册结果
 */
public class RegisterBean<T> {

    private boolean registerFlag;

    // get & set

    public boolean getRegisterFlag() {
        return registerFlag;
    }

    public void setRegisterFlag(boolean registerFlag) {
        this.registerFlag = registerFlag;
    }

    // toString

    @Override
    public String toString() {
        return "RegisterBean{" +
                "registerFlag=" + registerFlag +
                '}';
    }
}
