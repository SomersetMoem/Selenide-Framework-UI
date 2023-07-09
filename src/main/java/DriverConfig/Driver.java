package DriverConfig;

import App.AppConfig;
import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import static io.opentelemetry.semconv.trace.attributes.SemanticAttributes.EventDomainValues.BROWSER;

public class Driver {

    /**
     * Инициализирует настройки WebDriver для выполнения тестов.
     * PageLoadStrategy - стратегия загрузки страницы, если значение eager, то перед выполнением тестов WebDriver будет ждать пока страница не будет полностью загружена.
     * BrowserSize - размер окна браузера.
     * HoldBrowserOpen - параметр  определяет, должен ли WebDriver оставаться открытым после завершения выполнения тестов.
     * Screenshots - Параметр screenshots определяет, должен ли WebDriver сохранять скриншоты в процессе выполнения тестов.
     * Если headless = true, то все тесты будут выполнены без графического интерфейса.
     */
    public static void initDriver() {

        TestConfig.initConfig();

        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = false;
        Configuration.screenshots = true;
        Configuration.timeout = 5000;
        Configuration.pageLoadTimeout = 10000;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .savePageSource(false)
                .screenshots(false));

        if(TestConfig.isHeadless()) {
            Configuration.headless = true;
        } else {
            Configuration.headless = false;
        }

        switch (TestConfig.browser)
        {
            case "chrome":
                Configuration.browser = Browsers.CHROME;
                break;
            case "firefox":
                Configuration.browser = Browsers.FIREFOX;
                break;
        }
    }

    /**
     * Получает текущий экземпляр WebDriver, используемый Selenide.
     * @return текущий экземпляр WebDriver
     */
    public static WebDriver currentDriver() {
        return WebDriverRunner.getWebDriver();
    }

    /**
     * Открывает браузер и переходит на указанный URL-адрес.
     * @param url URL-адрес, на который необходимо перейти
     */
    public static void open(String url) {
        Selenide.open(url);
    }

    /**
     * Обновляет текущую страницу браузера.
     */
    public static void refresh() {
        Selenide.refresh();
    }

    /**
     * Выполняет переданный JavaScript-скрипт в текущем драйвере. Может быть полезно для
     * выполнения некоторых действий в браузере для которых selenide не предназначен.
     * @param script - JavaScript-скрипт, который необходимо выполнить
     * @throws java.lang.Exception если выполнение скрипта не удалось
     */
    public static void executeJs(String script) {
        JavascriptExecutor js = (JavascriptExecutor)currentDriver();
        try {
            js.executeScript(script);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Ожидает, пока URL страницы не содержит заданный фрагмент.
     * @param urlChunk фрагмент URL, который ожидается в адресе страницы
     */
    public static void waitForUrlContains(String urlChunk) {
        WebDriverWait wait = new WebDriverWait(currentDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains(urlChunk));
    }

    /**
     * Метод ожидает, пока текущий URL-адрес не перестанет содержать указанный кусок адреса.
     * Если URL-адрес продолжает содержать указанный кусок адреса после 20-ти попыток с интервалом в 1 секунду,
     * метод завершит свою работу. Метод использует метод getCurrentUrl() из WebDriver для получения текущего URL-адреса.
     * @param urlChunk кусок URL-адреса, наличие которого нужно проверить
     */
    public static void waitForUrlDoesNotContain(String urlChunk) {
        int maxTime = 20;
        while(  currentDriver().getCurrentUrl().contains(urlChunk)  && maxTime > 0) {
            wait(1);
            maxTime--;
        }
    }

    /**
     * Увеличивает размер окна браузера до максимального значения.
     */
    public static void maximize() {
        currentDriver().manage().window().maximize();
    }

    /**
     * Изменяет размер окна браузера до заданных размеров
     * @param width ширина окна браузера в пикселях
     * @param height высота окна браузера в пикселях
     */
    public static void changeWindowSize(int width, int height) {
        currentDriver().manage().window().setSize(new Dimension(width, height));
    }


    /**
     * Очищает куки и локальное хранилище браузера, открывает главную страницу приложения
     */
    public static void clearCookies() {
        open(AppConfig.baseUrl);
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    /**
     * Заакрывает текущий экземпляр браузера, освобождает ресурсы и завершает сеанс селениума
     */
    public static void close() {
        currentDriver().quit();
    }

    /**
     * Ожидание на указанное количество секунд
     * @param seconds время ожидания в секундах
     */
    public static void wait(int seconds)
    {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Создает скриншот текущей страницы и сохраняет его в папку "screenshots" внутри директории "test-output"
     * Имя файла скриншота формируется в формате "screenshot_HHmmssSSS.png" с использованием текущего времени
     * Если папка "screenshots" не существует, то она будет создана
     */
    public static void takeScreenshot() {

        File scrFile = ((TakesScreenshot) currentDriver()).getScreenshotAs(OutputType.FILE);

        String path = System.getProperty("user.dir")
                + File.separator + "test-output"
                + File.separator + "screenshots"
                + File.separator + " " + "screenshot_" +  (new SimpleDateFormat("HHmmssSSS").format(new Date())) + ".png";
        try {
            FileUtils.copyFile(scrFile, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Получает список логов браузера
     * @return список объектов типа LogEntry, содержащий записи логов браузера.
     */
    public static List<LogEntry> getBrowserLogs() {
        LogEntries log = currentDriver().manage().logs().get("browser");
        List<LogEntry> logList = log.getAll();
        return logList;
    }

    /**
     * Добавляет переданный cookie в текущую сессию браузера
     * @param cookie объект типа Cookie, который нужно добавить в текущую сессию браузера
     */
    public static void addCookie(Cookie cookie) {
        currentDriver().manage().addCookie(cookie);
    }

    /**
     * Получает cookie из текущей сессии браузера по его имени
     * @param cookieName имя cookie, который нужно получить из текущей сессии браузера
     * @return объект типа Cookie, соответствующий переданному имени, если он был найден, иначе null
     */
    public static Cookie getCookie(String cookieName) {
        return currentDriver().manage().getCookieNamed(cookieName);
    }

    /**
     * Удаляет cookie из текущей сессии браузера по его имени
     * @param cookieName имя cookie, который нужно удалить из текущей сессии браузера
     */
    public static void deleteCookie(String cookieName) {
        currentDriver().manage().deleteCookieNamed(cookieName);
    }

    /**
     * Получает все логи браузера
     * @return  Строка, содержащая все логи консоли браузера, разделенные символом новой строки
     */
    public static String getConsoleLogs() {
        return String.join("\n", Selenide.getWebDriverLogs(BROWSER));
    }
}
