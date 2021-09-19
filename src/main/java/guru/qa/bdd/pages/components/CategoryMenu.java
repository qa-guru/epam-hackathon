package guru.qa.bdd.pages.components;


import guru.qa.bdd.domain.MenuItem;
import guru.qa.core.ElementList;
import org.openqa.selenium.WebElement;

import static guru.qa.core.Core.locate;
import static guru.qa.core.Core.locateAll;

public class CategoryMenu {

    private final WebElement self = locate(".navigation__overflow");
    private final ElementList menuItems = locateAll(".nav__links--primary");

    public void navigateTo(MenuItem menuName) {
        menuItems.findByText(menuName.getDisplayedName()).click();
    }
}
