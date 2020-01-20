package lambdasinaction.chap3.my;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.function.ObjIntConsumer;

/**
 * @Author: 52483
 * @CreateDate: 2019/11/16 12:09:03
 * @Version: v1.0
 * @Description:
 */
public class Lambdas {
    // Filtering with lambdas
    private static int a1 = 2;

    public static <T> void main(String[] args) {
        /**Lambda表达式赋给一个变量*/
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"), new Apple(120, "red"));
        Runnable r = () -> System.out.println("This this run");
        r.run();

        /**排序*/
        Comparator<Apple> comparator = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
        Comparator<Apple> comparator1 = (a1, a2) -> a1.getWeight().compareTo(a2.getWeight());
        inventory.sort(comparator1);
        System.out.println(inventory);

        /**原生类型的函数式接口*/
        IntPredicate evenNumbers = (int i) -> i % 2 == 0;
        System.out.println(evenNumbers.test(20));

        new ObjIntConsumer<T>() {
            @Override
            public void accept(T t, int i) {

            }
        };

        /**Lambda可以没有限
         制地捕获（也就是在其主体中引用）实例变量和静态变量。但局部变量必须显式声明为final，
         或事实上是final*/
        int portNumber = 1337;
        Runnable r1 = () -> System.out.println("r1 port number:" + portNumber);
        /**执行下面的语句会报错*/
        //portNumber = 335;

        List<String> list = new ArrayList<>();

        Runnable r2 = () -> System.out.println("r2 list:" + list);
        list.add("haha");
        r2.run();

        Runnable r3 = () -> System.out.println("r3 a1:" + a1);
        a1 = 4;
        r3.run();


    }

    public static class Apple {
        private Integer weight = 0;
        private String color = "";

        public Apple(int weight, String color) {
            this.weight = weight;
            this.color = color;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String toString() {
            return "Apple{" +
                    "color='" + color + '\'' +
                    ", weight=" + weight +
                    '}';
        }
    }

}
