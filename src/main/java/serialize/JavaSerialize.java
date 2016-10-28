package serialize;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;

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
        String encodeString = (URLEncoder.encode("2分恭贺贴：\n"
            + "\n"
            + "[url=QRrqEr]\n"
            + "[url=mIvMzy]\n"
            + "[url=R77Nj2]\n"
            + "[url=qA7zIb]\n"
            + "[url=AFBrEf]\n"
            + "[url=I7vqa2]\n"
            + "[url=ERbiQf]\n"
            + "[url=YvIRNb]\n"
            + "[url=32UBRj]\n"
            + "[url=NRzMFz]\n"
            + "[url=6f2AVv]\n"
            + "[url=ieiyii]\n"
            + "[url=2EJrua]\n"
            + "[url=ArEjYn]\n"
            + "[url=3aYBfq]\n"
            + "[url=YBZrIn]\n"
            + "\n"
            + "1.5分恭贺贴：\n"
            + "\n"
            + "[url=ER77Fz]\n"
            + "[url=NJ3q2m]\n"
            + "[url=vE3uqm]\n"
            + "[url=jINnIj]\n"
            + "[url=qQr2Er]\n"
            + "[url=QneqU3]\n"
            + "[url=fiy6fe]\n"
            + "[url=ruUnie]\n"
            + "[url=Q7RJ7z]\n"
            + "[url=yiUjqm]\n"
            + "[url=ym2Afm]\n"
            + "[url=vumqAr]\n"
            + "[url=y6f2If]\n"
            + "[url=2Evy22]\n"
            + "[url=aaUJrm]\n"
            + "[url=BvINve]\n"
            + "[url=QRZBzi]\n"
            + "[url=imyAJv]\n"
            + "[url=j2YFrq]\n"
            + "[url=vq2URv]\n"
            + "[url=Nz2eam]\n"
            + "[url=vYbyIf]\n"
            + "[url=bUvAN3]\n"
            + "[url=ErMRRj]\n"
            + "[url=IriUna]\n"
            + "[url=UfQ7Nr]\n"
            + "[url=Z3YJji]\n"
            + "[url=vyyAjm]\n"
            + "[url=6Jzi2i]\n"
            + "[url=fAna6n]\n"
            + "[url=Rv6bEj]\n"
            + "[url=Fr2Abi]\n"
            + "[url=rY3aQj]\n"
            + "[url=NjaAnu]\n"
            + "[url=eQ3Ebm]\n"
            + "[url=Z77FJ3]\n"
            + "[url=Q3yQBj]\n"
            + "[url=Vn6nAj]\n"
            + "\n"
            + "1分恭贺贴：\n"
            + "\n"
            + "[url=yqimmy]\n"
            + "[url=i6viqe]\n"
            + "[url=vEVFVn]\n"
            + "[url=ZFNzu2]\n"
            + "[url=eaUJ3q]\n"
            + "[url=3URjUz]\n"
            + "[url=6bENri]\n"
            + "[url=YFfE7z]\n"
            + "[url=e6FV32]\n"
            + "[url=2YVnYf]\n"
            + "[url=7rQNz2]\n"
            + "[url=ZvArA3]", "utf-8"));
        System.out.println(URLDecoder.decode(encodeString,"utf-8"));
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
