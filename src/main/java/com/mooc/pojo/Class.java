package com.mooc.pojo;

public class Class {

  private int id;

  private String name;

  private String description = "欢迎进入本课程的学习，学习结束后记得参加课程测试！";   // 课程介绍

  private int kId;   // 对应知识点的ID

  private int level;


  /**
   * 构造方法
   * @param name 课程名字
   * @param kId 所属知识点id
   * @param level 课程难度等级
   */
  public Class(String name, int kId, int level) {
    this.name = name;
    this.kId = kId;
    this.level = level;
  }

  // get和set方法
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

//  public int gettId() {
//    return tId;
//  }
//
//  public void settId(int tId) {
//    this.tId = tId;
//  }

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

  // toString方法

  @Override
  public String toString() {
    return "Class{" +
            "id=" + id +
//            ", tId=" + tId +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", kId=" + kId +
            ", level=" + level +
            '}';
  }
}