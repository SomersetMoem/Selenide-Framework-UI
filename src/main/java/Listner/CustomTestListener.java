package Listner;

import Allure.AllureAttachment;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.selenide.LogType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import static io.qameta.allure.Allure.attachment;

public class CustomTestListener implements ITestListener {

    private Logger log = LoggerFactory.getLogger(CustomTestListener.class);
    @Override
    public void onTestStart(ITestResult result)
    {
        log.info("Test class started:" + result.getTestClass().getName());
        log.info("Test started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result)
    {
        log.info("Test SUCCESS: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        AllureAttachment.screenshotAs("Last screenshot");
        AllureAttachment.pageSource();
        AllureAttachment.browserConsoleLogs();
        log.info("Test FAILED: " + result.getName());
        if (result.getThrowable()!=null) {
            result.getThrowable().printStackTrace();
        }
    }
    }