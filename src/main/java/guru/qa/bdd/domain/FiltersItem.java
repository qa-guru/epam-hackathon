package guru.qa.bdd.domain;

public enum FiltersItem {

    PRICE("Shop by Price"),
    COLOUR("Shop by Colour"),
    SIZE("Shop by Size"),
    GENDER("Shop by gender"),
    COLLECTION("Shop by Collection"),
    CATEGORY("Shop by Category"),
    BRAND("Shop by Brand");

    private final String filterName;

    FiltersItem(String displayedName) {
        this.filterName = displayedName;
    }

    public String getFilterName() {
        return filterName;
    }

    @Override
    public String toString() {
        return filterName;
    }
}
