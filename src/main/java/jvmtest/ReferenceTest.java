package jvmtest;

import java.lang.ref.WeakReference;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author liming
 * @version [版本号, 三月 21, 2016]
 * @see [相关类/方法]
 *
 * @since [产品/模块版本]
 */
public class ReferenceTest
{
    public static void main(String[] args)
        throws InterruptedException
    {
        WeakReference r = new WeakReference(new String("I'm here"));
        WeakReference sr = new WeakReference("I'm here");
        System.out.println("before gc: r=" + r.get() + ", static=" + sr.get());
        System.gc();
        Thread.sleep(100);
        //只有r.get()变为null
        System.out.println("after gc: r=" + r.get() + ", static=" + sr.get());
    }
}
