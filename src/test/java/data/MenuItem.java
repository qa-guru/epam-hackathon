package data;

public enum MenuItem {
    BRANDS("BRANDS"),
    STREETWEAR("STREETWEAR"),
    SNOW("SNOW"),
    ACCESSORIES("ACCESSORIES"),
    YOUTH("YOUTH");

    private final String displayedName;

    MenuItem(String displayedName) {
        this.displayedName = displayedName;
    }

    public String getDisplayedName() {
        return displayedName;
    }


    @Override
    public String toString() {
        return displayedName;
    }

}
