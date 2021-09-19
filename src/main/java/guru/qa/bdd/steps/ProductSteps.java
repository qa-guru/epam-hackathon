package guru.qa.bdd.steps;

import guru.qa.bdd.domain.AlertMessage;
import guru.qa.bdd.domain.Review;
import guru.qa.bdd.domain.ReviewFieldError;
import guru.qa.core.Core;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static guru.qa.core.Core.locate;
import static guru.qa.core.matcher.SimpleElementMatcher.assertThat;

public class ProductSteps {
    private static final String productName = "Biarritz Women black";
    private static final String productCode = "111114_black";

    @Given("^Open product page")
    public void openProductPage() {
        Core.open("https://apparel-uk.local:9002/ucstorefront/en/Categories/Accessories-women/Watches-women/Biarritz-Women/p/111114_black");
    }

    @And("^I can see the product code")
    public void seeProductCode() {
        assertThat(locate(".product-details .code"))
                .hasText(productCode);
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
                .hasText(productName);
    }

    @Then("^Go to basket")
    public void goToBasket() {
        locate("#cboxContent .add-to-cart-button").click();
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

    @And("^Click on review button")
    public void clickReviewButton() {
        locate(".js-review-write-toggle").click();
    }

    @When("^Fill form")
    public void fillForm(DataTable data) {
        List<Review> reviews = Review.transformFromDataTable(data);
        Review review = reviews.get(0);
        locate("#review\\.headline").sendKeys(review.getTitle());
        locate("#review\\.comment").sendKeys(review.getDescription());
        locate(String.format("#reviewForm .js-ratingIcon:nth-child(%s)", review.getRate())).click();
        locate("#alias").sendKeys(review.getName());
    }

    @Then("^Submit review form")
    public void submitForm() {
        locate("#reviewForm button").click();
    }

    @And("^Check review alert message")
    public void checkAlertMessage() {
        assertThat(locate(".global-alerts .getAccAlert"))
                .containsText(AlertMessage.SUCCESSFUL_REVIEW.getText());
    }

    @And("^Check alert error message")
    public void checkAlertErrorMessage() {
        assertThat(locate(".global-alerts .getAccAlert"))
                .containsText(AlertMessage.MANDATORY_REVIEW_FIELD.getText());
    }

    @And("^Check review error message")
    public void checkErrorMessage() {
        assertThat(locate("#headline\\.errors"))
                .hasText(ReviewFieldError.EMPTY_TITLE.getText());
        assertThat(locate("#comment\\.errors"))
                .hasText(ReviewFieldError.EMPTY_DESCRIPTION.getText());
        assertThat(locate("#rating\\.errors"))
                .hasText(ReviewFieldError.NO_RATE.getText());
    }

    @And("^Check product in basket")
    public void checkProductInBasket() {
        assertThat(locate(".item__list__cart .item__list--item .item__name"))
                .hasText(productName);
        assertThat(locate(".item__list__cart .item__list--item .item__code"))
                .hasText(productCode);
    }
}
