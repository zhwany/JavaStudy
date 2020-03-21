package com.mabo.stack;

import java.util.Scanner;

/**
 * @author MaBo
 * @create 2020-03-21 15:02
 */

public class ArrayStackDemo {

    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(5);
        String key = "";
        boolean flag = true;
        Scanner cin = new Scanner(System.in);

        while (flag) {
            System.out.println("push");
            System.out.println("pop");
            System.out.println("list");
            System.out.println("exit");
            key = cin.nextLine();
            switch (key) {
                case "push":
                    System.out.println("输入一个数");
                    int val = cin.nextInt();
                    arrayStack.push(val);
                    break;
                case "pop":
                    System.out.println(arrayStack.pop());
                    break;
                case "list":
                    arrayStack.list();
                    break;
                case "exit":
                    cin.close();
                    flag = false;
                    break;
            }
        }
        System.out.println("程序结束~~");
    }

}

//定义一个 ArrayStack 表示栈
class ArrayStack {
    private int maxSize; //栈的大小
    private int[] stack; //用来模拟栈的数组
    private int top = -1; //top用来表示栈顶，初始化为 -1

    //构造器
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //判断栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //判断栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        //判断栈是否满
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        stack[++top] = value;
    }

    //出栈
    public int pop() {
        //判断栈是否为空
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        return stack[top--];
    }

    //遍历栈
    public void list() {
        //判断栈是否为空
        if (isEmpty()) {
            System.out.println("栈空, 无数据~~");
            return;
        }
        //栈的遍历应建立在不破坏栈的基础之上，top 指针不应改变，否则会影响 pop操作
//        while (top != -1) {
//            System.out.println(stack[top--]);
//        }
        //所以这里采取 for 循环
        for (int i = top; i >= 0; i--) {
            System.out.println("stack[" + i + "]:" + stack[i]);
        }
    }

}
