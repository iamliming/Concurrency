package javapuzzle;

/**
 * 所有int类型相乘，溢出了
 *
 * @author liming
 * @version [版本号, 三月 18, 2016]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class LongDivision
{
    public static void main(String[] args)
    {
        final long MICROS_PER_DAY = 24 * 60 * 60 * 1000 * 1000;
        final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;
        System.out.println(MICROS_PER_DAY/MILLIS_PER_DAY);
    }
}
