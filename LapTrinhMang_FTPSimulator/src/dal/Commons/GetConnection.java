package dal.Commons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author HUỲNH QUANG VINH
 */
public class GetConnection {

    private Connection conn;
    private static GetConnection instance;

    public static GetConnection getInstance() {
        if (instance == null) {
            instance = new GetConnection();
        }
        return instance;
    }

    private GetConnection() {
        connectToSqlServer();
    }

    public Connection getConn() {
        return conn;
    }
    
    public void disconnect() throws SQLException{
        conn.close();
    }

    public void connectToSqlServer() {
        try {
            Class.forName(ConfigAuthen.getInstance().getForname());
            conn = DriverManager.getConnection(ConfigAuthen.getInstance().getUrl(),
                    ConfigAuthen.getInstance().getUser(),
                    ConfigAuthen.getInstance().getPassword());
            System.out.println("Connect database successfully.!!!");
        } catch (Exception e) {
            System.err.println("Connect failed , please check again.!!!" + e);
        }
    }

    public static void main(String[] args) {
        new GetConnection();
    }
}
