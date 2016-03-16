package io.netty.example;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author liming
 * @version [版本号, 十二月 01, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Random;
import java.util.Scanner;

/**
 * NIO写大文件比较
 * @author Will
 *
 */
public class WriteBigFileComparison {

    // data chunk be written per time
    private static final int DATA_CHUNK = 128 * 1024 * 1024;

    // total data size is 2G
    private static final long LEN = 256 * 1024 * 1024L;


    public static void writeWithFileChannel() throws IOException {
        File file = new File("e:/test/fc.dat");
        if (file.exists()) {
            file.delete();
        }

        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        FileChannel fileChannel = raf.getChannel();

        byte[] data = null;
        long len = LEN;
        ByteBuffer buf = ByteBuffer.allocate(DATA_CHUNK);
        int dataChunk = DATA_CHUNK / (1024 * 1024);
        while (len >= DATA_CHUNK) {
            System.out.println("write a data chunk: " + dataChunk + "MB");

            buf.clear(); // clear for re-write
            data = new byte[DATA_CHUNK];
            for (int i = 0; i < DATA_CHUNK; i++) {
                buf.put(data[i]);
            }

            data = null;

            buf.flip(); // switches a Buffer from writing mode to reading mode
            fileChannel.write(buf);
            fileChannel.force(true);

            len -= DATA_CHUNK;
        }

        if (len > 0) {
            System.out.println("write rest data chunk: " + len + "B");
            buf = ByteBuffer.allocateDirect((int) len);
            data = new byte[(int) len];
            for (int i = 0; i < len; i++) {
                buf.put(data[i]);
            }

            buf.flip(); // switches a Buffer from writing mode to reading mode, position to 0, limit not changed
            fileChannel.write(buf);
            fileChannel.force(true);
            data = null;
        }

        fileChannel.close();
        raf.close();
    }

    /**
     * write big file with MappedByteBuffer
     * @throws IOException
     */
    public static void writeWithMappedByteBuffer() throws IOException {
        File file = new File("e:/test/mb.dat");
        if (file.exists()) {
            file.delete();
        }

        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        FileChannel fileChannel = raf.getChannel();
        int pos = 0;
        MappedByteBuffer mbb = null;
        byte[] data = null;
        long len = LEN;
        int dataChunk = DATA_CHUNK / (1024 * 1024);
        while (len >= DATA_CHUNK) {
            System.out.println("write a data chunk: " + dataChunk + "MB");

            mbb = fileChannel.map(MapMode.READ_WRITE, pos, DATA_CHUNK);
            data = new byte[DATA_CHUNK];
            mbb.put(data);

            data = null;

            len -= DATA_CHUNK;
            pos += DATA_CHUNK;
        }

        if (len > 0) {
            System.out.println("write rest data chunk: " + len + "B");

            mbb = fileChannel.map(MapMode.READ_WRITE, pos, len);
            data = new byte[(int) len];
            mbb.put(data);
        }

        data = null;
        unmap(mbb);   // release MappedByteBuffer
        fileChannel.close();
    }

    public static void writeWithTransferTo() throws IOException {
        File file = new File("e:/test/transfer.dat");
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        Random random = new Random();
        for(int i = 0; i < 10000000; i++)
        {
            Integer  k = random.nextInt(1000000)+10000000;
            bw.write(k.toString());//输出字符串
            bw.newLine();//换行
            if(i > 0 && i%100000 == 0)
            {
                bw.flush();
            }
        }

        bw.close();
    }

    public static void readFile()
        throws IOException
    {
        String path = "e:/test/transfer.dat";
        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = new FileInputStream(path);
            sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                // System.out.println(line);
            }
            // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }
    }


    /**
     * 在MappedByteBuffer释放后再对它进行读操作的话就会引发jvm crash，在并发情况下很容易发生
     * 正在释放时另一个线程正开始读取，于是crash就发生了。所以为了系统稳定性释放前一般需要检
     * 查是否还有线程在读或写
     * @param mappedByteBuffer
     */
    public static void unmap(final MappedByteBuffer mappedByteBuffer) {
        try {
            if (mappedByteBuffer == null) {
                return;
            }

            mappedByteBuffer.force();
            AccessController.doPrivileged(new PrivilegedAction<Object>() {
                @Override
                @SuppressWarnings("restriction")
                public Object run() {
                    try {
                        Method getCleanerMethod = mappedByteBuffer.getClass()
                            .getMethod("cleaner", new Class[0]);
                        getCleanerMethod.setAccessible(true);
                        sun.misc.Cleaner cleaner =
                            (sun.misc.Cleaner) getCleanerMethod
                                .invoke(mappedByteBuffer, new Object[0]);
                        cleaner.clean();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("clean MappedByteBuffer completed");
                    return null;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) throws IOException {
        long l = System.currentTimeMillis();
        //writeWithFileChannel();
        //System.out.println(System.currentTimeMillis() - l);
        l = System.currentTimeMillis();
        readFile();
        System.out.println(System.currentTimeMillis() - l);
        //l = System.currentTimeMillis();
        //writeWithMappedByteBuffer();
        //System.out.println(System.currentTimeMillis() - l);
    }

}
