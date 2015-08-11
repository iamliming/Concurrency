package concurrent.example.threadlocal;

import domain.Connection;
import domain.ConnectionManager;

/**
 * @author liming
 * @since 2015/8/11
 */
public class ConnectionCache
{
    private static final ThreadLocal<Connection> connectionHold = new ThreadLocal<Connection>(){
        private Connection connection;
        @Override
        protected Connection initialValue()
        {
            return ConnectionManager.getConnection("ABC");
        }

    };

    public static Connection getConnectionHold(){
        return connectionHold.get();
    }
}
