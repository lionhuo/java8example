/**
 * Copyright (c) 2012 Conversant Solutions. All rights reserved.
 * <p>
 * Created on 2019/4/1.
 */
package org.hp.example;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class PredicateExample {

    public static <T> List<T> filter(List<T> list, Predicate<T> predicate){
        List<T> results = new ArrayList<>();
        for(T t : list){
            if(predicate.test(t)){
                results.add(t);
            }
        }
        return results;
    }

    public static void main(String[] args) {
        List<String> strList = Arrays.asList("1", "ab", "b3", "da", "2", "i");
        Predicate<String> predicate = s -> s.length() > 1;
        List<String> resultList = filter(strList, predicate);
        System.out.println(JSONObject.toJSONString(resultList));

        List<String> resultList2 = strList.stream().filter(s -> s.length() > 1).collect(toList());
        System.out.println(JSONObject.toJSONString(resultList2));
    }
}
