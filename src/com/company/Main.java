package com.company;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        String s="III";
        HashMap<String, Integer> map=new HashMap<>();
        map.put("I",1);
        map.put("V",5);
        map.put("X",10);
        map.put("L",50);
        map.put("C",100);
        map.put("D",500);
        map.put("M",1000);
        String temp=String.valueOf(s.charAt(0));
        int temp1=map.get(String.valueOf(s.charAt(0)));
        boolean test=map.get(String.valueOf(s.charAt(1)))>map.get(String.valueOf(s.charAt(0)));
        System.out.println(test);
    }
}
