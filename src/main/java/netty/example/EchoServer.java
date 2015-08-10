package netty.example;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * The type Echo server.
 * @author liming
 * @since 2015 /8/4
 */
public class EchoServer
{
    private int port;

    /**
     * Instantiates a new Echo server.
     *
     * @param port the port
     */
    public EchoServer(int port)
    {
        this.port = port;
    }

    /**
     * Start int.
     *
     * @author liming
     * @return the int
     * @throws InterruptedException the interrupted exception
     */
    public void start() throws InterruptedException
    {
        EventLoopGroup group = new NioEventLoopGroup();
        try
        {
            //create serverbootstrap
            ServerBootstrap bootstrap = new ServerBootstrap();
            //制定NIO transport， 本地socket端口端口
            //在channel pipeline上增加handle
            bootstrap.group(group)
                .channel(NioServerSocketChannel.class)
                .localAddress(port)
                .childHandler(new ChannelInitializer()
                {
                    @Override
                    protected void initChannel(Channel channel)
                        throws Exception
                    {
                        channel.pipeline().addLast(new EchoServerHandler());
                    }

                });
            //绑定server 等待
            ChannelFuture future = bootstrap.bind().sync();
            System.out.println(String.format("%s started and listen on “ %s ”", EchoServer.class.getName(),
                future.channel().localAddress()));
            future.channel().closeFuture().sync();
        }
        finally
        {
            group.shutdownGracefully().sync();
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws InterruptedException the interrupted exception
     */
    public static void main(String[] args)
        throws InterruptedException
    {
        new EchoServer(65535).start();
    }
}
