package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MainPage {
    //Локатор кнопки "Войти в аккаунт"
    @FindBy(how = How.XPATH, using = "")
    private SelenideElement enterInAccountButton;
    //Локатор вкладки "Булки"
    @FindBy(how = How.XPATH, using = "")
    private SelenideElement bunTab;
    //Локатор раздела "Булки"
    @FindBy(how = How.XPATH, using = "")
    private SelenideElement bunSection;
    //Локатор вкладки "Соусы"
    @FindBy(how = How.XPATH, using = "")
    private SelenideElement sauceTab;
    //Локатор раздела "Соусы"
    @FindBy(how = How.XPATH, using = "")
    private SelenideElement sauceSection;
    //Локатор вкладки "Начинки"
    @FindBy(how = How.XPATH, using = "")
    private SelenideElement fillingTab;
    //Локатор раздела "Начинки"
    @FindBy(how = How.XPATH, using = "")
    private SelenideElement fillingSection;

    @Step("Клик по кнопке Войти в аккаунт")
    public void enterInAccountButtonClick() {
        enterInAccountButton.click();
    }
    @Step("Клик по вкладке Булки")
    public void bunTabClick() {
        bunTab.click();
    }
    @Step("Проверка, что активный раздел - Булки")
    public void bunSectionIsVisible() {
        bunSection.shouldBe(Condition.visible);
    }
    @Step("Клик по вкладке Соусы")
    public void sauceTabClick() {
        sauceTab.click();
    }
    @Step("Проверка, что активный раздел - Соусы")
    public void sauceSectionIsVisible() {
        sauceSection.shouldBe(Condition.visible);
    }
    @Step("Клик по вкладке Начинки")
    public void fillingTabClick() {
        fillingTab.click();
    }
    @Step("Проверка, что активный раздел - Начинки")
    public void fillingSectionIsVisible() {
        fillingSection.shouldBe(Condition.visible);
    }


}
