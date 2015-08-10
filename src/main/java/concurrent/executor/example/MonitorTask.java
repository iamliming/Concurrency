package concurrent.executor.example;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author liming
 * @since 2015/8/7
 */
public class MonitorTask implements Runnable
{
    private ThreadPoolExecutor executor;
    private int seconds;
    private volatile boolean run = true;

    public MonitorTask(ThreadPoolExecutor executor, int delay)
    {
        this.executor = executor;
        this.seconds = delay;
    }

    public void shutDown(){
        this.run = false;
    }

    @Override
    public void run()
    {
        while(run){
            System.out.println(String.format(
                "[Monitor] [%d/%d] Active:%d,Completed:%d,Task:%d,isShutDown:%b,isTerminated:%b",
                executor.getCorePoolSize(),
                executor.getMaximumPoolSize(),
                executor.getActiveCount(),
                executor.getCompletedTaskCount(),
                executor.getTaskCount(),
                executor.isShutdown(),
                executor.isTerminated()
            ));
            try{
                Thread.sleep(seconds * 1000l);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
