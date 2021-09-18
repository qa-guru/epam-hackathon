package steps;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.codeborne.selenide.Selenide.$$;

public class LoginSteps {

    //System.out.println("Step 1 Create new driver");
    //здесь уже непосредственно шаги кукумбера идут

    @Given("^Open login page")
    public void openHomePage(){
        Selenide.open("https://apparel-uk.local:9002/ucstorefront/en");
    }

    @When("^Search in search field")
    public void searchData(){
        Selenide.$("#js-site-search-input").setValue("book").pressEnter();
    }

    @Then("Check result")
    public void gettest11(){
        $$(".product-item").shouldHave(CollectionCondition.sizeGreaterThan(0));
    }
}
