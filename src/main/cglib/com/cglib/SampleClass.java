package com.cglib;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author liming
 * @version [版本号, 四月 01, 2016]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class SampleClass
{
    public static void main(String[] args)
    {
        System.out.println(System.currentTimeMillis());


        DateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = null;
        try
        {
            date = formatter.parse("20160706123000");
        }
        catch (ParseException e)
        {
            // e.printStackTrace();
        }

        System.out.println(date.getTime());

    }
}
