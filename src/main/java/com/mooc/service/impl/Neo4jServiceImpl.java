package com.mooc.service.impl;

import com.mooc.dao.Neo4jDataAccess;

import com.mooc.service.Neo4jService;

import java.util.Arrays;

public class Neo4jServiceImpl implements Neo4jService {

    // 数据库连接信息
    String url = "bolt://localhost:7687";
    String user = "neo4j";
    String password = "7474";

    @Override
    public void addKpoint(String kname) {
        Neo4jDataAccess access = new Neo4jDataAccess(url, user, password);
        access.addKpoint(kname);
        access.close();
    }

    @Override
    public String[] getNextByKname(String kname) {
        Neo4jDataAccess access = new Neo4jDataAccess(url, user, password);
        String[] nextNodes = access.getNextByKname(kname);
        access.close();
        return nextNodes;
    }

    @Override
    public String[] getLastByKname(String kname) {
        Neo4jDataAccess access = new Neo4jDataAccess(url, user, password);
        String[] lastNodes = access.getLastByKname(kname);
        access.close();
        return lastNodes;
    }

    @Override
    public String[] kpointOrder(String[] knames) {
        return new String[0];
    }
}
