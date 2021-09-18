package steps;

import guru.qa.core.Core;
import io.cucumber.java.After;

public class BaseSteps {

    @After
    public void tearDownBrowser() {
        Core.close();
    }
}
