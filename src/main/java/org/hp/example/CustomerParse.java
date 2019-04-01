/**
 * Copyright (c) 2012 Conversant Solutions. All rights reserved.
 * <p>
 * Created on 2019/4/1.
 */
package org.hp.example;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomerParse {

    public JSONObject getJsonFromResources(){
        String jsonStr = "";
        try {
            String filePath = this.getClass().getResource("/customerData.json").getPath();
//            String property = System.getProperty("/reportData.json");
//            System.out.println(filePath);
//            System.out.println(property);
            jsonStr = FileUtils.readFileToString(new File(filePath), "utf-8");
//            System.out.println(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return JSON.parseObject(jsonStr);

    }

    public static void main(String[] args) {
        CustomerParse cp = new CustomerParse();

        List<String> customerIdList = new ArrayList<>();
        JSONObject customerObj = cp.getJsonFromResources();
        JSONArray jsonArray = customerObj.getJSONArray("customer");
        for(Object var1 : jsonArray){
            customerIdList.add(((JSONObject)var1).getString("id"));
        }
        System.out.println(JSONObject.toJSONString(customerIdList));
    }
}
