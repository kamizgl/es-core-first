package futureTaskTest;

public class InterruptionInjava implements Runnable {

    public void run() {
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("yes!! im interupted but im still running");
            }
            else{
                System.out.println("----------------------------");
            }
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new InterruptionInjava(), "testThread");
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        System.out.println("begin interupt ....");
        thread.interrupt();
        System.out.println("end interupt ....");

    }
}
