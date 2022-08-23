import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class SectionConstructorTest {
    MainPage mainPage;

    @Before
    public void setUp() {
        //Для проверки в браузере Edge
        /*Configuration.browser = "edge";
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\rvvolkov1\\Documents\\Yap\\WebDriver\\bin\\msedgedriver.exe");*/
        Configuration.startMaximized = true;
        mainPage = open("https://stellarburgers.nomoreparties.site", MainPage.class);
    }

    @After
    public void tearDown() {
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("Переходы между разделами блока раздела «Конструктор»")
    @Description("Проверка, что работают переходы к разделам: «Булки», «Соусы», «Начинки»")
    public void constructorTransitionsTest() {
        mainPage.fillingTabClick();
        mainPage.fillingSectionIsVisible();
        mainPage.sauceTabClick();
        mainPage.sauceSectionIsVisible();
        mainPage.bunTabClick();
        mainPage.bunSectionIsVisible();
    }
}
