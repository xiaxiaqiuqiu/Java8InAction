package lambdasinaction.chap6.my;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * @Author: 52483
 * @CreateDate: 2019/12/21 15:09:25
 * @Version: v1.0
 * @Description:
 */
public class CustomSetCollector<T> implements Collector<T, Set<T>, Set<T>> {
    @Override
    public Supplier<Set<T>> supplier() {
        System.out.println("supplier invoke");
        return HashSet<T>::new;
    }

    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        System.out.println("accumulator invoke");
        return Set<T>::add;
    }

    @Override
    public BinaryOperator<Set<T>> combiner() {
        System.out.println("combiner invoke");

        return (set1, set2) -> {
            set1.addAll(set2);
            return set1;
        };
    }

    @Override
    public Function<Set<T>, Set<T>> finisher() {
        System.out.println("finisher invoke");
        //   return t -> t;
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        System.out.println("characteristics invoke");
        //返回不可变的集合
        // return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH, UNORDERED));
        return Collections.emptySet();

    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("aaa", "hello", "hello");
        Set<String> set = list.stream().collect(new CustomSetCollector<>());
        System.out.println(set);
    }
}
