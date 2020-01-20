package other;

/**
 * @Author: 52483
 * @CreateDate: 2020/1/13 22:20:51
 * @Version: v1.0
 * @Description:
 */
public class InnerClassAndLambda {
    Runnable r1 = () -> System.out.println(this);
    Runnable r2 = new Runnable() {
        @Override
        public void run() {
            System.out.println(this);
        }
    };

    public static void main(String[] args) throws InterruptedException {
        InnerClassAndLambda innerClassAndLambda = new InnerClassAndLambda();
        Thread thread1 = new Thread(innerClassAndLambda.r1);
        thread1.start();
        Thread.sleep(1000);
        Thread thread2 = new Thread(innerClassAndLambda.r2);
        thread2.start();

    }

}

