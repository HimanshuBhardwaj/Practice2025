package org.example.jan05;

import java.util.HashMap;
import java.util.HashSet;

public class POC {
    public static void main(String[] args) {
        HashMap<Integer, Integer> set = new HashMap<>();

        set.put(1,1);
        set.put(1,2);
        set.put(2,2);
        System.out.println(set);
    }
}
