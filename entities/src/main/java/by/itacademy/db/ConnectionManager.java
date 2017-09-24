package by.itacademy.db;

import java.sql.Connection;

/**
 * Project KR. Created by masiuk-l on 20.08.2017.
 *
 * Manages database connection
 */
public class ConnectionManager {
    private static ThreadLocal<Connection> tl = new ThreadLocal<>();

    /**
     * @return new Connection from DataSource
     * @throws DBException in case of connection obtaining exceptions
     */
    public static Connection getConnection() throws DBException {
        try {
            if (tl.get() == null) {
                tl.set(DataSource.getInstance().getConnection());
            }
            return tl.get();
        } catch (Exception e) {
            throw new DBException("Ошибка получения соединения " + e.getMessage());
        }
    }
}
