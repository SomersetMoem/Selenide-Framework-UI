package Helpers;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SwitchWindows {

    /**
     * Получает идентификатор окна, исключая указанный идентификатор.
     *
     * @param excludedWindowHandle Идентификатор окна, который необходимо исключить.
     * @return Идентификатор окна, отличного от исключенного окна.
     * @throws IllegalStateException Если не удалось дождаться появления нового окна или не найдено окно, отличное от исключенного окна.
     */
    public static String getWindowHandleExcept(String excludedWindowHandle)
    {
        WebDriver driver = WebDriverRunner.getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        } catch (TimeoutException e) {
            throw new IllegalStateException("Не удалось дождаться появления нового окна");
        }

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(excludedWindowHandle)) {
                return windowHandle;
            }
        }

        throw new IllegalStateException("Не найдено окно, отличное от исключенного окна");
    }

    /**
     * Переключается на указанное окно.
     *
     * @param windowHandle Идентификатор окна, на которое необходимо переключиться.
     */
    public static void switchToWindow(String windowHandle){
        WebDriver driver = WebDriverRunner.getWebDriver();
        driver.switchTo().window(windowHandle);
    }

    /**
     * Закрывает текущее окно.
     */
    public static void closeWindow()
    {
        Selenide.closeWindow();
    }
}
