package org.example.frb27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeSet;

/*
Name: Himanshu Bhardwaj
Date: 27-02-2025
*/
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String [] str = br.readLine().split(" ");
            long n = Long.parseLong(str[0]);
            long k = Long.parseLong(str[1]);

            String cellColour = br.readLine();

            str = br.readLine().split(" ");

            long [] penalty = new long[str.length];

            for (int i=0;i<penalty.length;i++) {
                penalty[i] = Long.parseLong(str[i]);
            }

            pw.append(minimumPenalty(n,k,cellColour,penalty)+"\n");
        }

        pw.flush();
        pw.close();
    }

    private static long minimumPenalty(long n, long k, String cellColour, long[] penalty) {
        TreeSet<Segment> segments = getSegments(cellColour,penalty);

        while (k-- > 0 && !segments.isEmpty()) {
            segments.remove(segments.last());
        }
        return (!segments.isEmpty())?segments.last().maxPenalty:0;
    }

    private static TreeSet<Segment> getSegments(String cellColour, long[] penalty) {
        int startB=-1;
        TreeSet<Segment> treeSet = new TreeSet<>();

        for (int i=0;i<cellColour.length();i++) {
            if (cellColour.charAt(i)=='R') {
                if (startB!=-1) {
                    treeSet.add(new Segment(startB,i-1,maxVal(startB,i-1,penalty)));
                    startB=-1;
                }
            } else {
                if (startB==-1) {
                    startB=i;
                }
            }
        }

        if (startB != -1) {
            treeSet.add(new Segment(startB, cellColour.length()-1, maxVal(startB, cellColour.length()-1, penalty)));
        }


        return treeSet;
    }

    private static long maxVal(int s, int e, long[] penalty) {
        long max = penalty[s];
        for (int i=s;i<=e;i++) {
            max = Math.max(max,penalty[i]);
        }
        return max;
    }
}

class Segment implements Comparable<Segment>{
    int s;
    int e;
    long maxPenalty;

    public Segment(int s, int e, long maxPenalty) {
        this.s = s;
        this.e = e;
        this.maxPenalty = maxPenalty;
    }

    @Override
    public int compareTo(Segment o) {
        if ((this.maxPenalty != o.maxPenalty)) {
            return Long.compare(this.maxPenalty, o.maxPenalty);
        }
        return Integer.compare(this.s, o.s);
    }
}