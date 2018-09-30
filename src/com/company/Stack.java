package com.company;


import java.util.Iterator;

/**
 * Created by Administrator on 2018/9/27 0027.
 */
public class Stack<Item> implements Iterable<Item> {
    private Node first;
    private int N;
    private class Node{
        Item item;
        Node next;
    }

    public int size(){return N;}
    public boolean isEmpty(){return N==0;}
    public void push(Item item){
        Node oldfirst=first;
        first=new Node();
        first.item=item;
        first.next=oldfirst;
        N++;
    }
    public Item pop(){
        Item item=first.item;
        first=first.next;
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
