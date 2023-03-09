package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Dashboard {
    public Dashboard() {
        dashboardPage.shouldBe(Condition.visible);
    }

    private SelenideElement dashboardPage = $("[data-test-id=dashboard");
}
