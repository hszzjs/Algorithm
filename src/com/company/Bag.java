package com.company;


import java.util.Iterator;

/**
 * Created by Administrator on 2018/9/25 0025.
 */
public class Bag<Item> implements Iterable<Item> {
    private Node first;//链表首节点，最近添加的元素
    private int N;
    private class Node{
        //定义节点的嵌套类
        Item item;
        Node next;
    }

    public void add(Item item){
        Node oldfirst=first;
        first=new Node();
        first.item=item;
        first.next=oldfirst;
        N++;
    }

    public boolean isEmpty(){
        return first==null;
    }
    public int size(){
        return N;
    }

    public Iterator<Item> iterator(){
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>{
        private Node current=first;//最近添加的元素就是当前元素

        public boolean hasNext(){
            return current!=null;
        }

        public Item next(){
            Item item=current.item;
            current=current.next;
            return item;
        }

        public void remove(){
        }
    }

}
