package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static guru.qa.core.Core.locate;
import static guru.qa.core.matcher.SimpleElementMatcher.assertThat;

public class SearchSteps {

    @When("Search product by code")
    public void searchProductByCode() {
        WebElement input = locate(".js-site-search-input");
        input.sendKeys("111114_black");
        input.sendKeys(Keys.ENTER);
    }

    @Then("Check search result")
    public void checkSearchResult() {
        assertThat(locate(".details .name"))
                .hasText("Biarritz Women black");
    }
}
