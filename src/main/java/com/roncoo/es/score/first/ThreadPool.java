package com.roncoo.es.score.first;

import java.util.concurrent.*;

public class ThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ThreadProcess process = new ThreadProcess();
        try {
            for (int i = 0; i < 7; i++) {
                process.process(i + "");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static class ThreadProcess {

        BlockingQueue blockingQueue = new LinkedBlockingDeque(4);
        ThreadPoolExecutor poolExecutor =new ThreadPoolExecutor(1, 3, 6, TimeUnit.SECONDS, blockingQueue, new ThreadPoolExecutor.AbortPolicy());;


        public void process(String name) {

            /**
             * 1, 核心线程数，核心线程除非特别设定poolExecutor.allowCoreThreadTimeOut(true)是不会受到存活时间的影响。
             * 3, 最大线程数, 最大线程-核心线程=存活时间之后需要回收的线程,
             * 5, 线程存活时间, 如果不设定poolExecutor.allowCoreThreadTimeOut(true)，那么五秒后，回收两个线程，剩余一个核心线程不会回收
             * TimeUnit.SECONDS,时间单位。就是前面数字的单位
             * blockingQueue, 阻塞队列的长度，是当线程池慢了之后后面可以排队的线程的线程的长度
             * new ThreadPoolExecutor.CallerRunsPolicy()，当线程池满了，并且队列也满了之后的处理策略.总共有4种，实际使用中，也就这种比较靠谱了。
             */
//            BlockingQueue blockingQueue = blockingQueue;
//            if (poolExecutor == null) {
//                poolExecutor = poolExecutor
//            }
            poolExecutor.allowCoreThreadTimeOut(true);
            Task t = new Task(name);
            poolExecutor.execute(t);

            /**
             * java还自己提供了不同的工具类。其实也就是new ThreadPoolExecutor固定塞入了不同的参数。
             * 不过我觉得当你明白了各个参数的含义后，还是自己去设定比较符合你的需求
             * schedule那个比较特殊，是另一种实现方式
             */
//            ThreadPoolExecutor poolExecutor2 = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        }


    }

    static class Task implements Runnable {
        private String name;

        Task(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("task " + name + " is runing");
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}