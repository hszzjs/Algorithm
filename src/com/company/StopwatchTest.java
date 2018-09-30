package com.company;

/**
 * Created by Administrator on 2018/9/27 0027.
 */
public class StopwatchTest {
    public static void main(String[] args){
        Stopwatch timer=new Stopwatch();
        for(int k=0;k<20;k++){
            System.out.println("eruiohgiouer");
        }
        double time=timer.elaspedTime();
        System.out.println(time+"seconds");
    }
}
class Stopwatch{
    private final long start;
    public Stopwatch(){
        start=System.currentTimeMillis();
    }
    public double elaspedTime(){
        long now=System.currentTimeMillis();
        return (now-start)/1000.0;
    }
}