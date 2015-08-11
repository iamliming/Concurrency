package basic;

import java.nio.charset.Charset;
import java.nio.charset.spi.CharsetProvider;
import java.util.Iterator;

import sun.nio.cs.StandardCharsets;

/**
 * @author liming
 * @since 2015/8/11
 */
public class StringExample
{
    public static void main(String[] args)
    {
        //charset和bytebuffer
        String name = "李明";
        char[] c = name.toCharArray();
        for (int i = 0; i < c.length; i++)
        {
            System.out.println(c[i]);
        }
        //设置编码，转码
        String newStr = new String(name.getBytes(Charset.forName("GB2312")));
        System.out.println(newStr);
        //获取所有的标准字符集
        CharsetProvider provider = new StandardCharsets();
        Iterator<Charset> iterator =  provider.charsets();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
