package com.company;

/**
 * Created by Administrator on 2018/10/2 0002.
 */
public class Quick3way {
    public static void sort(Comparable[] a){
        StdRandom.shuffle(a);
        sort(a,0,a.length-1);
    }

    private static void sort(Comparable[] a,int lo,int hi){
        int lt=lo,i=lo+1,gt=hi;
        Comparable v=a[lo];
        while (i<=gt){
            int cmp=a[i].compareTo(v);
            if(cmp<0) exch(a,i++,lt++);
            else if(cmp>0) exch(a,i,gt--);
            else i++;
        }
        sort(a,lo,lt-1);
        sort(a,gt+1,hi);
    }

    public static void exch(Comparable[] a,int i,int j){
        Comparable t=a[i];
        a[i]=a[j];
        a[j]=t;
    }
}
