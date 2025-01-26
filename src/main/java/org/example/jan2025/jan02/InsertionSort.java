package org.example.jan2025.jan02;

public class InsertionSort {
    public static void main(String[] args) {
        int [] arr = {-1,0,-2,-3};
        Sort sort = new BubbleSort();
        sort.sort(arr);

        for (int x:arr) {
            System.out.print(x+", ");
        }
    }
}

//17:22 -> 17:26
class BubbleSort implements Sort {

    @Override
    public void sort(int[] arr) {
        for (int i=arr.length-1;i>=0;i--) {
            for (int j=1;j<=i;j++) {
                if (arr[j]<arr[j-1]) {
                    swap(j,j-1,arr);
                }
            }
        }
    }

    private void swap(int s, int e, int[] arr) {
        int t = arr[s];
        arr[s]=arr[e];
        arr[e]=t;
    }
}

class InsertionSortImpl implements Sort {

    @Override
    public void sort(int[] arr) {
        for (int i=1;i<arr.length;i++) {
            for (int j=i-1;j>=0;j--) {
                if (arr[j+1]<arr[j]) {
                    swap(j+1,j,arr);
                }
            }
        }
    }

    private void swap(int s, int e, int[] arr) {
        int t = arr[s];
        arr[s]=arr[e];
        arr[e]=t;
    }
}

interface Sort {
    void sort(int [] arr);
}