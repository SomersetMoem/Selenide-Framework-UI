package app.pages;
import app.AppConfig;
import com.codeborne.selenide.Selenide;
import helpers.Trim;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public abstract class BasePage {

    protected String pageUrl;

    /**
     * Конструктор класса BasePage, который принимает URL страницы в качестве аргумента.
     * @param pageUrl URL страницы
     */
    public BasePage(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    /**
     * Метод открывает страницу, используя переданный URL страницы, предварительно обрезая слеши с помощью метода trim()
     * @see Trim
     */
    public void open() {
        String url = Trim.rtrim(AppConfig.baseUrl, "/") + "/" + Trim.ltrim(pageUrl, "/");
        Selenide.open(url);
    }

    /**
     * Нажимает на элемент, заданный локатором.
     * @param locator локатор элемента.
     */
    public void clickElement(By locator) {
        $(locator).click();
    }

    /**
     * Вводит текст в элемент, заданный локатором.
     * @param locator локатор элемента.
     * @param text    текст для ввода.
     */
    public void typeText(By locator, String text) {
        $(locator).setValue(text);
    }

    /**
     * Возвращает текст элемента, заданного локатором.
     * @param locator локатор элемента.
     * @return текст элемента.
     */
    public String getElementText(By locator) {
        return $(locator).getText();
    }

    /**
     * Возвращает значение атрибута "value" элемента, заданного локатором.
     * @param locator локатор элемента.
     * @return значение атрибута "value" элемента.
     */
    public String getElementValue(By locator) {
        return $(locator).getAttribute("value");
    }

    /**
     * Проверяет, отображается ли элемент, заданный локатором.
     * @param locator локатор элемента.
     * @return true, если элемент отображается, false в противном случае.
     */
    public boolean isElementDisplayed(By locator) {
        return $(locator).isDisplayed();
    }

    /**
     * Прокручивает страницу до элемента, заданного локатором.
     * @param locator локатор элемента.
     */
    public void scrollToElement(By locator) {
        $(locator).scrollTo();
    }

    /**
     * Принимает (нажимает "OK") alert-окно.
     */
    public void acceptAlert() {
        switchTo().alert().accept();
    }

    /**
     * Отклоняет (нажимает "Cancel") alert-окно.
     */
    public void dismissAlert() {
        switchTo().alert().dismiss();
    }




}
