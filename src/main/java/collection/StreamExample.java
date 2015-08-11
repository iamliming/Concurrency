package collection;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author liming
 * @since 2015/8/11
 * Stream 用来处理Collections，分为两个阶段
 *  1.Configuration----filter和map
 *  2.Processing
 */
public class StreamExample
{
    public static void main(String[] args)
    {
        List<String> list = new ArrayList<>();
        list.add("abc");
        list.add("bcd");
        list.add("");
        Stream<String> stream = list.stream();
        //List<String> rst = stream.filter(s -> !s.isEmpty()).map(s -> s.toUpperCase())   .collect(Collectors.toList());
        String rst = stream.filter(s -> !s.isEmpty()).map(s -> s.toUpperCase()).reduce("", (acc, item) -> acc + " " + item);
        System.out.println(rst);
    }
}
