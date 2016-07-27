package jedis;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author liming
 * @version [版本号, 七月 06, 2016]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class JedisTest
{
    public static void main(String[] args)
    {

        String str = "   中文中     啊啊  ";
        boolean s =  ((str == null) || (str.trim().length() == 0));
        System.out.println(s);
    }
}
