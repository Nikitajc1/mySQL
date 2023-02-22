import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginInput = $("[data-test-id=login] input");
    private SelenideElement passwordInput = $("[data-test-id=password] input");
    private SelenideElement button = $("[data-test-id=action-login]");

    public VerificationCodePage validLogin(String login) {
        loginInput.setValue(login);
        passwordInput.setValue("qwerty123");
        button.click();
        return new VerificationCodePage();
    }
}
