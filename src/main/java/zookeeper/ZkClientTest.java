package zookeeper;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author liming
 * @version [版本号, 九月 23, 2016]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ZkClientTest
{
    public static void main(String[] args)
        throws InterruptedException
    {
        for(int i = 0; i< 1000;i++)
        {
            System.out.print(13868184537l+i+";");
        }
        //ZkClient zkClient = new ZkClient("10.211.93.156:2181");
        //if(zkClient.exists("/test"))
        //{
        //    zkClient.createEphemeral("/test/test");
        //}
        //Thread.sleep(1000l);
        //zkClient.exists("/test/test");
    }
}
