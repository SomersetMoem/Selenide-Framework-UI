package App;

import pages.LoginPage;

public class App {

    public LoginPage loginPage;

    public App() {
        loginPage = PageBuilder.buildLoginPage();
    }
}
