package steps;


import api.ApiCalls;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import java.io.IOException;

import static guru.qa.core.Core.locate;

public class LoginSteps {

    private ApiCalls apiCalls = new ApiCalls();

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
}
