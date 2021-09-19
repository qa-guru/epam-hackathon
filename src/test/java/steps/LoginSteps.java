package steps;


import api.ApiCalls;
import domain.CommonError;
import guru.qa.core.Core;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.io.IOException;

import static guru.qa.core.Core.locate;
import static guru.qa.core.matcher.SimpleElementMatcher.assertThat;

public class LoginSteps {

    private ApiCalls apiCalls = new ApiCalls();

    @Given("Open Login page")
    public void openLoginPage() {
        Core.open("https://apparel-uk.local:9002/ucstorefront/en/login");
    }

    @And("Login with username as {string} and password as {string}")
    public void login(String username, String password) {
        locate("#j_username").sendKeys(username);
        locate("#j_password").sendKeys(password);
        locate("#loginForm button[type='submit']").click();
    }

    @Given("Api Login with username as {string} and password as {string}")
    public void apiLogin(String username, String password) throws IOException, InterruptedException {
        apiCalls.apiLogin(username, password);
    }


    @Then("Check invalid login data error")
    public void checkInvalidLoginDataError() {
        assertThat(locate(".global-alerts")).hasText(CommonError.INVALID_LOGIN_DATA.getText());
    }
}
