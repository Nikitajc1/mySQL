package Data;

import lombok.Data;
import lombok.Value;

public class DataHelper {
    private String login = "vasya";
    private String password = "qwerty123";

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}