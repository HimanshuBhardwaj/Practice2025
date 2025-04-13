package org.example.april13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.TreeSet;

/*
Name: Himanshu Bhardwaj
Date: 13-04-2025
*/
public class E {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String [] str = br.readLine().split(" ");
            int [] arr = new int[n];
            TreeSet<Element> elements = new TreeSet<>();

            for (int i=0;i<arr.length;i++) {
                elements.add(new Element(Integer.parseInt(str[i]),i));
                arr[i]=Integer.parseInt(str[i]);
            }

            pw.append(maximumXOR(arr,elements,n)+"\n");

        }

        pw.flush();
        pw.close();
    }

    private static long maximumXOR(int[] arr, TreeSet<Element> elements, int n) {

        return 0;
    }
}


class Element implements Comparable<Element> {
    int value;
    int pos;

    public Element(int value, int pos) {
        this.value = value;
        this.pos = pos;
    }

    @Override
    public int compareTo(Element o) {
        if (this.value != o.value) {
            return Integer.compare(this.value, o.value);
        } else {
            return Integer.compare(this.pos, o.pos);
        }
    }
}
