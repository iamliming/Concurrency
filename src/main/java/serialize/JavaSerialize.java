package serialize;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import domain.PersonJava;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author liming
 * @version [版本号, 七月 27, 2016]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class JavaSerialize
{
    public static void writeObject( ObjectOutputStream os)
        throws IOException
    {
        for(int i = 0; i < 5; i++)
        {
            PersonJava person = new PersonJava();
            person.setId(i);
            person.setName("Tony" + i);
            person.setEmail(i+"@163.com");
            os.writeObject(person);
            if(i % 100000 == 0 &&  i != 0)
            {
                os.flush();
            }
        }
        os.flush();
        os.close();
    }

    public static void readObject(ObjectInputStream os)
        throws IOException, ClassNotFoundException
    {
        while(true)
        {
            Object obj = os.readObject();
            if(obj == null)
            {
                break;
            }
            PersonJava personJava = (PersonJava)obj;
        }
        os.close();
    }

    public static void main(String[] args)
        throws IOException, ClassNotFoundException
    {
        //System.out.println(URLEncoder.encode("abc|abc>"));
        //System.out.println(URLDecoder.decode("abc|abc>"));
        FileOutputStream fs = new FileOutputStream("javaserialize.txt");
        ObjectOutputStream os = new ObjectOutputStream(fs);
        long startTime = System.currentTimeMillis();
        writeObject(os);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);

        FileInputStream fis = new FileInputStream("javaserialize.txt");
        ObjectInputStream is = new ObjectInputStream(fis);
        startTime = System.currentTimeMillis();
        readObject(is);
        endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }
}
