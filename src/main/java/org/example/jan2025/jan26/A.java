package org.example.jan2025.jan26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/*
Name: Himanshu Bhardwaj
Date: 26-01-2025
*/
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String str = br.readLine();
            int count=0;

            for (char c:str.toCharArray()) {
                count+= c-'0';
            }

            pw.append(count+"\n");
        }



        pw.flush();
        pw.close();
    }
}
