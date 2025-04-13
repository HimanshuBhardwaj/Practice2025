package org.example.jan2025.jan26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/*
Name: Himanshu Bhardwaj
Date: 26-01-2025
*/
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            br.readLine();
            String [] str = br.readLine().split(" ");
            long [] a = new long[str.length];

            for (int i=0;i<str.length;i++) {
                a[i] = Long.parseLong(str[i]);
            }
            pw.append(isPossibleToFind(a)?"YES":"NO").append("\n");
        }

        pw.flush();
        pw.close();
    }

    private static boolean isPossibleToFind(long[] a) {
        int posMinElement = minEPos(a);
        return firstGoRight(a,posMinElement);
    }

    private static boolean firstGoRight(long[] a, int posMinElement) {
        long [] cloneA = a.clone();
        int count=1;
        return false;
    }

    private static int minEPos(long[] a) {
        return 0;
    }
}
