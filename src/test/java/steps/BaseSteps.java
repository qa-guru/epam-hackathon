package steps;


import guru.qa.core.Core;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import static guru.qa.core.Core.locate;
import static guru.qa.core.matcher.SimpleElementMatcher.assertThat;


public class BaseSteps {

    @And("Logout")
    public void logout() {
        locate(".nav__links a[href='/ucstorefront/en/logout']").click();
    }

    @Then("Check that user as {string} is logged in")
    public void checkThatUserAsIsLoggedIn(String username) {
        assertThat(locate(".logged_in")).hasTexts("Welcome", username);
        assertThat(locate(".nav__links a[href='/ucstorefront/en/logout']")).isVisible();
    }

    @After
    public void tearDownBrowser() {
        Core.close();
    }
}
