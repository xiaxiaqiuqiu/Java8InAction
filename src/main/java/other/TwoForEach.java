package other;

import java.util.*;

/**
 * @Author: 52483
 * @CreateDate: 2020/1/12 14:36:18
 * @Version: v1.0
 * @Description:
 */
public class TwoForEach {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("haha", "bbboo", "ddd");
        strings.stream().map(item -> item).forEach(System.out::println);
        //没用用到stream, 仅仅是使用的迭代
        strings.forEach(System.out::println);

    }
}
