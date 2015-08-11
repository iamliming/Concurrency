package math;

/**
 * @author liming
 * @since 2015/8/11
 */
public class MathExample
{
    public static void main(String[] args)
    {
        double d1 = 0d;
        for (int i = 0; i < 10; i++)
        {
            d1 += 0.011d;
        }
        System.out.println(d1);//精度问题 0.10999999999999997
        //Math.ceil() function rounds a floating point value up to the nearest integer value.
        //The rounded value is returned as a double. Here is a Math.ceil() Java example:
        //小数位直接进一位，获取一个新的浮点型
        System.out.println(Math.ceil(d1));//1.0
        System.out.println(Math.ceil(0f));//0.0

        //The Math.floor() function rounds a floating point value down to the nearest integer value.
        //The rounded value is returned as a double. Here is a Math.floor() Java example:
        System.out.println(Math.floor(d1));//0.0

        double radians = Math.pow(12,2);
        System.out.println("radians = " + radians);
    }
}
