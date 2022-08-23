import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.*;
import site.nomoreparties.stellarburgers.*;

import static com.codeborne.selenide.Selenide.*;

public class UserAuthorizationAndLogoutTests {
    static Faker faker = new Faker();
    private static String email = faker.internet().emailAddress();
    private static String password = faker.internet().password(6, 9);
    private static String name = faker.name().firstName();
    private static String accessToken;
    private static UserBaseSteps userBaseSteps;
    static ValidatableResponse response;
    AuthorizationPage authorizationPage;
    SiteHeaders siteHeaders;
    MainPage mainPage;
    RegistrationPage registrationPage;
    AccountProfilePage accountProfilePage;
    RestorePasswordPage restorePasswordPage;

    @BeforeClass
    public static void beforeClass() {
        userBaseSteps = new UserBaseSteps();
        userBaseSteps.userCreationAndRegistration(email, password, name);
    }

    @AfterClass
    public static void afterClass() {
        response = userBaseSteps.userAuthorization(email, password);
        accessToken = response.extract().body().path("accessToken");
        userBaseSteps.deleteUser(accessToken);
    }

    @Before
    public void setUp() {
        //Для проверки в браузере Edge
        /*Configuration.browser = "edge";
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\rvvolkov1\\Documents\\Yap\\WebDriver\\bin\\msedgedriver.exe");*/
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
