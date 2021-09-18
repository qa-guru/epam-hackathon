package pages;

import guru.qa.core.ElementList;
import lombok.Getter;
import pages.components.CategoryMenu;
import pages.components.FiltersSidebar;

import static guru.qa.core.Core.locateAll;


@Getter
public class CategoryPage {

    private CategoryMenu menu = new CategoryMenu();

    private FiltersSidebar filters = new FiltersSidebar();

    private ElementList productsGrid = locateAll(".product-item");


}
