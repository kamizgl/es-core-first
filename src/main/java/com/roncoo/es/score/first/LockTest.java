package com.roncoo.es.score.first;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    private Lock lock = new ReentrantLock();

    public void tryLockTest(Thread thread) {

        try {

            if (lock.tryLock(3000, TimeUnit.SECONDS)) {
                try {
                    System.out.println("线程" + thread.getName() + "获取到了锁");
                    Thread.sleep(2000);

                } catch (Exception e) {
                    System.out.println("线程" + thread.getName() + "执行完毕释放锁");

                } finally {
                    System.out.println("线程" + thread.getName() + "执行完毕释放锁");
                    lock.unlock();
                }
            } else {
                System.out.println("我是线程" + Thread.currentThread().getName() + "当前锁被别人占用，我无法获取锁");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final LockTest lockTest = new LockTest();

        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                lockTest.tryLockTest(Thread.currentThread());
            }
        }, "thread1");

    Thread thread2 = new Thread(new Runnable() {
        public void run() {
            lockTest.tryLockTest(Thread.currentThread());
        }
    }, "thread2");
        thread1.start();
        thread2.start();
    }


}



