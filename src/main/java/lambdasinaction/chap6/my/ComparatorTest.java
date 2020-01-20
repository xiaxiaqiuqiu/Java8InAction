package lambdasinaction.chap6.my;

import java.util.*;

/**
 * @Author: 52483
 * @CreateDate: 2019/12/16 22:11:59
 * @Version: v1.0
 * @Description:
 */
public class ComparatorTest {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("nihaoa", "helo", "hello", "World", "aya", "wozuichang");
        /**按照自然顺序排序*/
       /*list.sort(null);
       System.out.println(list);*/
        //等价于下面的写法
       /* Collections.sort(list);
        System.out.println(list);*/
        /**根据list中元素的长度进行升序排序*/
        /**1、使用lambda表达式*/
        /* Collections.sort(list, (item1, item2) -> (item1.length() - item2.length());*/
     /*   Collections.sort(list, Comparator.comparingInt(String::length));
        System.out.println(list);*/
        /**根据list中元素的长度进行降序排序*/
        /**1、使用lambda表达式*/
        /* Collections.sort(list, (item1, item2) -> (item2.length() - item1.length());*/
        /*  Collections.sort(list, Comparator.comparingInt(String::length).reversed());*/
        /**item需要强转成String，因为item被默认推断成Object类 , 如果没有reversed()则可以推断出来类型*/
        /*Collections.sort(list, Comparator.comparingInt((String item) -> item.length()).reversed());*/
        /**根据字符串长度进行比较，然后按照不区分大小写的方式进行排序,下面几种方式是等价的*/
        // Collections.sort(list, Comparator.comparingInt(String::length).thenComparing(String.CASE_INSENSITIVE_ORDER));
        // Collections.sort(list, Comparator.comparingInt(String::length).thenComparing((item1, item2) -> item1.toLowerCase().compareTo(item2.toLowerCase())));
        // Collections.sort(list, Comparator.comparingInt(String::length).thenComparing(Comparator.comparing(String::toLowerCase)));
        /**根据字符串长度进行比较，然后按照不区分大小写的方式进行逆序排序*/
        //Collections.sort(list, Comparator.comparingInt(String::length).thenComparing(String::toLowerCase, Comparator.reverseOrder()));
        Collections.sort(list, Comparator.comparingInt(String::length).reversed());
        System.out.println(list);


    }
}
