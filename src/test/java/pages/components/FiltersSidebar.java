package pages.components;

import data.FiltersItem;
import guru.qa.core.ElementList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static guru.qa.core.Core.locateAll;

public class FiltersSidebar {

    private final ElementList filters = locateAll("div.facet");

    public String setFilter(FiltersItem filter, int indexFilter) {

        WebElement filterInUse = filters.stream()
                .filter(webElement -> filter.getFilterName().equals(webElement.getText().substring(0,filter.getFilterName().length())))
                .findAny()
                .orElse(null);

        WebElement filterActive = filterInUse.findElements(new By.ByCssSelector("span.facet__list__text")).get(indexFilter);

        String filterActiveString = filterActive.getText();
        filterActive.click();

        return filterActiveString;

    }

    public void setFilter(FiltersItem filter, String stringFilter) {

        WebElement filterInUse = filters.stream()
                .filter(webElement -> filter.getFilterName().equals(webElement.getText().substring(0,filter.getFilterName().length())))
                .findAny()
                .orElse(null);

        WebElement filterActive = filterInUse.findElements(new By.ByCssSelector("span.facet__text")).stream()
                .filter(webElement -> stringFilter
                        .equals(webElement
                                .getText()
                                .substring(0, Math.min(stringFilter.length(), webElement.getText().length())))) //stringFilter.length())))
                .findAny()
                .orElse(null);//find(Condition.text(stringFilter));

        String filterActiveString = filterActive.getText();
        filterActive.click();

    }

}
