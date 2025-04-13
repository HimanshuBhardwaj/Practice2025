package org.example.march.march15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/*
Name: Himanshu Bhardwaj
Date: 15-03-2025
*/
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String [] str = br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int m = Integer.parseInt(str[1]);

            int [][] arr = new int[n][m];

            for (int i=0;i<n;i++) {
                String row = br.readLine();
                for (int j=0;j<row.length();j++) {
                    arr[i][j] = row.charAt(j)-'0';
                }
            }

            pw.append(minRows(arr)+"\n");
        }

        pw.flush();
        pw.close();
    }

    private static int minRows(int[][] arr) {
        int r=0;
        int c=0;

        int row[] = new int[arr.length];
        int column [] = new int[arr[0].length];


        for (int i=0;i<arr.length;i++) {
            for (int j=0;j<arr[0].length;j++) {
                if (arr[i][j]==1) {
                    row[i]++;
                    column[j]++;
                }
            }
        }

        for (int x:row) {
            r+=(x&1);
        }

        for (int x:column) {
            c+=(x&1);
        }

        return Math.max(c,r);
    }
}
