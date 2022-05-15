package com.mooc.dao;

import org.neo4j.driver.*;

import static org.neo4j.driver.Values.parameters;

/**
 * 对 neo4j 数据库的访问
 */
public class Neo4jDataAccess {

    /*
    * 待完成功能：
    * 1. 获取结点，并映射为Knoledge对象
    * 2. 获取某结点一定范围内的前驱
    * 3. 获取某节点一定范围内的后继
    *
    *
    *
    * */

    // Driver objects are thread-safe and are typically made available application-wide.
    Driver driver;

    public Neo4jDataAccess(String uri, String user, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }


    /**
     * 添加知识结点
     * @param kname 结点的 kname 属性值
     */
    private void addKpoint(String kname)
    {
        // Sessions are lightweight and disposable connection wrappers.
        try (Session session = driver.session())
        {
            // Wrapping a Cypher Query in a Managed Transaction provides atomicity
            // and makes handling errors much easier.
            // Use `session.writeTransaction` for writes and `session.readTransaction` for reading data.
            // These methods are also able to handle connection problems and transient errors using an automatic retry mechanism.
            session.writeTransaction(tx -> tx.run("MERGE (n:kpoint {kname: $x})", parameters("x", kname)));
        }
    }

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

    private void getNodeByKname(String kname)
    {
        try (Session session = driver.session())
        {
            // A Managed transaction is a quick and easy way to wrap a Cypher Query.
            // The `session.run` method will run the specified Query.
            // This simpler method does not use any automatic retry mechanism.
            Result result = session.run(
                    "MATCH (n:kpoint) WHERE n.kname=$x RETURN n.kname AS name",
                    parameters("x", kname));
            // Each Cypher execution returns a stream of records.
            while (result.hasNext())
            {
                Record record = result.next();
                // Values can be extracted from a record by index or name.
                System.out.println(record.get("name").asString());
            }
        }
    }

    public void close()
    {
        // Closing a driver immediately shuts down all open connections.
        driver.close();
    }

    public static void main(String... args)
    {
        String url = "bolt://localhost:7687";
        String user = "neo4j";
        String password = "7474";
        Neo4jDataAccess example = new Neo4jDataAccess(url, user, password);
        example.getNodeByKname("数据库");
        example.close();
    }

}
