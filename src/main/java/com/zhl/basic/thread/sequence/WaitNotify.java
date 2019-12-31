package com.zhl.basic.thread.sequence;

/**
 * @author Lenovo
 * @title: WaitNotify
 * @projectName basic
 * @description: TODO
 * @date 2019/11/29 14:37
 */
public class WaitNotify {
    private static Object lock1 = new Object();
    private static int flag = 3;

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock1) {
                   while(true){
                    if (flag == 3) {
                        System.out.println(3);
                        flag--;
                        lock1.notifyAll();
                    } else {
                        if(flag==0){
                            break;
                        }
                        try {
                            lock1.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        ;
                    }
                }}
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock1) {
                    while(true) {
                        if (flag == 2) {
                            System.out.println(2);
                            flag--;
                            lock1.notifyAll();
                        } else {
                            if(flag==0){
                                break;
                            }
                            try {

                                lock1.wait();

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ;
                        }
                    }
                }
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock1) {
                    while(true){
                    if (flag == 1) {
                        System.out.println(1);
                        flag--;
                        lock1.notifyAll();
                    } else {
                        if(flag==0){
                            break;
                        }
                        try {
                            lock1.wait();

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        ;
                    }
                }}
            }
        });
        thread2.start();
        thread3.start();
        thread1.start();


    }
}
