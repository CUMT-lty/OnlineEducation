package com.mooc.service;

public interface Neo4jService {


    /**
     * 添加知识结点
     * @param kname 结点的 kname 属性值
     */
    void addKpoint(String kname);


    /**
     * 找到一个结点的所有 直接后继 结点
     * @param kname 要检索的结点
     * @return 一个String数组，是找到的后继结点集合
     */
    String[] getNextByKname(String kname);


    /**
     * 找到一个结点的所有 直接前驱 结点
     * @param kname 要检索的结点
     * @return 一个String数组，是找到的 前驱结点集合
     */
    String[] getLastByKname(String kname);



}
