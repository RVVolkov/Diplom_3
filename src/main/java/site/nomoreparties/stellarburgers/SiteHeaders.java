package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SiteHeaders {
    //Локатор кнопки "конструктор"
    @FindBy(how = How.XPATH, using = "//p[text()='Конструктор']")
    private SelenideElement constructorButton;
    //Локатор логотипа сайта
    @FindBy(how = How.XPATH, using = "//a[@class='active']")
    private SelenideElement stellarBurgersLogo;
    //Локатор кнопки "личный кабинет"
    @FindBy(how = How.XPATH, using = "//p[text()='Личный Кабинет']")
    private SelenideElement accountProfileButton;

    @Step("Клик по кнопке конструктор")
    public void constructorButtonClick() {
        constructorButton.click();
    }
    @Step("Клик на логотип сайта")
    public void stellarBurgersLogoClick() {
        stellarBurgersLogo.click();
    }
    @Step("Клик по кнопке личный кабинет")
    public void accountProfileButtonClick() {
        accountProfileButton.click();
    }
}
