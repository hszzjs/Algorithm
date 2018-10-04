package com.company;

/**
 * Created by Administrator on 2018/10/4 0004.
 */
public class BinarySearchST<Key extends Comparable<Key>,Value> {
    private Key[] keys;
    private Value[] values;
    private int N;
    public BinarySearchST(int capacity){
        keys=(Key[]) new Comparable[capacity];
        values=(Value[]) new Object[capacity];
    }

    public int size(){
        return N;
    }

    public boolean isEmpty(){
        return size()==0;
    }

    public Value get(Key key){
        if(isEmpty()) return null;
        int i=rank(key);
        if(i<N && keys[i].compareTo(key)==0) return values[i];
        else return null;
    }

    public void put(Key key, Value value){
        int i=rank(key);
        if(i<N && keys[i].compareTo(key)==0){
            values[i]=value;return;
        }
        for (int j=N;j>i;j--){
            keys[j]=keys[j-1];values[j]=values[j-1];
        }
        keys[i]=key;
        values[i]=value;
        N++;
    }

    public int rank(Key key){
        int lo =0,hi=N-1;
        while(lo<=hi){
            int mid=lo+(hi-lo)/2;
            int cmp=key.compareTo(keys[mid]);
            if(cmp<0) hi=mid-1;
            else if(cmp>0) lo=mid+1;
            else return mid;
        }
        return lo;
    }

    public Key min(){
        return keys[0];
    }

    public Key max(){
        return keys[N-1];
    }

    public Key select(int k){
        return keys[k];
    }

    public Key ceiling(Key key){
        int i=rank(key);
        return keys[i];
    }

    public boolean contains(Key key){
        return get(key)!=null;
    }

    public Iterable<Key> keys(Key lo,Key hi){
        Queue<Key> q=new Queue<Key>();
        for(int i=rank(lo);i<rank(hi);i++){
            q.enqueue(keys[i]);
        }
        if(contains(hi))
            q.enqueue(keys[rank(hi)]);
        return q;
    }
}
