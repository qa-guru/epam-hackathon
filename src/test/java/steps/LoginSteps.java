package steps;

import guru.qa.core.Core;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static guru.qa.core.Core.locate;
import static guru.qa.core.Core.locateAll;
import static guru.qa.core.matcher.ElementListMatcher.assertThat;

public class LoginSteps {

    //System.out.println("Step 1 Create new driver");
    //здесь уже непосредственно шаги кукумбера идут

    @Given("^Open login page")
    public void openHomePage() {
        Core.open("https://apparel-uk.local:9002/ucstorefront/en");
    }

    @When("^Search in search field")
    public void searchData() {
        locate("#js-site-search-input").sendKeys("book");
        locate(".js_search_button").click();
    }

    @Then("Check result")
    public void gettest11() {
        assertThat(locateAll(".product-item")).hasSizeGreaterThan(1);
    }

    @After("@closeBrowser")
    public void closeBrowser() {
        Core.close();
    }
}
