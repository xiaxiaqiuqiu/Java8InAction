package lambdasinaction.chap3.my;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodReference {

    public static void main(String[] args) {
        //静态方法引用
        Function<String, Integer> staticMethodReference = Integer::parseInt;
        Integer i = staticMethodReference.apply("234");
        System.out.println(i);

        //指向任意类型实例方法的方法引用
        BiFunction<String, Integer, Character> instanceMethodReference = String::charAt;
        char c = instanceMethodReference.apply("hakilk", 2);
        System.out.println(c);

        //指向现有对象的实例方法的方法引用
        String str = "hahah";
        Function<Integer, Character> integerCharacterFunction = str::charAt;

        //构造函数方法引用
        /**无参数*/
        Supplier<String> supplier = String::new;
        String str1 = supplier.get();
        /**有参数*/
        BiFunction<Integer, String, Apple> appleFunction = Apple::new;
        Apple apple = appleFunction.apply(20, "red");


    }

    public static class Apple {
        private Integer weight = 0;
        private String color = "";

        public Apple(Integer weight, String color) {
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
