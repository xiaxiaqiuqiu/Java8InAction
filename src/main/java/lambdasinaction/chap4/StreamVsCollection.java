package lambdasinaction.chap4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;


public class StreamVsCollection {

    public static void main(String... args) {
        List<String> names = Arrays.asList("Java8", "Lambdas", "In", "Action");
        Stream<String> s = names.stream();
        s.forEach(System.out::println);
        // uncommenting this line will result in an IllegalStateException
        // because streams can be consumed only once
        //s.forEach(System.out::println);
        List<Dish> list = Dish.menu.stream().filter(dish -> dish.getCalories() > 700).limit(3).collect(toList());
        System.out.println(list);

    }
}