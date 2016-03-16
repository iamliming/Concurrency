/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author liming
 * @version [版本号, 九月 16, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class TestMe
{
    public static void main(String[] args) throws Exception {
        int i = 0x1;
        int j = 0x1;
        System.out.println(i|j);
        /*testGetObject();
        System.out.println("Second gc() call (in main)");
        System.gc();
        Thread.sleep(100000l);
        System.out.println("End of main");*/
        String s = "wo是";
        System.out.println(s.getBytes().length);
    }

    public static void testGetObject() throws Exception {
        /*System.out.println("Creating ClassLoader");
        ClassLoader cl = new URLClassLoader(new URL[] {new File("./x").toURI().toURL()});
        System.out.println("Loading Class");
        Class<?> clazz = cl.loadClass("GCTester");

        System.out.println("Getting static field");
        Field field = clazz.getField("INSTANCE");

        System.out.println("Reading static value");
        Object object = field.get(null);
        System.out.println("Got value: " + object);

        System.out.println("First gc() call");
        System.gc();
        Thread.sleep(1000);*/
    }
}
