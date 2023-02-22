import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.sql.DriverManager.getConnection;
import static java.sql.DriverManager.setLoginTimeout;


public class SqlTest {

    LoginPage login;
    @BeforeEach
    void openChrome() {
        login = open("http://localhost:9999/", LoginPage.class);
    }

    @AfterAll
    static void dockerDown() throws IOException {
        String[] cmd = new String[]{"docker-compose", "down"};
        Process process = Runtime.getRuntime().exec(cmd);
    }

    @Test
    void firstTest() {
        var runner = new QueryRunner();
        Object logSQL;
        Object generatedCodeSQL;

        try (
                var conn = getConnection(Data.url, Data.user, Data.pass);
        ) {
            logSQL = runner.query(conn, Data.vasyaSQL, new ScalarHandler<>());
            generatedCodeSQL = runner.query(conn, Data.generatedCode, new ScalarHandler<>());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        var verif = login.validLogin(String.valueOf(logSQL));
        verif.validVerification(String.valueOf(generatedCodeSQL));
    }
}
