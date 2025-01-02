package org.example.jan02;

public class MergeSortPOC {
    public static void main(String[] args) {
        int [] arr = {1};
        MergeSortI mergeSortI = new MergeSort();
        mergeSortI.sort(arr);

        for (int x:arr) {
            System.out.print(x + ", ");
        }
        System.out.println();
    }
}

class MergeSort implements MergeSortI {

    @Override
    public void sort(int[] arr) {
        int [] na = sortHelper(0,arr.length-1,arr);

        for (int i=0;i<arr.length;i++) {
            arr[i] = na[i];
        }
    }

    private int[] sortHelper(int s, int e, int[] arr) {
        if (s<0 || e<0 || s>e) {
            return null;
        }

        if (s==e) {
            int [] na = new int[1];
            na[0] = arr[s];
            return na;
        }

        if (s==e-1) {
            int [] na = new int[2];
            na[0]=Math.min(arr[s],arr[e]);
            na[1]=Math.max(arr[s],arr[e]);
            return na;
        }

        int mid = (s+e)/2;
        int [] left = sortHelper(s,mid,arr);
        int [] right = sortHelper(mid,e,arr);

        return merge(left,right);
    }

    private int[] merge(int[] left, int[] right) {
        if (left==null && right==null) {
            return null;
        }
        if (left==null) {
            return right;
        }
        if (right==null) {
            return left;
        }

        int pl=0;
        int pr=0;
        int pos=0;
        int[] na = new int[left.length+right.length];

        while (pl<left.length && pr<right.length) {
            if (left[pl]<right[pr]) {
                na[pos++]=left[pl++];
            } else {
                na[pos++]=right[pr++];
            }
        }

        while (pl < left.length) {
            na[pos++] = left[pl++];
        }

        while (pr < right.length) {
            na[pos++] = right[pr++];
        }

        return na;
    }
}

// 16:42 - 16:52
interface MergeSortI {
    void sort(int []arr);
}