package hadoop;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author liming
 * @version [版本号, 十月 08, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ShowFileStatus
{
    public static void main(String[] args) throws IOException
    {
        try {
            URI dsf = URI.create("hdfs://hadoop1:9000/tmp/wordcount/kkk.txt");
            Configuration conf = new Configuration();

            FileSystem fs = FileSystem.get(dsf,conf);
            FSDataInputStream hdfsInStream = fs.open(new Path(dsf));

            byte[] ioBuffer = new byte[1024];
            int readLen = hdfsInStream.read(ioBuffer);
            while(readLen!=-1)
            {
                System.out.write(ioBuffer, 0, readLen);
                readLen = hdfsInStream.read(ioBuffer);
            }
            hdfsInStream.close();
            fs.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //String pathStr = "hdfs://192.168.80.131/user/hadoop/input";
        //Path path = new Path(pathStr);
        //Configuration conf = new Configuration();
        //FileSystem fs = FileSystem.get(URI.create(pathStr), conf);
        //FileStatus status = fs.getFileStatus(path);
        //System.out.println("path = " + status.getPath());
        //System.out.println("owner = " + status.getOwner());
        //System.out.println("block size = " + status.getBlockSize());
        //System.out.println("permission = " + status.getPermission());
        //System.out.println("replication = " + status.getReplication());
    }
}



