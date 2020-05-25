package other;

import java.util.function.*;

/**
 * @Author: 52483
 * @CreateDate: 2020/1/5 17:08:00
 * @Version: v1.0
 * @Description:
 */
public class ConsAndIntConsTransfer {
    public void test(Consumer<Integer> consumer) {
        System.out.println("is intconsumer:" + (consumer instanceof IntConsumer));
        consumer.accept(100);
    }

    public static void main(String[] args) {
        Consumer<Integer> consumer = i -> System.out.println(i);
        IntConsumer intConsumer = i -> System.out.println(i);
        ConsAndIntConsTransfer consAndIntConsTransfer = new ConsAndIntConsTransfer();
      //  consAndIntConsTransfer.test(consumer);//面向对象方式
        consAndIntConsTransfer.test(consumer);//面向对象方式
        /**如下两种调用方式都会抛错start*/
        /*consAndIntConsTransfer.test(intConsumer);
        consAndIntConsTransfer.test((Consumer<Integer>)intConsumer);*/
        /**如下两种调用方式都会抛错end*/
      /*  consAndIntConsTransfer.test(consumer::accept);//函数式方式
        consAndIntConsTransfer.test(intConsumer::accept);//函数式方式
        consAndIntConsTransfer.test(i -> System.out.println(i));*/

    }
}
