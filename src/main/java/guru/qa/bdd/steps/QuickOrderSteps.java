package guru.qa.bdd.steps;

import guru.qa.core.Core;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static guru.qa.core.Core.locate;
import static guru.qa.core.matcher.SimpleElementMatcher.assertThat;

public class QuickOrderSteps {

    private final String productName = "Biarritz Women black";
    private final String productCode = "111114_black";
    private final Double productPrice = 80.96;

    @Given("^Open quick order page")
    public void openQuickOrderPage() {
        Core.open("https://apparel-uk.local:9002/ucstorefront/en/quickOrder");
    }

    @When("^Add order by code")
    public void addOrderByCode() {
        WebElement input = locate(".item__list--item .js-sku-input-field");
        input.sendKeys(productCode);
        input.sendKeys(Keys.ENTER);
    }

    @When("^Add to basket")
    public void addToBasket() {
        locate("#js-add-to-cart-quick-order-btn-bottom").click();
    }

    /*
    @When("^Reset form")
    public void resetForm(){
        locate("#js-reset-quick-order-form-btn-bottom").click();
    }
    */

    @When("^Delete order")
    public void deleteOrder() {
        locate(".item__list--item .js-remove-quick-order-row").click();
    }

    @Then("^Check order data")
    public void checkOrderData() {
        assertThat(locate(".item__list--item"))
                .containsText(productName)
                .containsText(String.format("£%s", productPrice));
    }

    @Then("^Check empty data")
    public void orderDataIsNotPresent() {
        assertThat(locate(".item__list--item"))
                .hasNoText(productName);
    }
}
