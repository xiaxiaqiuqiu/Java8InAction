package lambdasinaction.chap6.my;

import lambdasinaction.chap6.*;

import java.util.*;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

/**
 * @Author: 52483
 * @CreateDate: 2019/12/15 21:23:50
 * @Version: v1.0
 * @Description:
 */
public class StreamTest {
    public static void main(String[] args) {
        //获取最小的calories
        /* Dish.menu.stream().collect(Collectors.minBy(Comparator.comparingInt(Dish::getCalories))).ifPresent(dish -> System.out.println(dish.getCalories()));*/
        //获取calories的平均值
       /* Double avarageCalories = Dish.menu.stream().collect(Collectors.averagingInt(Dish::getCalories));
        System.out.println(avarageCalories);*/
        //求总和
        /* System.out.println(Dish.menu.stream().collect(summingInt(Dish::getCalories)));*/

        //汇总信息
       /* IntSummaryStatistics summaryStatistics = Dish.menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(summaryStatistics);*/
        //拼接所有的菜名
     /*   String names = Dish.menu.stream().map(Dish::getName).collect(Collectors.joining(",", "<start> ", " <end>"));
        System.out.println(names);*/
        /**根据是否是蔬菜和类型进行二级分组*/
       /* Map<Boolean, Map<Dish.Type, List<Dish>>> cascadeGroup = Dish.menu.stream().collect(groupingBy(Dish::isVegetarian, groupingBy(Dish::getType)));
        System.out.println(cascadeGroup);*/
        /**根据calories是否大于500进行分区*/
       /*  Map<Boolean, List<Dish>> caloriesPartition = Dish.menu.stream().collect(partitioningBy(dish -> dish.getCalories() > 500));
        System.out.println(caloriesPartition);*/
        /**根据calories是否大于500进行分区，并统计个数*/
       /* Map<Boolean, Long> partitionAndCounting = Dish.menu.stream().collect(partitioningBy(dish -> dish.getCalories() > 500, counting()));
        System.out.println(partitionAndCounting);*/
        /**根据type进行分组，然后取出每个组中的最小calories*/
        Map<Dish.Type, Dish> minCaloriesGroupByType = Dish.menu.stream().collect(groupingBy(Dish::getType, collectingAndThen(minBy(comparing(Dish::getCalories)), Optional::get)));
        System.out.println(minCaloriesGroupByType);


    }

}
