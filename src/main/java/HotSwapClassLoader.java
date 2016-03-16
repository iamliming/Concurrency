/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author liming
 * @version [版本号, 二月 15, 2016]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class HotSwapClassLoader extends ClassLoader
{
    public HotSwapClassLoader()
    {
        super(HotSwapClassLoader.class.getClassLoader());
    }

    public Class loadByte(byte[] classByte)
    {
        return defineClass(null, classByte, 0, classByte.length);
    }
}
