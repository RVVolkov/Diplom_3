import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.*;
import site.nomoreparties.stellarburgers.*;

import static com.codeborne.selenide.Selenide.*;

public class UserTransitionsTests {
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
    AccountProfilePage accountProfilePage;

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
