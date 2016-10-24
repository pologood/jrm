package com.suning.jrm.util.jsondb;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanchangyou on 2016/10/22.
 * json转换为sql的实用工具类
 */
public class JsonSqlUtil {
    /**
     * json转换为select的sql语句
     *
     * @param tableName
     * @param json
     * @return
     */
    public static String json2select(String tableName, String json) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from ").append(tableName).append(" where 1 = 1");
        JSONObject jsonObject = JsonUtil.fromObject(json);
        for (Object key : jsonObject.keySet()) {
            sql.append(" and ").append(key).append(" = ");
            Object value = jsonObject.get(key);
            sql.append(convertSqlValue(value));
        }
        return sql.toString();
    }

    /**
     * sql 插入
     *
     * @param tableName
     * @param jsonObject
     * @return
     */
    static String json2insert(String tableName, JSONObject jsonObject) {

        StringBuilder sql = new StringBuilder();
        sql.append("insert into ").append(tableName);

        StringBuilder fieldNames = new StringBuilder();
        StringBuilder fieldValues = new StringBuilder();

        for (Object key : jsonObject.keySet()) {
            if (fieldNames.length() > 0) {
                fieldNames.append(", ");
            }
            fieldNames.append(key);

            if (fieldValues.length() > 0) {
                fieldValues.append(", ");
            }

            String sqlValue = null;
            Object value = jsonObject.get(key);
            fieldValues.append(convertSqlValue(value));

        }
        sql.append("(").append(fieldNames).append(") values (").append(fieldValues).append(")");
        return sql.toString();
    }

    /**
     * sql 插入
     *
     * @param tableName
     * @param json
     * @return
     */
    public static String json2insertOne(String tableName, String json) {
        return json2insert(tableName, JsonUtil.fromObject(json));
    }

    /**
     * sql 插入
     *
     * @param tableName
     * @param json
     * @return
     */
    public static List<String> json2insertAll(String tableName, String json) {

        List<String> sqls = new ArrayList<String>();
        JSONArray jsonArray = JsonUtil.fromArray(json);
        for (Object object : jsonArray) {
            JSONObject jsonObject = (JSONObject) object;
            String sql = json2insert(tableName, jsonObject);
            sqls.add(sql);
        }
        return sqls;
    }

    /**
     * 转换为sql的value
     *
     * @param value
     * @return
     */
    private static String convertSqlValue(Object value) {
        StringBuilder sqlValue = new StringBuilder();
        if (value instanceof String) {
            sqlValue.append("'").append(value).append("'");
        } else if (value instanceof Number) {
            sqlValue.append(value);
        } else {
            throw new RuntimeException("not supported");
        }
        return sqlValue.toString();
    }
}
