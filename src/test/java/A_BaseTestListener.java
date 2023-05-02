import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import helpers.Driver;

import java.io.ByteArrayInputStream;

public class A_BaseTestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult testResult) {
        Driver.takeScreenshot();
        Driver.getBrowserLogs();
    }


    @Override
    public void onTestStart(ITestResult testResult) {}

    @Override
    public void onTestSuccess(ITestResult testResult) {}

    @Override
    public void onTestSkipped(ITestResult testResult) {}

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult testResult) {}

    @Override
    public void onStart(ITestContext testContext) {}

    @Override
    public void onFinish(ITestContext testContext) {}
}
