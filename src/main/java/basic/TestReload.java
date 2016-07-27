package basic;

import java.lang.ref.WeakReference;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author liming
 * @version [版本号, 三月 30, 2016]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class TestReload
{
    public static void main(String[] args)
        throws InterruptedException
    {
        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.set("abc");
        WeakReference<String> s = new WeakReference<String>("ab");
        System.out.println(s.get());
        System.gc();
        Thread.sleep(100000l);
        System.out.println(threadLocal.get());
        System.out.println(s.get());

    }
}
