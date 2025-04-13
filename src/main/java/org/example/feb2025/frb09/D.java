package org.example.feb2025.frb09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/*
Name: Himanshu Bhardwaj
Date: 09-02-2025
*/
public class D {
    public static void main(String[] args) throws IOException {
        int [] a = {4,1,2,1,2,2,2,2,3,2,1,2};
        System.out.println(prefixSum(a));
    }


    static long prefixSum(int [] a) {
        long cs=0;
        long ps=0;

        for (int x:a) {
            ps+=x;
            cs+=ps;
        }

        return cs;
    }
}
