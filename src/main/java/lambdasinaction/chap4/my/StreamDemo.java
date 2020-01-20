package lambdasinaction.chap4.my;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 52483
 * @CreateDate: 2019/11/23 21:59:35
 * @Version: v1.0
 * @Description:
 */
public class StreamDemo {
    public static void main(String[] args) {
        //对于一张单词表， 返回一张列表， 列出里面各不相同的字符
     /*   String[] arrayOfWords = {"Goodbye", "World"};
        Stream<String> streamOfwords = Arrays.stream(arrayOfWords);

        List<String> words = Arrays.asList("Hello", "World");
        List<String> uniqueCharacters =
                words.stream()
                        .map(w -> w.split(""))
                        .flatMap(Arrays::stream)
                        .distinct()
                        .collect(Collectors.toList());*/
        //给定两个数字列表，返回所有的数对
        List<Integer> nums1 = Arrays.asList(1, 2, 3);
        List<Integer> nums2 = Arrays.asList(3, 4);
        List<List<Integer>> pairs = nums1.stream().flatMap(a -> nums2.stream().filter(b -> (a + b) % 3 == 0).map(b -> Arrays.asList(a, b))).collect(Collectors.toList());
        System.out.println(pairs);
        nums1.stream().forEach(item -> nums2.stream().forEach(item2 -> System.out.println(item + " " + item2)));
        //flatMap demo 将列表中所有元素平方后累加
        //  Stream<List<Integer>> stream = Stream.of(Arrays.asList(1, 3), Arrays.asList(6, 2));
        // stream.flatMap(List::stream).map(item -> item * item).forEach(System.out::println);


        //由流转换成array
     /*   Stream<String> stream = Stream.of("hello", "world", "helloworld");
        // String[] stringArray = stream.toArray(len -> new String[len]);
        String[] stringArray = stream.toArray(String[]::new);
        Arrays.asList(stringArray).forEach(System.out::println);*/
        //由流转换成List
        //    Stream<String> stream = Stream.of("hello", "world", "helloworld");
        //List<String> list = stream.collect(Collectors.toList());
        //List<String> asList = stream.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
//        List<String> list = stream.collect(() -> {
//            ArrayList<String> arrayList = new ArrayList<>();
//            return arrayList;
//        }, (list1, item) -> {
//            System.out.println("list1: " + list1 + "  item: " + item);
//            list1.add(item);
//        }, (list1, list2) -> {
//            System.out.println("2list1: " + list1 + " 2list2: " + list2);
//            list1.addAll(list2);
//        });
        //可以自定义返回的类型（比如ArrayList， LinkedList）
        // List<String> list = stream.collect(Collectors.toCollection(ArrayList::new));
        //System.out.println(list);
        //拼接流中的元素
//        Stream<String> stream = Stream.of("hello", "world", "helloworld");
//        String concatenateStr = stream.collect(Collectors.joining());
//        System.out.println(concatenateStr);


    }
}
