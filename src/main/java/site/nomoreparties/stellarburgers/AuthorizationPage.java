package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AuthorizationPage {
    //Локатор поля "Email"
    @FindBy(how = How.XPATH, using = "//label[text()='Email']")
    private SelenideElement emailField;
    //Локатор поля "Пароль"
    @FindBy(how = How.XPATH, using = "//label[text()='Пароль']")
    private SelenideElement passwordField;
    //Локатор кнопки "Войти"
    @FindBy(how = How.XPATH, using = "//button[text()='Войти']")
    private SelenideElement logInButton;
    //Локатор кнопки "Зарегистрироваться"
    @FindBy(how = How.XPATH, using = "//a[text()='Зарегистрироваться']")
    private SelenideElement registrationButton;
    //Локатор кнопки "Восстановить пароль"
    @FindBy(how = How.XPATH, using = "//a[text()='Восстановить пароль']")
    private SelenideElement restorePasswordButton;

    @Step("Ввод данных в поле email")
    public void setEmailField(String email) {
        emailField.setValue(email);
    }

    @Step("Ввод данных в поле пароль")
    public void setPasswordField(String password) {
        passwordField.setValue(password);
    }

    @Step("Клик по кнопке войти")
    public void logInButtonClick() {
        logInButton.click();
    }

    @Step("Клик по кнопке зарегистрироваться")
    public void registrationButtonClick() {
        registrationButton.click();
    }

    @Step("Клик по кнопке Восстановить пароль")
    public void restorePasswordButtonClick() {
        restorePasswordButton.click();
    }
}
