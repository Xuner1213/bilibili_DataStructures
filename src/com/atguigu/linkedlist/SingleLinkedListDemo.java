package com.atguigu.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(3, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(5, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(7, "林冲", "豹子头");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);

        HeroNode hero5 = new HeroNode(2, "宋江", "及时雨");
        HeroNode hero6 = new HeroNode(4, "卢俊义", "玉麒麟");
        HeroNode hero7 = new HeroNode(6, "吴用", "智多星");
        HeroNode hero8 = new HeroNode(8, "林冲", "豹子头");

        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);
        singleLinkedList1.addByOrder(hero5);
        singleLinkedList1.addByOrder(hero6);
        singleLinkedList1.addByOrder(hero7);
        singleLinkedList1.addByOrder(hero8);
        HeroNode head_new = SingleLinkedList.merge(singleLinkedList.getHead(), singleLinkedList1.getHead());
        HeroNode temp = head_new.next;
        System.out.println(head_new.next.next+"===");
        while (temp != null) {
            //输出节点的信息
            System.out.println(temp);
            temp = temp.next;
        }


//        //原链表
//        singleLinkedList.list();
//        //反转之后
////        SingleLinkedList.reversetList(singleLinkedList.getHead());
////        System.out.println("反转之后的链表");
////        singleLinkedList.list();
//        //逆序打印
//        System.out.println("逆序打印");
//        SingleLinkedList.reversePrint(singleLinkedList.getHead());
//
//
//
//        HeroNode newHeroNode = new HeroNode(2, "小路", "yuqilin");
//        singleLinkedList.update(newHeroNode);
//
//        System.out.println("修改后的链表");
//        singleLinkedList.list();
//
////        singleLinkedList.del(1);
////        singleLinkedList.del(2);
////        singleLinkedList.del(3);
////        singleLinkedList.del(4);
//
//        System.out.println("删除后的链表");
//        singleLinkedList.list();
//
//        System.out.println("有效的节点个数=" + SingleLinkedList.getLength(singleLinkedList.getHead()));
//
//        //测试一下看看是否得到了倒数第K个节点
//        HeroNode res = SingleLinkedList.findLastIndexNode(singleLinkedList.getHead(), 4);
//        System.out.println("res=" + res);
//
    }
}

class SingleLinkedList {
    //先初始化一个头节点，头节点不要动,不存放具体的数据
    private HeroNode head = new HeroNode(0,"","");

    //返回头节点
    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    //思路，当不考虑编号顺序时
    //1.找到当前链表的最后结点
    //2.将最后这个节点的next指向新的节点
    public void add(HeroNode heroNode) {
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        HeroNode temp = head.next;
        //遍历链表，找到最后
        while (true) {
            //找到链表的最后
            if (temp == null) {
                break;
            }
            //如果没有找到最后，将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp指向了链表的最后
        temp.next = heroNode;
    }

    //第二种方式添加英雄时，根据排名将英雄插入到指定位置
    //将最后这个节点的next指向新的节点
    public void addByOrder(HeroNode heroNode) {
        //因为头节点不能动，因此我们仍然通过一个辅助指针（变量）来帮助找到添加的位置
        //因为单链表，因此我们找的temp是位于添加位置的前一个节点，否则加不进去
        HeroNode temp = head;
        boolean flag = false;//标志添加的编号是否存在，默认为false
        while (true) {
            if (temp.next == null) {//说明到了链表的最后
                break;
            }
            if (temp.next.no > heroNode.no) {//位置找到，就在temp的后面插入
                break;
                //heroNode.next = temp.next;
                //temp.next = heroNode;
            } else if (temp.next.no == heroNode.no) {//说明添加的编号已然存在
                flag = true;
                break;
            }
            temp = temp.next;//后移，遍历当前链表
        }
        //判断flag的值
        if (flag) {
            System.out.printf("准备插入的英雄的编号%d已经存在了，不能加入\n",heroNode.no);
        } else {
            //插入到链表中,temp
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改节点信息，根据no修改，即no不能改变
    //根据heroNode的编号修改
    public void update(HeroNode newHeroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据no
        //定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false;//是否找到该节点
        while (true) {
            if (temp == null) {
                break;//已经遍历完链表
            }
            if (temp.no == newHeroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            System.out.printf("没有找到编号为%d的节点，不能修改/n", newHeroNode.no);
        }
    }

    //显示链表[遍历]
    public void list() {
        if (head.next == null) {
            System.out.printf("链表为空");
            return;
        }
        //因为头节点，不能动，因此需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //删除节点
    //思路
    //1.head不能动，因此我们需要找到一个temp辅助节点找到待删除节点的前一个节点
    //2.说明我们在比较时，是temp。temp.no和需要删除的节点的no比较
    public void del(int no) {
        HeroNode temp = head;
        boolean flag = false;//是否找到待删除节点
        while (true) {
            if (temp.next == null) {//已经到链表的最后
                break;
            }
            if (temp.next.no == no) {
                //找到待删除节点的前一个节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag == true) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的%d节点不存在", no);
        }
    }

    //方法：获取单链表的节点的个数（如果是头节点的链表，需要不统计头节点）

    /**
     * @param heroNode 链表的头节点
     * @return 返回的是有效节点的个数
     */
    public static int getLength(HeroNode heroNode) {
        if (heroNode.next == null) {
            return 0;
        }
        int length = 0;
        //定义一个辅助的变量
        HeroNode cur = heroNode.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    //查找单链表中的倒数第k个节点
    //思路如下：
    //1.编写一个方法，接受head节点，同时接收一个index
    //2.index表示倒数第index个节点
    //3.先把链表从头到尾遍历，得到链表的长度
    //4.得到size后，我们从链表的第一个开始遍历(size - index)
    //5.找到返回，没找到，返回空
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        //判断链表是为空，返回null
        if (head.next == null) {
            return null;//没有找到
        }
        //第一个遍历得到链表的长度
        int size = getLength(head);
        //第二次遍历size - index位置，就是我们倒数第k个节点
        //先做一个index校验
        if (index <= 0 || index > size) {
            return null;
        }
        //定义辅助变量，for 循环定位到倒数的index
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    //将单链表反转
    public static void reversetList(HeroNode head) {
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }
        //定义一个辅助指针（变量），帮助我们遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null;//指向当前节点[cur]的下一个节点
        HeroNode reverseHead = new HeroNode(0, "", "");
        //遍历原来的链表
        //每遍历一个节点，就将其取出，并放在新的链表reverseHead的最前端
        while (cur != null) {
            next = cur.next;//先暂时保存当前节点的下一个节点，因为后面需要使用
            cur.next = reverseHead.next;//将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur;//将cur链接到新的链表上
            cur = next;//让cur后移
        }
        //将head.next指向reverseHead.next，实现单链表的反转
        head.next = reverseHead.next;
    }

    //方式2
    //可以利用栈这个数据结构，将各个节点压入栈中，然后利用栈的先进后出的特点，就实现了栈的逆序打印的效果
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;//空链表，不能打印
        }
        //创建一个栈，将各个节点压入栈
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        //将链表中所有节点压入栈中
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;//cur后移，这样可以压入下一个节点
        }
        //将栈中的节点打印
        while (stack.size() > 0) {
            System.out.println(stack.pop());//stack的特点是先进后出
        }
    }

    //合并两个有序的单链表，合并之后的链表依然有序
    public static HeroNode merge(HeroNode head1, HeroNode head2) {
        HeroNode head = null;
        if (head1 == null) {
            return head2;
        }

        if (head2 == null) {
            return head1;
        }

        if (head1.no <= head2.no) {
            head = head1;
            head.next = merge(head1.next, head2);
        } else {
            head = head2;
            head.next = merge(head1, head2.next);
        }
        return head;
    }

}



class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int hNo, String hName, String hNickname) {
        this.no = hNo;
        this.name = hName;
        this.nickname = hNickname;
    }

    @Override
    public String toString() {
        return "HeroNode [no="+ no +", name =" +name + "nickname="+ nickname+"]";
    }
}
