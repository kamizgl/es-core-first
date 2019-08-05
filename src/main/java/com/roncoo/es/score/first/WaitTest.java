package com.roncoo.es.score.first;

public class WaitTest {

    public static void main(String[] args) {
        TreadA t1 = new TreadA("t1");

//        synchronized (t1) {
//            try {
//                System.out.println(Thread.currentThread().getName() + "start t1");
//                t1.start();
//                System.out.println(Thread.currentThread().getName() + "wart");
//                t1.wait();
//                System.out.println(Thread.currentThread().getName() + "wart");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        for (int i = 0; i < 10000; i++) {
            new WaitTest();
        }

    }
}
