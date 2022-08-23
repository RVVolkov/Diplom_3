package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class AccountProfilePage {
    private final String accountProfilePage = "https://stellarburgers.nomoreparties.site/account/profile";
    //Локатор кнопки выйти
    @FindBy(how = How.XPATH, using = ".//button[@class='Account_button__14Yp3 text text_type_main-medium text_color_inactive']")
    private SelenideElement logOutButton;

    @Step("Проверка, что Вы в личном кабинете")
    public void accountProfilePageIsVisible() {
        webdriver().shouldHave(url(accountProfilePage));
    }

    @Step("Клик по кнопке Выход")
    public void logOutButtonClick() {
        logOutButton.click();
    }
}
