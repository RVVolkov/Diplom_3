package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegistrationPage {
    //Локатор поля имя
    @FindBy(how = How.XPATH, using = "//label[text()='Имя']")
    private SelenideElement nameField;
    //Локатор поля email
    @FindBy(how = How.XPATH, using = "//label[text()='Email']")
    private SelenideElement emailField;
    //Локатор поля пароль
    @FindBy(how = How.XPATH, using = "//label[text()='Пароль']")
    private SelenideElement passwordField;
    //Локатор кнопки "зарегистрироваться"
    @FindBy(how = How.XPATH, using = "//button[text()='Зарегистрироваться']")
    private SelenideElement registrationButton;
    //Локатор кнопки "войти"
    @FindBy(how = How.XPATH, using = "//a[text()='Войти']")
    private SelenideElement logInButton;

    //Сообщение "Некорректный пароль"
    @FindBy(how = How.XPATH, using = "//p[text()='Некорректный пароль']")
    private SelenideElement incorrectPasswordErrorMessage;

    @Step("Ввод данных в поле имя")
    public void setNameField(String name) {
        nameField.setValue(name);
    }

    @Step("Ввод данных в поле email")
    public void setEmailField(String email) {
        emailField.setValue(email);
    }

    @Step("Ввод данных в поле пароль")
    public void setPasswordField(String password) {
        passwordField.setValue(password);
    }

    @Step("Клик по кнопке зарегистрироваться")
    public void registrationButtonClick() {
        registrationButton.click();
    }

    @Step("Клик по кнопке войти")
    public void logInButtonClick() {
        logInButton.click();
    }

    @Step("Проверка отображения ошибки Некорректный пароль")
    public void incorrectPasswordErrorMessageCheck() {
        incorrectPasswordErrorMessage.isDisplayed();
    }
}
