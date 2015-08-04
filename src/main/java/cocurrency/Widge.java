package cocurrency;

/**
 * @author liming
 * @since 2015/7/31
 */
public class Widge
{
    public synchronized void dosomething()
        throws InterruptedException
    {
        System.out.println(Thread.currentThread().getName()+":parent");
    }
    public synchronized void other(){
        System.out.println("other");
    }
}
