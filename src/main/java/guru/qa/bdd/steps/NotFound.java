package guru.qa.bdd.steps;

import guru.qa.bdd.domain.AlertMessage;
import guru.qa.core.Core;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static guru.qa.core.Core.locate;
import static guru.qa.core.matcher.SimpleElementMatcher.assertThat;

public class NotFound {

    @Given("^Open not found page")
    public void openMainPage() {
        Core.open("https://apparel-uk.local:9002/ucstorefront/en/dgdfgfdgdf");
    }

    @When("See not found message")
    public void checkNotFoundMessage() {
        assertThat(locate(".global-alerts .getAccAlert"))
                .containsText(AlertMessage.NOT_FOUND.getText());
    }

    @Then("Click on continue shopping")
    public void clickOnContinueShopping() {
        locate(".js-shopping-button").click();
    }
}
