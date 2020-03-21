package com.mabo.linkedlist;

import java.util.Stack;

/**
 * @author MaBo
 * @create 2020-03-11 17:26
 */

//测试栈 Stack 的使用
public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();

        //入栈
        stack.add("jack");
        stack.add("tom");
        stack.add("smith");

        //出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }
}
