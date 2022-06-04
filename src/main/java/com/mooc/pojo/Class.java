package com.mooc.pojo;

public class Class {

  private int id;

  private String name;

  private String description = "欢迎进入本课程的学习，学习结束后记得参加课程测试！";   // 课程介绍

  private int kId;   // 对应知识点的ID

  private int level;


  // get和set方法

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getkId() {
    return kId;
  }

  public void setkId(int kId) {
    this.kId = kId;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  @Override
  public String toString() {
    return "Class{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", kId=" + kId +
            ", level=" + level +
            '}';
  }
}