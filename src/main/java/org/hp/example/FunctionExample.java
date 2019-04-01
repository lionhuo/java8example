/**
 * Copyright (c) 2012 Conversant Solutions. All rights reserved.
 * <p>
 * Created on 2019/4/1.
 */
package org.hp.example;

import com.alibaba.fastjson.JSONObject;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class FunctionExample {

    public static <T, R> List<R> map(List<T> list, Function<T, R> f){
        List<R> resultList = new ArrayList<>();
        for(T t : list){
            R r = f.apply(t);
            resultList.add(r);
        }
        return resultList;
    }

    public static void main(String[] args) {
        List<String> strList = Arrays.asList("1", "ab", "b3", "da", "2", "i");

        Function<String, Integer> f = str -> str.length();
        List<Integer> strLengthList = map(strList, f);
        System.out.println(JSONObject.toJSONString(strLengthList));

        strLengthList = strList.stream().map(f).collect(toList());
        System.out.println(JSONObject.toJSONString(strLengthList));
    }
}
