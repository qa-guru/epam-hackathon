package steps;

import data.FiltersItem;
import data.MenuItem;
import guru.qa.core.Core;
import guru.qa.core.matcher.SimpleElementMatcher;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import pages.CategoryPage;
import pages.components.CategoryMenu;

import java.util.Locale;

import static guru.qa.core.Core.locate;
import static guru.qa.core.Core.locateAll;
import static org.assertj.core.api.Assertions.assertThat;

public class FiltersSteps {

    private CategoryPage categoryPage = new CategoryPage();
    private int priceStarts;
    private String size;
    private String color;
    private String collectionFilter = "T-Shirts women";
    private String categoryFilter = "Streetwear men";
    private String brandFilter = "Billabong";

    @Given("Open brands page")
    public void openBrandsPage() {
        Core.open("https://apparel-uk.local:9002/ucstorefront/en");
        CategoryMenu menu = categoryPage.getMenu();
        menu.navigateTo(MenuItem.BRANDS);
    }

    @When("Use price filter")
    public void usePriceFilter() {
        String priceRange = categoryPage.getFilters().setFilter(FiltersItem.PRICE, 2);
        priceStarts = Integer.parseInt(priceRange.substring(1, 3));
    }

    @Then("Check if the price is right")
    public void checkIfThePriceIsRight() {
        int priceToCompare = Integer.parseInt(locateAll(".price").get(0).getText().substring(1, 3));
        assertThat(priceStarts).isLessThanOrEqualTo(priceToCompare);
    }

    @When("Use size filter")
    public void useSizeFilter() {
        size = categoryPage.getFilters().setFilter(FiltersItem.SIZE, 2);
        size = size.substring(0, size.indexOf(" "));
    }

    @Then("Check if the size is right")
    public void checkIfTheSizeIsRight() {
        String name = categoryPage.getProductsGrid().get(0).findElement(By.className("name")).getText();
        assertThat(name).contains(size);
    }

    @When("Use color filter")
    public void useColorFilter() {
        color = categoryPage.getFilters().setFilter(FiltersItem.COLOUR, 0);
        color = color.substring(0, color.indexOf(" "));
    }

    @Then("Check if the color is right")
    public void checkIfTheColorIsRight() {
        String name = categoryPage.getProductsGrid().get(0).findElement(By.className("name")).getText().toUpperCase(Locale.ROOT);
        assertThat(name).contains(color);
    }

    @When("Use gender filter")
    public void useGenderFilter() {
        categoryPage.getFilters().setFilter(FiltersItem.GENDER, "Female");
    }

    @Then("Check if the gender is right")
    public void checkIfTheGenderIsRight() {
        String name = categoryPage.getProductsGrid().get(0).findElement(By.className("name")).getText();
        assertThat(name).contains("Women");
    }

    @When("Use collection filter")
    public void useCollectionFilter() {
        categoryPage.getFilters().setFilter(FiltersItem.COLLECTION, collectionFilter);
        categoryPage.getProductsGrid().get(0).click();
    }

    @Then("Check if the collection is right")
    public void checkIfTheCollectionIsRight() {
        SimpleElementMatcher.assertThat(locate("title")).hasAttribute("text", collectionFilter);
    }

    @When("Use category filter")
    public void useCategoryFilter() {
        categoryPage.getFilters().setFilter(FiltersItem.CATEGORY, categoryFilter);
        categoryPage.getProductsGrid().get(0).click();
    }

    @Then("Check if the category is right")
    public void checkIfTheCategoryIsRight() {
        SimpleElementMatcher.assertThat(locate("title")).hasAttribute("text", categoryFilter);
    }

    @When("Use Brand filter")
    public void useBrandFilter() {
        categoryPage.getFilters().setFilter(FiltersItem.BRAND, brandFilter);
        categoryPage.getProductsGrid().get(0).findElement(By.tagName("a")).click();
    }

    @Then("Check if the Brand is right")
    public void checkIfTheBrandIsRight() {
        SimpleElementMatcher.assertThat(locate("div.description")).containsText(brandFilter);
    }
}
