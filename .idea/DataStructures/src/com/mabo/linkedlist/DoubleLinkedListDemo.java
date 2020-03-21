package com.mabo.linkedlist;

/**
 * @author MaBo
 * @create 2020-03-17 2:01
 * @description this is shuangxianglianbiaoxuexi
 */

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        System.out.println("双向链表的测试");
        //创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        //创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.list();

        //修改
        doubleLinkedList.update(hero4, "公孙胜", "入云龙");
        System.out.println("修改后的链表情况");
        doubleLinkedList.list();

        //删除
        doubleLinkedList.delete(hero3);
        System.out.println("删除后链表情况");
        doubleLinkedList.list();

        //按照英雄编号顺序添加
        HeroNode2 heroNode2 = new HeroNode2(0, "mabo", "patric");
        doubleLinkedList.addByOrder(heroNode2);
        System.out.println("测试按照英雄编号添加 0 号英雄");
        doubleLinkedList.list();
    }
}

class DoubleLinkedList {
    //先初始化一个头节点，头节点不要动
    private HeroNode2 head = new HeroNode2(0, "", "");

    //返回头结点
    public HeroNode2 getHead() {
        return head;
    }

    //添加一个节点到双向链表的最后
    public void add(HeroNode2 heroNode) {
        //因为 head 节点不能动，因此需要一个辅助节点遍历 temp
        HeroNode2 temp = head;
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
        //形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //按照编号顺序添加
    public void addByOrder(HeroNode2 heroNode) {
        //因为 head 节点不能动，因此需要一个辅助节点遍历 temp
        HeroNode2 temp = head;
        //遍历链表，找到最后
        while (true) {
            //找到链表的最后
            if (temp.next.no > heroNode.no) {
                break;
            }
            //如果没有找到最后，将 temp 后移
            temp = temp.next;
        }
        //当退出循环时，temp 就指向了链表的最后
        //形成一个双向链表
        heroNode.next = temp.next;
        heroNode.pre = temp;
        temp.next = heroNode;
        heroNode.next.pre = heroNode;
    }

    //修改链表中的节点信息，根据编号来修改（修改方法同单向链表）
    public void update(HeroNode2 heroNode, String name, String nickname) {
        HeroNode2 temp = head.next;
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

    //删除链表中的节点
    public void delete(HeroNode2 heroNode) {
        //判断当前链表是否为空
        if (head.next == null) {
            System.out.println("链表为空~~");
            return;
        }
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                System.out.println("没有找到该节点");
                return;
            }
            if (temp.no == heroNode.no) {
                temp.pre.next = temp.next;
                //???下一句代码注意，如果删除的是最后一个元素，temp.next.pre 将会是无中生有(NullPointException)
                if (temp.next != null) {
                    temp.next.pre = temp.pre;
                }
                //temp.next 会被 jvm 的垃圾回收机制处理
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
        HeroNode2 temp = head.next;
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

//定义 HeroNode2，每个 HeroNode2 对象就是一个节点
class HeroNode2 {
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;  //指向下一个节点，默认为 null
    public HeroNode2 pre;   //指向上一个节点，默认为 null

    //构造器
    public HeroNode2(int no, String name, String nickName) {
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