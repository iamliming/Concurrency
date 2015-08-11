package domain;

/**
 * @author liming
 * @since 2015/8/11
 */
public class ConnectionManager
{
    public  static Connection getConnection(String name){
        Connection connection = new Connection();
        connection.setName("name");
        return connection;
    }
}
