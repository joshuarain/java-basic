package com.zhl.basic.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch的简单例子
 *
 * @author JoonWhee
 * @Date 2018年1月27日
 */
public class CountDownLatchTest {

    static CountDownLatch timeOutCountDownLatch = new CountDownLatch(1);

    public static void main(String args[]) {
        try {
            new Driver(10);
            testAwaitTimtOut();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 测试带超时的await方法
    public static void testAwaitTimtOut() throws InterruptedException {
        System.out.println("before await(long timeout, TimeUnit unit)");
        timeOutCountDownLatch.await(3, TimeUnit.SECONDS);   //等待超时时间为3秒
        System.out.println("after await(long timeout, TimeUnit unit)");
    }

}

class Driver {
    public Driver(int N) throws InterruptedException {
        CountDownLatch startSignal = new CountDownLatch(1); // 定义一个CountDownLatch, 计数器值为1, 也就是每次await(), 需要执行1次countDown(), 才能继续执行await()外面的代码
        CountDownLatch doneSignal = new CountDownLatch(N);  // 定义一个CountDownLatch, 计数器值为N, 也就是每次await(), 需要执行N次countDown(), 才能继续执行await()外面的代码

        for (int i = 0; i < N; ++i) {
            // 创建并启动线程
            new Thread(new Worker(startSignal, doneSignal)).start();
        }
        Thread.sleep(2000); // 睡眠2秒, 可以看到10个线程都在等待startSignal.countDown()执行
        System.out.println();
        startSignal.countDown(); // 解除所有线程的阻塞
        doneSignal.await(); // 等待所有线程执行doneSignal.countDown(), 才通过
        System.out.println();
        System.out.println("Main thread-after:doneSignal.await()------");
    }
}

class Worker implements Runnable {
    private final CountDownLatch startSignal;
    private final CountDownLatch doneSignal;

    Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + "-before:startSignal.await()");
            startSignal.await();    // 线程会在此处等待, 直到startSignal.countDown()执行
            System.out.println(Thread.currentThread().getName() + "-after:startSignal.await()");
            doneSignal.countDown();
        } catch (InterruptedException ex) {
        } // return;
    }
}

