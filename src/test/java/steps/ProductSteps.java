package steps;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.codeborne.selenide.Selenide.$$;

public class ProductSteps {
    @Given("^Open product page")
    public void openProductPage(){
        Selenide.open("https://apparel-uk.local:9002/ucstorefront/en/Categories/Accessories-women/Watches-women/Biarritz-Women/p/111114_black");
    }

    @And("^I can see the product code")
    public void seeProductCode(){
        Selenide.$(".product-details .code").shouldHave(Condition.text("111114_black"));
    }

    @When("^Increase Item Quantity by one")
    public void increaseItemQuantity(){
        Selenide.$(".page-details-add-to-cart-component .js-qty-selector-plus").click();
    }

    @When("^Decrease Item Quantity by one")
    public void decreaseItemQuantity(){
        Selenide.$(".page-details-add-to-cart-component .js-qty-selector-minus").click();
    }

    @When("^Add to bag")
    public void addToBasket(){
        Selenide.$("#addToCartButton").click();
    }

    @And("^I can see the product name")
    public void checkProductName(){
        Selenide.$("#cboxContent .name").shouldHave(Condition.text("Biarritz Women black"));
    }

    @Then("^Go to basket")
    public void goToBasket(){
        Selenide.$("#cboxContent .add-to-cart-item").click();
    }

    @Then("^Continue shopping")
    public void continueShopping(){
        Selenide.$("#cboxContent .js-mini-cart-close-button").click();
    }

    @Then("^Close window")
    public void closeWindow(){
        Selenide.$("#cboxContent #cboxClose").click();
    }

    // this feature doesn't work?
    @When("^Pick up in the store")
    public void pickUpInTheStore(){
        Selenide.$(".AddToCart-PickUpInStoreAction button").click();
    }

    // this feature doesn't work?
    @When("^Add to wishlist")
    public void addToWishList(){
        Selenide.$(".glyphicon-heart").click();
    }

    @When("^Click on tab review")
    public void clickOnTabReview(){
        Selenide.$("#tabreview").click();
    }

    @And("^I can see write a review button")
    public void clickReviewButton(){
        Selenide.$(".js-review-write-toggle").click();
    }

    @When("^Fill form")
    public void fillForm(){
        Selenide.$("#reviewForm [name=\"headline\"]").setValue("test");
        Selenide.$("#reviewForm [name=\"comment\"]").setValue("test");
        Selenide.$("#reviewForm .js-ratingIcon:nth-child(9)").click();
    }

    @Then("^Submit review form")
    public void submitForm(){
        Selenide.$("#reviewForm button").click();
    }

    @And("^Check alert message")
    public void checkAlertMessage(){
        Selenide.$(".global-alerts .getAccAlert").shouldHave(
            Condition.text("×\nThank you for your review."));
    }
}
