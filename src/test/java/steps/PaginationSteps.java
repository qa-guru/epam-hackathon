package steps;

import data.MenuItem;
import guru.qa.core.Core;
import guru.qa.core.ElementList;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java8.El;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.CategoryPage;
import pages.components.CategoryMenu;

import static guru.qa.core.Core.locate;
import static guru.qa.core.Core.locateAll;
import static guru.qa.core.matcher.SimpleElementMatcher.assertThat;

public class PaginationSteps {

    private CategoryPage categoryPage = new CategoryPage();

    @Given("Open store brand page")
    public void openStoreBrandPage() {
        Core.open("https://apparel-uk.local:9002/ucstorefront/en");
        CategoryMenu menu = categoryPage.getMenu();
        menu.navigateTo(MenuItem.BRANDS);
    }

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
