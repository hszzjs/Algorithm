package com.company;

import java.util.Comparator;

/**
 * Created by Administrator on 2018/9/29 0029.
 */
public class Insertion {
    public static void sort(Comparable[] a){//按照升序排列
        int N=a.length;
        for(int i=1;i<N;i++){
            for(int j=i;j>0&& less(a[j],a[j-1]);j--){
                exch(a,j,j-1);
            }
        }
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i < hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
        }
        assert isSorted(a, lo, hi);
    }

    public static void sort(Object[] a, int lo, int hi, Comparator comparator) {
        for (int i = lo + 1; i < hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j-1], comparator); j--) {
                exch(a, j, j-1);
            }
        }
        assert isSorted(a, lo, hi, comparator);
    }

    public static boolean less(Comparable v,Comparable w){
        return v.compareTo(w)<0;
    }

    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static boolean less(Object v, Object w, Comparator comparator) {
        return comparator.compare(v, w) < 0;
    }

    public static void exch(Comparable[] a,int i, int j){
        Comparable t=a[i];
        a[i]=a[j];
        a[j]=t;
    }

    public static void show(Comparable[] a){
        for(int i=0;i<a.length;i++){
            System.out.print(a[i]);
        }
        System.out.println();
    }

    public static boolean isSorted(Comparable[] a){
        for(int i=0;i<a.length;i++){
            if(less(a[i+1],a[i])) return false;
        }
        return true;
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i < hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    private static boolean isSorted(Object[] a, int lo, int hi, Comparator comparator) {
        for (int i = lo + 1; i < hi; i++)
            if (less(a[i], a[i-1], comparator)) return false;
        return true;
    }


    public static void sort(String[] a, int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(a[j], a[j-1], d); j--)
                exch(a, j, j-1);
    }

    // exchange a[i] and a[j]
    private static void exch(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // is v less than w, starting at character d
    private static boolean less(String v, String w, int d) {
        // assert v.substring(0, d).equals(w.substring(0, d));
        for (int i = d; i < Math.min(v.length(), w.length()); i++) {
            if (v.charAt(i) < w.charAt(i)) return true;
            if (v.charAt(i) > w.charAt(i)) return false;
        }
        return v.length() < w.length();
    }

}
