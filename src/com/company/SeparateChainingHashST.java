package com.company;

/**
 * Created by Administrator on 2018/10/9 0009.
 */
public class SeparateChainingHashST<Key extends Comparable<Key>,Value> {
    //SequetialSearchST
    private int N;//键值对总数
    private int M;//散列表的大小
    private SequentialSearchST<Key, Value>[] st;//存放链表对象的数组
    public SeparateChainingHashST() {//默认的构造函数会使用997条链表
        this(997);
    }
    public SeparateChainingHashST(int M) {
        //创建M条链表
        this.M = M;
        //创造一个(SequentialSearchST<Key, Value>[])类型的,长度为M的数组
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for(int i = 0; i < M; i++) {
            //为每一个数组元素申请一个空间
            st[i] = new SequentialSearchST();
        }
    }
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }
    public Value get(Key key) {
        return (Value)st[hash(key)].get(key);
    }
    public void put(Key key, Value val) {
        st[hash(key)].put(key, val);
    }
    public void delete(Key key) {
        st[hash(key)].delete(key);
    }
    public Iterable<Key> keys(){
        Queue<Key> queue = new Queue<Key>();
        for(int i = 0; i < M; i++) {
            System.out.println("第" + i +"个元素的链表");
            for(Key key : st[i].keys()) {
                queue.enqueue(key);
                System.out.print(key + " " + get(key) + " ,");
            }
            System.out.println();
        }
        return queue;
    }
    public static void main(String[] args) {
        SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<String, Integer>(5);
        for (int i = 0; i < 13; i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
        st.delete("M");
        StdOut.println("*************************************");
        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }

}
