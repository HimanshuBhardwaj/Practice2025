package org.example.jan2025.jan22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/*
Name: Himanshu Bhardwaj
Date: 22-01-2025
*/
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String [] str = br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int l = Integer.parseInt(str[1])-1;
            int r = Integer.parseInt(str[2])-1;

            long a[] = new long[n];
            str = br.readLine().split(" ");

            for (int i=0;i<n;i++) {
                a[i] = Long.parseLong(str[i]);
            }

            pw.append(getMinimumSum(a,l,r)+"\n");
        }

        pw.flush();
        pw.close();
    }

    private static long getMinimumSum(long[] a, int l, int r) {
        long arraySum = getSum(a,0,a.length-1);

        if (l==0 && r==a.length-1) {
            return arraySum;
        } else if (r==a.length-1) {
            return prefixMin(a,l,r);
        } else if (l==0) {
            return suffixMin(a,l,r);
        } else {
            return Math.min(prefixMin(a,l,r), suffixMin(a,l,r));
        }
    }

    static long prefixMin(long a[], int l, int r) {
        ArrayList<Long> prefixA = new ArrayList<>();
        ArrayList<Long> mainA = new ArrayList<>();

        for (int i=0;i<l;i++) {
            prefixA.add(a[i]);
        }

        for (int i=l;i<=r;i++) {
            mainA.add(a[i]);
        }

        Collections.sort(prefixA);
        Collections.sort(mainA, Collections.reverseOrder());
        return getMinimumSumHelper(prefixA,mainA);
    }

    static long suffixMin(long [] a, int l, int r) {
        ArrayList<Long> prefixA = new ArrayList<>();
        ArrayList<Long> mainA = new ArrayList<>();

        for (int i=r+1;i<a.length;i++) {
            prefixA.add(a[i]);
        }

        for (int i=l;i<=r;i++) {
            mainA.add(a[i]);
        }

        Collections.sort(prefixA);
        Collections.sort(mainA, Collections.reverseOrder());
        return getMinimumSumHelper(prefixA,mainA);
    }

    private static long getMinimumSumHelper(ArrayList<Long> prefixA, ArrayList<Long> mainA) {
        long maxDiff=0;
        long diff = 0;

        for (int i=0;i<prefixA.size() && i<mainA.size();i++) {
            diff += mainA.get(i)-prefixA.get(i);
            maxDiff = Math.max(maxDiff,diff);
        }

        long sum=0;

        for (long x:mainA) {
            sum+=x;
        }

        return sum-maxDiff;
    }

    private static long getSum(long[] a, int l, int r) {
        long sum=0;

        for (int i=l;i<=r;i++) {
            sum+=a[i];
        }

        return sum;
    }
}
