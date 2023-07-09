import DriverConfig.Driver;
import App.App;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

public class BaseTest {

    protected App app;
    protected SoftAssert softAssert;
    protected Logger logger;


    @BeforeClass
    public void setUpNewClass() {
        app = new App();
        softAssert = new SoftAssert();
        Driver.initDriver();
        logger = LogManager.getLogger(getClass());
    }

    @AfterMethod
    public void clearCookies (ITestResult result) {
        Driver.clearCookies();
    }

    @AfterClass
    public void closeDriver(){
        Driver.close();
    }





}