package com.company;

import java.util.Iterator;

/**
 * Created by Administrator on 2018/9/27 0027.
 */
public class Queue<Item> implements Iterable<Item>{
    private Node first;
    private Node last;
    private int N;
    private class Node{
        Item item;
        Node next;
    }

    public int size(){return N;}
    public boolean isEmpty(){return N==0;}
    public void enqueue(Item item){
        Node oldlast=last;
        last=new Node();
        last.item=item;
        last.next=null;
        if(isEmpty()) first=last;//如果是空的队列输入节点，就必须有first=last
        else oldlast.next=last;
        N++;
    }
    public Item dequeue(){
        Item item=first.item;
        first=first.next;
        if(isEmpty()) last=null;//当队列是空的时候，表头和表尾必须都是null
        N--;
        return item;
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
