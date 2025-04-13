package org.example.april5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.*;

/*
Name: Himanshu Bhardwaj
Date: 23-03-2025
*/
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            ArrayList<Long> list = new ArrayList<>();

            String [] str = br.readLine().split(" ");

            for (String s: str) {
                list.add(Long.parseLong(s));
            }

            System.out.println(new B().isPossible(list)?"Yes":"No");
        }

        pw.flush();
        pw.close();
    }

    boolean isPossible(ArrayList<Long> list) {
        Collections.sort(list);

        if (list.get(0)==list.get(1)) {
            return true;
        }

        ArrayList<Long> divisor = new ArrayList<>();

        for (int i=1;i<list.size();i++) {
            if (list.get(i)%list.get(0)==0) {
                divisor.add(list.get(i)/list.get(0));
            }
        }

        return containsCoprime(divisor);
    }

    private boolean containsCoprime(ArrayList<Long> divisor) {
        for (int i=0;i<divisor.size();i++) {
            for (int j=i+1;j<divisor.size();j++) {
                if (gcd(divisor.get(i),divisor.get(j))==1) {
                    return true;
                }
            }
        }

        return false;
    }

    long gcd(long a, long b) {
        if (a>b) {
            return gcd(b,a);
        }

        if (a==0) {
            return b;
        }

        if (a==1) {
            return 1;
        }

        return gcd(b%a,a);
    }
}
