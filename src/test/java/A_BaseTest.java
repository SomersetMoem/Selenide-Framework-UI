import app.App;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Driver;
import io.qameta.allure.testng.AllureTestNg;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import io.qameta.allure.selenide.AllureSelenide;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

class A_BaseTest {

    protected App app;
    protected SoftAssert softAssert;
    protected Logger logger;


    @BeforeClass
    public void setUp() {

        Driver.initDriver();

        app = new App();
        softAssert = new SoftAssert();

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterMethod
    public void clearCookies() {
        Driver.clearCookies();
    }

    @AfterClass
    public void tearDown() {
        Driver.close();
    }
}
