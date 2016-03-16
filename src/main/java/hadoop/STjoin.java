package hadoop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author liming
 * @version [版本号, 十月 12, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class STjoin
{
    public static int time = 0;

    public static final String SPLIT = "_";

    public static class Map extends Mapper<Object, Text, Text, Text>
    {
        @Override
        protected void map(Object key, Text value,
            Context context)
            throws IOException, InterruptedException
        {
            String relationtype = new String();
            String line = value.toString();
            int i = 0;
            String[] values = line.split(" ");

            String childname = values[0];
            String parentname = values[1];
            context.write(new Text(parentname), new Text("p" + SPLIT + childname));
            context.write(new Text(childname), new Text("c" + SPLIT + parentname));
        }
    }

    public static class Reduce extends Reducer<Text, Text, Text, Text>
    {
        private static Text KEY = new Text();
        private static Text VALUE = new Text();
        @Override
        protected void reduce(Text key, Iterable<Text> values,
            Context context)
            throws IOException, InterruptedException
        {
            List<String> grandChild = new ArrayList<String>();
            List<String> grandParent = new ArrayList<String>();

            Text value = new Text();
            Iterator<Text> vals = values.iterator();

            while (vals.hasNext())
            {
                String s = vals.next().toString();
                if(s.startsWith("p")){
                    grandChild.add(s.substring(1));
                }
                else{
                    grandParent.add(s.substring(1));
                }
            }
            for(String c : grandChild){
                KEY.set(c);
                for(String p : grandParent){
                    VALUE.set(p);
                    context.write(KEY, VALUE);
                }
            }

        }
    }

    public static void main(String[] args)
        throws IOException, ClassNotFoundException, InterruptedException
    {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "STJoin");
        job.setJarByClass(STjoin.class);
        job.setMapperClass(Map.class);
        job.setReducerClass(Reduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
