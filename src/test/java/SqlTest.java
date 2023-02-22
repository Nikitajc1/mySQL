import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static com.codeborne.selenide.Selenide.open;


public class SqlTest {

    LoginPage login;
    @BeforeEach
    void openChrome() {
        login = open("http://localhost:9999/", LoginPage.class);
    }

    @AfterAll
    static void dockerDown() throws IOException {
        DatabaseProcess.dockerDown();
    }

    @Test
    void firstTest() {
        DatabaseProcess databaseProcess = new DatabaseProcess();

        databaseProcess.processing();
        var verif = login.validLogin(String.valueOf(DatabaseProcess.logSQL));
        databaseProcess.processing();
        verif.validVerification(String.valueOf(DatabaseProcess.generatedCodeSQL));
    }
}