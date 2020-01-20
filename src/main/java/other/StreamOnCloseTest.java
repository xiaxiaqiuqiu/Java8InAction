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

        List<String> list = Arrays.asList("hello", "add", "bb");
        Stream stream = list.stream();
        stream.onClose(() -> {
            System.out.println("aaaaaaaaaa");
            throw new NullPointerException("first exception");
        }).onClose(() -> {
            System.out.println("bbbbbbbbbbbbbb");
            throw new ArithmeticException("second exception");
        }).onClose(() -> {
            System.out.println("ccccccccccccccccc");
            throw new ConcurrentModificationException("third exception");
        });
        stream.close();

    }

}
