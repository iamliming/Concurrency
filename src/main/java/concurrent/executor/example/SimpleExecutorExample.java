package concurrent.executor.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 简单创建爱你一个固定大小为3个线程的线程池
 * 创建10个任务到线程池
 * 关闭线程池
 *
 * @author liming
 * @since 2015/8/7
 */
public class SimpleExecutorExample
{
    public static void main(String[] args)
    {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++)
        {
            WorkThread task = new WorkThread(String.valueOf(i));
            executorService.submit(task);
        }
        executorService.shutdown();
        while(!executorService.isTerminated()){}
        System.out.println("All task is terminated!");
    }
}
