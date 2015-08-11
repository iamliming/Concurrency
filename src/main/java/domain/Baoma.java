package domain;

/**
 * @author liming
 * @since 2015/8/11
 */
public class Baoma implements Vehicle
{

    private String color;

    public Baoma(String color)
    {
        this.color = color;
    }

    @Override
    public String bender()
    {
        return "奔驰";
    }

    @Override
    public String color()
    {
        return color;
    }
}
