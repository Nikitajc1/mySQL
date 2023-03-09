package Pages;

import Pages.Dashboard;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class VerificationCodePage {
    public VerificationCodePage() {
        verificationField.shouldBe(Condition.visible);
    }
    private SelenideElement verificationField = $("[data-test-id=code] input");
    private SelenideElement verificationButton = $("[data-test-id=action-verify]");

    public Dashboard validVerification(String sqlCode) {
        verificationField.setValue(sqlCode);
        verificationButton.click();
        return new Dashboard();
    }
}
