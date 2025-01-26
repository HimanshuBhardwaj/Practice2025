package org.example.jan2025.jan01;

import java.util.ArrayList;
import java.util.Arrays;

public class AllPermutuation {
    public static void main(String[] args) {
        int a[] = {2,2,1,1,1};
        PerMI perMI = new PerMImpl();
        PerMI perMI1 = new PermImplDP();

        System.out.println(perMI.allPerms(a).size());

        for (ArrayList<Integer> aa: perMI.allPerms(a)) {
            System.out.println(aa);
        }

        System.out.println();
        System.out.println(perMI1.allPerms(a).size());

        for (ArrayList<Integer> aa: perMI1.allPerms(a)) {
            System.out.println(aa);
        }
    }
}

class PermImplDP implements PerMI {

    @Override
    public ArrayList<ArrayList<Integer>> allPerms(int[] arr) {
        ArrayList<ArrayList<Integer>> allPerms = new ArrayList<>();
        Arrays.sort(arr);

        allPermsHelper(0,arr.length-1,arr,allPerms);
        return allPerms;
    }

    private void allPermsHelper(int s, int e, int[] arr, ArrayList<ArrayList<Integer>> allPerms) {
        if (s>=e) {
            allPerms.add(getList(arr));
            return;
        }

        for (int i=s;i<=e;i++) {
            if (isLastAppearnce(arr,i)) {
                swap(i,s,arr);
                allPermsHelper(s+1,e,arr,allPerms);
                swap(i,s,arr);
            }
        }
    }

    private boolean isLastAppearnce(int[] arr, int p) {

        for (int i=p+1;i<arr.length;i++) {
            if (arr[i]==arr[p]) {
                return false;
            }
        }

        return true;
    }

    private void swap(int s, int d, int[] arr) {
        int t = arr[s];
        arr[s]=arr[d];
        arr[d]=t;
    }

    private ArrayList<Integer> getList(int[] arr) {
        ArrayList<Integer> list = new ArrayList<>();

        for (int i=0;i<arr.length;i++) {
            list.add(arr[i]);
        }
        return list;
    }
}


class PerMImpl implements PerMI {

    @Override
    public ArrayList<ArrayList<Integer>> allPerms(int[] arr) {
        ArrayList<ArrayList<Integer>> allPerms = new ArrayList<>();

        allPermsHelper(0,arr.length-1,arr,allPerms);
        return allPerms;
    }

    private void allPermsHelper(int s, int e, int[] arr, ArrayList<ArrayList<Integer>> allPerms) {
        if (s > e) {
            allPerms.add(GetList(arr));
        }

        for (int i=s;i<=e;i++) {
            swap(s,i,arr);
            allPermsHelper(s+1,e,arr,allPerms);
            swap(s,i,arr);
        }
    }

    private void swap(int s, int e, int []arr) {
        int temp = arr[s];
        arr[s]=arr[e];
        arr[e]=temp;
    }

    private ArrayList<Integer> GetList(int[] arr) {
        ArrayList<Integer> list = new ArrayList<>();

        for (int i=0;i<arr.length;i++) {
            list.add(arr[i]);
        }

        return list;
    }
}


interface PerMI {
    ArrayList<ArrayList<Integer>> allPerms(int [] arr);
}
