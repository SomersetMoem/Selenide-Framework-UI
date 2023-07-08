package Helpers;

import io.qameta.allure.Flaky;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class TestNGRetry implements IRetryAnalyzer   {
    private static final int MAX_RETRY_COUNT = 3;
    private int retryCount = 0;

    /**
     * Определяет, нужно ли повторить выполнение теста.
     * Метод будет вызван для каждого тестового результата.
     *
     * @param result Результат выполнения теста.
     * @return true, если необходимо повторить выполнение теста; false в противном случае.
     */
    @Override
    public boolean retry(ITestResult result) {
        if (result.getMethod().getConstructorOrMethod().getMethod().isAnnotationPresent(Flaky.class)
                && retryCount < MAX_RETRY_COUNT) {
            retryCount++;
            return true;
        }
        return false;
    }
}