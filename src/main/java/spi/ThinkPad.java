package spi;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author liming
 * @version [版本号, 三月 30, 2016]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ThinkPad implements Computer
{
    @Override
    public void getName()
    {
        System.out.println("ThinkPad");
    }
}
