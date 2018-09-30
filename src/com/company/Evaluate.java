package com.company;

import java.util.Stack;

/**
 * Created by Administrator on 2018/9/27 0027.
 */
public class Evaluate {
    public static void main(String[] args){
        String math_option="(1+((2+3)*(4*5)))";
        Stack<String> ops=new Stack<String >();
        Stack<Double> num=new Stack<Double>();
        for(int i=0;i<math_option.length();i++){
            String s=math_option.substring(i,i+1);
//            System.out.println(s);
            if (s.equals("(")) ;
            else if(s.equals("+")) ops.push(s);
            else if(s.equals("-")) ops.push(s);
            else if(s.equals("*")) ops.push(s);
            else if(s.equals("/")) ops.push(s);
            else if(s.equals("sqrt")) ops.push(s);
            else if(s.equals(")")){
                String op=ops.pop();
                double v=num.pop();
                if(op.equals("+")) v=num.pop()+v;
                else if(op.equals("-")) v=num.pop()-v;
                else if(op.equals("*")) v=num.pop()*v;
                else if(op.equals("/")) v=num.pop()/v;
                else if(op.equals("sqrt")) v=Math.sqrt(v);
                num.push(v);
//                System.out.println(v);
            }
            else num.push(Double.parseDouble(s));
        }
        System.out.println(num);
    }
}
