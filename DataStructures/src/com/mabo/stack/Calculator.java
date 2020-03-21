package com.mabo.stack;

/**
 * @author MaBo
 * @create 2020-03-21 19:18
 */

public class Calculator {
    //使用栈实现综合计算器（不能计算带有括号的）
    public static void main(String[] args) {
        String expression = "13-9/3+4*2";
        //创建两个栈，数栈与符号栈
        ArrayStack2 numsStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义需要的相关变量
        int index = 0;  //用于扫描表达式
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' '; //用于将每次扫描得到的操作符保存到 ch 中
        String keepNum = ""; //用于拼接多位数
        //开始循环表达式
        while (true) {
            //依次获得expression中的字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断 ch 是什么，然后再做相应的处理
            if (operStack.isOper(ch)) { //如果这是一个操作符
                //判断当前的符号栈是否为空
                if (!operStack.isEmpty()) { //不为空
                    //继续判断，用当前的操作符的优先级与栈内栈顶的操作符的优先级进行对比
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numsStack.pop();
                        num2 = numsStack.pop();
                        oper = operStack.pop();
                        res = numsStack.cal(num1, num2, oper);
                        //把运算的结果入数栈
                        numsStack.push(res);
                        if (!operStack.isEmpty()) { //不为空
                            num1 = numsStack.pop();
                            num2 = numsStack.pop();
                            oper = operStack.pop();
                            res = numsStack.cal(num1, num2, oper);
                            //把运算的结果入数栈
                            numsStack.push(res);
                        }
                        //把当前的符号如符号栈
                        operStack.push(ch);
                    } else {
                        //如果当前的操作符的优先级大于栈中的操作符，直接入符号栈
                        operStack.push(ch);
                    }
                } else {    //如果为空直接入栈
                    operStack.push(ch);
                }
            } else {
                //numsStack.push(ch - 48);
                //如按上面操作会出现问题，因为是一位一位扫描，就不能解决多位数的加法
                /**
                 * 解决思路：
                 * 1.在处理数时，需要向 expression 的表达式的 index 后一位再看一位，如果是数就进行扫描，如果是符号在入栈
                 * 2.因此我们定义一个变量 keepNum 字符串，用于拼接
                 */

                //处理多位数
                keepNum += ch;

                //如果 ch 已经是 expression 的最后一位，则就直接入栈
                if (index == expression.length() - 1) {
                    numsStack.push(Integer.parseInt(keepNum));
                } else {
                    //判断下一个字符是不是数字，如果是数字，就继续扫描，不是则入栈
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        //如果后一位是运算符，则将keepNum入栈
                        numsStack.push(Integer.parseInt(keepNum));
                        //重新初始化 keepNum
                        keepNum = "";
                    }
                }
            }
            //index++，并判断 expression 是否遍历完毕
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        //当表达式扫描完毕，就顺序的从数栈和符号栈pop元素，计算剩下的
        while (true) {
            //如果符号栈为空，则计算最后的结果，此时数栈中只有一个数字
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numsStack.pop();
            num2 = numsStack.pop();
            oper = operStack.pop();
            res = numsStack.cal(num1, num2, oper);
            numsStack.push(res);
        }

        System.out.println(expression + "=" + numsStack.pop());

    }

}

//定义一个 ArrayStack2 表示栈
class ArrayStack2 {
    private int maxSize; //栈的大小
    private int[] stack; //用来模拟栈的数组
    private int top = -1; //top用来表示栈顶，初始化为 -1

    //构造器
    public ArrayStack2(int maxSize) {
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

    /**
     * 返回运算符的优先级
     *
     * @param oper 运算符
     * @return 运算符的优先级（我们自己设置）
     */
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    //判断是否是运算符
    public boolean isOper(char oper) {
        return oper == '*' || oper == '/' || oper == '+' || oper == '-';
    }

    public int cal(int num1, int num2, int oper) {
        int res = 0; //用于存放计算的结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1; //这里注意两数顺序
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }

    //查看当前栈顶的值
    public int peek() {
        return stack[top];
    }

}
