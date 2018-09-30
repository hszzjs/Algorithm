package com.company;

/**
 * Created by Administrator on 2018/9/19 0019.
 */
public class SumofTwoNumbers {
    public int[] twoSum(int[] nums, int target){
        int[] out=new int[2];
        for(int i=1;i<nums.length;i++){
            for(int k=1;k<(nums.length-i);k++){
                if(target==nums[i]+nums[k]){
                    out[1]=i;
                    out[2]=k;
                }
            }
        }
        return out;
    }
}
