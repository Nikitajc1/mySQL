import Data.DataHelper;
import Data.DatabaseProcess;
import Pages.LoginPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;


public class SqlTest {

    LoginPage login;
    @BeforeEach
    void openChrome() {
        login = open("http://localhost:9999/", LoginPage.class);
    }

    @AfterAll
    static void cleanSQL() {
        try {
            DatabaseProcess.clean();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void firstTest() {
        var data = new DataHelper();

        DatabaseProcess.processing();
        var verif = login.login(data.getLogin(), data.getPassword());
        verif.validVerification(DatabaseProcess.processing());
    }
}