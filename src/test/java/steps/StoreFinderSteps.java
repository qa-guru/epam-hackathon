package steps;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.codeborne.selenide.Selenide.$$;

public class StoreFinderSteps {
    @Given("^Open store finder page")
    public void openStoreFinderPage(){
        Selenide.open("https://apparel-uk.local:9002/ucstorefront/en/store-finder");
    }

    @When("^Search in store finder field")
    public void searchData(){
        Selenide.$("#storelocator-query").setValue("london").pressEnter();
    }

    @Then("Check search result")
    public void checkResult(){
        Selenide.$(".js-store-finder-pager-item-all").shouldHave(Condition.text("45"));
        Selenide.$(".store__finder--details-info").shouldHave(Condition.visible);
    }
}
