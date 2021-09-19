package guru.qa.bdd.steps;

import guru.qa.core.Core;
import io.cucumber.java.en.Given;

public class MainSteps {

    @Given("^Open main page")
    public void openMainPage() {
        Core.open("https://apparel-uk.local:9002/ucstorefront/en");
    }
}
