package jvmtest;

import java.util.ArrayList;
import java.util.List;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author liming
 * @version [版本号, 三月 09, 2016]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class RuntimeConstantPoolOOM
{
    public static void main(String[] args)
    {
        List<String> list = new ArrayList<String>();
        int i = 0;
        while(true)
        {
            list.add(String.valueOf(i++).intern());
        }
    }
}
