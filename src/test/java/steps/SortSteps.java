package steps;

import guru.qa.core.ElementList;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import pages.CategoryPage;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static guru.qa.core.Core.locate;

public class SortSteps {

    private final CategoryPage categoryPage = new CategoryPage();

    @When("Set sorting by name ascending")
    public void setSortingByNameAscending() {
        locate("#sortOptions1").click();
        locate("option[value=name-asc]").click();
    }

    @Then("Check sorted results ascending")
    public void checkSortedResults() {
        ElementList products = categoryPage.getProductsGrid();

        List<String> firstLettersList;
        firstLettersList = products.stream()
                .map(webElement -> webElement.getText().substring(0, 1))
                .collect(Collectors.toList());
        Assertions.assertEquals(firstLettersList.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList()), firstLettersList);
    }

    @When("Set sorting by name descending")
    public void setSortingByNameDescending() {
        locate("#sortOptions1").click();
        locate("option[value=name-desc]").click();
    }

    @Then("Check sorted results descending")
    public void checkSortedResultsDescending() {
        ElementList products = categoryPage.getProductsGrid();

        List<String> firstLettersList;
        firstLettersList = products.stream()
                .map(webElement -> webElement.getText().substring(0, 1))
                .collect(Collectors.toList());
        Assertions.assertEquals(firstLettersList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()), firstLettersList);
    }

    @When("Set sorting by price ascending")
    public void setSortingByPriceAscending() {
        locate("#sortOptions1").click();
        locate("option[value=price-asc]").click();

    }

    @Then("Check sorted price results ascending")
    public void checkSortedPriceResultsAscending() {
        ElementList products = categoryPage.getProductsGrid();

        List<Double> priceList;
        priceList = products.stream()
                .map(webElement -> Double.parseDouble(webElement.findElement(By.className("price")).getText().substring(1)))
                .collect(Collectors.toList());
        Assertions.assertEquals(priceList.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList()), priceList);
    }

    @When("Set sorting by price descending")
    public void setSortingByPriceDescending() {
        locate("#sortOptions1").click();
        locate("option[value=price-desc]").click();
    }

    @Then("Check sorted price results descending")
    public void checkSortedPriceResultsDescending() {
        ElementList products = categoryPage.getProductsGrid();

        List<Double> priceList;
        priceList = products.stream()
                .map(webElement -> Double.parseDouble(webElement.findElement(By.className("price")).getText().substring(1)))
                .collect(Collectors.toList());
        Assertions.assertEquals(priceList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()), priceList);
    }
}
