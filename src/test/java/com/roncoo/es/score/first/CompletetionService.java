package com.roncoo.es.score.first;

import java.util.Random;
import java.util.concurrent.*;

public class CompletetionService {

    public static class Task implements Callable<Integer>{

        private int i;

        Task(int i) {
            this.i = i;
        }
        public Integer call() throws Exception {
            Thread.sleep(new Random().nextInt(5000));

            System.out.println(Thread.currentThread().getName()+"  "+i);
            return i;
        }
    }


    public void run(){
        ExecutorService executorService =
                Executors.newFixedThreadPool(10);

        CompletionService<Integer>  completionService  = new ExecutorCompletionService<Integer>(executorService);
        for (int i = 0; i < 10; i++) {
            completionService.submit(new CompletetionService.Task(i));

        }
    }

    public static void main(String[] args) {
        new CompletetionService().run();
    }
}
