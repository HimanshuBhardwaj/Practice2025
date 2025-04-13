package org.example.frb27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/*
Name: Himanshu Bhardwaj
Date: 27-02-2025
*/
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
             long n = Long.parseLong(br.readLine());
             if (n>=2) {
                 long ans = 3+ (n/15)+((n-1)/15)+((n-2)/15);
                 pw.append(ans+"\n");
             }
             else if (n==0) {
                  pw.append(1+"\n");
             } else if (n==1) {
                 pw.append(2+"\n");
             }
        }

        pw.flush();
        pw.close();
    }
}
