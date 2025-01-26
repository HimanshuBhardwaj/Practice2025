package org.example.jan2025.jan19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String [] str = br.readLine().split(" ");

            long [] a = new long[n];

            for (int i=0;i<str.length;i++) {
                a[i] = Long.parseLong(str[i]);
            }

            pw.append(isPossible(a)?"YES\n":"NO\n");
        }

        pw.flush();
        pw.close();
    }

    private static boolean isPossible(long[] a) {
        long min[] = new long[a.length];
        min[min.length-1]=Integer.MAX_VALUE;
        long prevMin = Integer.MIN_VALUE;

        for (int i=min.length-2;i>=0;i--) {
            min[i] = Math.min(min[i+1],a[i+1]);
        }

        for (int i=0;i<a.length-1;i++) {
            if (a[i] > a[i+1] || prevMin>min[i]) {
                return false;
            }

            long mn = prevMin==Integer.MIN_VALUE?0:prevMin;
            long diff = Math.abs(a[i]-a[i+1]);
            long maxNum = Math.max(a[i],a[i+1]);


            if (maxNum>mn) {
                if (diff==0) {
                    return false;
                }

                long numSub = (long)Math.ceil((double) (maxNum-mn)/(double)diff);
                long numToSub = numSub*diff;
                a[i]-=numToSub;
                a[i+1]-=numToSub;
            }
        }

        for (int i=1;i<a.length;i++) {
            if (a[i]<a[i-1]) {
                return false;
            }
        }

        return true;
    }
}
