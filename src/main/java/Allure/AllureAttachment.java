package Allure;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static DriverConfig.Driver.getConsoleLogs;

public class AllureAttachment {

    /**
     * Возвращает сообщение в виде текста.
     * @param attachName имя вложения
     * @param message сообщение
     */
    @Attachment(value = "{attachName}", type = "text/plain")
    public static String attachAsText(String attachName, String message) {
        return message;
    }

    /**
     * Прикрепляет исходный код страницы к отчету в виде текстового файла
     */
    @Attachment(value = "Page source", type = "text/plain")
    public static byte[] pageSource() {
        return getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    /**
     * Прикрепляет скриншот к отчету в формате PNG
     * @param attachName имя прикрепления
     */
    @Attachment(value = "{attachName}", type = "image/png")
    public static byte[] screenshotAs(String attachName) {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    /**
     * Прикрепляет журналы консоли браузера как текст.
     */
    @Attachment(value = "Console log", type = "text/plain")
    public static void browserConsoleLogs() {
        attachAsText("Browser console logs", getConsoleLogs());
    }

}
