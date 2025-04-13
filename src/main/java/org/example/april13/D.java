package org.example.april13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/*
Name: Himanshu Bhardwaj
Date: 13-04-2025
*/
public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String p = br.readLine();
            String s = br.readLine();

            pw.append(isPossible(p,s)?"YES":"NO").append("\n");
        }

        pw.flush();
        pw.close();
    }

    private static boolean isPossible(String p, String s) {
        ArrayList<String> tp = tokenise(p);
        ArrayList<String> ts = tokenise(s);

        if (tp.size() != ts.size()) {
            return false;
        }

        for (int i=0;i<tp.size();i++) {
            if (!possibleCombination(tp.get(i).length(), ts.get(i).length(),tp.get(i).charAt(0),ts.get(i).charAt(0))) {
                return false;
            }
        }

        return true;
    }


    private static ArrayList<String> tokenise(String p) {
        ArrayList<String> list = new ArrayList<>();

        for (int i=0;i<p.length();) {
            int di=i;
            StringBuilder sb = new StringBuilder();
            while (di<p.length() && p.charAt(di) == p.charAt(i)) {
                sb.append(p.charAt(i));
                di++;
            }
            i=di;
            list.add(sb.toString());
        }

       // System.out.println(p+"\t\t"+list);

        return list;
    }

    private static boolean possibleCombination(int s, int s1, char c, char c1) {
        if (s1>=s && s1<=(2*s) && c==c1) {
            return true;
        }

        return false;
    }
}
