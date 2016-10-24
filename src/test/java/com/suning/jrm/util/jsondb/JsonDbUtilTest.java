package com.suning.jrm.util.jsondb;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by yanchangyou on 2016/10/22.
 */
public class JsonDbUtilTest {

    static {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    Connection conn;
    @Before
    public void before() throws SQLException {
        String url = "jdbc:mysql://127.0.0.1/test?zeroDateTimeBehavior=convertToNull";
        String username = "root";
        String password = "";
        conn = DriverManager.getConnection(url, username, password);
    }
    @After
    public void after() throws SQLException {
        conn.close();
    }
    @Test
    public void selectForList() throws Exception {
        String sql = "select * from account";
        List rows = DbUtil.selectForList(conn, sql);
        System.out.println(rows);

    }
    @Test
    public void select() {
        String json = "{\"id\":1,\"code\":\"001\"}";
        String tableName = "account";
        String result = JsonDbUtil.select(conn, tableName, json);
        System.out.println(result);

        result = JsonDbUtil.select(conn, tableName, json);
        System.out.println(result);

        result = JsonDbUtil.select(conn, tableName, null);
        System.out.println(result);
    }

    @Test
    public void insert() {
        String json = "{\"id\":1,\"code\":\"001\"}";
        String tableName = "account";
        String result = JsonDbUtil.select(conn, tableName, json);
        System.out.println(result);

        result = result.replace("\"id\":1", "\"id\":" + System.currentTimeMillis());
        System.out.println(result);

        int count = JsonDbUtil.insert(conn, tableName, result);
        System.out.println(count);

        json = json.replace("\"id\":1", "\"id\":" + System.currentTimeMillis());
        count = JsonDbUtil.insert(conn, tableName, json);
        System.out.println(count);
    }

}