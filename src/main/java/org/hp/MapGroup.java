/**
 * Copyright (c) 2012 Conversant Solutions. All rights reserved.
 * <p>
 * Created on 2019/3/29.
 */
package org.hp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;

import javax.sql.rowset.Joinable;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.groupingBy;

public class MapGroup {

    public List<Map<String, String>> json2List(JSONObject json){
        List<Map<String, String>> mapList = new ArrayList<>();
        for(Map.Entry<String, Object> entry : json.entrySet()){
            mapList.addAll(JSON.parseObject(JSON.toJSONString(entry.getValue()), List.class));
        }
        return mapList;
    }

    public JSONObject getJsonFromResources(){
        String jsonStr = "";
        try {
            String filePath = this.getClass().getResource("/reportData.json").getPath();
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
        MapGroup mg = new MapGroup();
        JSONObject json = mg.getJsonFromResources();
        List<Map<String, String>> mapList = mg.json2List(json);

        List<Map<String, Map<String, Double>>> listMap = new ArrayList<>();

        Map<String, Map<String, Double>> testMap = mapList.stream()
                .collect(groupingBy(map -> ((String)((Map)map).get("month")),
                    groupingBy(map -> "hd_transmux", summingDouble(map -> (Double.valueOf((String)((Map)map).get("hd_transcoding")))))//summingDouble(map -> (Double.valueOf((String)((Map)map).get("hd_transmux")))))
                ));
        Map<String, Map<String, Double>> testMap2 = mapList.stream()
                .collect(groupingBy(map -> ((String)((Map)map).get("month")),
                        groupingBy(map -> "hd_transcoding", summingDouble(map -> (Double.valueOf((String)((Map)map).get("hd_transcoding")))))//summingDouble(map -> (Double.valueOf((String)((Map)map).get("hd_transmux")))))
                ));

        listMap.add(testMap);
        listMap.add(testMap2);

        Map maps = listMap.stream().collect(groupingBy(map -> {
            List<String> keyList = new ArrayList<>();
            Iterator<Map.Entry<String, Map<String, Double>>> iterator = ((Map)map).entrySet().iterator();
            while (iterator.hasNext()){
                keyList.add(iterator.next().getKey());
            }
            return keyList;
        }, toList()));

        System.out.println(JSON.toJSONString(testMap));
        System.out.println(JSON.toJSONString(testMap2));
        System.out.println(JSON.toJSONString(maps));

//        Map<String, Map> testMap1 = mapList.stream()
//                .collect(groupingBy(map -> ((String)((Map)map).get("month"))));

//        double rvalue = mapList.stream().mapToDouble(map -> Double.parseDouble(map.get("hd_transmux")))
//                .reduce((a, b) -> a + b).getAsDouble();
//        System.out.println(rvalue);

        mapList.stream().map(map -> map.get("hd_transmux"))
                .forEach(map -> {
                    System.out.println(map);
                });
    }
}
