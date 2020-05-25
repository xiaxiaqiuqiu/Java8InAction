package other;

import java.util.stream.*;

/**
 * @Author: 52483
 * @CreateDate: 2020/4/13 21:48:34
 * @Version: v1.0
 * @Description:
 */
public class StreamPeekDemo {
    public static void main(String[] args) {
                Stream.of("one", "two", "three", "four")
                        .filter(e -> e.length() > 3)
                        .peek(e -> System.out.println("Filtered value: " + e))
                        .map(String::toUpperCase)
                         .peek(e -> System.out.println("Mapped value: " + e))
                        .collect(Collectors.toList());
    }
}
