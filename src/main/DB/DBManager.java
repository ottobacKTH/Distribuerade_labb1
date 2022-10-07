package main.DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {
    private static DBManager instance = null;
    private Connection connection = null;

    private static DBManager getInstance()
    {
        if(instance == null)
        {
            instance = new DBManager();
        }
        return instance;
    }

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
    public static Connection getConnection()
    {
        return getInstance().connection;
    }
}
