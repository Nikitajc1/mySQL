import com.codeborne.selenide.Selenide;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class DatabaseProcess {

    static Object logSQL;
    static Object generatedCodeSQL;

    public void processing() {
        var runner = new QueryRunner();

        try {
                var conn = getConnection(Data.url, Data.user, Data.pass);
            logSQL = runner.query(conn, Data.vasyaSQL, new ScalarHandler<>());
            generatedCodeSQL = runner.query(conn, Data.generatedCode, new ScalarHandler<>());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static void dockerDown() throws IOException {
        String[] cmd = new String[]{"docker-compose", "down"};
        Process process = Runtime.getRuntime().exec(cmd);
    }
}
