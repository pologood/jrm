package com.suning.jrm.util.jsondb;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 日期格式化
 * Created by yanchangyou on 2016/10/23.
 */
public class JsonDateProcessor implements JsonValueProcessor {
    /**
     * 供调用的 static 实例
     */
    public static final JsonDateProcessor instance = new JsonDateProcessor();

    private String datePattern = "yyyy-MM-dd";
    private SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
    private String dateTimePattern = "yyyy-MM-dd HH:mm:ss";
    private SimpleDateFormat dateTimeFormat = new SimpleDateFormat(dateTimePattern);

    public Object processObjectValue(String key, Object value, JsonConfig jc) {
        if (value == null) {
            return "";
        } else if (value instanceof Timestamp) {
            return dateTimeFormat.format((Date) value);
        } else if (value instanceof Date) {
            return dateFormat.format((Date) value);
        } else {
            return value.toString();
        }
    }


    public Object processArrayValue(Object o, JsonConfig jsonConfig) {
        return null;
    }

    public static void main(String[] args) {
        java.sql.Date date = new java.sql.Date(1);
        System.out.println(date instanceof Date);

        Map map = new HashMap();
        map.put("date", date);
        JsonConfig jsonConfig = new JsonConfig();

        jsonConfig.registerJsonValueProcessor(Date.class, JsonDateProcessor.instance);
        jsonConfig.registerJsonValueProcessor(java.sql.Date.class, JsonDateProcessor.instance);
        Object object = JSONObject.fromObject(map, jsonConfig);
        System.out.println(object);

    }
}
