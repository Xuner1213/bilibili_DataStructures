package com.atguigu.linkedlist;

public class Josepfu {
    public static void main(String[] args) {
        //测试一把看看构建环形链表和遍历是否ok
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();

        //测试一把小孩出圈
        circleSingleLinkedList.countBoy(1, 2, 5);
    }
}

//创建环形的单向链表

class CircleSingleLinkedList {
    //创建first节点，当前没有编号
    private Boy first = new Boy(-1);
    //添加小孩节点，构造一个环形的链表
    public void addBoy(int nums) {
        //nums做一个数据校验
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null;//辅助指针，帮助构建环形链表
        //使用for循环来创建我们的环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(first);
                curBoy = first;//让current指向第一个小孩
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历当前的环形链表
    public void showBoy() {
        //判断链表是否为空
        if (first == null) {
            System.out.println("没有任何小孩");
            return;
        }
        //因为first不能动，因此我们仍然使用一个辅助指针完成遍历
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号%d\n", curBoy.getNo());
            if (curBoy.getNext() == first) {//说明已经遍历完毕
                break;
            }
            curBoy = curBoy.getNext();//curBoy后移
        }
    }

    /**
     * @param startNo 表示从第几个number开始数数
     * @param countNum 表示数几下
     * @param nums 最初总共有多少个小孩
     */
    //根据用户的输入，计算小孩出圈的顺序
    public void countBoy(int startNo, int countNum, int nums) {
        //先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.printf("参数输入有误，请重新输入");
            return;
        }

        //创建一个hepler指针，帮助小孩出圈
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) {//说明helper指向了最后一个节点
                break;
            }
            helper = helper.getNext();
        }

        //小孩报数前，先让first和helper移动k-1次
        for (int j = 0; j < startNo - 1; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        //当小孩报数时，让first和helper指针同时移动m-1次，然后出圈
        //这里是一个循环的操作，直到圈中只有一个节点
        while (true) {
            if (helper == first) {//说明圈中只有一个人
                break;
            }
            //让first和helper指针同时移动m-1次，然后出圈
            for (int j = 0; j < countNum - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的节点就是要出圈的节点
            System.out.printf("小孩%d出圈\n", first.getNo());
            //这时将first指向的小孩出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号%d\n", first.getNo());
    }
}

//创建一个Boy类，表示一个节点
class Boy {
    private int no;//编号
    private Boy next;//指向下一个节点，默认为null
    public Boy (int no) {
        this.no = no;
    }

    public void setNo(int no) {
        this.no = no;
    }


    public void setNext(Boy next) {
        this.next = next;
    }

    public int getNo() {
        return no;
    }

    public Boy getNext() {
        return next;
    }
}