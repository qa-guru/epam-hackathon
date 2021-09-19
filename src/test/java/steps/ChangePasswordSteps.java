package steps;

import domain.CommonError;
import guru.qa.core.Core;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static guru.qa.core.Core.locate;
import static guru.qa.core.matcher.SimpleElementMatcher.assertThat;

public class ChangePasswordSteps {

    @Given("Open Change password page")
    public void openChangePasswordPage() {
        Core.open("https://apparel-uk.local:9002/ucstorefront/en/my-account/update-password");
    }

    @And("Fill in currentPassword as {string} and newPassword as {string} and corfirmPassword as {string}")
    public void fillInPasswordData(String currentPassword, String newPassword, String confirmPassword) {
        locate("#currentPassword").sendKeys(currentPassword);
        locate("#newPassword").sendKeys(newPassword);
        locate("#checkNewPassword").sendKeys(confirmPassword);
    }

    @And("Click update password")
    public void clickUpdatePassword() {
        locate(".accountActions button[type='submit']").click();
    }

    @Then("Check that error is displayed that current password is incorrect")
    public void checkThatErrorIsDisplayedThatCurrentPasswordIsIncorrect() {
        assertThat(locate("[id='currentPassword.errors']")).hasText(CommonError.CURRENT_PASSWORD_NOT_MATCH.getText());
    }
}
