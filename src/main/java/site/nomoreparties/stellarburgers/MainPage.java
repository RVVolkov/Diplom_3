package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class MainPage {
    private final String mainPage = "https://stellarburgers.nomoreparties.site/";

    //Локатор кнопки "Войти в аккаунт"
    @FindBy(how = How.XPATH, using = "//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']")
    private SelenideElement enterInAccountButton;
    //Локатор вкладки "Булки"
    @FindBy(how = How.XPATH, using = ".//span[text()='Булки']")
    private SelenideElement bunTab;
    //Локатор раздела "Булки"
    @FindBy(how = How.XPATH, using = ".//h2[text()='Булки']")
    private SelenideElement bunSection;
    //Локатор вкладки "Соусы"
    @FindBy(how = How.XPATH, using = ".//span[text()='Соусы']")
    private SelenideElement sauceTab;
    //Локатор раздела "Соусы"
    @FindBy(how = How.XPATH, using = ".//h2[text()='Соусы']")
    private SelenideElement sauceSection;
    //Локатор вкладки "Начинки"
    @FindBy(how = How.XPATH, using = ".//span[text()='Начинки']")
    private SelenideElement fillingTab;
    //Локатор раздела "Начинки"
    @FindBy(how = How.XPATH, using = ".//h2[text()='Начинки']")
    private SelenideElement fillingSection;

    @Step("Клик по кнопке Войти в аккаунт")
    public void enterInAccountButtonClick() {
        enterInAccountButton.click();
    }

    @Step("Клик по вкладке Булки")
    public void bunTabClick() {
        bunTab.shouldBe(Condition.enabled, Duration.ofSeconds(3));
        bunTab.click();
    }

    @Step("Проверка, что активный раздел - Булки")
    public void bunSectionIsVisible() {
        bunSection.shouldBe(Condition.visible);
    }

    @Step("Клик по вкладке Соусы")
    public void sauceTabClick() {
        sauceTab.shouldBe(Condition.enabled, Duration.ofSeconds(3));
        sauceTab.click();
    }

    @Step("Проверка, что активный раздел - Соусы")
    public void sauceSectionIsVisible() {
        sauceSection.shouldBe(Condition.visible);
    }

    @Step("Клик по вкладке Начинки")
    public void fillingTabClick() {
        fillingTab.shouldBe(Condition.enabled, Duration.ofSeconds(3));
        fillingTab.click();
    }

    @Step("Проверка, что активный раздел - Начинки")
    public void fillingSectionIsVisible() {
        fillingSection.shouldBe(Condition.visible);
    }

    @Step("Проверка, что Вы на главной странице и виден блок Соберите бургер")
    public void mainPageIsVisible() {
        webdriver().shouldHave(url(mainPage));
    }


}
