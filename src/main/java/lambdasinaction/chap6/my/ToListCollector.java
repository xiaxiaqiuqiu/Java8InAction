package lambdasinaction.chap6.my;

import lambdasinaction.chap6.*;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static java.util.stream.Collector.Characteristics.*;
import static lambdasinaction.chap6.Dish.menu;

public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

    @Override
    public Supplier<List<T>> supplier() {
        System.out.println("create supplier");
        return () -> {
            System.out.println("supplier invoke");
            return new ArrayList<T>();};
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        System.out.println("create accumulator");
        return (list, item) ->{
            System.out.println("accumulator invoke:" +list+"  thread: " + Thread.currentThread().getName());
            list.add(item);
        };
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        System.out.println("finisher invoke");
        return i -> i;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        System.out.println("create combiner");
        return (list1, list2) -> {
            System.out.println("combiner invoke");
         //   System.out.println("----------list1:" + list1 +"   ;list2:" + list2);
            list1.addAll(list2);
            return list1;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        /*即使加入了CONCURRENT属性也是不起作用的，因为List本身是有序的
        * */
        return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH, CONCURRENT));
    }


    public static void main(String[] args) {
        for(int i = 0; i < 1; i++) {
            List<String> strs = Arrays.asList("aa", "bbb", "ccc", "dd", "ee", "ff", "aa", "bbb", "ccc", "dd", "ee", "ff", "aa", "bbb", "ccc", "dd", "ee", "ff");
            List<String> res = strs.parallelStream().collect(new ToListCollector<String>());
            //List<String> res = strs.stream().collect(new ToListCollector<String>());
            System.out.println(res);
       }


    }

}
