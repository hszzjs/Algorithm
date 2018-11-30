package com.company;

/**
 * Created by Administrator on 2018/10/9 0009.
 */

import java.util.NoSuchElementException;

/**
 *******************************************************************
 * public class ST<key,value> 无序符号表API
 *******************************************************************
 * ST() 创建一张符号表
 * void put(Key key,Value val) 将键值对存入表中（若值为空则将键key从表中删除）
 * Value get(Key key) 获取键key对应的值（若键key不存在则返回null）
 * void delete(Key key) 从表中删去键key（及其对应的值）
 * boolean contains(Key key) 键key在表中是否有对应的值
 * boolean isEmpty() 表是否为空
 * int size() 表中的键值对数量
 * Iterable<Key> keys() 表中的所有键的集合
 *******************************************************************
 */
public class SequentialSearchST<Key extends Comparable<Key>, Value>{

    private Node<Key, Value> first;//链表头结点
    private int N; //链表中表中的键值对数量

    private static class Node<Key, Value> {
        //链表结点定义
        public Key key;
        public Value value;
        public Node<Key, Value> next;

        public Node(Key k, Value val, Node<Key, Value> next) {
            key = k;
            value = val;
            this.next = next;
        }
    }

    /**
     * 创建一张符号表
     */
    public SequentialSearchST() {
        first = null;
        N = 0;
    }

    /**
     * 将键值对存入表中（若值为空则将键key从表中删除）
     * @param key 待更新键
     * @param val 待更新值
     */
    public void put(Key key, Value val) {
        if (key == null)
            throw new IllegalArgumentException();
        //若值为空则将键key从表中删除
        if(val == null) {
            delete(key);
            return;
        }
        //key存在，更新键值对
        for (Node<Key, Value> p = first; p != null; p = p.next) {
            if (p.key.equals(key)) {
                p.value = val;
                return;
            }
        }
        //采用头插法，插入键值对
        Node<Key, Value> node = new Node<Key, Value>(key, val, first);
        first = node;
        N++;
    }

    /**
     * 获取键key对应的值（若键key不存在则返回null）
     * @param key 键
     * @return 键key对应的值（若键key不存在则返回null）
     */
    public Value get(Key key) {
        if (key == null)
            throw new IllegalArgumentException();

        for (Node<Key, Value> p = first ; p != null; p = p.next) {
            if (p.key.equals(key))
                return p.value;
        }
        return null;
    }

    /**
     * 从表中删去键key（及其对应的值）
     * @param key 待删除的键
     */
    public void delete(Key key) {
        if(key == null)
            throw new IllegalArgumentException();
        if(first.key.equals(key)) {
            //待删除键值对为头结点
            N--;
            first = first.next;
            return;
        }
        //待删除键值对非头结点
        for (Node<Key, Value> p = first; p.next != null; p = p.next) {
            Node<Key, Value> pnext = p.next;
            if (pnext != null && pnext.equals(key)) {
                N--;
                p.next = pnext.next;
                pnext = null;
                return;
            }
        }
        //链表中不存在该键值对
        throw new NoSuchElementException();
    }

    /**
     * 键key在表中是否有对应的值
     * @param key 键
     * @return 键key在表中有对应的值,返回true，否则，返回false
     */
    public boolean contains(Key key) {
        if (key == null)
            throw new IllegalArgumentException();
        for (Node<Key, Value> p = first; p != null; p = p.next) {
            if (p.key.equals(key))
                return true;
        }
        return false;
    }

    /**
     * 表是否为空
     * @return 空，返回true；否则，返回false
     */
    public boolean isEmpty() {
        return N == 0;
    }


    /**
     * 获取表中的键值对数量
     * @return 表中的键值对数量
     */
    public int size() {
        return N;
    }


    /**
     * 获取表中的所有键的集合
     * @return 表中的所有键的集合
     */
    public Iterable<Key> keys() {
        //采用队列存储
        Queue<Key> queue = new Queue<Key>();
        for (Node<Key, Value> p = first; p != null; p = p.next) {
            queue.enqueue(p.key);
        }
        return queue;
    }

    /**
     * Unit tests the <tt>SequentialSearchST</tt> data type.
     */
    public static void main(String[] args) {
        SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }
}
