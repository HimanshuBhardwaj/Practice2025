package org.example.feb2025.frb09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.TreeSet;

/*
Name: Himanshu Bhardwaj
Date: 09-02-2025
*/
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String str = br.readLine();
            pw.append(minimumString(str)+"\n");
        }

        pw.flush();
        pw.close();
    }

    private static int minimumString(String str) {

        char prevC = '1';
        for (char c: str.toCharArray()) {
            if (c==prevC) {
                return 1;
            }
            prevC = c;
        }

        return str.length();
    }
}
