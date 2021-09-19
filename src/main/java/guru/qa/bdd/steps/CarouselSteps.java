package guru.qa.bdd.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import static guru.qa.core.Core.locate;

public class CarouselSteps {
    @And("Check product page")
    public void checkProductPage(){
        locate(".product-details .name").isDisplayed();
    }

    @Then("Click on product best selling link")
    public void clickOnLink(){
        locate(".owl-wrapper-outer .owl-item  a").click();
    }

    @And("Check first best selling product")
    public void checkFirstBestSellingProduct(){
        locate(".owl-wrapper-outer .owl-item .carousel__item--name").isDisplayed();
    }
}
