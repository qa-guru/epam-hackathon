package steps;

import data.MenuItem;
import guru.qa.core.Core;
import io.cucumber.java.en.Given;
import pages.CategoryPage;
import pages.components.CategoryMenu;

public class StoreBrandSteps {
    private final CategoryPage categoryPage = new CategoryPage();

    @Given("Open store brand page")
    public void openStoreBrandPage() {
        Core.open("https://apparel-uk.local:9002/ucstorefront/en");
        CategoryMenu menu = categoryPage.getMenu();
        menu.navigateTo(MenuItem.BRANDS);
    }
}
