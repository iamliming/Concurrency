package example;

/**
 * 总问
 *
 * @author liming
 * @version 2.1.1.1
 * @date 2014-07-28 10:17
 * @id $Id$
 */
public class HelloWorld
{
    public static void main(String[] from)
    {
        staticFunction();
    }

    static HelloWorld hw = new HelloWorld();

    static
    {
        System.out.println("1");
    }

    {
        System.out.println("2");
    }

    HelloWorld()
    {
        System.out.println("3");
        System.out.println("a="+a+"b="+b);
    }
    public static void staticFunction()
    {
        System.out.println("4");
    }
    int a = 110;

    static int b = 112;


}
