package other;

import java.util.*;
import java.util.stream.*;

/**
 * @Author: 52483
 * @CreateDate: 2020/1/1 09:41:29
 * @Version: v1.0
 * @Description:
 */
public class StreamOnCloseTest {
    public static void main(String[] args) {

        // test1();
        // test2();
        test3();

    }

    private static void test1() {
        List<String> list = Arrays.asList("hello", "add", "bb");
        Stream stream = list.stream();
        stream.onClose(() -> {
            System.out.println("aaaaaaaaaa");
            throw new NullPointerException("first exception");
        }).onClose(() -> {
            System.out.println("bbbbbbbbbbbbbb");
            throw new ArithmeticException("second exception"); //这个异常会被压制（suppressed）
        }).onClose(() -> {
            System.out.println("ccccccccccccccccc");
            throw new ConcurrentModificationException("third exception"); //这个异常会被压制（suppressed）
        });
        stream.close();
    }

    private static void test2() {
        List<String> list = Arrays.asList("hello", "add", "bb");
        try (Stream<String> stream = list.stream()) {
            stream.onClose(() -> {
                System.out.println("aaaaaaaaaa");
                throw new NullPointerException("first exception");
            }).onClose(() -> {
                System.out.println("bbbbbbbbbbbbbb");
                throw new NullPointerException("second exception"); //这个异常也会被压制（suppressed）
            }).onClose(() -> {
            System.out.println("ccccccccccccccccc");
            throw new ConcurrentModificationException("third exception"); //这个异常会被压制（suppressed）
            }).forEach(System.out::println);


        }
    }
    /**
     * 输出的结果中只有一个异常，因为相同的异常不可能压制自己
     * */
    private static void test3() {
        List<String> list = Arrays.asList("hello", "add", "bb");
        NullPointerException exception = new NullPointerException("exception");
        try (Stream<String> stream = list.stream()) {
            stream.onClose(() -> {
                System.out.println("aaaaaaaaaa");
                throw exception;
            }).onClose(() -> {
                System.out.println("bbbbbbbbbbbbbb");
                throw exception;
            }).onClose(() -> {
                System.out.println("ccccccccccccccccc");
                throw exception;
            }).forEach(System.out::println);


        }
    }

}
