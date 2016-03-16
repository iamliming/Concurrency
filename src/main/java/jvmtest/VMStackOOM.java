package jvmtest;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author liming
 * @version [版本号, 三月 09, 2016]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class VMStackOOM
{
    private void dontStop()
    {
        while (true)
        {

        }
    }

    public void stackLeakByThread()
    {
        while (true)
        {
            Thread t = new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    dontStop();
                }
            });
            t.start();
        }
    }

    public static void main(String[] args)
    {
        VMStackOOM oom = new VMStackOOM();
        oom.stackLeakByThread();
    }
}
