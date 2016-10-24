package com.suning.jrm.util.jsondb;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * json 相关处理，适用于此框架的全局配置
 * <p>
 * Created by yanchangyou on 2016/10/23.
 */
public class JsonUtil {

    static JsonConfig jsonConfig = new JsonConfig();

    static {
        jsonConfig.registerJsonValueProcessor(Date.class, JsonDateProcessor.instance);
        jsonConfig.registerJsonValueProcessor(java.sql.Date.class, JsonDateProcessor.instance);
        jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, JsonDateProcessor.instance);
    }

    /**
     * json字符串转换为json对象，带全局参数
     *
     * @param json
     * @return
     */
    public static JSONObject fromObject(String json) {
        return JSONObject.fromObject(json, jsonConfig);
    }

    /**
     * json字符串转换为json数组, 带全局参数
     *
     * @param json
     * @return
     */
    public static JSONArray fromArray(String json) {
        return JSONArray.fromObject(json, jsonConfig);
    }

    /**
     * list 转换为 json array
     *
     * @param list
     * @return
     */
    public static JSONArray fromArray(List<Map<String, Object>> list) {
        return JSONArray.fromObject(list, jsonConfig);
    }

    /**
     * list to json
     *
     * @param list
     * @return
     */
    public static String list2json(List<Map<String, Object>> list) {
        return fromArray(list).toString();
    }

    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        String json = "{\"id\":1,\"code\":\"001\",\"inputDate\":\"2015-05-13 17:05:07\"}";
        JSONObject jsonObject = new JSONObject();
        jsonObject = JSONObject.fromObject(json);
        for(Object key : jsonObject.keySet()) {
            Object value = jsonObject.get(key);
            System.out.println(key + " : " + value + "(" + value.getClass() + ")");
        }
        Object bean = JSONObject.toBean(jsonObject);
        System.out.print(bean);
        System.out.print(PropertyUtils.getProperty(bean,"id"));
    }
}
