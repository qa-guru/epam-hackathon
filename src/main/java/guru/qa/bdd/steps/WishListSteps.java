package guru.qa.bdd.steps;

import guru.qa.core.Core;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;

import static guru.qa.core.Core.locate;
import static guru.qa.core.matcher.SimpleElementMatcher.assertThat;

public class WishListSteps {
    private static final String wishName = "love";

    @Given("^Open wishlist page")
    public void openWishListPage() {
        Core.open("https://apparel-uk.local:9002/ucstorefront/wishlists");
    }

    @When("Create a new list")
    public void createNewList(){
        WebElement input = locate("#newwishlistname");
        input.sendKeys(wishName);
        locate(".btn-outline-secondary").click();
    }

    @Then("Check wishlist")
    public void checkWishList(){
        assertThat(locate("table")).containsText(wishName);
    }

    @When("Remove wishlist")
    public void removeFromWishlist(){
        locate("#removeWishlistButton").click();
        locate("#modalElement .btn-warning").click();
    }

    @Then("Check empty wishlist")
    public void checkEmptyWishList(){
        assertThat(locate("table")).hasNoText(wishName);
    }
}
