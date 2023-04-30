import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

public class ExampleTest extends A_BaseTest
{
    @Test
    @Feature("Login")
    @Story("Login via email")
    @Description("Test login with valid email")
    public void loginViaEmail() {
        app.loginPage.open();
        app.loginPage.login("tomsmith", "SuperSecretPassword!");

        logger.info("Sample info message");
        logger.warn("Sample warn message");
        logger.error("Sample error message");
        logger.fatal("Sample fatal message");

        softAssert.assertEquals(2,2);
        softAssert.assertAll();
    }
}
