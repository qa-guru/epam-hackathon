package guru.qa.bdd.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import static guru.qa.core.Core.locate;
import static guru.qa.core.matcher.SimpleElementMatcher.assertThat;

public class CarouselSteps {
    private String productName;

    @And("Check product page")
    public void checkProductPage(){
        assertThat(locate(".product-details .name")).containsIgnoringCaseText(productName);
    }

    @Then("Click on product best selling link")
    public void clickOnLink(){
        locate(".owl-wrapper-outer .owl-item  a").click();
    }

    @And("Check first best selling product")
    public void checkFirstBestSellingProduct(){
        productName = locate(".owl-wrapper-outer .owl-item .carousel__item--name").getText();
    }
}
