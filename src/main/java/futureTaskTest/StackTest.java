package futureTaskTest;

public class StackTest {

    private  Object instance;

    public StackTest(){
        byte[] m = new byte[20 * 1024 * 1024];
    }

    public static void main(String[] args) {

        StackTest m1 =new StackTest();

        StackTest m2 =new StackTest();

        StackTest m3 =new StackTest();

        m1.instance=m2;
        m2.instance =m1;

        m1 =null;
        m2= null;
        System.gc();

    }
}
