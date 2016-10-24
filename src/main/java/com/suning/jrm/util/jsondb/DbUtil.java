package com.suning.jrm.util.jsondb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据库sql操作和结果返回工具类
 * <p>
 * Created by yanchangyou on 2016/10/24.
 */
public class DbUtil {


    /**
     * 查询sql，转换为map
     *
     * @param conn
     * @param sql
     * @return
     */
    public static List<Map<String, Object>> selectForList(Connection conn, String sql) throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery(sql);
        ResultSetMetaData metaData = rs.getMetaData();
        ArrayList<String> columnNames = new ArrayList<String>();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            columnNames.add(metaData.getColumnName(i));
        }
        List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
        while (rs.next()) {

            Map<String, Object> row = new HashMap<String, Object>();
            for (String columnName : columnNames) {
                row.put(columnName, rs.getObject(columnName));
            }
            rows.add(row);
        }
        rs.close();
        return rows;
    }

    /**
     * 执行sql语句
     *
     * @param conn
     * @return
     */
    public static boolean executeSql(Connection conn, String sql) {
        System.out.println("sql : " + sql);
        boolean isOk = false;
        try {
            isOk = conn.createStatement().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }
        return isOk;
    }
}
