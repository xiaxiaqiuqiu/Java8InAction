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
 * 2、当使用了parallelStream，并且characteristics中没有设置CONCURRENT，则会启动多个线程来操作多个累加器，并通过combiner来合并结果
 * 3、使用了parallelStream，并且characteristics中设置了CONCURRENT，则只生成一个累加器，会有多个线程操作它。不需要调用combiner()。
 *   不会输出"combiner invoke"，combiner()中的Lambda表达式也不会被调用
 *
 *    关键的源代码：
 *    Stream.collect具体实现为ReferencePipeline.collect
 *    ReferencePipeline L570
 *            if (isParallel()
 *                 && (collector.characteristics().contains(Collector.Characteristics.CONCURRENT))
 *                 && (!isOrdered() || collector.characteristics().contains(Collector.Characteristics.UNORDERED))) {
 *             container = collector.supplier().get();
 *             BiConsumer<A, ? super P_OUT> accumulator = collector.accumulator();
 *             forEach(u -> accumulator.accept(container, u));
 *         }
 *         else {
 *             container = evaluate(ReduceOps.makeRef(collector));
 *         }
 * 4、使用串行流的话，只生成一个累加器，【1】只执行一次
 * 5、并行流会开多少个线程呢？默认情况下会生成CPU内核数量的线程，比如4核的CPU会生成8个线程（超线程的情况，硬件中是4个内核，8个逻辑处理器）
 * 【2】处可以打印出来处理器的数量
 *  当然也可能少于8个，取决于任务数的多少
 */
public class CustomMapCollector<T> implements Collector<T, Set<T>, Map<T, T>> {
    /**
     * Collect中的文档
     *     A a1 = supplier.get();
     *     accumulator.accept(a1, t1);
     *     accumulator.accept(a1, t2);
     *     R r1 = finisher.apply(a1);  // result without splitting，串行
     *
     *     A a2 = supplier.get();
     *     accumulator.accept(a2, t1);
     *     A a3 = supplier.get();
     *     accumulator.accept(a3, t2);
     *     R r2 = finisher.apply(combiner.apply(a2, a3));  // result with splitting，并行
     * @return
     */
    @Override
    public Supplier<Set<T>> supplier() {
        System.out.println("supplier invoke");
        //return HashSet<T>::new;
        return () -> {
            System.out.println("---------------");/**【1】*/
            return new HashSet<T>();

        };
    }

    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        System.out.println("accumulator invoke");

        return (set, item) -> {
            //加上Characteristics.CONCURRENT，并且使用parallelStream，如果不打印set也不会报错。 one thread to modify a Collection
            //while another thread is iterating over it.可能会抛出java.util.ConcurrentModificationException
            System.out.println("set:" + set + " accumulator:" + Thread.currentThread().getName());
            set.add(item);
        };
    }

    @Override
    public BinaryOperator<Set<T>> combiner() {
        System.out.println("combiner invoke");
        return (set1, set2) -> {
            System.out.println("set1:" + set1 + " set2:" + set2);
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
        /**1、加上Characteristics.IDENTITY_FINISH，程序会报错，因为Set不能直接转换成Map
         * 关键的源代码：
         * Stream.collect具体实现为ReferencePipeline.collect
         * -->ReferencePipeline L580  return collector.characteristics().contains(Collector.Characteristics.IDENTITY_FINISH)
         *                ? (R) container
         *                : collector.finisher().apply(container);   // 判断是否包含IDENTITY_FINISH ,如果包含则将结果容器强制类型转换直接返回，否则要对结果容器执行finisher()
         *
         */
        /**2、加上Characteristics.CONCURRENT，多个线程会操作同一个accumulator,就不用调用combiner整合累加器，最好不要加这个属性*/

        return Collections.unmodifiableSet(EnumSet.of(Characteristics.UNORDERED
         //       ,Characteristics.CONCURRENT
        ));
    }

    public static void main(String[] args) {
        //System.out.println(Runtime.getRuntime().availableProcessors());/**【2】*/
        for (int i = 0; i < 1; i++) {
            List<String> list = Arrays.asList("haha", "haha", "lal", "word", "hello", "a", "a", "kj", "ad", "b", "i");
            Set<String> set = new HashSet<>();
            set.addAll(list);
            Map<String, String> map = set.parallelStream().collect(new CustomMapCollector<String>());
            System.out.println(map);
        }
    }


}
