package jvmtest;

/**
 * 类变量有准备和赋初始值两个阶段
 *
 * 而局部变量没有赋初始值阶段,编译时就将报错
 *
 * @author liming
 * @version [版本号, 三月 16, 2016]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class NoInitvalueInStack
{
    public static void main(String[] args)
    {
        int a = 1;
        System.out.println(a);
    }
}
