package org.example.feb2025.frb09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeSet;

/*
Name: Himanshu Bhardwaj
Date: 09-02-2025
*/
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String [] str = br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int m = Integer.parseInt(str[1]);

            str = br.readLine().split(" ");

            ArrayList<Long> a = new ArrayList<>();

            for (String an: str) {
                a.add(Long.parseLong(an));
            }

            str = br.readLine().split(" ");

            ArrayList<Long> b = new ArrayList<>();

            for (String an: str) {
                b.add(Long.parseLong(an));
            }

            pw.append((isPossible(a,b,n,m)?"YES":"NO")+"\n");

        }

        pw.flush();
        pw.close();
    }

    private static boolean isPossible(ArrayList<Long> a, ArrayList<Long> b, int n, int m) {
        TreeSet<Long> bSet = new TreeSet<>(b);
        long prevE = 1;


        a.set(0, Math.min(a.get(0),bSet.first()-a.get(0)));
        prevE = a.get(0);

        for (long x: a) {
            if (x==prevE) {
                continue;
            }

            Long bSetE = bSet.ceiling(x+prevE);

            if (bSetE == null) {
                if (x < prevE) {
                    return false;
                }

                prevE = x;
                continue;
            }


            if (Math.min(bSetE-x,x) >= prevE) {
                prevE = Math.min(bSetE-x,x);
            } else {
                if (Math.max(bSetE-x,x) >= prevE) {
                    prevE = Math.max(bSetE - x, x);
                } else {
                    return false;
                }
            }
        }


        return true;
    }
}
