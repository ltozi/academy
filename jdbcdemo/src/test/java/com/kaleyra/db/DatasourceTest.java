package com.kaleyra.db;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

public class DatasourceTest {

//    public static final String JDBC_URL = "jdbc:hsqldb:file:db/hsqldb-db;shutdown=true;hsqldb.write_delay=false";
    public static final String JDBC_URL = "jdbc:hsqldb:mem:inmemory;hsqldb.sqllog=3";
    public static final String USERNAME = "SA";
    public static final String PASSWORD = "";
    private Connection conn;

    @Before
    public void setupTest() throws SQLException {
        conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        conn.setAutoCommit(false);
    }

    @After
    public void teardown() throws SQLException {
//        Statement statement = conn.createStatement();
//        statement.executeQuery("");
//        conn.commit();
    }

    @Test
    public void initConnection() throws SQLException {


        Statement stmt = conn.createStatement();

        ResultSet rs =
                stmt.executeQuery("CREATE TABLE user " +
                        "(id INTEGER IDENTITY PRIMARY KEY, " +
                        "name VARCHAR(30), " +
                        "email  VARCHAR(50))");


        stmt.executeQuery("INSERT INTO user (name, email) VALUES ('Luigi', 'luigi.toziani@kaleyra.com')");

        conn.commit();

//        while (rs.next()) {
//            int x = rs.getInt("a");
//            String s = rs.getString("b");
//            float f = rs.getFloat("c");
//        }
    }



}
