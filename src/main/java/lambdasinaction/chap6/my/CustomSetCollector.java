package lambdasinaction.chap6.my;

import org.w3c.dom.ls.*;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static java.util.stream.Collector.Characteristics.CONCURRENT;
import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;
import static java.util.stream.Collector.Characteristics.UNORDERED;

/**
 * @Author: 52483
 * @CreateDate: 2019/12/21 15:09:25
 * @Version: v1.0
 * @Description:
 * 1、对应视频：张龙深入理解Java8 P28
 * 2、【1】处的执行结果为：
 * supplier invoke
 * accumulator invoke
 * combiner invoke
 * characteristics invoke
 * characteristics invoke
 * [aaa, dd, e33, ddd, hello, dddd]
 *
 * 源码的关键步骤为：
 * Stream.collect具体实现为ReferencePipeline.collect
 * -->container = evaluate(ReduceOps.makeRef(collector));
 *  ReduceOps.makeRef
 *      -->Supplier<I> supplier = Objects.requireNonNull(collector).supplier();
 *      -->BiConsumer<I, ? super T> accumulator = collector.accumulator();
 *      -->BinaryOperator<I> combiner = collector.combiner();
 *      所以我们能看到输出 supplier invoke
 *                       accumulator invoke
 *                       combiner invoke
 *
 *      -->ReduceOps L185 return collector.characteristics().contains(Collector.Characteristics.UNORDERED)//判断是否有序
 *  evaluate
 *      -->terminalOp.evaluateSequential(this, sourceSpliterator(terminalOp.getOpFlags()));  //terminalOp.getOpFlags() 第一次打印characteristics invoke
 * -->ReferencePipeline L580  return collector.characteristics().contains(Collector.Characteristics.IDENTITY_FINISH)
 *                ? (R) container
 *                : collector.finisher().apply(container);   // collector.characteristics()第二次打印characteristics invoke
 *                                                           // 判断是否包含IDENTITY_FINISH ,如果包含则将结果容器强制类型转换直接返回，否则要对结果容器执行finisher()
 *3、IDENTITY_FINISH作用
 *  Collectors类中
 *   private static <I, R> Function<I, R> castingIdentity() {
 *         return i -> (R) i;
 *     }
 *   创建CollectorImpl时，如果我们不传入finisher,则默认使用castingIdentity()
 *  CollectorImpl(Supplier<A> supplier,
 *  *                       BiConsumer<A, T> accumulator,
 *  *                       BinaryOperator<A> combiner,
 *  *                       Set<Characteristics> characteristics) {
 *  *             this(supplier, accumulator, combiner, castingIdentity(), characteristics);
 *  *         }
 *
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
        /*start !注意，下面的写法会报错。在supplier()方法中我们可以返回HashSet也可以返回TreeSet等*/
        //return HashSet<T>::add;
        /*end*/
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
        return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH, UNORDERED));
        //return Collections.emptySet();

    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("aaa", "hello", "hello", "ddd","dd","dddd","e33");
        Set<String> set = list.stream().collect(new CustomSetCollector<>());//【1】
        System.out.println(set);

    }
}
