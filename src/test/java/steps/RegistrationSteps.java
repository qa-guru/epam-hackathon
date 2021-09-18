package steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import domain.FieldError;
import domain.User;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class RegistrationSteps {


    @Given("Open Registration page")
    public void openRegistrationPage() {
        Selenide.open("https://apparel-uk.local:9002/ucstorefront/en/login");
    }

    @And("Fill in registration data")
    public void fillInFollowingDetails(DataTable data) {
        List<User> users = User.transformFromDataTable(data);
        User user = users.get(0);
        $("[id='register.title']").selectOption(user.getTitle());
        $("[id='register.firstName']").setValue(user.getFirstname());
        $("[id='register.lastName']").setValue(user.getLastname());
        $("[id='register.email']").setValue(user.getEmail());
        $("[id='password']").setValue(user.getPassword());
        $("[id='register.checkPwd']").setValue(user.getConfirmation());
    }

    @And("Confirm consent")
    public void confirmConsent() {
        $("[id='consentForm.consentGiven1']").setSelected(true);
    }

    @And("Agree with terms and conditions")
    public void agreeWithTermsAndConditions() {
        $("[id='registerChkTermsConditions']").setSelected(true);
    }

    @And("Click to register")
    public void clickToRegister() {
        $(".register__section button[type='submit']").click();
    }

    @Then("Check that user is registered")
    public void checkThatUserIsRegistered() {
        $(".main__inner-wrapper .global-alerts").shouldHave(Condition.text("Thank you for registering."));
    }

    @Then("Check error that user already exists")
    public void checkErrorThatUserAlreadyExists() {
        $("[id='email.errors']").scrollIntoView(false).shouldHave(Condition.text(FieldError.ACCOUNT_ALREADY_EXISTS.getText()));
    }

    @Then("Check empty first name error")
    public void checkEmptyFirstNameError() {
        $("[id='firstName.errors']").scrollIntoView(false).shouldHave(Condition.text(FieldError.EMPTY_FIRST_NAME.getText()));
    }

    @Then("Check empty last name error")
    public void checkEmptyLastNameError() {
        $("[id='lastName.errors']").scrollIntoView(false).shouldHave(Condition.text(FieldError.EMPTY_LAST_NAME.getText()));

    }

    @Then("Check email error")
    public void checkEmailError() {
        $("[id='email.errors']").scrollIntoView(false).shouldHave(Condition.text(FieldError.INVALID_EMAIL.getText()));
    }
}
