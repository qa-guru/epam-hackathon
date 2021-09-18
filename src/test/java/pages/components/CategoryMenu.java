package pages.components;

import data.MenuItem;
import guru.qa.core.ElementList;
import org.openqa.selenium.WebElement;

import static guru.qa.core.Core.locate;
import static guru.qa.core.Core.locateAll;

public class CategoryMenu {

    private WebElement self = locate(".navigation__overflow");
    private ElementList menuItems = locateAll(".nav__links--primary");

    public void navigateTo(MenuItem menuName) {
        menuItems.stream()
                .filter(webElement -> menuName.getDisplayedName().equals(webElement.getText()))
                .findAny()
                .orElse(null).click();
    }

}
