package futureTaskTest;

import javafx.concurrent.Task;

import java.util.concurrent.*;

public class FutureTaskTest {

    private int i= 0;

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future future = executorService.submit(
                new Callable<Object>() {
            @Override
            public Object call() throws Exception {

                Long start = System.currentTimeMillis();
                while (true) {
                    Long current = System.currentTimeMillis();
                    if ((current - start) > 1000) {
                        return 1;
                    }
                }
            }
        });

        try {
            Integer result = (Integer)future.get();
            System.out.println(result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
