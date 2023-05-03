package helpers;

public class TestConfig {

    public static String browser = "chrome";
    public static String headless = "0";
    /**
     * Проверяем установлено ли системное свойство browser, если оно установлено то присваиваем переменной browser его значение, если нет, то присваиваем
     * значение по умолчанию chrome.
     * Проверяем установлено ли системное свойство headless, если оно установлено то присваиваем переменной headless его значение, если нет, то присваиваем
     * значение по умолчанию 0 (false), а если оно установлено 1 (true), то метод isHeadless вернет true и Configuration.headless = true.
     * */
    public static void initConfig() {
        browser = System.getProperty("browser") == null ? "chrome" : System.getProperty("browser");
        headless = System.getProperty("headless") == null ? "0" : System.getProperty("headless");
    }

    /**
     * Проверяет, включен ли режим headless веб-драйвера
     * @return true, если режим headless включен, иначе false
     */
    public static boolean isHeadless() {
        return headless.contains("1");
    }
}
