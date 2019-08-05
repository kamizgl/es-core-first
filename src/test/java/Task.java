import java.util.concurrent.*;

public class Task implements Callable<Integer> {

    public Integer call() throws Exception {
        System.out.println("子线程在进行计算");
        Thread.sleep(1500);
        int sum =0;
        for (int i = 0; i < 100; i++) {
            sum+=i;
        }
        return sum;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Task task = new Task();
        Task task2 = new Task();
        FutureTask<Integer> integerFutureTask = new FutureTask<Integer>(task);
        FutureTask<Integer> integerFutureTask2 = new FutureTask<Integer>(task2);

        executorService.submit(integerFutureTask);
        executorService.submit(integerFutureTask2);

        System.out.println("主线程正在执行任务");


        try {
            Integer integer = integerFutureTask.get();
//            Integer integer = integerFutureTask.get(1,TimeUnit.SECONDS);
            System.out.println("运行结果 已经出现 他是);" + integer);
            Integer integer1 = integerFutureTask2.get();
            System.out.println("运行结果 已经出现 他是)2;" + integer1);

        } catch (InterruptedException e) {
            e.printStackTrace();

        } catch (ExecutionException e) {
            System.out.println("123123123");
            e.printStackTrace();
        }

        System.out.println("所有任务执行完毕");
        executorService.shutdown();
    }
}
