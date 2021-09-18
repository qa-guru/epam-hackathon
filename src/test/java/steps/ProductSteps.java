package steps;

import guru.qa.core.Core;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static guru.qa.core.Core.locate;
import static guru.qa.core.matcher.SimpleElementMatcher.assertThat;

public class ProductSteps {
    @Given("^Open product page")
    public void openProductPage() {
        Core.open("https://apparel-uk.local:9002/ucstorefront/en/Categories/Accessories-women/Watches-women/Biarritz-Women/p/111114_black");
    }

    @And("^I can see the product code")
    public void seeProductCode() {
        assertThat(locate(".product-details .code"))
                .hasText("111114_black");
    }

    @When("^Increase Item Quantity by one")
    public void increaseItemQuantity() {
        locate(".page-details-add-to-cart-component .js-qty-selector-plus").click();
    }

    @When("^Decrease Item Quantity by one")
    public void decreaseItemQuantity() {
        locate(".page-details-add-to-cart-component .js-qty-selector-minus").click();
    }

    @When("^Add to bag")
    public void addToBasket() {
        locate("#addToCartButton").click();
    }

    @And("^I can see the product name")
    public void checkProductName() {
        assertThat(locate("#cboxContent .name"))
                .hasText("Biarritz Women black");
    }

    @Then("^Go to basket")
    public void goToBasket() {
        locate("#cboxContent .add-to-cart-item").click();
    }

    @Then("^Continue shopping")
    public void continueShopping() {
        locate("#cboxContent .js-mini-cart-close-button").click();
    }

    @Then("^Close window")
    public void closeWindow() {
        locate("#cboxContent #cboxClose").click();
    }

    // this feature doesn't work?
    @When("^Pick up in the store")
    public void pickUpInTheStore() {
        locate(".AddToCart-PickUpInStoreAction button").click();
    }

    // this feature doesn't work?
    @When("^Add to wishlist")
    public void addToWishList() {
        locate(".glyphicon-heart").click();
    }

    @When("^Click on tab review")
    public void clickOnTabReview() {
        locate("#tabreview").click();
    }

    @And("^I can see write a review button")
    public void clickReviewButton() {
        locate(".js-review-write-toggle").click();
    }

    @When("^Fill form")
    public void fillForm() {
        locate("#reviewForm [name=\"headline\"]").sendKeys("test");
        locate("#reviewForm [name=\"comment\"]").sendKeys("test");
        locate("#reviewForm .js-ratingIcon:nth-child(9)").click();
    }

    @Then("^Submit review form")
    public void submitForm() {
        locate("#reviewForm button").click();
    }

    @And("^Check alert message")
    public void checkAlertMessage() {
        assertThat(locate(".global-alerts .getAccAlert"))
                .hasText("×\nThank you for your review.");
    }
}
