package org.example.april5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

/*
Name: Himanshu Bhardwaj
Date: 23-03-2025
*/
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            if (n%2 == 0) {
                pw.append("-1\n");
            } else {
                pw.append(n+" ");

                for (int i=1;i<n;i++) {
                    pw.append(i+" ");
                }
                pw.append("\n");
            }
        }

        pw.flush();
        pw.close();
    }


    void printPerm(int n) {
        ArrayList<Integer> list = new ArrayList<>();

        for (int i=1;i<=n;i++) {
            list.add(i);
        }

        printValidList(list,0);
    }

    private void printValidList(ArrayList<Integer> list, int index) {
        if (index==list.size()) {
            //System.out.println(list);
            if (valid(list,list.size())) {
                System.out.println(index+"\t"+list);
            }
            return;
        }

        for (int j=index;j<list.size();j++) {
            Collections.swap(list,j,index);
            printValidList(list,index+1);
        }
    }

    private boolean valid(ArrayList<Integer> list, int n) {
        for (int i=1;i<n;i++) {
            if (Math.max(list.get(i),list.get(i-1))%(i+1) != i) {
                return false;
            }
        }
        return true;
    }
}
