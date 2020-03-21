package com.mabo.linkedlist;

/**
 * @author MaBo
 * @create 2020-03-20 20:36
 */
public class Joseph {

    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        //创建单项环形链表的测试
//        circleSingleLinkedList.addBoy(15);
//        circleSingleLinkedList.showBoy();

        //约瑟夫问题利用单项环形链表的解决
        circleSingleLinkedList.outBoy(1,3,6);
    }

}

class CircleSingleLinkedList {
    //创建一个 first 节点，一直指向第一个节点
    private Boy first = null;

    //添加节点，构建环形链表
    public void addBoy(int nums) {
        //对传入参数合法性校验
        if (nums < 1) {
            System.out.println("nums error!");
            return;
        }
        Boy curBoy = null; //辅助节点(尾指针），帮助构建环形链表
        //创建长度为 nums 的环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号创建小孩节点
            Boy boy = new Boy(i);
            //第一个节点进行单独处理
            if (i == 1) {
                first = boy;
                first.setNext(boy); //构成环
                curBoy = first; //first 节点不能动，curBoy 节点该发挥他的作用了~
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    public void showBoy() {
        //判断链表是否为空
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        Boy curBoy = first;
        while (true) {
            System.out.println("小孩的编号：" + curBoy.getNo());
            //判断循环是否遍历完毕
            if (curBoy.getNext() == first) {
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    /**
     * @param startNo   表示从第几个小孩开始
     * @param countNum  表示数几下
     * @param nums      表示最初有多少小孩在圈中
     */
    public void outBoy(int startNo, int countNum, int nums) {
        this.addBoy(nums);
        //先对目前状态合法性校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误~请检查...");
            return;
        }
        //创建指向链表尾的指针
        Boy helper = first;
        while (true) {
            //判断循环是否遍历完毕
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前，先让 first 和 helper 移动 k-1 次
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //小孩报数时，让 first 和 helper 同时移动 m-1 次，出圈
        //这里是循环操作，直到最后圈中只剩下一个节点结束循环
        while (true) {
            if (helper == first) {     //说明圈中只有一个节点
                break;
            }
            //让 first 和 helper 同时移动 countNum-1 次，出圈
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的节点，就是要出圈的小孩节点
            System.out.println("小孩" + first.getNo() + "出圈");
            //这时将first指向的小孩节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.println("小孩" + first.getNo() + "出圈");
    }
}

//创建一个 Boy 类，表示一个节点
class Boy {
    private int no; //编号
    private Boy next; //指向下一个节点，默认为 null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
