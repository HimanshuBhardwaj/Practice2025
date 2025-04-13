package org.example.march.march15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.TreeMap;

/*
Name: Himanshu Bhardwaj
Date: 15-03-2025
*/
public class B {
    static  long[][] maxValueM;
    static long [][] minValueM;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            maxValueM = new long[100][100];
            minValueM = new long[100][100];

            for (int i=0;i<100;i++) {
                for (int j=0;j<100;j++) {
                    maxValueM[i][j]=-1;
                    minValueM[i][j]=-1;
                }
            }


            String [] str = br.readLine().split(" ");
            long x = Long.parseLong(str[0]);
            int n = Integer.parseInt(str[1]);
            int m = Integer.parseInt(str[2]);

            pw.append((maxValue(x,n+m,0)==0?0:maxValue(x, n, m))+" "+((maxValue(x,n+m,0)==0)?0:minValue(x, n, m))+"\n");
        }

        pw.flush();
        pw.close();
    }

    private static long maxValue(long x, long n, long m) {
        if (m==0) {
            for (int i=0;i<n && x>=1;i++) {
                x = x/2 + (x%2);
            }
            return x;
        }

        if (n==0) {
            for (int i=0;i<m && x!=0;i++) {
                x = x/2;
            }
            return x;
        }

        if (maxValueM[(int)n][(int)m]!=-1) {
            return maxValueM[(int)n][(int)m];
        }

        long a = maxValue(x/2+(x%2),n-1,m);
        long b = maxValue(x/2,n,m-1);

        maxValueM[(int)n][(int)m] =
                Math.max(a,b);

        return maxValueM[(int)n][(int)m];
    }

    private static long minValue(long x, int n, int m) {
        if (m==0) {
            for (int i=0;i< n&& x!=0;i++) {
                x = x/2;
            }
            return x;
        }

        if (n==0) {
            for (int i=0;i<m && x!=0;i++) {
                x = x/2;
            }
            return x;
        }

        if (minValueM[n][m] != -1) {
            return minValueM[n][m];
        }

        long a = minValue(x/2+(x%2),n-1,m);
        long b = minValue(x/2,n,m-1);

        minValueM[n][m] = Math.min(a,b);

        return minValueM[n][m];
    }
}


