package concurrent.executor.example;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author liming
 * @since 2015/8/7
 */
public class RejectedExecutionHandleImpl implements RejectedExecutionHandler
{
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor)
    {
        System.out.println(r.toString() + " is rejected!");
    }
}
