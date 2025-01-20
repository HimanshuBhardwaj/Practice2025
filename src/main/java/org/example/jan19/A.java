package org.example.jan19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String [] str = br.readLine().split(" ");
            int [] a = new int[str.length+1];
            a[0]=Integer.parseInt(str[0]);
            a[1]=Integer.parseInt(str[1]);
            a[3]=Integer.parseInt(str[2]);
            a[4]=Integer.parseInt(str[3]);

            pw.append(maxFibonacci(a)+"\n");
        }

        pw.flush();
        pw.close();
    }

    static int maxFibonacci(int [] a) {
        int cf=0;

        for (int i=-300;i<=300;i++) {
            a[2]=i;
            cf = Math.max(fibonacci(a),cf);
        }

        return cf;
    }

    static int fibonacci(int a[]) {
        int count=0;

        for (int i=0;i+2<a.length;i++) {
            if (a[i+2]==a[i+1]+a[i]) {
                count++;
            }
        }

        return count;
    }
}
