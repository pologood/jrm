package com.suning.jrm.util.jsondb;

import org.junit.Assert;

/**
 * Created by yanchangyou on 2016/10/22.
 */
public class JsonSqlUtilTes {

    @org.junit.Test public void testJson2select() throws Exception {
        String exceptedSql = "select * from account where 1 = 1 and id = 1 and code = '001'";
        String json = "{\"id\":1,\"code\":\"001\"}";
        String tableName = "account";
        String sql = JsonSqlUtil.json2select(tableName, json);
        Assert.assertNotNull(sql);
        Assert.assertEquals(sql, exceptedSql);
    }

}
