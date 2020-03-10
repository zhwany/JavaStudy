package com.mabo.linkedlist;

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

        //创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //顺序加入元素
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);

        //乱序加入
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);

        //删除 hero4 节点
        singleLinkedList.delete(hero4);

        //修改 hero3 节点的信息
        String str1 = "马博";
        String str2 = "派大星";
        singleLinkedList.update(hero3, str1, str2);

        //顺序输出元素
        singleLinkedList.list();
    }
}

//定义 SingleLinkedList 管理我们的英雄
class SingleLinkedList {
    //先初始化一个头节点，头节点不要动
    private HeroNode head = new HeroNode(0, "", "");

    //添加节点到单向链表
    //思路,当不考虑编号顺序时
    //1.找到当前链表的最后节点
    //2.将最后这个节点的 next 指向新的节点
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

    //第二种添加英雄的方式
    //在添加英雄时，根据排名将英雄添加到指定的位置
    //（如果有这个排名，则添加失败，并给出提示）
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