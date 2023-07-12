package pages;
import Helpers.ApiAuth;
import Helpers.DataGenerate;
import App.AppConfig;
import com.codeborne.selenide.*;
import Helpers.Trim;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;

import java.util.Date;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static Helpers.SwitchWindows.getWindowHandleExcept;
import static Helpers.SwitchWindows.switchToWindow;

public abstract class BasePage {

    protected String pageUrl;

    public BasePage(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    @Step("Открыть страницу")
    public void open() {
        String url = Trim.rtrim(AppConfig.baseUrl, "/") + "/" + Trim.ltrim(pageUrl, "/");
        Selenide.open(url);
    }

    @Step("Проверка URL")
    public void shouldURL()
    {
        String url = Trim.rtrim(AppConfig.baseUrl, "/") + "/" + Trim.ltrim(pageUrl, "/");
        webdriver().shouldHave(url(url));
    }

    @Step("Проверка текста в еррор-меседже")
    public void shouldErrorMassage(String expectedErrorMassage)
    {
        SelenideElement ERROR_MASSAGE = $(".v-messages__message");
        ERROR_MASSAGE.shouldHave(Condition.text(expectedErrorMassage));
    }

    @Step("Ожидает указанное колличество секунд")
    public void sleep(int second) throws InterruptedException {
        Thread.sleep(second*1000);
    }

    @Step("Количество слов в текст равно n")
    public void shouldQuantityWords(SelenideElement element, int expectedQuantity)
    {
        String text = element.text().trim();
        String[] words = text.split("\\s+");
        assert words.length == expectedQuantity;
    }

    @Step("Снять фокус с поля")
    public void elementNoFocus(SelenideElement element)
    {
        element.sendKeys(Keys.TAB);
    }

    @Step("Авторизация с помощью полученного token через API")
    public void addTokenCookie()
    {
        Date expDate = new Date();
        expDate.setTime(expDate.getTime() * (1000*1000));
        Cookie cookie = new Cookie("accessToken", ApiAuth.getToken(DataGenerate.generatePhoneNumber(), DataGenerate.generatorCode()),"domen", "/", expDate);
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
        Selenide.refresh();
    }

    @Step("Нажать Enter")
    public void pressEnter()
    {
        actions().sendKeys(Keys.ENTER).perform();
    }

    @Step("Проверка url в открывшемся окне")
    public void shouldUrlNewWindow(String expectedUrl) throws InterruptedException {
        String originalWindow = WebDriverRunner.getWebDriver().getWindowHandle();
        String newWindowHandle = getWindowHandleExcept(originalWindow);
        switchToWindow(newWindowHandle);
        webdriver().shouldHave(url(expectedUrl));
        closeWindow();
        switchToWindow(originalWindow);
    }


}
