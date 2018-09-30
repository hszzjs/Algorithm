package com.company;

/**
 * Created by Administrator on 2018/9/25 0025.
 */
public class Bag_Test {
    public static void main(String[] args){
        Bag<Double> numbers=new Bag<Double>();
        for(int k=0;k<=10;k++){
            double temp=Math.random();
            System.out.println(temp);
            numbers.add(temp);
        }
        System.out.println("***********");
        int n=numbers.size();
        double sum=0;
        for(double x:numbers){
            System.out.println(x);
            sum+=x;
        }
        System.out.println("***********");
        double mean=sum/n;
        sum=0.0;
        for(double x:numbers){
            System.out.println(x);
            sum+=(x-mean)*(x-mean);
        }
        double std=Math.sqrt(sum/(n-1));
        System.out.printf("Mean:%.2f\n",mean);
        System.out.printf("Square:%.2f\n",std);
    }
}
