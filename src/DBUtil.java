import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {


    private static final String DB_URL = "jdbc:mysql://localhost:3306/can2gymdb";
    private static final String USER = "root";
    private static final String PASSWORD ="password";

    //Static method to open DB connection -- Static so you don't have to keep creating a DBUtil object
    public static Connection openConnection(){
        try{
            Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            return con;
        }
        catch (SQLException e) {
            return null;
        }
    }


}
