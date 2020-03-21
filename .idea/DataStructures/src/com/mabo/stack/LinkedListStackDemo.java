package com.mabo.stack;

/**
 * @author MaBo
 * @create 2020-03-21 15:54
 */

public class LinkedListStackDemo {

    public static void main(String[] args) {
        linkedListStack stack = new linkedListStack();
        for (int i = 0; i < 6; i++) {
            stack.push(i);
        }
        for (int i = 0; i < 6; i++) {
            System.out.println(stack.pop());
        }
    }

}

class linkedListStack {
    int top;
    ListNode head;

    public linkedListStack() {
        head = new ListNode(0);
        top = -1;
    }

    public boolean isNull() {
        return top == -1;
    }

    public void push(int val) {
        ListNode node = new ListNode(val);
        top++;
        node.next = head.next;
        head.next = node;
    }

    public int pop() {
        if (isNull()) {
            throw new RuntimeException("栈空");
        }
        top--;
        ListNode temp = head.next;
        head.next = head.next.next;
        return temp.val;
    }
}

class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}