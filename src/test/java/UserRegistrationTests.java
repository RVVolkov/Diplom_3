import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.AuthorizationPage;
import site.nomoreparties.stellarburgers.RegistrationPage;

import static com.codeborne.selenide.Selenide.*;

public class UserRegistrationTests {
    Faker faker = new Faker();
    private String email = faker.internet().emailAddress();
    private String password = faker.internet().password(6, 9);
    private String incorrectPassword = faker.internet().password(3, 5);
    private String name = faker.name().firstName();
    private String accessToken;
    private UserBaseSteps userBaseSteps;
    ValidatableResponse response;
    RegistrationPage registrationPage;
    AuthorizationPage authorizationPage;

    @Before
    public void setUp() {
        //Для проверки в браузере Edge
        /*Configuration.browser = "edge";
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\rvvolkov1\\Documents\\Yap\\WebDriver\\bin\\msedgedriver.exe");*/
        Configuration.startMaximized = true;
        registrationPage = open("https://stellarburgers.nomoreparties.site/register", RegistrationPage.class);
        authorizationPage = page(AuthorizationPage.class);
    }

    @After
    public void tearDown() {
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("Регистрация пользователя с корректными данными")
    @Description("Проверка успешной регистрации")
    public void userRegistrationWithValidCredentials() {
        registrationPage.setNameField(name);
        registrationPage.setEmailField(email);
        registrationPage.setPasswordField(password);
        registrationPage.registrationButtonClick();
        authorizationPage.loginPageIsVisible();
        userBaseSteps = new UserBaseSteps();
        response = userBaseSteps.userAuthorization(email, password);
        accessToken = response.extract().body().path("accessToken");
        userBaseSteps.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Регистрация пользователя с некорректным паролем")
    @Description("Проверка появления ошибки про Некорректный пароль")
    public void userRegistrationWithIncorrectPassword() {
        registrationPage.setNameField(name);
        registrationPage.setEmailField(email);
        registrationPage.setPasswordField(incorrectPassword);
        registrationPage.registrationButtonClick();
        registrationPage.incorrectPasswordErrorMessageCheck();
    }
}
