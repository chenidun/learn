package com.demo.learn.clyTest.question;

import java.util.HashMap;

/**
 * Created by luwt on 2021/5/19.
 */
public class Test {

    public static void main(String[] args) {
       fun();
    }

    public static  void fun(){
        // 无重复数字有序列表，找出元素之和为100的两个元素
        // 两层循环，一层从前往后，一层从后往前，找出和为100的数字记录下标，下次从此处循环
        int[] nums = new int[]{1,3,5,10,13, 27,43,56,68,73, 89,95,97,99,100, 200};
        int right = 0;
        HashMap<Integer, Integer> result = new HashMap<>();
        for (int out = 0; out < nums.length - 1; out++){
            for (int in = nums.length - 1 - right; in >= out; in--){
//                System.out.println(out + " : " + in);
                if ((nums[out] + nums[in]) == 100){
                    right = nums.length -1 - in;
                    result.put(nums[out], nums[in]);
                    System.out.println("下标：" + out + " 值：" + nums[out] + "      下标：" + in + " 值：" +  nums[in]);
                }
            }
        }
    }
}
