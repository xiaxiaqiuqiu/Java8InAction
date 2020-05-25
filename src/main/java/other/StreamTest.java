package other;

import java.util.*;

/**
 * @Author: 52483
 * @CreateDate: 2020/5/2 16:39:52
 * @Version: v1.0
 * @Description:
 */
public class StreamTest {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("aa","bb","hello");
      //  System.out.println(list.getClass());
        /**测试每个元素执行顺序 start*/
      /*  list.stream().filter(str->str.length() > 1).map(str->{
            System.out.println(str);
            return str.length();
        }).forEach(System.out::println);*/
        /**测试每个元素执行顺序 end*/
        /**foreach test start*/
      //  list.stream().forEach(System.out::println); //进入的是ReferencePipeline.Head中的foreach
       list.stream().map(item->item + "1").forEach(System.out::println);//进入的是ReferencePipeline中的foreach
         /**foreach test end*/

    }
}
