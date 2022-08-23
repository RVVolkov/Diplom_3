package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class RestorePasswordPage {
    private final String restorePasswordPage = "https://stellarburgers.nomoreparties.site/login";

    //Локатор кнопки "Войти"
    @FindBy(how = How.XPATH, using = ".//a[@href='/login']")
    private SelenideElement logInButton;

    @Step("Клик по кнопке Войти")
    public void logInButtonClick() {
        logInButton.click();
    }

    @Step("Проверка, что Вы на странице восстановления пароля")
    public void authPageIsVisible() {
        webdriver().shouldHave(url(restorePasswordPage));
    }
}
