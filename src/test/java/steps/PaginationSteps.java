package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import static guru.qa.core.Core.locateAll;
import static guru.qa.core.matcher.SimpleElementMatcher.assertThat;

public class PaginationSteps {

    @When("Go forward 3 pages page by page")
    public void goForwardPageByPage() {
        locateAll(".pagination").get(0).findElement(By.className("pagination-next")).click();
        locateAll(".pagination").get(0).findElement(By.className("pagination-next")).click();
        locateAll(".pagination").get(0).findElement(By.className("pagination-next")).click();
    }

    @Then("Check if it is a 4th page")
    public void checkIfItIsALastPage() {
        assertThat(locateAll(".pagination").get(0).findElement(By.className("active"))).containsText("4");
    }


}
