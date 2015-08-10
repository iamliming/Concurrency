package concurrent.executor.example;

/**
 * @author liming
 * @since 2015/8/7
 */
public class WorkThread implements  Runnable
{
    private String command;

    public WorkThread(String command)
    {
        this.command = command;
    }

    @Override
    public void run()
    {
        System.out.println(Thread.currentThread().getName() + " Start. Command="+command);
        processCommand();
        System.out.println(Thread.currentThread().getName() + " End.Command="+command);
    }

    private void processCommand(){
        try
        {
            Thread.sleep(5000l);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public String toString()
    {
        return "WorkThread{" +
            "command='" + command + '\'' +
            '}';
    }
}
