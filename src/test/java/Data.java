import lombok.Value;

public class Data {
    private Data() {
    }

    static String generatedCode = "select code from auth_codes group by code order by max(created) desc limit 1;";
    static String vasyaSQL = "select login from users where login='vasya';";

    static String url = "jdbc:mysql://localhost:3306/app";
    static String user = "app";
    static String pass = "pass";

}
