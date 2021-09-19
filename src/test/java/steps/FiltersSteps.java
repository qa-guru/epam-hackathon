package steps;

import data.FiltersItem;
import data.MenuItem;
import guru.qa.core.Core;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import pages.CategoryPage;
import pages.components.CategoryMenu;

import java.util.Locale;

import static guru.qa.core.Core.locate;

public class FiltersSteps {

    private final CategoryPage categoryPage = new CategoryPage();
    int priceStarts;
    String size;
    String color;
    String collectionFilter = "T-Shirts women";
    String categoryFilter = "Streetwear men";
    String brandFilter = "Billabong";

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
        int priceToCompare = Integer.parseInt(categoryPage.getProductsGrid().get(0).findElement(By.className("price")).getText().substring(1, 3));
        Assertions.assertTrue(priceStarts <= priceToCompare);
    }

    @When("Use size filter")
    public void useSizeFilter() {
        size = categoryPage.getFilters().setFilter(FiltersItem.SIZE, 2);
        size = size.substring(0, size.indexOf(" "));
    }

    @Then("Check if the size is right")
    public void checkIfTheSizeIsRight() {
        String name = categoryPage.getProductsGrid().get(0).findElement(By.className("name")).getText();
        Assertions.assertTrue(name.contains(size));
    }

    @When("Use color filter")
    public void useColorFilter() {
        color = categoryPage.getFilters().setFilter(FiltersItem.COLOUR, 0);
        color = color.substring(0, color.indexOf(" "));
    }

    @Then("Check if the color is right")
    public void checkIfTheColorIsRight() {
        String name = categoryPage.getProductsGrid().get(0).findElement(By.className("name")).getText().toUpperCase(Locale.ROOT);
        Assertions.assertTrue(name.contains(color));
    }

    @When("Use gender filter")
    public void useGenderFilter() {
        categoryPage.getFilters().setFilter(FiltersItem.GENDER, "Female");
    }

    @Then("Check if the gender is right")
    public void checkIfTheGenderIsRight() {
        String name = categoryPage.getProductsGrid().get(0).findElement(By.className("name")).getText();
        Assertions.assertTrue(name.contains("Women"));
    }

    @When("Use collection filter")
    public void useCollectionFilter() {
        categoryPage.getFilters().setFilter(FiltersItem.COLLECTION, collectionFilter);
        categoryPage.getProductsGrid().get(0).click();
    }

    @Then("Check if the collection is right")
    public void checkIfTheCollectionIsRight() {
        Assertions.assertTrue(locate("title").getAttribute("text").contains(collectionFilter));
    }

    @When("Use category filter")
    public void useCategoryFilter() {
        categoryPage.getFilters().setFilter(FiltersItem.CATEGORY, categoryFilter);
        categoryPage.getProductsGrid().get(0).click();
    }

    @Then("Check if the category is right")
    public void checkIfTheCategoryIsRight() {
        Assertions.assertTrue(locate("title").getAttribute("text").contains(categoryFilter));
    }

    @When("Use Brand filter")
    public void useBrandFilter() {
        categoryPage.getFilters().setFilter(FiltersItem.BRAND, brandFilter);
        categoryPage.getProductsGrid().get(0).findElement(By.tagName("a")).click(); // $("a").click();
    }

    @Then("Check if the Brand is right")
    public void checkIfTheBrandIsRight() {
        Assertions.assertTrue(locate("div.description").getText().contains(brandFilter));
    }
}
