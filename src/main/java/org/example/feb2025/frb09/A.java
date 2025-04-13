package org.example.feb2025.frb09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/*
Name: Himanshu Bhardwaj
Date: 09-02-2025
*/
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String str = br.readLine();

            String newStr = str.substring(0,str.length()-2)+"i\n";
            pw.append(newStr);

        }

        pw.flush();
        pw.close();
    }
}
