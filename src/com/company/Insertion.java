package com.company;

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

    public static boolean less(Comparable v,Comparable w){
        return v.compareTo(w)<0;
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
}
