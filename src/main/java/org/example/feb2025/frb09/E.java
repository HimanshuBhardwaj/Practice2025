package org.example.feb2025.frb09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

/*
Name: Himanshu Bhardwaj
Date: 09-02-2025
*/  
public class E {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String [] str = br.readLine().split(" ");

            int n = Integer.parseInt(str[0]);
            int m = Integer.parseInt(str[1]);

            ArrayList<Array> arrays = new ArrayList<>();

            for (int i=0;i<n;i++) {
                str = br.readLine().split(" ");
                long [] a = new long[str.length];

                for (int j=0;j<a.length;j++) {
                    a[j] = Long.parseLong(str[j]);
                }

                arrays.add(new Array(a,getSum(a),getPrefixSum(a)));
            }

            pw.append(getMaxScore(arrays)+"\n");
        }

        pw.flush();
        pw.close();
    }

    private static long getMaxScore(ArrayList<Array> arrays) {
        Collections.sort(arrays, Collections.reverseOrder());
        long a [] = new long[arrays.size()*arrays.get(0).a.length];

        for (int i=0;i<arrays.size();i++) {
            for (int j=0;j<arrays.get(i).a.length;j++) {
                a[(i*arrays.get(0).a.length)+j] = arrays.get(i).a[j];
            }
        }

        return getPrefixSum(a);
    }

    private static long getPrefixSum(long[] a) {
        long prefixS=0;
        long cps=0;

        for (long x: a) {
            prefixS+=x;
            cps+=prefixS;
        }

        return cps;
    }

    private static long getSum(long[] a) {
        long s=0;

        for (long x: a) {
            s+=x;
        }

        return s;
    }
}

class Array implements Comparable<Array> {
    long [] a;
    long sum;
    long prefixSum;

    public Array(long[] a, long sum, long prefixSum) {
        this.a = a;
        this.sum = sum;
        this.prefixSum = prefixSum;
    }

    @Override
    public int compareTo(Array o) {
        if (this.sum != o.sum) {
            return Long.compare(this.sum, o.sum);
        }

        if (this.prefixSum != o.prefixSum) {
            return Long.compare(this.prefixSum, o.prefixSum);
        }

        for (int i=0;i<a.length;i++) {
            if (Long.compare(this.a[i],o.a[i])!=0) {
                return Long.compare(this.a[i],o.a[i]);
            }
        }
        return 0;
    }
}
