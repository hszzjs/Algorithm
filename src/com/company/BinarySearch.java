package com.company;

/**
 * Created by Administrator on 2018/9/21 0021.
 */
public class BinarySearch {
    public static int rank(int key, int[] a){
        return rank(key,a,0,a.length-1);
    }

    public static int rank(int key, int[] a,int lo, int hi){
        if(lo>hi) return -1;
        int mid = (lo + hi) / 2;
        if (a[mid] > key) {
            return rank(key, a, lo, mid - 1);
        } else if (a[mid] < key) {
            return rank(key, a, mid + 1, hi);
        }else return mid;
    }

    public static void main(String[] args){
        int key=2;
        int[] a=new int[]{1,2,3,6,8,9,10};
        System.out.println(rank(key,a));
        System.out.println((0+15)/2);
        System.out.println(2.0*Math.E-6*100000000.1);
        System.out.println(true && false || true && true);

    }
}
