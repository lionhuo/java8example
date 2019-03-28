/**
 * Copyright (c) 2012 Conversant Solutions. All rights reserved.
 * <p>
 * Created on 2019/3/29.
 */
package org.hp;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.List;
import java.util.Map;

public class MapGroup {

    public List<Map> json2List(String json){

    }

    public JSONObject getJsonFromResources(){
        try(FileInputStream fi = new FileInputStream(new File("/reportData.json"));
            ){
//            String jsonStr = "";
//            while(b){
//
//            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
