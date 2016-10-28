package forkjoin;

import java.util.concurrent.ForkJoinTask;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author liming
 * @version [版本号, 八月 03, 2016]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class CalculateForkTask extends ForkJoinTask
{
    @Override
    public Object getRawResult()
    {
        return null;
    }

    @Override
    protected void setRawResult(Object value)
    {

    }

    @Override
    protected boolean exec()
    {
        return false;
    }
}
