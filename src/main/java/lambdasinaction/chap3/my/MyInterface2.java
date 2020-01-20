package lambdasinaction.chap3.my;

/**
 * @Author: 52483
 * @CreateDate: 2019/11/30 09:11:02
 * @Version: v1.0
 * @Description:
 */
public interface MyInterface2 {
    default void myMethod() {
        System.out.println("my method 2");
    }
}
