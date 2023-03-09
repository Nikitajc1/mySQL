package Data;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static java.sql.DriverManager.getConnection;

@UtilityClass
public class DatabaseProcess {

    private static Object generatedCodeSQL;
    private static String generatedCode = "select code from auth_codes group by code order by max(created) desc limit 1;";
    private static String url = "jdbc:mysql://localhost:3306/app";
    private static String user = "app";
    private static String pass = "pass";
    private static QueryRunner runner = new QueryRunner();

    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }

    public static String processing() {

        try {
                var conn = getConn();
            generatedCodeSQL = runner.query(conn, generatedCode, new ScalarHandler<>());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return (String) generatedCodeSQL;
    }

    public static void clean() throws SQLException {
        var connection = getConn();
        runner.execute(connection, "DELETE FROM auth_codes");
        runner.execute(connection, "DELETE FROM card_transactions");
        runner.execute(connection, "DELETE FROM cards");
        runner.execute(connection, "DELETE FROM users");
    }
}
