package steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class QuickOrderSteps {
    private final String productName = "Biarritz Women black";
    private final String productCode = "111114_black";
    private final Double productPrice = 80.96;

    @Given("^Open quick order page")
    public void openQuickOrderPage(){
        Selenide.open("https://apparel-uk.local:9002/ucstorefront/en/quickOrder");
    }

    @When("^Add order by code")
    public void addOrderByCode(){
        Selenide.$(".item__list--item .js-sku-input-field").setValue(productCode).pressEnter();
    }


    @When("^Add to basket")
    public void addToBasket(){
        Selenide.$("#js-add-to-cart-quick-order-btn-bottom").click();
    }

    /*
    @When("^Reset form")
    public void resetForm(){
        Selenide.$("#js-reset-quick-order-form-btn-bottom").click();
    }
     */

    @When("^Delete order")
    public void deleteOrder(){
        Selenide.$(".item__list--item .js-remove-quick-order-row").click();
    }

    @Then("^Check order data")
    public void checkOrderData(){
        Selenide.$(".item__list--item").shouldHave(Condition.text(productName));
        Selenide.$(".item__list--item").shouldHave(Condition.text(String.format("£%s",productPrice)));
    }

    @Then("^Check empty data")
    public void orderDataIsNotPresent(){
        Selenide.$(".item__list--item").shouldNotHave(Condition.text(productName));
    }
}
