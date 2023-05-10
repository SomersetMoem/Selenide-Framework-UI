import app.App;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.AllureAttachment;
import helpers.DataGenerate;
import helpers.Driver;
import io.qameta.allure.testng.AllureTestNg;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import io.qameta.allure.selenide.AllureSelenide;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

class A_BaseTest {

    protected App app;
    protected SoftAssert softAssert;
    protected Logger logger;


    @BeforeClass
    public void setUpNewClass() {
        app = new App();
        softAssert = new SoftAssert();
        Driver.initDriver();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        logger = LogManager.getLogger(getClass());
    }

    @AfterMethod
    public void addAttachment(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
        AllureAttachment.screenshotAs("Last screenshot");
        AllureAttachment.pageSource();
        AllureAttachment.browserConsoleLogs();
        AllureAttachment.addVideo();}
        Driver.clearCookies();
    }

    @AfterClass
    public void closeDriver(){
        Driver.close();
    }





}
