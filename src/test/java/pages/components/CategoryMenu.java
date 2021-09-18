package pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.MenuItem;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CategoryMenu {

    private SelenideElement self = $(".navigation__overflow");
    private ElementsCollection menuItems = self.$$(".nav__links--primary");

    public void navigateTo(MenuItem menuName) {
        menuItems.find(text(menuName.getDisplayedName())).click();
    }

    public void navigateToSub(MenuItem menuName, String sub) {
        menuItems.find(text(menuName.getDisplayedName())).hover();
        $$("li.nav__link--secondary").find(Condition.text(sub)).click();
    }

}
