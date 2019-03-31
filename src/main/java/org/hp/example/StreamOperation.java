/**
 * Copyright (c) 2012 Conversant Solutions. All rights reserved.
 * <p>
 * Created on 2019/4/1.
 */
package org.hp.example;

import com.alibaba.fastjson.JSONObject;
import org.hp.constants.Artist;
import org.hp.constants.SampleData;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamOperation {
    int addUp(Stream<Integer> numbers){
        return numbers.reduce(0, (a, b) -> a + b);
    }

    List<String> findArtistNameAndNation(List<Artist> artistList){
        return artistList.stream().flatMap(artist -> Stream.of(artist.getName(), artist.getNationality())).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        StreamOperation so = new StreamOperation();
        Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5, 6);
        int sum = so.addUp(numbers);
        System.out.println(sum);

        List<String> artistNameAndNation = so.findArtistNameAndNation(SampleData.membersOfTheBeatles);
        System.out.println(JSONObject.toJSONString(artistNameAndNation));
    }
}
