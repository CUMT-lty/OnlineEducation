package com.mooc.dao;

import org.neo4j.driver.*;

import java.util.ArrayList;
import java.util.List;

import static org.neo4j.driver.Values.parameters;

/**
 * 对 neo4j 数据库的访问
 */
public class Neo4jDataAccess {

    // Driver objects are thread-safe and are typically made available application-wide.
    Driver driver;

    public Neo4jDataAccess(String uri, String user, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    /**
     * 添加知识结点
     * @param kname 结点的 kname 属性值
     */
    public void addKpoint(String kname) {
        // Sessions are lightweight and disposable connection wrappers.
        try (Session session = driver.session()) {
            // Wrapping a Cypher Query in a Managed Transaction provides atomicity
            // and makes handling errors much easier.
            // Use `session.writeTransaction` for writes and `session.readTransaction` for reading data.
            // These methods are also able to handle connection problems and transient errors using an automatic retry mechanism.
            session.writeTransaction(tx -> tx.run("MERGE (n:kpoint {kname: $x})", parameters("x", kname)));
        }
    }


    /* 不提供删除结点和关系的方法 */


    /**
     * 找到一个结点的所有 直接后继 结点
     * @param kname 要检索的结点
     * @return 一个String数组，是找到的后继结点集合
     */
    public String[] getNextByKname(String kname) {
        List<String> nextNodes = new ArrayList<>();   // 存放最终的结点
        try (Session session = driver.session())
        {
            // A Managed transaction is a quick and easy way to wrap a Cypher Query.
            // The `session.run` method will run the specified Query.
            // This simpler method does not use any automatic retry mechanism.
            Result result = session.run(   // cql语句和参数说明
                    "match (n1:kpoint)-[:next]->(n2:kpoint) where n1.kname=$x return n2.kname as nextKpoint",
                    parameters("x", kname));
            // Each Cypher execution returns a stream of records.
            while (result.hasNext()) {
                Record record = result.next();
                // Values can be extracted from a record by index or name.
                String pointName = record.get("nextKpoint").asString();  // 获取结点名
                nextNodes.add(pointName);  // 加入结果集合
            }
        }
        int size = nextNodes.size();
        return nextNodes.toArray(new String[size]);
    }


    /**
     * 找到一个结点的所有 直接前驱 结点
     * @param kname 要检索的结点
     * @return 一个String数组，是找到的 前驱结点集合
     */
    public String[] getLastByKname(String kname) {
        List<String> lastNodes = new ArrayList<>();   // 存放最终的结点
        try (Session session = driver.session()) {
            // A Managed transaction is a quick and easy way to wrap a Cypher Query.
            // The `session.run` method will run the specified Query.
            // This simpler method does not use any automatic retry mechanism.
            Result result = session.run(
                    "MATCH (n1:kpoint)-[:next]->(n2:kpoint) WHERE n2.kname=$x RETURN n1.kname AS lastKpoint",
                    parameters("x", kname));
            // Each Cypher execution returns a stream of records.
            while (result.hasNext()) {
                Record record = result.next();
                // Values can be extracted from a record by index or name.
                String pointName = record.get("lastKpoint").asString();
                lastNodes.add(pointName);
            }
        }
        int size = lastNodes.size();
        return lastNodes.toArray(new String[size]);
    }


    /**
     * Closing a driver immediately shuts down all open connections.
     */
    public void close() {
        // Closing a driver immediately shuts down all open connections.
        driver.close();
    }




    /**
     * 用来测试的main方法
     * @param args
     */
    /*public static void main(String[] args) {
        String url = "bolt://localhost:7687";
        String user = "neo4j";
        String password = "7474";
        Neo4jDataAccess example = new Neo4jDataAccess(url, user, password);
        example.getNextByKname("java");
        example.close();
    }*/

    /* 一个简单的示例 */
    /*private void printPeople(String initial)
    {
        try (Session session = driver.session())
        {
            // A Managed transaction is a quick and easy way to wrap a Cypher Query.
            // The `session.run` method will run the specified Query.
            // This simpler method does not use any automatic retry mechanism.
            Result result = session.run(
                    "MATCH (a:Person) WHERE a.name STARTS WITH $x RETURN a.name AS name",
                    parameters("x", initial));
            // Each Cypher execution returns a stream of records.
            while (result.hasNext())
            {
                Record record = result.next();
                // Values can be extracted from a record by index or name.
                System.out.println(record.get("name").asString());
            }
        }
    }*/


}
