package zookeeper;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author liming
 * @version [版本号, 九月 23, 2016]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ZookeeperTest
{
    public static void main(String[] args)
        throws IOException, KeeperException, InterruptedException
    {
        ZooKeeper zooKeeper = new ZooKeeper("10.211.93.156:2181", 10000, new ZKWatcher());
        String s = zooKeeper.create("/test","root data".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(zooKeeper.getChildren("/", null));
    }
}

class ZKWatcher implements Watcher
{
    @Override
    public void process(WatchedEvent watchedEvent)
    {
        if(watchedEvent.getType().equals(Event.EventType.NodeCreated))
        {
            System.out.println("create:"+watchedEvent.getPath());
        }
        else if(watchedEvent.getType().equals(Event.EventType.NodeDeleted))
        {
            System.out.println("delete:"+watchedEvent.getPath());
        }
        else if(watchedEvent.getType().equals(Event.EventType.NodeDataChanged))
        {
            System.out.println("DataChanged:"+watchedEvent.getPath());
        }else if(watchedEvent.getType().equals(Event.EventType.NodeChildrenChanged))
        {
            System.out.println("child changer:"+watchedEvent.getPath());
        }
        else
        {
            System.out.println(watchedEvent.getType());
        }
    }
}
