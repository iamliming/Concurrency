package concurrent.executor.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author liming
 * @since 2015/8/7
 */
public class WorkPool
{
    public static void main(String[] args)
        throws InterruptedException
    {
        BlockingQueue queue = new LinkedBlockingQueue<>(2);
        for (int i = 0; i < 10; i++)
        {
            queue.add(new WorkThread("c"+i));
            System.out.println(queue.size());
        }


        RejectedExecutionHandler rejectedHandler = new RejectedExecutionHandleImpl();
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        /*core 2
        max 4
        activeTime 2秒---线程数大于core的空闲线程最大等待时间
        无界FIFO队列*/
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2,
            4,
            2l,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue(6),
            threadFactory,
            rejectedHandler);
        MonitorTask monitorTask = new MonitorTask(poolExecutor, 1);
        new Thread(monitorTask).start();
        //
        for (int i = 0; i < 10; i++)
        {
            WorkThread thread = new WorkThread("cmd-"+i);
            poolExecutor.execute(thread);
        }
        Thread.sleep(30000l);
        poolExecutor.shutdown();
        Thread.sleep(5000l);
        monitorTask.shutDown();
    }
}
