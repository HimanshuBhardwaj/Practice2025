package org.example.april13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.TreeSet;

/*
Name: Himanshu Bhardwaj
Date: 13-04-2025
*/
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            int [] [] arr = new int[n][n];

            for (int i=0;i<n;i++) {
                String [] str = br.readLine().split(" ");

                for (int j=0;j<n;j++) {
                    arr[i][j] = Integer.parseInt(str[j]);
                }
            }

            pw.append(getPermutution(n, arr)+"\n");
        }

        pw.flush();
        pw.close();
    }

    private static String getPermutution(int n, int[][] arr) {
        int [] p = new int[2*n];
        p[0]=getFirstElemet(arr);

        for (int i=1;i<p.length;i++) {
            int pos=i+1;
            int cc = getC(pos,n);
            int r = pos-cc;

            //System.out.println(pos+"\t"+r+"\t"+cc);
            p[pos-1] = arr[r-1][cc-1];
        }


        StringBuilder sb = new StringBuilder();

        for (int x:p) {
         sb.append(x+" ");
        }

        return sb.toString();
    }

    private static int getC(int pos, int n) {

        for (int i=1;i<=n;i++) {
            int c=i;
            int r=pos-i;

            if (1<= c && n>=c && 1<=r && n>=r) {
                return c;
            }

        }
        return -1;
    }

    private static int getColumn(int pos, int n) {
        return ((pos-1)%(n))+1;
    }

    private static int getFirstElemet(int[][] arr) {
        TreeSet<Integer> set = new TreeSet<>();

        for (int i=0;i<arr.length;i++) {
            for (int j=0;j<arr[0].length;j++) {
                set.add(arr[i][j]);
            }
        }
        int e=1;

        for (int x:set) {
            if (e != x) {
                return e;
            } else {
                e++;
            }
        }

        return e;
    }
}
