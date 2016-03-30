package javapuzzle;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author liming
 * @version [版本号, 三月 21, 2016]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class AnimalFarm
{
    public static void main(String[] args)
    {
        final String pig = "length: 10";
        final String dog = "length: " + pig.length();
        System.out. println( pig == dog);
        System.out.println(System.identityHashCode(pig));
        System.out.println(System.identityHashCode(dog));
    }
}
