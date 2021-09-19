package pages;

import guru.qa.core.ElementList;
import pages.components.CategoryMenu;
import pages.components.FiltersSidebar;

import static guru.qa.core.Core.locateAll;

public class CategoryPage {

    public CategoryMenu getMenu() {
        return menu;
    }

    private final CategoryMenu menu = new CategoryMenu();

    public FiltersSidebar getFilters() {
        return filters;
    }

    private final FiltersSidebar filters = new FiltersSidebar();

    public ElementList getProductsGrid() {
        return productsGrid;
    }

    private final ElementList productsGrid = locateAll(".product-item");
}
