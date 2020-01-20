package lambdasinaction.chap6.my;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * @Author: 52483
 * @CreateDate: 2019/12/21 15:09:25
 * @Version: v1.0
 * @Description: 1、自定义了Map收集器
 * 2、当使用了parallelStream，则会启动多个线程来操作多个累加器，并通过combiner来合并结果
 * 3、使用了parallelStream，并且characteristics中设置了concurrent，则会有多个线程操作同一个累加器
 */
public class CustomMapCollector<T> implements Collector<T, Set<T>, Map<T, T>> {
    @Override
    public Supplier<Set<T>> supplier() {
        System.out.println("supplier invoke");
        //return HashSet<T>::new;
        return () -> {
            System.out.println("---------------");
            return new HashSet<T>();

        };
    }

    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        System.out.println("accumulator invoke");

        return (set, item) -> {
            //加上Characteristics.CONCURRENT后，如果不打印set也不会报错。 one thread to modify a Collection
            //while another thread is iterating over it.可能会抛出java.util.ConcurrentModificationException
            System.out.println("set:" + set + " accumulator:" + Thread.currentThread().getName());
            set.add(item);
        };
    }

    @Override
    public BinaryOperator<Set<T>> combiner() {
        System.out.println("combiner invoke");

        return (set1, set2) -> {
            // System.out.println("set1:" + set1 + " set2:" + set2);
            set1.addAll(set2);
            return set1;
        };
    }

    @Override
    public Function<Set<T>, Map<T, T>> finisher() {
        System.out.println("finisher invoke");
        return set -> {
            //System.out.println("set:" + set);
            Map<T, T> map = new ConcurrentHashMap<>();
            set.stream().forEach(item -> {
                map.put(item, item);
            });
            return map;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        //加上Characteristics.CONCURRENT，多个线程会操作同一个accumulator,就不用调用combiner整合累加器，最好不要加这个属性
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.UNORDERED));
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            List<String> list = Arrays.asList("haha", "haha", "lal", "word", "hello", "a", "a", "kj", "ad", "b", "i");
            Set<String> set = new HashSet<>();
            set.addAll(list);
            Map<String, String> map = set.parallelStream().collect(new CustomMapCollector<String>());
            System.out.println(map);
        }
    }


}
