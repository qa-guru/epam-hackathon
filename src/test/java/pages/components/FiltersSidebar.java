package pages.components;

import guru.qa.core.ElementList;
import org.openqa.selenium.WebElement;

import static guru.qa.core.Core.locateAll;

public class FiltersSidebar {

    private ElementList simpleFilters = locateAll(".facet__list__label");
    private ElementList textFilters = locateAll(".facet__text");

    public WebElement applyCheckBoxFilter(String filter) {
        WebElement filterElement = simpleFilters.findByText(filter);
        filterElement.click();
        return filterElement;
    }

    public WebElement applyTextLinkFilter(String filter) {
        WebElement filterElement = textFilters.findByText(filter);
        filterElement.click();
        return filterElement;
    }
}
