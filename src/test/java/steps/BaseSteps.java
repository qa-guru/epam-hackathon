package steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class BaseSteps {

    @Before
    public void setUp() {
        Configuration.startMaximized = true;
    }

    @After
    public void tearDownBrowser() {
        Selenide.closeWebDriver();
    }
}
