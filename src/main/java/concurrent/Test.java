package concurrent;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * TODO 类的功能描述。
 *
 * @author liming
 * @version 2.2.0
 * @date 2014-08-07 20:43
 * @id $Id$
 */
public class Test {

    private static void methodA(int i) {
        if(i % 2 == 0){
            throw new IllegalArgumentException("abc");
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException
    {
        ExecutorService executor = Executors.newCachedThreadPool();
        CompletionService<Integer> comp = new ExecutorCompletionService<>(executor);
        for(int i = 0; i<5; i++) {
            comp.submit(new Task());
        }
        executor.shutdown();
        int count = 0, index = 1;
        while(count<5) {
            Future<Integer> f = comp.poll();
            if(f == null) {
                System.out.println(index + " 没发现有完成的任务");
            }else {
                System.out.println(index + "产生了一个随机数: " + f.get());
                count++;
            }
            index++;
            TimeUnit.MILLISECONDS.sleep(500);
        }
    }
}

class Task implements Callable<Integer>
{

    @Override
    public Integer call() throws Exception {
        Random rand = new Random();
        TimeUnit.SECONDS.sleep(rand.nextInt(7));
        return rand.nextInt();
    }

}