import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.sql.DriverManager.getConnection;
import static java.sql.DriverManager.setLoginTimeout;


public class SqlTest {

    @BeforeEach
    void openChrome() {
        open("http://localhost:9999/");
    }

    @Test
    void webTest() {

        var vasyaSQL = "select login from users where login='vasya';";
        var generatedCode = "select code from auth_codes group by code order by max(created) desc limit 1;";
        var runner = new QueryRunner();
        Object logSQL;
        Object generatedCodeSQL;
        String password = "qwerty123";

        try (
                var conn = getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
        ) {
            logSQL = runner.query(conn, vasyaSQL, new ScalarHandler<>());
            generatedCodeSQL = runner.query(conn, generatedCode, new ScalarHandler<>());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        $("[data-test-id=login] input").setValue(String.valueOf(logSQL));
        $("[data-test-id=password] input").setValue(password);
        $("[data-test-id=action-login]").click();

        try {
            DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass").abort(runnable -> new Thread(runnable).start());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        /* По какой-то причине информация из ДБ о сгенерированных кодах не обновляется,
        пока я не проверну такой трюк. Выглядит криво и некрасиво, но по другому я пока не знаю,
        как решить эту проблему.
         */

        $("[data-test-id=code] input").setValue(String.valueOf(generatedCodeSQL));
        $("[data-test-id=action-verify]").click();
        $("[data-test-id=dashboard").shouldBe(Condition.visible);
    }
}
