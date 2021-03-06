package com.interview.pre.boss;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TestABC {
    /*public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatchA=new CountDownLatch(1);
        CountDownLatch countDownLatchB=new CountDownLatch(1);
        Semaphore semaphoreC = new Semaphore(1);
        Semaphore semaphoreB = new Semaphore(1);

        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphoreC.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
                semaphoreC.release();
            }
        }, "Thread-C");

        Thread threadB = new Thread(() -> {
            try {
                semaphoreB.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            semaphoreB.release();
            countDownLatchB.countDown();
        }, "Thread-B");

        Thread threadA = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            countDownLatchA.countDown();
        }, "Thread-A");

        semaphoreB.acquire();
        semaphoreC.acquire();

        threadA.start();
        threadB.start();
        threadC.start();

        countDownLatchA.await();
        semaphoreB.release();
        countDownLatchB.await();
        semaphoreC.release();
    }*/

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrierA=new CyclicBarrier(2);
        CyclicBarrier cyclicBarrierB=new CyclicBarrier(2);

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());

                // ??????????????????A????????????
                try {
                    cyclicBarrierA.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                    throw new RuntimeException("cylicBarrier.await()???????????????",e);
                }
            }
        }, "Thread-A");

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());

                // ??????????????????B????????????
                try {
                    cyclicBarrierA.await();
                    cyclicBarrierB.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                    throw new RuntimeException("cylicBarrier.await()???????????????",e);
                }
            }
        }, "Thread-B");

        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cyclicBarrierB.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                    throw new RuntimeException("cylicBarrier.await()???????????????",e);
                }
                // ???????????????????????????????????????????????????
                System.out.println(Thread.currentThread().getName());
            }
        }, "Thread-C");

        threadA.start();
        threadB.start();
        threadC.start();
    }

}