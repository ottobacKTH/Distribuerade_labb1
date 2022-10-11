package main.DB;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Class that distributes a logged in connection to the database
 */
public class DBManager {
    private static DBManager instance = null;
    private Connection connection = null;

    /**
     * returns a new DBManager instance if no instance exists. Otherwise return existing
     * @return DBManager instance
     */
    private static DBManager getInstance()
    {
        if(instance == null)
        {
            instance = new DBManager();
        }
        return instance;
    }

    /**
     * Constructor that creates a connection to the database
     */
    private DBManager()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost/labb1_db?user=backEndLabbUser&password=password");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    /**
     * Return connection to database
     * @return Connection
     */
    public static Connection getConnection()
    {
        return getInstance().connection;
    }
}
