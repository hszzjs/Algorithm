package com.company;

/**
 * Created by Administrator on 2018/10/30 0030.
 * 带权重的边
 */
public class Edge {
    private int v;
    private int w;
    private double weight;

    public Edge(int v,int w,double weight){
        this.v=v;
        this.w=w;
        this.weight=weight;
    }

    public double weight(){return weight;}
    public int either(){return v;}//对于该加权边对象的两个顶点的情况都是未知的
    public int other(int vertex){//针对直到该加权边对象有一个顶点已知
        if(vertex==v) return w;
        else if(vertex==w) return v;
        else throw new RuntimeException("Inconsistent edge");
    }

    public int compareTo(Edge that){
        if(this.weight<that.weight) return -1;
        else if(this.weight>that.weight) return 1;
        else return 0;
    }

    public String toString(){
        return String.format("%d-%d %.2f",v,w,weight);
    }
}
