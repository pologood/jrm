package com.suning.jrm.util.jsondb;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * json转换为db的实用工具类
 *
 * Created by yanchangyou on 2016/10/22.
 *
 */
public class JsonDbUtil {


    /**
     * 查询
     *
     * @param conn
     * @param json
     * @return
     */
    public static String select(Connection conn, String tableName, String json) {
        String sql = JsonSqlUtil.json2select(tableName, json);
        List<Map<String, Object>> rows = null;
        try {
            rows = DbUtil.selectForList(conn, sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }
        return JsonUtil.list2json(rows);
    }

    /**
     * 插入
     *
     * @param conn
     * @param tableName
     * @param json
     * @return
     */
    public static int insert(Connection conn, String tableName, String json) {
        json = json.trim();
        if (json.startsWith("{")) {
            json = "[" + json + "]";
        }
        List<String> sqls = JsonSqlUtil.json2insertAll(tableName, json);
        int count = 0;
        for (String sql : sqls) {
            if (DbUtil.executeSql(conn, sql)) {
                count++;
            }
        }
        return count;
    }

}
