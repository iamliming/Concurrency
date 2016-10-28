package nio.reactor;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author liming
 * @version [版本号, 七月 28, 2016]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class Reactor implements Runnable
{
    public final Selector selector;
    public final ServerSocketChannel serverSocketChannel;

    public Reactor(int port)
        throws IOException
    {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
    }

    @Override
    public void run()
    {

    }

    public static void main(String[] args)
        throws IOException
    {
        Reactor reactor = new Reactor(123);
    }
}
