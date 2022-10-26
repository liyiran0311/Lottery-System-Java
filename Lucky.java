package com.vc.homework;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 幸运抽奖
 * author: VC
 * create: 2022/10/20 9:11
 * version: 1.0.0
 */
public class Lucky {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] names = new String[5];
        String[] pwds = new String[5];
        int[] cards = new int[5];
        //保存匹配用户名的下标
        int index = -1;
        //用户输入的选项
        int choice;
        while (true) {

            //验证输入是否正确
            while (true) {
                System.out.println("1.注册");
                System.out.println("2.登录");
                System.out.println("3.抽奖");
                //判断是否是数字
                if (!sc.hasNextInt()) {
                    System.out.println("请输入数字!");
                    sc.next();
                    continue;
                }
                //取出缓存内容
                choice = sc.nextInt();
                //判断范围
                if (choice < 1 || choice > 3) {
                    System.out.println("输入范围错误!");
                    continue;
                }
                break;
            }
            switch (choice) {
                case 1: {
                    System.out.println("注册...");
                    System.out.println("请输入用户名:");
                    String name = sc.next();
                    //验证用户名是否重复,遍历姓名数组
                    boolean duplicated = false;
                    for (int i = 0; i < names.length; i++) {
                        //处理NPE
                        if (names[i] == null) {
                            continue;
                        }
                        if (names[i].equalsIgnoreCase(name)) {
                            duplicated = true;
                            System.out.println("用户名重复!");
                            break;
                        }
                    }
                    //回到主菜单
                    if (duplicated) {
                        continue;
                    }
                    System.out.println("请输入密码:");
                    String pwd = sc.next();
                    //保存注册用户
                    int card;
                    //随机生成卡号
                    while (true) {
                        card = (int) (Math.random() * (9999 - 1000 + 1) + 1000);
                        //判断卡号是否重复
                        duplicated = false;
                        for (int j = 0; j < cards.length; j++) {
                            if (card == cards[j]) {
                                duplicated = true;
                                //重复了
                                break;
                            }
                        }
                        //判断是否生成成功
                        if (!duplicated) {
                            //不重复
                            break;
                        }
                    }
                    System.out.println("注册成功,请牢记您的卡号:" + card);
                    //找空位
                    int i = 0;
                    for (; i < names.length; i++) {
                        if (names[i] == null) {
                            //找到了,装入注册用户
                            names[i] = name;
                            pwds[i] = pwd;
                            cards[i] = card;
                            break;
                        }
                    }
                    //没有空位
                    if (i == names.length) {
                        //扩容
                        //String[] newNames = new String[names.length + (names.length >> 1)];
                        String[] newNames = Arrays.copyOf(names, names.length + (names.length >> 1));
                        String[] newPwds = Arrays.copyOf(pwds, pwds.length + (pwds.length >> 1));
                        int[] newCards = Arrays.copyOf(cards, cards.length + (cards.length >> 1));
                        //添加新元素
                        newNames[names.length] = name;
                        newPwds[pwds.length] = pwd;
                        newCards[cards.length] = card;
                        //关联新老数组
                        names = newNames;
                        pwds = newPwds;
                        cards = newCards;
                    }
                    break;
                }
                case 2: {
                    System.out.println("登录");
                    System.out.println("请输入用户名:");
                    String name = sc.next();
                    //验证用户是否存在
                    for (int i = 0; i < names.length; i++) {
                        //找到了
                        if (name.equalsIgnoreCase(names[i])) {
                            //记录下标
                            index = i;
                            break;
                        }
                    }
                    if (index == -1) {
                        //用户名不存在
                        System.out.println("用户名不存在");
                        continue;
                    }
                    int i = 0;
                    for (; i < 3; i++) {
                        System.out.println("请输入密码:");
                        String pwd = sc.next();
                        //验证密码
                        if (pwds[index].equals(pwd)) {
                            System.out.println("登录成功!");
                            break;
                        } else {
                            System.out.println("密码错误!还剩" + (2 - i) + "次机会!");
                        }
                    }
                    //密码错误,重置登录用户下标
                    if (i == 3) {
                        index = -1;
                    }
                    break;
                }
                case 3: {
                    System.out.println("抽奖");
                    //如果没有登录就不能抽奖
                    if (index == -1) {
                        System.out.println("请先登录");
                        break;
                    }
                    System.out.println("请输入卡号:");
                    int card = sc.nextInt();
                    //判断卡号是否正确
                    if (card != cards[index]) {
                        System.out.println("卡号错误!");
                        //回到主菜单
                        continue;
                    }
                    //表示是否中奖
                    boolean win = false;
                    System.out.print("今日幸运卡号:");
                    //抽奖:随机生成5个数字
                    for (int i = 0; i < 5; i++) {
                        int rand = (int) (Math.random() * (9999 - 1000 + 1) + 1000);
                        if (rand == card) {
                            win = true;
                        }
                        System.out.print(rand + "\t");
                    }
                    //是否中奖
                    if (win) {
                        System.out.println("\n恭喜中奖!");
                    } else {
                        System.out.println("\n没有中奖!");
                    }
                }
                break;
            }
        }
    }


}

