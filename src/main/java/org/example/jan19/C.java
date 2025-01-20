package org.example.jan19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String [] str = br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int k = Integer.parseInt(str[1]);

            int [] a = new int[n+1];

            str = br.readLine().split(" ");

            for (int i=0;i<str.length;i++) {
                a[Integer.parseInt(str[i])]++;
            }

            pw.append(getScore(n,k,a)+"\n");
        }

        pw.flush();
        pw.close();
    }

    private static int getScore(int n, int k, int[] a) {
        int count=0;

        for (int i=1;i<a.length;i++) {
            int num = i;
            int cNum = k-num;

            if (cNum >= a.length || num>=a.length || cNum<0) {
                continue;
            }

            if (num == cNum) {
                count+= a[num]/2;
            } else {
                count+= Math.min(a[num],a[cNum]);
            }
            a[num]=0;
            a[cNum]=0;
        }

        return count;
    }
}
