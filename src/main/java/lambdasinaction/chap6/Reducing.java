package lambdasinaction.chap6;

import org.openjdk.jmh.util.*;

import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.*;
import static lambdasinaction.chap6.Dish.menu;

public class Reducing {

    public static void main(String ... args) {
        System.out.println("Total calories in menu: " + calculateTotalCalories());
        System.out.println("Total calories in menu: " + calculateTotalCaloriesWithMethodReference());
        System.out.println("Total calories in menu: " + calculateTotalCaloriesWithoutCollectors());
        System.out.println("Total calories in menu: " + calculateTotalCaloriesUsingSum());
        System.out.println("Total calories in menu: " + calculateTotalCaloriesUsingSum());
        System.out.println("names in menu: " + joinDishName());
    }

    private static int calculateTotalCalories() {
        return menu.stream().collect(reducing(0, Dish::getCalories, (Integer i, Integer j) -> i + j));
    }

    private static int calculateTotalCaloriesWithMethodReference() {
        return menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));
    }

    private static int calculateTotalCaloriesWithoutCollectors() {
        return menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
    }

    private static int calculateTotalCaloriesUsingSum() {
        return menu.stream().mapToInt(Dish::getCalories).sum();
    }

    private static String joinDishName(){
        Stream<StringBuilder> stream = menu.stream().map(dish -> new StringBuilder(dish.getName()));
       return stream.reduce(StringBuilder::append).get().toString();

    }
}