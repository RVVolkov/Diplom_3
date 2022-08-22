package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RestorePasswordPage {
    //Локатор кнопки "Войти"
    @FindBy(how = How.XPATH, using = "//a[text()='Войти']")
    private SelenideElement logInButton;

    @Step("Клик по кнопке Войти")
    public void logInButtonClick() {
        logInButton.click();
    }
}
