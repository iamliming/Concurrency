package spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author liming
 * @version [版本号, 三月 30, 2016]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class SpiTest
{
    public static void main(String[] args)
    {
        ServiceLoader<Computer> computers = ServiceLoader.load(Computer.class);
        Iterator<Computer> computIterator = computers.iterator();
        while(computIterator.hasNext())
        {
            computIterator.next().getName();
        }
    }
}
