package lambdasinaction.chap7.my;

import java.util.concurrent.*;
import java.util.stream.*;

/**
 * @Author: 52483
 * @CreateDate: 2020/4/19 17:00:47
 * @Version: v1.0
 * @Description: 利用fork-join框架并行计算数组中的值
 *
 */
public class MyForkJoinSumCalculator extends RecursiveTask<Long> {
    public static final long THRESHOLD = 10;

    private final long[] numbers;
    private final int start;
    private final int end;
    public MyForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    private MyForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }
    @Override
    protected Long compute() {
        int length = end - start;
        if(length < THRESHOLD){
            return computeSequentially();
        }

        MyForkJoinSumCalculator left = new MyForkJoinSumCalculator(numbers, start, start + length/2);
        left.fork();
        MyForkJoinSumCalculator right = new MyForkJoinSumCalculator(numbers, start + length/2, end);
        long rightResult = right.compute();
        long leftResult = left.join();
        long result = leftResult + rightResult;
        return result;
    }

    private long computeSequentially() {
        long sum = 0;
        for(int i = start; i < end; i ++){
            sum = sum + numbers[i];
        }
        return  sum;
    }

    public static long forkJoinSum(long n){
        long[] nums = LongStream.rangeClosed(0, n).toArray();
        ForkJoinTask<Long> forkJoinTask = new MyForkJoinSumCalculator(nums);
        long result = MyParallelStreamsHarness.FORK_JOIN_POOL.invoke(forkJoinTask);
        return result;
    }
}
