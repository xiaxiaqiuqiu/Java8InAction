package lambdasinaction.chap4.my;

import java.util.IntSummaryStatistics;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * @Author: 52483
 * @CreateDate: 2019/11/23 21:59:35
 * @Version: v1.0
 * @Description:
 */
public class StreamDemo1 {
    public static void main(String[] args) {
        //generate生成无限制流, 生成随机数流
        Stream<String> stream = Stream.generate(UUID.randomUUID()::toString);
        stream.findFirst().ifPresent(System.out::println);

        //生成不断迭代的无限流
        Stream.iterate(1, item -> item + 2).limit(6).forEach(System.out::println);

        //找出该流中大于2的元素，然后将每个元素乘以2， 然后忽略掉流中的前两个元素，然后取出流中的前两个元素，最后求出流中
        //元素的总和
        int sum = Stream.iterate(1, item -> item + 2).limit(6).mapToInt(item -> item + 2).skip(2).limit(2).sum();
        //统计常用的结果可以用summaryStatistics方法,但要注意如果没有元素， min = Integer.MAX_VALUE; max = Integer.MIN_VALUE;
        IntSummaryStatistics summaryStatistics = Stream.iterate(1, item -> item + 2).limit(6).mapToInt(item -> item + 2).skip(2).limit(2).summaryStatistics();
        int min = summaryStatistics.getMin();
        int max = summaryStatistics.getMax();

    }
}
