package jvmtest;

import java.io.IOException;
import java.io.InputStream;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author liming
 * @version [版本号, 三月 16, 2016]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ClassLoaderTest
{
    public static void main(String[] args)
        throws ClassNotFoundException
    {
        ClassLoader myClassLoader = new ClassLoader()
        {
            @Override
            public Class<?> loadClass(String name)
                throws ClassNotFoundException
            {
                try{
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if(is == null)
                    {
                        return super.loadClass(name);
                    }
                    else{
                        System.out.println("else");
                        byte[] b = new byte[is.available()];
                        is.read(b);
                        return defineClass(name, b, 0, b.length);
                    }
                }
                catch (IOException e)
                {
                    throw new ClassNotFoundException(name);
                }
            }
        };
        Object obj = myClassLoader.loadClass("jvmtest.ClassLoaderTest");
        System.out.println(obj.getClass());
        System.out.println(obj instanceof ClassLoaderTest);
    }
}
