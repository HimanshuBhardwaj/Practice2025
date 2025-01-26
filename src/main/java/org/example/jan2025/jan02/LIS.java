package org.example.jan2025.jan02;

import java.util.LinkedList;

public class LIS {
    public static void main(String[] args) {
        int [] arr = {111,11,21,11,1,1};
        LongestIncreasingSubsI longestIncreasingSubsI = new LongestIncreasingSubBFImpl();
        LongestIncreasingSubsI longestIncreasingSubsI1 = new LongestINSTopDown();

        System.out.println(longestIncreasingSubsI.LIS(arr));
        System.out.println(longestIncreasingSubsI1.LIS(arr));

        int [] arr1 = {0,1,0,1,10,1};
        System.out.println(longestIncreasingSubsI.LIS(arr1));
        System.out.println(longestIncreasingSubsI.LIS(arr1));

        int [] arr2 = {0};
        System.out.println(longestIncreasingSubsI.LIS(arr2));
        System.out.println(longestIncreasingSubsI1.LIS(arr2));
    }
}

//17:10
class LongestINSTopDown implements LongestIncreasingSubsI {

    @Override
    public int LIS(int[] arr) {
        int lis [] = new int[arr.length];
        for (int i=0;i<lis.length;i++) {
            lis[i]=1;
        }

        return LISHelper(0,arr,lis);
    }

    private int LISHelper(int index, int[] arr, int[] lis) {
        if (index==lis.length) {
            int maz = lis[0];
            for (int x:lis) {
                maz = Math.max(maz,x);
            }
            return maz;
        }

        for (int i=index-1;i>=0;i--) {
            if (arr[i] <= arr[index]) {
                lis[index] = Math.max(lis[index],1+lis[i]);
            }
        }

        return LISHelper(index+1,arr,lis);
    }
}

// 16:54
class LongestIncreasingSubBFImpl implements LongestIncreasingSubsI {

    @Override
    public int LIS(int[] arr) {
        LinkedList<Integer> list = new LinkedList<>();
        return LISHelper(0,arr,list);
    }

    private int LISHelper(int i, int[] arr, LinkedList<Integer> list) {
        if (i==arr.length) {
            if (increasing(list)) {
                return list.size();
            }
            return 0;
        }

        int max=0;
        list.addLast(arr[i]);
        max = Math.max(max,LISHelper(i+1,arr,list));
        list.removeLast();
        max = Math.max(max,LISHelper(i+1,arr,list));

        return max;
    }

    private boolean increasing(LinkedList<Integer> list) {
        if (list.size() <= 1) {
            return true;
        }

        int prev=-1;

        for (int x:list) {
            if (prev==-1) {
                prev=x;
            } else {
                if (prev > x) {
                    return false;
                } else {
                    prev=x;
                }
            }
        }

        return true;
    }
}

interface LongestIncreasingSubsI {
    int LIS(int []arr);
}