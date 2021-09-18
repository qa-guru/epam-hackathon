package pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.FiltersItem;

import static com.codeborne.selenide.Selenide.$$;

public class FiltersSidebar {

    private ElementsCollection filters = $$("div.facet");

    public String setFilter(FiltersItem filter, int indexFilter) {

        SelenideElement filterInUse = filters.find(Condition.text(filter.getFilterName()));
        SelenideElement filterActive = filterInUse.$$("span.facet__list__text").get(indexFilter);

        String filterActiveString = filterActive.text();
        filterActive.click();

        return filterActiveString;

    }

    public String setFilter(FiltersItem filter, String stringFilter) {

        SelenideElement filterInUse = filters.find(Condition.text(filter.getFilterName()));
        SelenideElement filterActive = filterInUse.$$("span.facet__text").find(Condition.text(stringFilter));

        String filterActiveString = filterActive.text();
        filterActive.click();

        return filterActiveString;

    }

}
