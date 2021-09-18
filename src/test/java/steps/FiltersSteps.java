package steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import data.FiltersItem;
import data.MenuItem;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.CategoryPage;
import pages.components.CategoryMenu;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class FiltersSteps {

    private CategoryPage categoryPage = new CategoryPage();
    int priceStarts;
    String size;
    String color;
    String collectionFilter = "T-Shirts women";
    String categoryFilter = "Streetwear men";
    String brandFilter = "Billabong";

    @Given("Open brands page")
    public void openBrandsPage() {
        Selenide.open("https://apparel-uk.local:9002/ucstorefront/en");
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
        int priceToCompare = Integer.parseInt(categoryPage.getProductsGrid().get(0).$(".price").getOwnText().substring(1, 3));
        Assert.assertTrue(priceStarts <= priceToCompare);
    }

    @When("Use size filter")
    public void useSizeFilter() {
        size = categoryPage.getFilters().setFilter(FiltersItem.SIZE, 2);
        size = size.substring(0, size.indexOf(" "));
    }

    @Then("Check if the size is right")
    public void checkIfTheSizeIsRight() {
        String name = categoryPage.getProductsGrid().get(0).$(".name").text();
        Assert.assertTrue(name.contains(size));
    }

    @When("Use color filter")
    public void useColorFilter() {
        color = categoryPage.getFilters().setFilter(FiltersItem.COLOUR, 0);
        color = color.substring(0, color.indexOf(" "));
    }

    @Then("Check if the color is right")
    public void checkIfTheColorIsRight() {
        String name = categoryPage.getProductsGrid().get(0).$(".name").text().toUpperCase(Locale.ROOT);
        Assert.assertTrue(name.contains(color));
    }

    @When("Use gender filter")
    public void useGenderFilter() {
        categoryPage.getFilters().setFilter(FiltersItem.GENDER, "Female");
    }

    @Then("Check if the gender is right")
    public void checkIfTheGenderIsRight() {
        String name = categoryPage.getProductsGrid().get(0).$(".name").text();
        Assert.assertTrue(name.contains("Women"));
    }

    @When("Use collection filter")
    public void useCollectionFilter() {
        categoryPage.getFilters().setFilter(FiltersItem.COLLECTION, collectionFilter);
        categoryPage.getProductsGrid().get(0).click();
    }

    @Then("Check if the collection is right")
    public void checkIfTheCollectionIsRight() {
        Assert.assertTrue($("title").getOwnText().contains(collectionFilter));
    }

    @When("Use category filter")
    public void useCategoryFilter() {
        categoryPage.getFilters().setFilter(FiltersItem.CATEGORY, categoryFilter);
        categoryPage.getProductsGrid().get(0).click();
    }

    @Then("Check if the category is right")
    public void checkIfTheCategoryIsRight() {
        Assert.assertTrue($("title").getOwnText().contains(categoryFilter));
    }

    @When("Use Brand filter")
    public void useBrandFilter() {
        categoryPage.getFilters().setFilter(FiltersItem.BRAND, brandFilter);
        categoryPage.getProductsGrid().get(0).$("a").click();
    }

    @Then("Check if the Brand is right")
    public void checkIfTheBrandIsRight() {
        Assert.assertTrue($("div.description").text().contains(brandFilter));
    }
}
