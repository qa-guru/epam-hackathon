package pages;

import com.codeborne.selenide.ElementsCollection;
import lombok.Getter;
import pages.components.CategoryMenu;
import pages.components.FiltersSidebar;

import static com.codeborne.selenide.Selenide.$$;


@Getter
public class CategoryPage {

    private CategoryMenu menu = new CategoryMenu();

    private FiltersSidebar filters = new FiltersSidebar();

    private ElementsCollection productsGrid = $$(".product-item");


}
