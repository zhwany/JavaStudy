package com.mabo.queue;

import java.util.Scanner;

/**
 * @author MaBo
 * @create 2020-03-09 16:03
 */

public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        //这里的环形队列预留了一位置空，所以队列有效长度长度会比设置的少 1，置空位也是一直移动的
        System.out.println("测试数组模拟环形队列的案例~");
        CircleArray queue = new CircleArray(4);//arrMaxSize 的值设置为 4，其队列的有效数据最大是 3
        char key = ' ';//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头元素");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.println("取出的数据是：");
                        System.out.println("res = " + res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = queue.headQueue();
                        System.out.println("队列的头元素是");
                        System.out.println("res = " + res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序结束~~");
    }
}

class CircleArray {
    private int maxSize; //表示数组的最大容量
    private int front;   //队列头，指向队列的第一个元素，front 初始值为 0
    private int rear;    //队列尾，指向队列的最后一个元素的后一个位置，rear 初始值为 0
    private int[] arr;   //该数组用于存放数据，模拟队列

    public CircleArray(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = 0;
        rear = 0;
    }

    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    //添加数据到队列
    public void addQueue(int n) {
        //判断队列是否满
        if (isFull()) {
            System.out.println("队列满");
            return;
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    //获取队列的数据，出队列
    public int getQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            //通过抛出异常
            throw new RuntimeException("队列空，不能取数据~~");
        }
        int temp = arr[front];
        front = (front + 1) % maxSize;
        return temp;
    }

    //显示队列的所有数据
    public void showQueue() {
        //遍历
        if (isEmpty()) {
            System.out.println("队列空，没有数据~~");
            return;
        }
//        for (int i = front; i < front + size(); i++) {
//            System.out.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);
//        }
        if (front < rear) {
            for (int i = front; i < rear; i++) {
                System.out.printf("arr[%d] = %d\n", i, arr[i]);
            }
        } else if (rear < front) {
            for (int i = front; i < maxSize; i++) {
                System.out.printf("arr[%d] = %d\n", i, arr[i]);
            }
            for (int i = 0; i < rear; i++) {
                System.out.printf("arr[%d] = %d\n", i, arr[i]);
            }
        }
    }

    //求出当前队列有效数据的个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    //显示队列的头数据
    public int headQueue() {
        if (isEmpty()) {
            //通过抛出异常
            throw new RuntimeException("队列空，不能取数据");
        }
        return arr[front];
    }

}
