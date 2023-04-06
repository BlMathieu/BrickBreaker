package fr.umontpellier.iut.brickbreaker.stockage.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLUtils {

    private static SQLUtils instance = null;
    private Connection connection;

    private SQLUtils() {
        String url = "";
        String driver = "";
        String user = "";
        String pass = "";
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static SQLUtils getInstance() {
        if (instance == null) instance = new SQLUtils();
        return instance;
    }

    public Connection getConnection() { return connection; }

    public void closeConnection() {
        String req = "COMMIT";
        try (PreparedStatement st = connection.prepareStatement(req); ) {
            st.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        instance =  null;
    }
}
