import app.AppConfig;
import helpers.DataGenerate;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.testng.Tag;
import io.qameta.allure.testng.Tags;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


public class ExampleTest extends A_BaseTest
{
    @Test
    @Story("Успешный вход в Систему")
    @Tags({@Tag("smoke")})
    @Feature("Аутентификация пользователей")
    public void loginViaEmail() {
        app.loginPage.open();
        softAssert.assertEquals(1,1);



    }
    @Test
    @Feature("Login")
    @Story("Login via email2")
    @Description("Test login with valid email")
    public void loginViaEmail1() {
        app.loginPage.open();
        app.loginPage.login("tomsmith", "SuperSecretPassword!");

        logger.info("Sample info message");
        logger.warn("Sample warn message");
        logger.error("Sample error message");
        logger.fatal("Sample fatal message");

        softAssert.assertEquals(1,1);
        softAssert.assertAll();
    }
}
