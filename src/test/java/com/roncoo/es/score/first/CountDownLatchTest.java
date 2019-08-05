package com.roncoo.es.score.first;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {



    public static void main(String[] args) {
        int threadCount=10;

        final CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i <threadCount ; i++) {
            new Thread(new Runnable() {
                public void run() {
                    System.out.println("线程" + Thread.currentThread().getId() + "开始出发");
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    latch.countDown();
                }
            }).start();

        }

        try {
            latch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("结束");


    }



}
