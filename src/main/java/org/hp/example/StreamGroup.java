/**
 * Copyright (c) 2012 Conversant Solutions. All rights reserved.
 * <p>
 * Created on 2019/3/31.
 */
package org.hp.example;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.hp.constants.Album;
import org.hp.constants.Artist;
import org.hp.constants.SampleData;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.maxBy;

public class StreamGroup {
    public void countNameNum(){
        Stream<String> names = Stream.of("John", "Paul", "George", "John",
                "Paul", "John");
        Map<String, Long> wordMap = names.collect(groupingBy(name -> name, counting()));
        System.out.println(JSONObject.toJSONString(wordMap));
    }

    public void findLongestNameByReduce(){
        Stream<String> names = Stream.of("John Lennon", "Paul McCartney",
                "George Harrison", "Ringo Starr", "Pete Best", "Stuart Sutcliffe");
//        Comparator<String> byNameLength = comparing(artist -> artist.length());

        String lname = names.reduce((a, b) -> {
            return Comparator.comparing(c -> ((String)c).length()).compare(a, b) > 0 ? a : b;
        }).get();
        System.out.println(lname);
    }

    public void findLongestNameByCollect(){
        Stream<String> names = Stream.of("John Lennon", "Paul McCartney",
                "George Harrison", "Ringo Starr", "Pete Best", "Stuart Sutcliffe");
        String lname = names.collect(maxBy(Comparator.comparing(name -> name.length()))).get();
        System.out.println(lname);
    }

    public void findAlbum(List<Album> albumList){
        albumList.stream().filter(album -> album.getTrackList().size() <= 3).forEach(album -> System.out.println(album.getName()));
    }

    public void countArtistMembers(List<Artist> artistList){
        long memberNum = artistList.stream().map(artist -> artist.getMembers().count()).reduce(0L, Long::sum);
        System.out.println(memberNum);
        artistList.stream().map(artist -> artist.getMembers())
                .forEach(memberN -> memberN.map(member -> member.getName()));
    }

    public static void main(String[] args) {
        StreamGroup sg = new StreamGroup();
        //
        sg.countNameNum();
        //
        sg.findLongestNameByReduce();
        //
        sg.findLongestNameByCollect();
        //
        List<Album> albumList = Arrays.asList(SampleData.aLoveSupreme, SampleData.manyTrackAlbum, SampleData.sampleShortAlbum);
        sg.findAlbum(albumList);
        //
        List<Artist> artistList = Arrays.asList(SampleData.theBeatles);
        sg.countArtistMembers(artistList);

    }
}
