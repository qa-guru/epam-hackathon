package pages.components;

import guru.qa.core.ElementList;
import org.openqa.selenium.WebElement;

import static guru.qa.core.Core.locateAll;

public class FiltersSidebar {

    private ElementList filters = locateAll(".facet__list__label");

    public WebElement applyFilter(String filter) {
        WebElement filterElement = filters.findByText(filter);
        filterElement.click();
        return filterElement;
    }
}
