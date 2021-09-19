package steps;

import data.MenuItem;
import guru.qa.core.Core;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CategoryPage;
import pages.components.CategoryMenu;

import java.util.Comparator;

import static guru.qa.core.Core.locate;
import static guru.qa.core.Core.locateAll;
import static guru.qa.core.matcher.ElementListMatcher.assertThat;

public class SortSteps {

    private CategoryPage categoryPage = new CategoryPage();

    @Given("Open store brand page")
    public void openStoreBrandPage() {
        Core.open("https://apparel-uk.local:9002/ucstorefront/en");
        CategoryMenu menu = categoryPage.getMenu();
        menu.navigateTo(MenuItem.BRANDS);
    }

    @When("Set sorting by name ascending")
    public void setSortingByNameAscending() {
        locate("#sortOptions1").click();
        locate("option[value=name-asc]").click();
    }

    @Then("Check sorted results ascending")
    public void checkSortedResults() {
        assertThat(categoryPage.getProductsGrid())
                .checkTextsOrder(Comparator.naturalOrder());
    }

    @When("Set sorting by name descending")
    public void setSortingByNameDescending() {
        locate("#sortOptions1").click();
        locate("option[value=name-desc]").click();
    }

    @Then("Check sorted results descending")
    public void checkSortedResultsDescending() {
        assertThat(categoryPage.getProductsGrid())
                .checkTextsOrder(Comparator.reverseOrder());
    }

    @When("Set sorting by price ascending")
    public void setSortingByPriceAscending() {
        locate("#sortOptions1").click();
        locate("option[value=price-asc]").click();

    }

    @Then("Check sorted price results ascending")
    public void checkSortedPriceResultsAscending() {
        assertThat(locateAll(".price").trimTo(5)).checkTextsOrder(Comparator.naturalOrder());
    }

    @When("Set sorting by price descending")
    public void setSortingByPriceDescending() {
        locate("#sortOptions1").click();
        locate("option[value=price-desc]").click();
    }

    @Then("Check sorted price results descending")
    public void checkSortedPriceResultsDescending() {
        assertThat(locateAll(".price")).checkTextsOrder(Comparator.reverseOrder());
    }
}
