package com.company;

/**
 * Created by Administrator on 2018/9/27 0027.
 */
public class CapacityStackTest {
    public static void main(String[] args){
        FixedCapacityStackOfStrings s=new FixedCapacityStackOfStrings(args.length);
        for(int i=0;i<args.length;i++){
            s.push(args[i]);
        }
        System.out.println(s);
    }
}
class FixedCapacityStackOfStrings{
    private String[] a;
    private int N;//作为数组的指针
    public FixedCapacityStackOfStrings(int cap){
        a=new String[cap];
    }

    public boolean isEmpty(){
        return N==0;
    }

    public void push(String item){
        a[N++]=item;
    }

    public String pop(){
        return a[--N];
    }

    public int size(){
        return N;
    }
}
class FixedCapacityStack<Item>{
    private Item[] o;
    private int N;
    public FixedCapacityStack(int cap){
        o=(Item[]) new Object[cap];//不能创建泛型数组所以需要进行强制类型转换
    }

    public void push(Item item){
        o[N++]=item;
    }

    public Item pop(){
        return o[--N];
    }

    public boolean isEmpty(){return N==0;}
    public int size(){return N;}
}