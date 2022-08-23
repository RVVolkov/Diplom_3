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

public class UserTransitionsTests {
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
    AccountProfilePage accountProfilePage;

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
        mainPage.enterInAccountButtonClick();
        authorizationPage.setEmailField(email);
        authorizationPage.setPasswordField(password);
        authorizationPage.logInButtonClick();
        mainPage.mainPageIsVisible();
    }

    @After
    public void tearDown() {
        accountProfilePage.accountProfilePageIsVisible();
        accountProfilePage.logOutButtonClick();
        authorizationPage.loginPageIsVisible();
        Selenide.closeWebDriver();
        response = userBaseSteps.userAuthorization(email, password);
        accessToken = response.extract().body().path("accessToken");
        userBaseSteps.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Авторизация пользователя и переход в личный кабинет")
    @Description("Проверка успешного перехода в личный кабинет")
    public void userTransitionToAccountProfilePage() {
        siteHeaders.accountProfileButtonClick();
    }

    @Test
    @DisplayName("Авторизация пользователя и переход на главную страницу")
    @Description("Проверка успешного перехода на главную страницу из личного кабинета по клику на логотип сайта")
    public void userTransitionToMainPage() {
        siteHeaders.accountProfileButtonClick();
        accountProfilePage.accountProfilePageIsVisible();
        siteHeaders.stellarBurgersLogoClick();
        mainPage.mainPageIsVisible();
        siteHeaders.accountProfileButtonClick();
    }

    @Test
    @DisplayName("Авторизация пользователя и переход в раздел «Конструктор»")
    @Description("Проверка успешного перехода в раздел «Конструктор» из личного кабинета")
    public void userTransitionToConstructor() {
        siteHeaders.accountProfileButtonClick();
        accountProfilePage.accountProfilePageIsVisible();
        siteHeaders.constructorButtonClick();
        mainPage.mainPageIsVisible();
        siteHeaders.accountProfileButtonClick();
    }
}
