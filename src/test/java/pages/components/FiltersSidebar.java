package pages.components;

import data.FiltersItem;
import guru.qa.core.ElementList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static guru.qa.core.Core.locateAll;

public class FiltersSidebar {

    private ElementList filters = locateAll("div.facet");

    public String setFilter(FiltersItem filter, int indexFilter) {
        WebElement filterInUse = filters.findByText(filter.getFilterName());
        WebElement filterActive = filterInUse.findElements(By.cssSelector("span.facet__list__text")).get(indexFilter);
        String filterActiveString = filterActive.getText();
        filterActive.click();
        return filterActiveString;
    }

    public String setFilter(FiltersItem filter, String stringFilter) {
        WebElement filterInUse = filters.findByText(filter.getFilterName());
        WebElement filterActive = ElementList.wrap(filterInUse.findElements(new By.ByCssSelector("span.facet__text")))
                .findByText(stringFilter);
        String filterActiveString = filterActive.getText();
        filterActive.click();
        return filterActiveString;
    }
}
