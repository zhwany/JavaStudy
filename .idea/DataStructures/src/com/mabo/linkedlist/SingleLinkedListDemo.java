package com.mabo.linkedlist;

import java.util.Stack;

/**
 * @author MaBo
 * @create 2020-03-10 1:06
 */

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //Test
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //这是利用数组倒序输出
//        HeroNode[] a = new HeroNode[4];
//        a[0] = hero1;
//        a[1] = hero2;
//        a[2] = hero3;
//        a[3] = hero4;
//        for (int i = a.length-1; i >= 0 ; i--) {
//            System.out.println("a[i] = " + a[i]);
//        }

        //创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        //顺序加入元素
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);

        //乱序加入
//        singleLinkedList.addByOrder(hero1);
//        singleLinkedList.addByOrder(hero4);
//        singleLinkedList.addByOrder(hero3);
//        singleLinkedList.addByOrder(hero2);

        //原来链表的情况~~
//        System.out.println("初始链表的情况~~");
//        singleLinkedList.list();
        //反转后的链表情况~~~
//        System.out.println("反转之后的链表情况~~~");
//        reverseHead(singleLinkedList.getHead());
//        singleLinkedList.list();

        //逆序输出链表
//        System.out.println("逆序输出链表(递归)~~~");
//        reversePrint(singleLinkedList.getHead());
//        System.out.println("逆序输出链表(栈)~~~");
//        reversePrint1(singleLinkedList.getHead());

        //删除 hero4 节点
//        singleLinkedList.delete(hero4);
        //修改 hero3 节点的信息
//        String str1 = "马博";
//        String str2 = "派大星";
//        singleLinkedList.update(hero3, str1, str2);
        //输出修改后的链表信息
//        singleLinkedList.list();

        //输出有效链表的长度
//        System.out.println("有效的节点个数=" + getLength(singleLinkedList.getHead()));

        //输出倒数第 2 个节点信息
//        System.out.println("倒数第 2 个节点信息" + findLastIndexNode(singleLinkedList.getHead(), 2));

        //合并两个有序的链表
        //创建两个链表
        HeroNode hero5 = new HeroNode(5, "远航", "1");
        HeroNode hero6 = new HeroNode(6, "正阳", "2");
        HeroNode hero7 = new HeroNode(7, "柳源", "3");
        HeroNode hero8 = new HeroNode(8, "马博", "4");
        HeroNode hero9 = new HeroNode(9, "奔腾", "5");
        HeroNode hero10 = new HeroNode(4, "孙卓", "6");
        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        singleLinkedList1.add(hero5);
        singleLinkedList1.add(hero8);
        singleLinkedList2.add(hero10);
        singleLinkedList2.add(hero6);
        singleLinkedList2.add(hero7);
        singleLinkedList2.add(hero9);
        SingleLinkedList singleLinkedList3 = new SingleLinkedList();
        System.out.println("链表 1 的信息");
        singleLinkedList1.list();
        System.out.println("链表 2 的信息");
        singleLinkedList2.list();
        mergeLink(singleLinkedList3.getHead(), singleLinkedList1.getHead(), singleLinkedList2.getHead());
        System.out.println("合并后链表 1 的信息");
        singleLinkedList1.list();
        System.out.println("合并后链表 2 的信息");
        singleLinkedList2.list();
        System.out.println("合并链表 1 和链表 2 到链表 3,合并结果如下");
        singleLinkedList3.list();
    }


    /**
     * @description 查找单链表中倒数第 k 个节点【新浪面试题】
     * @param head 要查找的链表的头结点
     * @param index 要找的倒数第几个节点
     * @return 倒数第 k 个节点
     *     思路如下
     *     1.编写一个方法，接收 head 节点，同时接收一个 index(表示倒数第几个节点）
     *     2.先把链表从头到尾遍历，得到链表的总长度 getLength
     *     3.用得到的链表长度减去index 得到正数的该节点位置
     *     4.找到了返回该节点，否则返回 null
     */
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        //判断链表是否为空
        if (head.next == null) {
            return null;
        }
        //调用 getLength 得到链表的长度
        int size = getLength(head);
        //判断 index 的合法性
        if (index < 0 || index > size) {
            return null;
        }
        //正数的次序
        int k =size - index;
        HeroNode cur = head.next;
        for (int i = 0; i < k; i++) {
            cur = cur.next;
        }
        return cur;
    }

    /**
     * 方法：获取到单链表的结点的个数（注意区分链表是否带有辅助用的头结点，有则不能统计进入
     * head -> 链表的头节点
     * rerturn -> 返回有效结点的个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    //将单链表反转
    public static void reverseHead(HeroNode head) {
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null) {
            System.out.println("链表为空");
            return;
        }
        //定义一个辅助的指针（变量），帮助我们遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null; //指向当前节点[cur]的下一个节点
        HeroNode reverseHead = new HeroNode(0, "", "");

        //遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表 reverseHead 最前端
        while (cur != null) {
            next = cur.next;  //先保存当前节点的下一个节点
            cur.next = reverseHead.next;  //将 cur 的下一个节点指向新的链表的最前端
            reverseHead.next = cur;  //将 cur 连接到新的链表上
            cur = next;  //让 cur 后移
        }
        head.next = reverseHead.next;

        //my code
//        HeroNode reverseHead1 = new HeroNode(0, "", "");
//        HeroNode cur1 = head.next;
//        while (cur1 != null) {
//            HeroNode temp = cur1;
//            cur1 = cur1.next;
//            temp.next = reverseHead1.next;
//            reverseHead1.next = temp;
//        }
//        head.next = reverseHead1.next;
    }

    //将单链表逆序输出(递归)
    public static void reversePrint(HeroNode head) {
        if(head.next != null){
            reversePrint(head.next);
        }
        if (head.no == 0) {
            return;
        }
        System.out.println(head.toString());
    }

    //将单链表逆序输出（栈）
    public static void reversePrint1(HeroNode head) {
        if (head.next == null) {
            return; //空链表，直接返回
        }
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        //将链表的所有节点压入栈
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        //将栈中元素进行打印，pop 出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    //合并两个有序的链表
    public static void mergeLink(HeroNode node, HeroNode node1, HeroNode node2) {
        HeroNode cur = node;
        HeroNode cur1 = node1.next;
        HeroNode cur2 = node2.next;

        while (cur1 != null && cur2 != null) {
            if (cur1.no <= cur2.no) {
                //this is 3.0 版本（不会破坏原表）
                HeroNode temp = new HeroNode(0,"0","0");
                temp.no = cur1.no;
                temp.name = cur1.name;
                temp.nickName = cur1.nickName;
                cur.next = temp;
                cur = cur.next;
                cur1 = cur1.next;

                //this is 2.0 版本（仍会破坏原表）
//                cur.next = cur1;
//                cur = cur1;
//                cur1 = cur1.next;

                //this is 1.0 版本（这是一团很糟糕的代码）
//                temp = cur1;
//                cur1 = cur1.next;
//                while (true) {
//                    if (cur.next == null) {
//                        break;
//                    }
//                    cur = cur.next;
//                }
//                temp.next = cur.next;
//                cur.next = temp;
            } else {
                //this is 3.0 版本（不会破坏原表）
                HeroNode temp = new HeroNode(0,"0","0");
                temp.no = cur2.no;
                temp.name = cur2.name;
                temp.nickName = cur2.nickName;
                cur.next = temp;
                cur = cur.next;
                cur2 = cur2.next;

                //this is 2.0 版本（仍会破坏原表）
//                cur.next = cur2;
//                cur = cur2;
//                cur2 = cur2.next;

                //this is 1.0 版本（不仅会破坏原表，而且操作傻逼）
//                temp = cur2;
//                cur2 = cur2.next;
//                while (true) {
//                    if (cur.next == null) {
//                        break;
//                    }
//                    cur = cur.next;
//                }
//                temp.next = cur.next;
//                cur.next = temp;
            }
        }

        cur.next = cur1 != null ? cur1 : cur2;//简明扼要，是不是比下面那团糟糕的代码好看的多了?

//        if (cur1 != null) {
//            temp = cur1;
//            cur1 = cur1.next;
//            while (true) {
//                if (cur.next == null) {
//                    break;
//                }
//                cur = cur.next;
//            }
//            temp.next = cur.next;
//            cur.next = temp;
//        }
//        if (cur2 != null) {
//            temp = cur2;
//            cur2 = cur2.next;
//            while (true) {
//                if (cur.next == null) {
//                    break;
//                }
//                cur = cur.next;
//            }
//            temp.next = cur.next;
//            cur.next = temp;
//        }
    }

}

//定义 SingleLinkedList 管理我们的英雄
class SingleLinkedList {
    //先初始化一个头节点，头节点不要动
    private HeroNode head = new HeroNode(0, "", "");

    //返回头结点
    public HeroNode getHead() {
        return head;
    }

    /**
     * @description 添加节点到单向链表（直接添加到链尾）
     *     思路,当不考虑编号顺序时
     *     1.找到当前链表的最后节点
     *     2.将最后这个节点的 next 指向新的节点
     */
    public void add(HeroNode heroNode) {
        //因为 head 节点不能动，因此需要一个辅助节点遍历 temp
        HeroNode temp = head;
        //遍历链表，找到最后
        while (true) {
            //找到链表的最后
            if (temp.next == null) {
                break;
            }
            //如果没有找到最后，将 temp 后移
            temp = temp.next;
        }
        //当退出循环时，temp 就指向了链表的最后
        //将最后节点的 next 指向 新的节点
        temp.next = heroNode;
    }

    /**
     * @decription 添加节点到单向链表（按照英雄的编号顺序添加）
     * 第二种添加英雄的方式
     *     在添加英雄时，根据排名将英雄添加到指定的位置
     *     （如果有这个排名，则添加失败，并给出提示）
     */
    public void addByOrder(HeroNode heroNode) {
        //因为头结点不能动，因此我们仍然通过一个辅助指针（变量） 来帮助找到添加的位置
        //同时有因为是单链表，因此我们找到的 temp 是位于添加位置的前一个节点，否则不能插入
        HeroNode temp = head;
        boolean flag = false; //标志添加的编号是否存在
        while (true) {
            if (temp.next == null) { //说明temp已经在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no) { //位置已找到，就在temp的后面插入
                break;
            } else if (temp.next.no == heroNode.no) { //说明希望添加的 heroNode 的编号已经存在
                flag = true;
            }
            temp = temp.next;
        }
        //判断 flag 的值
        if (flag) {
            //不能添加，说明编号存在
            System.out.printf("准备插入的英雄的编号 %d 已经存在了，不能加入\n", heroNode.no);
        } else {
            //将英雄插入到链表
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //删除链表中的节点
    public void delete(HeroNode heroNode) {
        HeroNode temp = head;
        if (temp.next == null) {
            System.out.println("链表为空~~");
            return;
        }
        while (true) {
            if (temp.next == null) {
                System.out.println("没有找到该节点");
                return;
            }
            if (temp.next.no == heroNode.no) {
                temp.next = temp.next.next;
                //temp.next 会被 jvm 的垃圾回收机制处理
                break;
            }
            temp = temp.next;
        }
    }

    //修改链表中的节点信息，根据编号来修改
    public void update(HeroNode heroNode, String name, String nickname) {
        HeroNode temp = head.next;
        //先判断链表是否为空
        if (temp == null) {
            System.out.println("链表为空~");
            return;
        }
        while (true) {
            if (temp == null) {
                System.out.printf("没有找到编号为 %d 的信息",heroNode.no);
                break;  //链表已经遍历结束
            }
            if (temp.no == heroNode.no) {//找到需要修改的节点
                temp.name = name;
                temp.nickName = nickname;
                break;
            }
            temp = temp.next;
        }
    }

    //遍历显示链表
    public void list() {
        //先判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为是头结点，不能动，所以我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true) {
            //先判断链表是否到最后
            if (temp == null) {
                break;
            }
            //输出节点信息
            System.out.println(temp.toString());
            //将 next 后移
            temp = temp.next;
        }
    }

}

//定义 HeroNode，每个 HeroNode 对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    //构造器
    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    //为了显示方法，我们重写 toString
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}