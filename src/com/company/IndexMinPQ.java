package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Administrator on 2018/10/3 0003.
 */
public class IndexMinPQ<Key extends Comparable<Key>> implements Iterable<Integer> {
    private int maxN;
    private int N;
    private int[] pq;
    private int[] qp;
    private Key[] keys;

    public IndexMinPQ(int capacity){
        if(capacity<=0){
            throw new IllegalArgumentException();
        }
        maxN=capacity;
        pq=new int[capacity+1];
        qp=new int[capacity+1];
        keys=(Key[]) new Comparable[capacity+1];
        for(int i=0;i<=maxN;i++){
            qp[i]=-1;
        }
    }

    public boolean isEmpty(){
        return N==0;
    }

    public int size(){
        return N;
    }

    public boolean contains(int i){
        return qp[i]!=-1;
    }

    public int minIndex(){
        if(isEmpty())
            throw new NoSuchElementException("IndexMinPQ underflow");
        return pq[1];
    }

    public Key min(){
        if(isEmpty())
            throw new NoSuchElementException("IndexMinPQ underflow");
        return keys[pq[1]];
    }

    public Key keyOf(int i){
        if(!contains(i))
            throw new NoSuchElementException("Index has not contains index i");
        return keys[i];
    }

    public void insert(int i,Key key){
        if(i<0 || i>=maxN)
            throw new IllegalArgumentException("index i out of boundary");
        if(contains(i))
            throw new IllegalArgumentException("index i has allocated");
        N++;
        qp[i]=N;
        pq[N]=i;
        keys[i]=key;
        adjustUp(N);
    }

    public int delMin(){
        if(isEmpty())
            throw new NoSuchElementException("IndexMinPQ underflow");
        int min=minIndex();
        delete(min);
        return min;
    }

    public void delete(int i){
        if(!contains(i))
            throw new NoSuchElementException("IndexMinPQ has not contains index i");
        int pqi=qp[i];
        exch(pqi,N--);
        adjustUp(pqi);
        adjustDown(pqi);
        qp[i]=-1;
        keys[i]=null;
        pq[N+1]=-1;
    }

    public void change(int i,Key key){
        if(!contains(i))
            throw new NoSuchElementException("IndexMinPQ hsz not contains index i");
        if(keys[i].compareTo(key)==0)
            throw new IllegalArgumentException("argument key equal to the original value");
        if(key.compareTo(keys[i])==0)
            increaseKey(i,key);
        else
            decreaseKey(i,key);

    }

    public void decreaseKey(int i,Key key){
        if(!contains(i))
            throw new NoSuchElementException("IndexMinPQ has not contains index i");
        if(key.compareTo(keys[i])>0)
            throw new IllegalArgumentException("argument key more than the original value");
        keys[i]=key;
        int pqi=qp[i];
        adjustUp(pqi);
        adjustDown(pqi);
    }

    public void increaseKey(int i,Key key){
        if(!contains(i))
            throw new NoSuchElementException("IndexMinPQ has not contains index i");
        if(key.compareTo(keys[i])<0)
            throw new IllegalArgumentException("argument key more than the original value");
        keys[i]=key;
        int pqi=qp[i];
        adjustUp(pqi);
        adjustDown(pqi);
    }

    private void exch(int i,int j){
        int t=pq[i];
        pq[i]=pq[j];
        pq[j]=t;
        int qpi=pq[i];
        int qpj=pq[j];
        qp[qpi]=i;
        qp[qpj]=j;
    }

    private boolean less(int i, int j){
        int ki=pq[i];
        int kj=pq[j];
        return keys[ki].compareTo(keys[kj])<0;
    }

    private void adjustUp(int i){
        while(2*i<=N){
            int k=2*i;
            while (k<N&& less(k+1,k))
                k++;
            exch(k,i);
            i=k;
        }
    }

    private void adjustDown(int i){
        while (i>1){
            int p=i/2;
            if(less(p,i))
                break;
            exch(p,i);
            i=p;
        }
    }

    public Iterator<Integer> iterator(){
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Integer>{
        IndexMinPQ<Key> copy;

        public HeapIterator(){
            copy=new IndexMinPQ<Key>(maxN);
            for(int i=1;i<=N;i++){
                int ki=pq[i];
                Key key=keys[ki];
                copy.insert(ki,key);
            }
        }

        public boolean hasNext(){
            return !copy.isEmpty();
        }

        public Integer next(){
            if(!hasNext())
                throw new NoSuchElementException("IndexMinPQ underflow");
            return copy.delMin();
        }

        public void remove(){
            throw new UnsupportedOperationException("unsupported remove operation");
        }
    }
}
