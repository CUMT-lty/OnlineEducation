package com.mooc.dao;

import org.neo4j.driver.*;

import static org.neo4j.driver.Values.parameters;

/**
 * 对 neo4j 数据库的访问
 */
public class Neo4jDataAccess {


    /*
    * 待完成功能：
    * 1. 获取结点
    * 2. 获取某结点所有前驱
    * 3. 获取某节点所有后继
    *
    * */


    /* 下面是一个例子 */

    // Driver objects are thread-safe and are typically made available application-wide.
    Driver driver;

    public Neo4jDataAccess(String uri, String user, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    /*private void addPerson(String name)
    {
        // Sessions are lightweight and disposable connection wrappers.
        try (Session session = driver.session())
        {
            // Wrapping a Cypher Query in a Managed Transaction provides atomicity
            // and makes handling errors much easier.
            // Use `session.writeTransaction` for writes and `session.readTransaction` for reading data.
            // These methods are also able to handle connection problems and transient errors using an automatic retry mechanism.
            session.writeTransaction(tx -> tx.run("MERGE (a:Person {name: $x})", parameters("x", name)));
        }
    }*/

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

    private void printNodeName(String name)
    {
        try (Session session = driver.session())
        {
            // A Managed transaction is a quick and easy way to wrap a Cypher Query.
            // The `session.run` method will run the specified Query.
            // This simpler method does not use any automatic retry mechanism.
            Result result = session.run(
                    "MATCH (n:kpoint) WHERE n.kname=$x RETURN n.kname AS name",
                    parameters("x", name));
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
//        example.addPerson("Ada");
//        example.addPerson("Alice");
//        example.addPerson("Bob");
        example.printNodeName("数据库");
        example.close();
    }

}
