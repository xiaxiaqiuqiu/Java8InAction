package lambdasinaction.chap3.my;

/**
 * @Author: 52483
 * @CreateDate: 2019/11/30 09:12:25
 * @Version: v1.0
 * @Description:
 */
public class MyClass implements MyInterface1, MyInterface2 {
    @Override
    public void myMethod() {
        MyInterface1.super.myMethod();
        // MyInterface2.super.myMethod();
        // System.out.println("my method");
    }

    public void k() {
        MyInterface1.super.myMethod();
    }

    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        myClass.myMethod();


    }
}
