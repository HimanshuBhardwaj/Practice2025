package org.example.april13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/*
Name: Himanshu Bhardwaj
Date: 13-04-2025
*/
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String [] str =br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int m = Integer.parseInt(str[1]);
            int l = Integer.parseInt(str[2]);
            int r = Integer.parseInt(str[3]);

            int s=0;
            int e=0;

            if (m<=r) {
                e+=m;
                m=0;
            } else {
                e+=r;
                m-=r;
            }

            if (m>0) {
                s-=m;
            }

            pw.append(s+" "+e+"\n");
        }

        pw.flush();
        pw.close();
    }
}
