package lambdasinaction.chap7.my;

import lambdasinaction.chap7.*;

import java.util.concurrent.*;
import java.util.function.*;

public class MyParallelStreamsHarness {

    public static final ForkJoinPool FORK_JOIN_POOL = new ForkJoinPool();

    public static void main(String[] args) {

        System.out.println("ForkJoin sum done, result: " + involve(MyForkJoinSumCalculator::forkJoinSum, 100L));
       // System.out.println("SideEffect sum done in: " + measurePerf(ParallelStreams::sideEffectSum, 10_000_000L) + " msecs" );
        //System.out.println("SideEffect prallel sum done in: " + measurePerf(ParallelStreams::sideEffectParallelSum, 10_000_000L) + " msecs" );
    }
    public static <T, R> R involve(Function<T, R> f, T input) {
        R result = f.apply(input);
        return result;
    }
}
