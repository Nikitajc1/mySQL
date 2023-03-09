package Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginInput = $("[data-test-id=login] input");
    private SelenideElement passwordInput = $("[data-test-id=password] input");
    private SelenideElement button = $("[data-test-id=action-login]");

    public VerificationCodePage login(String name, String pass) {

        loginInput.setValue(name);
        passwordInput.setValue(pass);
        button.click();
        return new VerificationCodePage();
    }
}