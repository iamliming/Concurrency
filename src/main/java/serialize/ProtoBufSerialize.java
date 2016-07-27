package serialize;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import domain.PersonMsg;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author liming
 * @version [版本号, 七月 27, 2016]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ProtoBufSerialize
{
    public static void main(String[] args)
        throws IOException
    {
        FileOutputStream fs = new FileOutputStream("googlebufSerialize.txt");
        ObjectOutputStream os = new ObjectOutputStream(fs);
        long start = System.currentTimeMillis();
        //构建Person对象
        for(int i = 1; i < 1000000; i++)
        {
            PersonMsg.Person.Builder personBuilder = PersonMsg.Person.newBuilder();
            personBuilder.setId(i);
            personBuilder.setName("Tony" + i);
            personBuilder.setEmail(i + "@163.com");
            PersonMsg.Person person = personBuilder.build();
            //将数据写到输出流
            person.writeDelimitedTo(os);
            if(i % 100000 == 0 || i == 999999){
                os.flush();
            }
        }
        os.close();
        System.out.println(System.currentTimeMillis() - start);
        /**
         * 以下为数据反序列化
         */
        FileInputStream is = new FileInputStream("D:\\git\\Concurrency\\googlebufSerialize.txt");
        ObjectInputStream ois = new ObjectInputStream(is);
        start = System.currentTimeMillis();
        while(true)
        {
            PersonMsg.Person personIn = PersonMsg.Person.parseDelimitedFrom(ois);
            if(personIn == null)
            {
                break;
            }
        }
        ois.close();
        System.out.println(System.currentTimeMillis() - start);
    }
}


//PersonMsg.PersonList.Builder personListBuilder = PersonMsg.PersonList.newBuilder();
//
////构建Person对象
//PersonMsg.Person.Builder personBuilder = PersonMsg.Person.newBuilder();
//for(int i = 1; i < 1000000; i++)
//    {
//    personBuilder.setId(1);
//    personBuilder.setName("Tony");
//    personBuilder.setEmail("1@163.com");
//    PersonMsg.Person person = personBuilder.build();
//    personListBuilder.addPerson(person);
//    if(i % 100)
//    PersonMsg.PersonList personList = personListBuilder.build();
//    }
//    //将数据写到输出流
//    person.writeTo(os);
//    os.flush();