package org.example.frb27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/*
Name: Himanshu Bhardwaj
Date: 27-02-2025
*/
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String [] str = br.readLine().split(" ");

            long n = Long.parseLong(str[0]);
            long x = Long.parseLong(str[1]);
            long k = Long.parseLong(str[2]);

            char [] seq = br.readLine().toCharArray();
            pw.append(numberOfTimeZero(n,x,k,seq)+"\n");
        }

        pw.flush();
        pw.close();
    }

    private static long numberOfTimeZero(long n, long x, long k, char[] seq) {
        int [] arr = new int[seq.length];
        int firstMX=-1;
        int firstZero=-1;
        long cs=0;

        for (int i=0;i<seq.length;i++) {
            if (seq[i]=='L') {
                arr[i]=-1;
            } else {
                arr[i]=1;
            }
            cs+=arr[i];

            if (cs == -1*x && firstMX==-1) {
                firstMX = i;
            }

            if (cs==0 && firstZero==-1) {
                firstZero=i;
            }
        }


        if (firstMX==-1) {
            return 0;
        }

        if (firstZero==-1) {
            return 1;
        }

        return 1+(k-firstMX-1)/(firstZero+1);
    }
}
