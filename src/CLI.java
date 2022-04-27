import com.j256.ormlite.support.ConnectionSource;
import config.Database;

import java.sql.SQLException;

public class CLI {
    public static void main(String[] args) throws SQLException {
        System.out.println("Ejecutando CLI...");

        ConnectionSource connection = new Database().getInstance();
    }
}
