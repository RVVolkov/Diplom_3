package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AccountProfilePage {
    //Локатор кнопки выйти
    @FindBy(how = How.XPATH, using = "//button[text()='Выход']")
    private SelenideElement logOutButton;

    @Step("Клик по кнопке Выход")
    public void logOutButtonClick() {
        logOutButton.click();
    }
}
