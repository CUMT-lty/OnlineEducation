package com.mooc.bean;


import java.util.Arrays;

public class AnsBean {

  int cId;
  int[] checkedAnswers;
  int[] ids;

  public int getcId() {
    return cId;
  }

  public void setcId(int cId) {
    this.cId = cId;
  }

  public int[] getCheckedAnswers() {
    return checkedAnswers;
  }

  public void setCheckedAnswers(int[] checkedAnswers) {
    this.checkedAnswers = checkedAnswers;
  }

  public int[] getIds() {
    return ids;
  }

  public void setIds(int[] ids) {
    this.ids = ids;
  }


  @Override
  public String toString() {
    return "AnsBean{" +
            "cId=" + cId +
            ", checkedAnswers=" + Arrays.toString(checkedAnswers) +
            ", ids=" + Arrays.toString(ids) +
            '}';
  }
}
