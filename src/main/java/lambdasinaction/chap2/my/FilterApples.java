package lambdasinaction.chap2.my;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: 52483
 * @CreateDate: 2019/11/6 20:53:48
 * @Version: v1.0
 * @Description:
 */
public class FilterApples {
    static List<Apple> inventory = Arrays.asList(new Apple(1, "red"), new Apple(5, "green"), new Apple(4, "green"));

    public static void main(String[] args) {
        /**过滤绿色的苹果*/
//        List<Apple> apples = filterGreenApples(inventory);
        /**传递颜色过滤苹果*/
//        List<Apple> apples = filterApplesByColor(inventory, "green");
        /**传递重量过滤苹果*/
//        List<Apple> apples = filterApplesByWeight(inventory, 4);
        /**使用行为参数化来过滤绿色苹果*/
//        List<Apple> greenApples = filterApples(inventory, new AppleGreenColorPredicate());
        /**使用匿名内部类*/
//        List<Apple> redApples = filterApples(inventory, new ApplePredicate() {
//            @Override
//            public boolean test(Apple apple) {
//                return "red".equals(apple.getColor());
//            }
//        });
        /**使用Lambda表达式*/
//        List<Apple> redApples = filterApples(inventory, (Apple apple) -> "red".equals(apple.getColor()));
        /**用泛型实现List类型抽象化*/
        /*List<Apple> redApples = filter(inventory, new Predicate<Apple>() {
            @Override
            public boolean test(Apple apple) {
                return "red".equals(apple.getColor());
            }
        });*/
        /**使用Lambda表达式*/
        List<Apple> redApples = filter(inventory, (Apple apple) -> "red".equals(apple.getColor()));
        System.out.println(redApples);
    }

    /**
     * 筛选绿苹果
     */
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple :
                inventory) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }

        }
        return result;
    }

    /**
     * 把颜色作为参数，按照颜色筛选苹果
     */
    public static List<Apple> filterApplesByColor(List<Apple> inventory, String color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple :
                inventory) {
            if (color.equals(apple.getColor())) {
                result.add(apple);
            }

        }
        return result;
    }

    /**
     * 把重量作为参数，按照重量筛选苹果
     */
    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() >= weight) {
                result.add(apple);
            }

        }
        return result;
    }

    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }


}
