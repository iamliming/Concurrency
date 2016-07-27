package domain;

import java.io.Serializable;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author liming
 * @version [版本号, 七月 27, 2016]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class PersonJava implements Serializable
{
    private static final long serialVersionUID = 3903978687528338665L;
    private int id;
    private String name;
    private String email;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
}
