package com.wxj.spring.springbootdemo;

import java.util.Stack;
import java.util.Scanner;
public class Test{
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public void add(int num){
        stack1.push(num);
    }
    public Integer poll(){
        if(stack1.isEmpty() && stack2.isEmpty()){
            throw new RuntimeException("queue is empty!");
        }else if(stack2.isEmpty()){
            while(stack1.size()>0){
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
    public Integer peek(){
        if(stack1.isEmpty() && stack2.isEmpty()){
            throw new RuntimeException("queue is empty!");
        }else if(stack2.isEmpty()){
            while(stack1.size()>0){
                stack2.push(stack1.pop());
            }
        }
        return stack2.peek();
    }

    public static void main(String[] args) {
        String[] eles = "my test".split(" ");
        System.out.println();
    }
}