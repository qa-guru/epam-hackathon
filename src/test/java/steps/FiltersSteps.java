package steps;

import data.MenuItem;
import guru.qa.core.Core;
import guru.qa.core.matcher.ElementListMatcher;
import guru.qa.core.matcher.SimpleElementMatcher;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CategoryPage;
import pages.components.CategoryMenu;

import static guru.qa.core.Core.locate;
import static guru.qa.core.Core.locateAll;
import static org.assertj.core.api.Assertions.assertThat;

public class FiltersSteps {

    private CategoryPage categoryPage = new CategoryPage();
    private int priceStarts;
    private String size;
    private String color;
    private String gender;
    private String collectionFilter = "T-Shirts women";
    private String categoryFilter = "Streetwear men";

    @Given("Open brands page")
    public void openBrandsPage() {
        Core.open("https://apparel-uk.local:9002/ucstorefront/en");
        CategoryMenu menu = categoryPage.getMenu();
        menu.navigateTo(MenuItem.BRANDS);
    }

    @When("Use price filter")
    public void usePriceFilter() {
        categoryPage.getFilters().applyCheckBoxFilter("£50-£99.99");
        priceStarts = 50;
    }

    @Then("Check if the price is right")
    public void checkIfThePriceIsRight() {
        int priceToCompare = Integer.parseInt(locateAll(".price").get(0).getText().substring(1, 3));
        assertThat(priceStarts).isLessThanOrEqualTo(priceToCompare);
    }

    @When("Use size filter")
    public void useSizeFilter() {
        categoryPage.getFilters().applyCheckBoxFilter("XXL");
        size = "XXL";
    }

    @Then("Check if the size is right")
    public void checkIfTheSizeIsRight() {
        categoryPage.getProductsGrid().forEach(p -> SimpleElementMatcher.assertThat(p).containsText(size));
    }

    @When("Use color filter")
    public void useColorFilter() {
        categoryPage.getFilters().applyCheckBoxFilter("BLUE");
        color = "Blue";
    }

    @Then("Check if the color is right")
    public void checkIfTheColorIsRight() {
        ElementListMatcher.assertThat(categoryPage.getProductsGrid()).containsTextInAnyElement(color);
    }

    @When("Use gender filter")
    public void useGenderFilter() {
        categoryPage.getFilters().applyTextLinkFilter("Female");
        gender = "Women";
    }

    @Then("Check if the gender is right")
    public void checkIfTheGenderIsRight() {
        ElementListMatcher.assertThat(categoryPage.getProductsGrid()).containsTextInAnyElement(gender);
    }

    @When("Use collection filter")
    public void useCollectionFilter() {
        categoryPage.getFilters().applyTextLinkFilter(collectionFilter);
        categoryPage.getProductsGrid().get(0).click();
    }

    @Then("Check if the collection is right")
    public void checkIfTheCollectionIsRight() {
        SimpleElementMatcher.assertThat(locate("title")).hasAttribute("text", collectionFilter);
    }

    @When("Use category filter")
    public void useCategoryFilter() {
        categoryPage.getFilters().applyTextLinkFilter(categoryFilter);
        categoryPage.getProductsGrid().get(0).click();
    }

    @Then("Check if the category is right")
    public void checkIfTheCategoryIsRight() {
        SimpleElementMatcher.assertThat(locate("title")).hasAttribute("text", categoryFilter);
    }
}
