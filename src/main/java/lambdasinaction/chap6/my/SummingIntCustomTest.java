package lambdasinaction.chap6.my;

import java.net.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static java.util.stream.Collector.Characteristics.CONCURRENT;
import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;
import static java.util.stream.Collector.Characteristics.UNORDERED;

/**
 * @Author: 52483
 * @CreateDate: 2019/12/16 22:11:59
 * @Version: v1.0
 * @Description:
 * 用Integer做容器是不能保存值的
 */
public class SummingIntCustomTest {
    public static void main(String[] args) {
        List<Integer> nos = Arrays.asList(1, 2, 3, 5, 9);
       Integer sum1 = nos.stream().collect(new Collector<Integer, Integer, Integer>() {


            @Override
            public Supplier<Integer> supplier() {
                System.out.println("enter supplier");
                return () -> Integer.valueOf(0);
            }

            @Override
            public BiConsumer<Integer, Integer> accumulator() {
                return (a, b) -> {

                    a = Integer.valueOf(a + b);

                };
            }

            @Override
            public BinaryOperator<Integer> combiner() {
                return (a, b) -> {a = Integer.valueOf(a + b);
                return  a;};
            }

            @Override
            public Function<Integer, Integer> finisher() {
                return null;
            }

            @Override
            public Set<Characteristics> characteristics() {
                return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH));
            }
        });

       System.out.println(sum1);
       Integer sum2 = nos.stream().collect(new Collector<Integer, int[], Integer>() {


            @Override
            public Supplier<int[]> supplier() {
               return () -> new int[1];

            }

            @Override
            public BiConsumer<int[], Integer> accumulator() {
                return (a, t) -> a[0] += t;


            }

            @Override
            public BinaryOperator<int[]> combiner() {
                return (a,b)->{
                   a[0] += b[0]; return a;

                };
            }

            @Override
            public Function<int[], Integer> finisher() {
                return a -> a[0];
            }

            @Override
            public Set<Characteristics> characteristics() {
                return new HashSet<>();
            }
        });
        System.out.println(sum2);
    }
}
