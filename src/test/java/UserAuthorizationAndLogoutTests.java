import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.*;

import static com.codeborne.selenide.Selenide.*;

public class UserAuthorizationAndLogoutTests {
    Faker faker = new Faker();
    private String email = faker.internet().emailAddress();
    private String password = faker.internet().password(6, 9);
    private String name = faker.name().firstName();
    private String accessToken;
    private UserBaseSteps userBaseSteps;
    ValidatableResponse response;
    AuthorizationPage authorizationPage;
    SiteHeaders siteHeaders;
    MainPage mainPage;
    RegistrationPage registrationPage;
    AccountProfilePage accountProfilePage;
    RestorePasswordPage restorePasswordPage;

    @Before
    public void setUp() {
        //Для проверки в браузере Edge
        /*Configuration.browser = "edge";
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\rvvolkov1\\Documents\\Yap\\WebDriver\\bin\\msedgedriver.exe");*/
        userBaseSteps = new UserBaseSteps();
        userBaseSteps.userCreationAndRegistration(email, password, name);
        Configuration.startMaximized = true;
        mainPage = open("https://stellarburgers.nomoreparties.site", MainPage.class);
        authorizationPage = page(AuthorizationPage.class);
        siteHeaders = page(SiteHeaders.class);
        accountProfilePage = page(AccountProfilePage.class);
        registrationPage = page(RegistrationPage.class);
        restorePasswordPage = page(RestorePasswordPage.class);
    }

    @After
    public void tearDown() {
        mainPage.mainPageIsVisible();
        siteHeaders.accountProfileButtonClick();
        accountProfilePage.accountProfilePageIsVisible();
        accountProfilePage.logOutButtonClick();
        authorizationPage.loginPageIsVisible();
        Selenide.closeWebDriver();
        response = userBaseSteps.userAuthorization(email, password);
        accessToken = response.extract().body().path("accessToken");
        userBaseSteps.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Авторизация пользователя по кнопке «Войти в аккаунт» на главной")
    @Description("Проверка успешной авторизации с главной страницы и выхода из аккаунта")
    public void userAuthFromMainPage() {
        mainPage.enterInAccountButtonClick();
        authorizationPage.setEmailField(email);
        authorizationPage.setPasswordField(password);
        authorizationPage.logInButtonClick();
    }

    @Test
    @DisplayName("Авторизация пользователя через кнопку «Личный кабинет» в хедере")
    @Description("Проверка успешной авторизации через хедер и выхода из аккаунта")
    public void userAuthFromAccountProfileButton() {
        siteHeaders.accountProfileButtonClick();
        authorizationPage.setEmailField(email);
        authorizationPage.setPasswordField(password);
        authorizationPage.logInButtonClick();
    }

    @Test
    @DisplayName("Авторизация пользователя через кнопку в форме регистрации")
    @Description("Проверка успешной авторизации через форму регистрации и выхода из аккаунта")
    public void userAuthFromRegisterForm() {
        mainPage.enterInAccountButtonClick();
        authorizationPage.registrationButtonClick();
        registrationPage.logInButtonClick();
        authorizationPage.setEmailField(email);
        authorizationPage.setPasswordField(password);
        authorizationPage.logInButtonClick();
    }

    @Test
    @DisplayName("Авторизация пользователя через кнопку в форме восстановления пароля")
    @Description("Проверка успешной авторизации через кнопку в форме восстановления пароля и выхода из аккаунта")
    public void userAuthFromRestorePasswordForm() {
        mainPage.enterInAccountButtonClick();
        authorizationPage.restorePasswordButtonClick();
        restorePasswordPage.logInButtonClick();
        authorizationPage.setEmailField(email);
        authorizationPage.setPasswordField(password);
        authorizationPage.logInButtonClick();
    }
}
