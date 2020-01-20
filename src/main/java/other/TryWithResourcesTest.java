package other;

/**
 * @Author: 52483
 * @CreateDate: 2019/12/28 20:28:30
 * @Version: v1.0
 * @Description:
 */
public class TryWithResourcesTest implements AutoCloseable {

    @Override
    public void close() throws Exception {
        System.out.println("close it");
    }

    public void doSomething() {
        System.out.println("do something");
    }

    public static void main(String[] args) throws Exception {
        try (TryWithResourcesTest tryWithResourcesTest = new TryWithResourcesTest()) {
            tryWithResourcesTest.doSomething();
        }
        System.out.println("hahahahah");
    }
}
