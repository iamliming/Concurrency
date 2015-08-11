package concurrent.example.threadlocal;

/**
 * @author liming
 * @since 2015/8/11
 */
public class ConnectionThread
{
    public static void main(String[] args)
    {
        Thread t = new Thread(){
            @Override
            public void run()
            {
                System.out.println(Thread.currentThread().getName());
                System.out.println(ConnectionCache.getConnectionHold());

            }
        };
        Thread t2 = new Thread(){
            @Override
            public void run()
            {
                System.out.println(Thread.currentThread().getName());
                System.out.println(ConnectionCache.getConnectionHold());
            }
        };
        t.start();
        t2.start();
    }
}
