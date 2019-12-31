package com.zhl.basic.thread.sequence;

import java.util.concurrent.CountDownLatch;

/**
 * @author Lenovo
 * @title: CountDownLatchSeq
 * @projectName basic
 * @description: TODO
 * @date 2019/11/29 13:36
 */
public class CountDownLatchSeq {
    static CountDownLatch countDownLatch1 = new CountDownLatch(1);
    static CountDownLatch countDownLatch2 = new CountDownLatch(1);
    public static void main(String[] args) throws InterruptedException {


        new Thread(new R1()).start();
        new Thread(new R2()).start();
        new Thread(new R3()).start();

    }

    static class R1 implements Runnable {


        @Override
        public void run() {
            try {
                countDownLatch2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(1);
                countDownLatch2.countDown();

        }
    }

    static class R2 implements Runnable {


        @Override
        public void run() {
            try {
                countDownLatch1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(2);
                countDownLatch2.countDown();
            }
        }


    static class R3 implements Runnable {

        @Override
        public void run() {
                System.out.println(3);
                countDownLatch1.countDown();

        }
    }

}
