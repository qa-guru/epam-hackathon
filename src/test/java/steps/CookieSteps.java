package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static guru.qa.core.Core.locate;
import static guru.qa.core.matcher.SimpleElementMatcher.assertThat;

public class CookieSteps {
    @When("See cookie message")
    public void checkCookieMessage(){
        assertThat(locate("#js-cookie-notification"))
                .containsText("We use cookies to ensure that we give you the best experience on our website");
    }

    @Then("Close cookie message")
    public void closeCookieMessage(){
        locate(".js-cookie-notification-accept").click();
    }

    @When("Check cookie message")
    public void checkCookieMessageIsNotPresent(){
        assertThat(locate("#js-cookie-notification")).isNotVisible();
    }
}
