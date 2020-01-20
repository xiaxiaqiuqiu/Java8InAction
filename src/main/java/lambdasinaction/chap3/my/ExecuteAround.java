package lambdasinaction.chap3.my;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @Author: 52483
 * @CreateDate: 2019/11/17 15:15:14
 * @Version: v1.0
 * @Description:
 */
public class ExecuteAround {
    /**
     * 原生的处理方式
     */
    public static String processFile() throws IOException {
        try (BufferedReader br =
                     new BufferedReader(
                             new FileReader("src/main/resources/lambdasinaction/chap3/data.txt")
                     )) {
            return br.readLine();
        }
    }

    /**
     * 转换成Lambda表达式的方式
     */
    @FunctionalInterface
    public interface BufferedReaderProcess {
        String process(BufferedReader br) throws IOException;
    }

    public static String processFile(BufferedReaderProcess brp) throws IOException {
        try (BufferedReader br =
                     new BufferedReader(
                             new FileReader("src/main/resources/lambdasinaction/chap3/data.txt")
                     )) {
            return brp.process(br);
        }
    }

    public static void main(String[] args) throws IOException {
        //processFile();
        // String oneLine = processFile((BufferedReader reader) -> reader.readLine());
        //System.out.println(oneLine);
        String twoLine = processFile((BufferedReader reader) -> reader.readLine() + reader.readLine());
        System.out.println(twoLine);

    }

}
