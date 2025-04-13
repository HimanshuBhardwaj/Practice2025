package org.example.jan2025.jan26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.TreeSet;

/*
Name: Himanshu Bhardwaj
Date: 26-01-2025
*/
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            br.readLine();
            String [] str = br.readLine().split(" ");
            TreeSet<Element> elements = new TreeSet<>();

            for (int i=0;i<str.length;i++) {
                elements.add(new Element(i,Integer.parseInt(str[i])));
            }
            pw.append(getMaxSum(elements)+"\n");
        }

        pw.flush();
        pw.close();
    }

    private static long getMaxSum(TreeSet<Element> elements) {
        long prevSum = 0;
        int n = elements.size();

        for (Element e: elements) {
            prevSum+=e.value;
        }

        TreeSet<Long> sums = new TreeSet<>();
        sums.add(prevSum);

        for (int i=0;i<n-1;i++) {
            Element first = elements.first();
            Element last = elements.last();

            if (last.value >= first.value) {
                prevSum = last.value-first.value;
                elements.remove(last);
                last.index=0;
                elements = deleteValues(elements, last, true);
            } else {
                prevSum = first.value - last.value;
                elements.remove(first);
                first.index = 0;
                elements = deleteValues(elements, first,false);
            }

            sums.add(prevSum);

        }

        return sums.last();
    }

    private static TreeSet<Element> deleteValues(TreeSet<Element> sums, Element pValue, boolean b) {
        TreeSet<Element> newSums = new TreeSet<>();
        Element prevE = pValue;
        int order=0;

        for (Element s: b?sums.reversed():sums) {
            Long value = s.value-prevE.value;
            newSums.add(new Element(order,value));
            prevE=s;
            order++;
        }

        return newSums;
    }

}

class Element implements Comparable<Element> {
    int index;
    long value;

    public Element(int index, long value) {
        this.index = index;
        this.value = value;
    }
    @Override
    public int compareTo(Element o) {
        return Integer.compare(this.index, o.index);
    }
}