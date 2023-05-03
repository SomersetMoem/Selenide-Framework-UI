package helpers;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static helpers.Driver.getConsoleLogs;


public class AllureAttachment {

    /**
     * Возвращает сообщение в виде текста.
     * @param attachName имя вложения
     * @param message сообщение
     * @return сообщение в виде текста
     */
    @Attachment(value = "{attachName}", type = "text/plain")
    public static String attachAsText(String attachName, String message) {
        return message;
    }

    /**
     * Этот метод возвращает исходный код страницы в виде массива байтов и прикрепляет его как текстовый файл.
     * Исходный код страницы получается путем вызова метода `getPageSource` у экземпляра WebDriver.
     * Затем исходный код преобразуется в массив байтов с использованием кодировки UTF-8.
     * Имя прикрепления - "Page source".
     * @return исходный код страницы в виде массива байтов
     */
    @Attachment(value = "Page source", type = "text/plain")
    public static byte[] pageSource() {
        return getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    /**
     * Этот метод возвращает скриншот в виде массива байтов и прикрепляет его как изображение в формате PNG.
     * Скриншот получается путем приведения экземпляра WebDriver к типу TakesScreenshot и вызова метода `getScreenshotAs` с аргументом `OutputType.BYTES`.
     * Имя прикрепления задается аргументом `attachName`.
     * @param attachName имя прикрепления
     * @return скриншот в виде массива байтов
     */
    @Attachment(value = "{attachName}", type = "image/png")
    public static byte[] screenshotAs(String attachName) {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    /**
     * Этот метод прикрепляет журналы консоли браузера как текст.
     * Журналы консоли получаются путем вызова метода `getConsoleLogs`.
     * Метод `attachAsText` используется для прикрепления журналов с заголовком "Browser console logs".
     */
    public static void browserConsoleLogs() {
        attachAsText("Browser console logs", getConsoleLogs());
    }

    /**
     * Этот метод возвращает строку HTML, содержащую элемент видео.
     * Элемент видео имеет ширину и высоту 100% и имеет атрибуты управления и автовоспроизведения.
     * Источник видео получается путем вызова метода `getVideoUrl` с идентификатором сеанса в качестве аргумента.
     * Тип видео - 'video/mp4'.
     * @return строка HTML, содержащая элемент видео
     */
    @Attachment(value = "Video", type = "text/html", fileExtension = ".html")
    public static String addVideo() {
        return "<html><body><video width='100%' height='100%' controls autoplay><source src='"
                + getVideoUrl(getSessionId())
                + "' type='video/mp4'></video></body></html>";
    }

    /**
     * Возвращает URL-адрес видеофайла записи теста по переданному идентификатору сессии.
     * @param sessionId идентификатор сессии, для которой нужно получить URL-адрес видеофайла
     * @return URL-адрес видеофайла записи теста по переданному идентификатору сессии
     * или null, если переданный URL-адрес некорректен
     * @throws NullPointerException если переданный идентификатор сессии является null
     */
    public static URL getVideoUrl(String sessionId) {
        String videoUrl = "https://selenoid.autotests.cloud/video/" + sessionId + ".mp4";

        try {
            return new URL(videoUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Получает идентификатор текущей сессии WebDriver.
     * @return идентификатор сессии в виде строки.
     */
    public static String getSessionId() {
        return ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
    }
}
