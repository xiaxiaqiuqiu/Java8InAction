package lambdasinaction.chap6.my;

import lambdasinaction.chap6.Dish;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: 52483
 * @CreateDate: 2019/12/7 15:44:59
 * @Version: v1.0
 * @Description:
 */
public class GroupDemo {
    public static void main(String[] args) {
        /**按name进行分组*/
        /* Map<String, List<Dish>> groupByName = Dish.menu.stream().collect(Collectors.groupingBy(Dish::getName));*/
        /**按name进行分组，并统计每组的元素*/
       /* Map<String, Long> groupByNameAndCount = Dish.menu.stream().collect(Collectors.groupingBy(Dish::getName, Collectors.counting()));
        System.out.println(groupByNameAndCount);*/
        /**按name进行分组，并求取calorie的平均值*/
        Map<String, Double> nameWithAveCalorie = Dish.menu.stream().collect(Collectors.groupingBy(Dish::getName, Collectors.averagingDouble(Dish::getCalories)));
        System.out.println(nameWithAveCalorie);
        /**分区partition by测试*/
        Map<Boolean, List<Dish>> partitionByCalories = Dish.menu.stream().collect(Collectors.partitioningBy(dish -> dish.getCalories() > 500));
        System.out.println(partitionByCalories);


    }
}
