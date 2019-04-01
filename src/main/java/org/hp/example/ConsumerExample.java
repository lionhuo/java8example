/**
 * Copyright (c) 2012 Conversant Solutions. All rights reserved.
 * <p>
 * Created on 2019/4/1.
 */
package org.hp.example;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerExample {

    public static <T> void foreach(List<T> list, Consumer<T> consumer){
        for(T t : list){
            consumer.accept(t);
        }
    }

    public static void main(String[] args) {
        List<String> strList = Arrays.asList("1", "ab", "b3", "da", "2", "i");
        Consumer<String> consumer = s -> System.out.println(s);
        foreach(strList, consumer);

        strList.stream().forEach(consumer);
    }
}
