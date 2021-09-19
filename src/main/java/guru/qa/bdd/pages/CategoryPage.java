package guru.qa.bdd.pages;

import guru.qa.bdd.pages.components.CategoryMenu;
import guru.qa.bdd.pages.components.FiltersSidebar;
import guru.qa.core.ElementList;

import static guru.qa.core.Core.locateAll;


public class CategoryPage {

    private CategoryMenu menu = new CategoryMenu();
    private FiltersSidebar filters = new FiltersSidebar();
    private ElementList productsGrid = locateAll(".product-item");

    public CategoryMenu getMenu() {
        return menu;
    }

    public FiltersSidebar getFilters() {
        return filters;
    }

    public ElementList getProductsGrid() {
        return productsGrid;
    }
}
