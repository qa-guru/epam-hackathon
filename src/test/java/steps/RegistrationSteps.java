package steps;

import domain.FieldError;
import domain.User;
import guru.qa.core.Core;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static guru.qa.core.Core.locate;
import static guru.qa.core.matcher.SimpleElementMatcher.assertThat;

public class RegistrationSteps {

    @Given("Open Registration page")
    public void openRegistrationPage() {
        Core.open("https://apparel-uk.local:9002/ucstorefront/en/login");
    }

    @And("Fill in registration data")
    public void fillInFollowingDetails(DataTable data) {
        List<User> users = User.transformFromDataTable(data);
        User user = users.get(0);
        new Select(locate("[id='register.title']")).selectByVisibleText(user.getTitle());
        locate("[id='register.firstName']").sendKeys(user.getFirstname());
        locate("[id='register.lastName']").sendKeys(user.getLastname());
        locate("[id='register.email']").sendKeys(user.getEmail());
        locate("[id='password']").sendKeys(user.getPassword());
        locate("[id='register.checkPwd']").sendKeys(user.getConfirmation());
    }

    @And("Confirm consent")
    public void confirmConsent() {
        locate("[id='consentForm.consentGiven1']").click();
    }

    @And("Agree with terms and conditions")
    public void agreeWithTermsAndConditions() {
        locate("[id='registerChkTermsConditions']").click();
    }

    @And("Click to register")
    public void clickToRegister() {
        locate(".register__section button[type='submit']").click();
    }

    @Then("Check that user is registered")
    public void checkThatUserIsRegistered() {
        assertThat(locate(".main__inner-wrapper .global-alerts"))
                .containsText("Thank you for registering.");
    }

    @Then("Check error that user already exists")
    public void checkErrorThatUserAlreadyExists() {
        assertThat(locate("[id='email.errors']"))
                .hasText(FieldError.ACCOUNT_ALREADY_EXISTS.getText());
    }

    @Then("Check empty first name error")
    public void checkEmptyFirstNameError() {
        assertThat(locate("[id='firstName.errors']"))
                .hasText(FieldError.EMPTY_FIRST_NAME.getText());
    }

    @Then("Check empty last name error")
    public void checkEmptyLastNameError() {
        assertThat(locate("[id='lastName.errors']"))
                .hasText(FieldError.EMPTY_LAST_NAME.getText());
    }

    @Then("Check email error")
    public void checkEmailError() {
        assertThat(locate("[id='email.errors']"))
                .hasText(FieldError.INVALID_EMAIL.getText());
    }
}
