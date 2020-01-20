package lambdasinaction.chap3.my;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Comparator.comparing;

/**
 * @Author: 52483
 * @CreateDate: 2019/11/23 08:39:21
 * @Version: v1.0
 * @Description:
 */
public class CompoundLambda {
    public static void main(String[] args) {
        //比较器复合
        List<Apple> inventory = Arrays.asList(new Apple(100, "red", "china"), new Apple(60, "green", "japan"), new Apple(60, "gray", "usa"));
        //1:逆序
        inventory.sort(comparing(Apple::getWeight).reversed());
        System.out.println("1:" + inventory);
        //2:比较器链，按重量递减排序，如果两个苹果一样重时，进一步按照国家排序
        List<Apple> inventory1 = Arrays.asList(new Apple(100, "red", "china"), new Apple(60, "gray", "usa"), new Apple(60, "green", "japan"));
        inventory1.sort(comparing(Apple::getWeight).reversed().thenComparing(Apple::getCountry));
        System.out.println("2:" + inventory1);

        //3:谓词复合
        List<Apple> inventory2 = Arrays.asList(new Apple(100, "red", "china"), new Apple(200, "red", "china"), new Apple(60, "gray", "usa"), new Apple(60, "green", "japan"));
        //获取红苹果
        Predicate<Apple> redApple = a -> "red".equals(a.getColor());
        List<Apple> redApples = filterApple(inventory2, redApple);
        System.out.println("red apples-->" + redApples);
        //获取非红苹果
        Predicate<Apple> notRedApple = redApple.negate();
        List<Apple> notRedApples = filterApple(inventory2, notRedApple);
        System.out.println("not red apples-->" + notRedApples);
        //要么是重（150克以上）的红苹果，要么是绿苹果
        Predicate<Apple> redAndHeavyAppleOrGreen = redApple.and(apple -> apple.getWeight() > 150).or(apple -> "green".equals(apple.getColor()));
        List<Apple> redAndHeavyOrGreenApples = filterApple(inventory2, redAndHeavyAppleOrGreen);
        System.out.println("red and heavy or green apples -->" + redAndHeavyOrGreenApples);

        //函数复合
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        /**等价于g(f(x))*/
        Function<Integer, Integer> h = f.andThen(g);
        System.out.println("g(f(x)):" + h.apply(1));

        /**等价于f(g(x))*/
        Function<Integer, Integer> h1 = f.compose(g);
        System.out.println("f(g(x)):" + h1.apply(1));


        //文本转换
        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> transformationPipeline
                = addHeader.andThen(Letter::checkSpelling)
                .andThen(Letter::addFooter);
        System.out.println("addHeader:" + addHeader.apply("hahah"));
        System.out.println("transformationPipeline:" + transformationPipeline.apply("zhunihaoyun labda"));

    }

    public static class Letter {
        public static String addHeader(String text) {
            return "From Raoul, Mario and Alan: " + text;
        }

        public static String addFooter(String text) {
            return text + " Kind regards";
        }

        public static String checkSpelling(String text) {
            return text.replaceAll("labda", "lambda");
        }
    }

    public static List<Apple> filterApple(List<Apple> inventory, Predicate<Apple> predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple a : inventory
        ) {
            if (predicate.test(a)) {
                result.add(a);
            }

        }
        return result;
    }

    public static class Apple {
        private Integer weight = 0;
        private String color = "";
        private String country;

        public Apple(Integer weight, String color) {
            this.weight = weight;
            this.color = color;
        }

        public Apple(Integer weight, String color, String country) {
            this.weight = weight;
            this.color = color;
            this.country = country;
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

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        @Override
        public String toString() {
            return "Apple{" +
                    "weight=" + weight +
                    ", color='" + color + '\'' +
                    ", country='" + country + '\'' +
                    '}';
        }
    }
}
