package org.example.april13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/*
Name: Himanshu Bhardwaj
Date: 13-04-2025
*/
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String [] str = br.readLine().split(" ");
            pw.append(str[0].charAt(0)).append(str[1].charAt(0)).append(str[2].charAt(0)).append("\n");
        }

        pw.flush();
        pw.close();
    }
}
