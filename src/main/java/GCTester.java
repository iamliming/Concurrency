/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author liming
 * @version [版本号, 九月 16, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class GCTester
{
    public static final GCTester INSTANCE=new GCTester();

    private GCTester() {
        System.out.println(this + " created");
    }

    public void finalize() {
        System.out.println(this + " finalized");
    }
}
