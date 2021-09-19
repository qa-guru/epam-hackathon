package guru.qa.bdd.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static guru.qa.core.Core.locate;
import static guru.qa.core.matcher.SimpleElementMatcher.assertThat;

public class ReadMoreSteps {
    @Then("Click on read more link")
    public void clickOnReadMoreLink(){
        locate(".simple-banner a[href=\"/ucstorefront/en/faq\"]").click();
    }

    @When("Check read more page")
    public void checkReadMorePage(){
        assertThat(locate("h2")).hasText("Frequently asked questions");
    }
}
