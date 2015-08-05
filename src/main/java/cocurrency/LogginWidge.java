package cocurrency;

/**
 * Widge和子类LogginWidge都实现dosomething（）方法，
 * 子类调用dosomething又显示调用了父类的synchronized dosomething方法，
 * 使用子类LogginWidge 锁重入
 * @author liming
 * @since 2015/7/31
 */
public class LogginWidge extends Widge
{
    @Override
    public synchronized void dosomething()
        throws InterruptedException

    {
        System.out.println(Thread.currentThread().getName()+"child");
        super.dosomething();
        Thread.sleep(100000l);
    }

    public static void main(String[] args)
        throws InterruptedException
    {
        Widge widge = new LogginWidge();
        widge.dosomething();
    }
}
