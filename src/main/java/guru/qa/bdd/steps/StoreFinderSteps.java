package guru.qa.bdd.steps;

import guru.qa.core.Core;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static guru.qa.core.Core.locate;
import static guru.qa.core.matcher.SimpleElementMatcher.assertThat;

public class StoreFinderSteps {

    @Given("^Open store finder page")
    public void openStoreFinderPage() {
        Core.open("https://apparel-uk.local:9002/ucstorefront/en/store-finder");
    }

    @When("^Search in store finder field")
    public void searchData() {
        WebElement input = locate("#storelocator-query");
        input.sendKeys("london");
        input.sendKeys(Keys.ENTER);
    }

    @Then("Check search store finder result")
    public void checkResult() {
        assertThat(locate(".js-store-finder-pager-item-all"))
                .hasText("45");
        assertThat(locate(".store__finder--details-info"))
                .isVisible();
    }
}
