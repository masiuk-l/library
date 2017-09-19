package by.itacademy;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Project KR. Created by masiuk-l on 20.08.2017.
 */
public class DataSource {

    private static DataSource datasource;
    private final String URL;
    private final String DRIVER;
    private final String USER;
    private final String PASSWORD;
    private ComboPooledDataSource pooledDatasource;

    {
        ResourceBundle rb = ResourceBundle.getBundle("database");
        if (rb == null) {
            URL = "UNDEFINED";
            USER = "UNDEFINED";
            PASSWORD = "UNDEFINED";
            DRIVER = "com.mysql.jdbc.Driver";
            System.out.println("Бандл для db не был инициализирован");
        } else {
            URL = rb.getString("url");
            USER = rb.getString("user");
            PASSWORD = rb.getString("password");
            DRIVER = rb.getString("driver");
        }
    }

    /**
     * private constructor - configures pooledDatasource
     */
    private DataSource() throws IOException, SQLException, PropertyVetoException {
        pooledDatasource = new ComboPooledDataSource();
        pooledDatasource.setDriverClass(DRIVER);
        pooledDatasource.setJdbcUrl(URL);
        pooledDatasource.setUser(USER);
        pooledDatasource.setPassword(PASSWORD);

        pooledDatasource.setMinPoolSize(10);
        pooledDatasource.setAcquireIncrement(5);
        pooledDatasource.setMaxPoolSize(20);
        pooledDatasource.setMaxStatements(180);
    }

    /**
     *
     * @return an instance of DataSource
     */
    public static synchronized DataSource getInstance() throws IOException, SQLException, PropertyVetoException {
        if (datasource == null) {
            datasource = new DataSource();
            return datasource;
        } else {
            return datasource;
        }
    }

    /**
     * @return a new connection from pooledDatasource
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        return pooledDatasource.getConnection();
    }
}
