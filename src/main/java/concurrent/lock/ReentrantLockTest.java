package concurrent.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * TODO 类的功能描述。
 *
 * @author liming
 * @version 2.2.0
 * @date 2014-08-04 17:45
 * @id $Id$
 */
public class ReentrantLockTest
{

    private static final ReentrantLock locks = new ReentrantLock();

    public static void main(String[] args)
        throws InterruptedException
    {

        Runnable threadA = () -> {
            while (true)
            {
                if (locks.tryLock())
                {
                    try
                    {
                        System.out.println("ThreadA" + locks.isFair());
                        return;
                    }
                    finally
                    {
                        locks.unlock();
                    }
                }
            }
        };
        Runnable threadB = () -> {
            while (true)
            {
                if (locks.tryLock())
                {
                    try
                    {
                        System.out.println("ThreadB" + locks.isFair());
                        return;
                    }
                    finally
                    {
                        locks.unlock();
                    }
                }
            }
        };
        Runnable threadC = () -> {
            try
            {
                if (locks.tryLock(2,TimeUnit.SECONDS))
                {
                    try
                    {
                        System.out.println("ThreadC" + locks.isFair());
                        Thread.sleep(5000l);
                        System.out.println("C OUT");
                        return;
                    }
                    finally
                    {
                        locks.unlock();
                    }
                }
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        };
        Runnable threadD = () -> {
            try
            {
                if (locks.tryLock(2,TimeUnit.SECONDS))
                {
                    try
                    {
                        System.out.println("ThreadD" + locks.isFair());
                        Thread.sleep(5000l);
                        System.out.println("D OUT");
                        return;
                    }
                    finally
                    {
                        locks.unlock();
                    }
                }
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        };
        /*Runnable threadA = () -> {
            try {
				locks.lock();
				if (locks.isHeldByCurrentThread()) {
					System.out.println("hold in current thread");

					Thread.sleep(2000);
					System.out.println("ThreadA done something");

				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				locks.unlock();
			}
		};

		Runnable threadB = () -> {
			try {
				locks.lock();
				if (locks.isHeldByCurrentThread()) {
					System.out.println("ThreadB has locked the control and working their task...");
				}

			} catch (final Exception e) {
				e.printStackTrace();
			} finally {
				locks.unlock();
			}
		};*/

        final ExecutorService executor = Executors.newScheduledThreadPool(2);
        executor.execute(threadC);
        executor.execute(threadD);
        executor.shutdown();
    }
}
