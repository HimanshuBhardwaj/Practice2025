package org.example.jan2025.jan19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String str[] = br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int m = Integer.parseInt(str[1]);

            Person [] person = new Person[n];

            for (int i=0;i<person.length;i++) {
                person[i] = new Person(i+1);
            }

            for (int i=0;i<n;i++) {
                String[] arr = br.readLine().split(" ");

                for (int j=0;j<arr.length;j++) {
                    person[i].number.add(Integer.parseInt(arr[j]));
                }
            }

            pw.append(getOrder(person)+"\n");

        }

        pw.flush();
        pw.close();
    }

    private static String getOrder(Person[] person) {
        for (Person p: person) {
            Collections.sort(p.number);
        }
        Arrays.sort(person);

        int prev=-1;

        for (int j=0;j<person[0].number.size();j++) {
            for (int i = 0; i < person.length; i++) {
                if (prev>person[i].number.get(j)) {
                    return "-1";
                }
                prev=person[i].number.get(j);
            }
        }

        StringBuilder sb = new StringBuilder();

        for (Person p:person) {
            sb.append(p.index+" ");
        }

        return sb.toString();
    }
}


class Person implements Comparable<Person> {
    int index;
    ArrayList<Integer> number;

    public Person(int i) {
        this.index = i;
        number = new ArrayList<>();
    }

    @Override
    public int compareTo(Person o) {
        return Integer.compare(this.number.get(0), o.number.get(0));
    }
}