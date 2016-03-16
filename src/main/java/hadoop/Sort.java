package hadoop;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author liming
 * @version [版本号, 十月 11, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class Sort
{
    public static class Map extends Mapper<Object, Text, IntWritable, IntWritable>{
        private static IntWritable data = new IntWritable();
        private static IntWritable tmp = new IntWritable(1);

        @Override
        protected void map(Object key, Text value,
            Context context)
            throws IOException, InterruptedException
        {
            String line = value.toString();
            data.set(Integer.parseInt(line));
            context.write(data, tmp);
        }
    }

    public static class Reduce extends Reducer<IntWritable, IntWritable, IntWritable,IntWritable>{
        private static IntWritable linenumber = new IntWritable(1);

        @Override
        protected void reduce(IntWritable key, Iterable<IntWritable> values,
            Context context)
            throws IOException, InterruptedException
        {
            for(IntWritable val : values)
            {
                context.write(linenumber, key);
                linenumber = new IntWritable(linenumber.get() + 1);
            }
        }
    }

    public static class Partition extends Partitioner<IntWritable,IntWritable>{
        @Override
        public int getPartition(IntWritable key, IntWritable value, int numPartitions)
        {
            int maxNum = 65223;
            int bound = maxNum/numPartitions + 1;
            int keyNum = key.get();
            for (int i = 0; i < numPartitions; i++)
            {
                if(keyNum < bound*i && keyNum >= bound*(i-1)){
                    return i-1;
                }
            }
            return -1;
        }
    }

    public static void main(String[] args)
        throws IOException, ClassNotFoundException, InterruptedException
    {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "sort");
        job.setJarByClass(Sort.class);
        job.setMapperClass(Map.class);
        job.setPartitionerClass(Partition.class);
        job.setReducerClass(Reduce.class);
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
