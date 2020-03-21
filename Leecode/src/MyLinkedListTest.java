/**
 * @author MaBo
 * @create 2020-03-17 11:14
 */

public class MyLinkedListTest {
    /**
     * Your MyLinkedList object will be instantiated and called as such:
     * MyLinkedList obj = new MyLinkedList();
     * int param_1 = obj.get(index);
     * obj.addAtHead(val);
     * obj.addAtTail(val);
     * obj.addAtIndex(index,val);
     * obj.deleteAtIndex(index);
     */
    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        //输出初始链表的长度
        int param_1 = list.size;
        System.out.println("初始链表的长度为 " + param_1);

        list.addAtHead(5);
        list.addAtTail(1);
//        list.addAtHead(5);
        list.addAtIndex(1,2);

        list.addAtTail(6);

        System.out.println(list.get(1));
        System.out.println(list.get(2));
        System.out.println(list.get(3));
        System.out.println(list.get(4));
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
}

class MyLinkedList {
    int size;
    ListNode head;

    /** Initialize your data structure here. */
    public MyLinkedList() {
        size = 0;
        head = new ListNode(0);
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index <= 0 || index > size) {
            System.out.println("第" + index + "个元素不存在");
            return -1;
        }
        ListNode cur = head.next;
        for (int i = 1; i < index; i++) {
            cur = cur.next;
        }
        return cur.val;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
//    public void addAtIndex(int index, int val) {
//        ListNode listNode;
//        ListNode temp;
//        if (index < 0 || index > size) {
//            return;
//        }
//        temp = head;
//        for (int i = 0; i < index-1; i++) {
//            temp = temp.next;
//        }
//        size++;
//        listNode = new ListNode(val);
//        listNode.next = temp.next;
//        temp.next = listNode;
//    }

    public void addAtIndex(int index, int val) {
        ListNode p, s;
        if (index > size) {
            return;
        }
        if (index < 0) {
            return;
        }
        p = head;
        for (int i = 0; i < index; ++i) {
            p = p.next;
        }
        size++;
        s = new ListNode(val);
        s.next = p.next;
        p.next = s;
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0 || index > size) {
            return;
        }
        ListNode cur = head;
        for (int i = 1; i < index; i++) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        size--;
    }

}