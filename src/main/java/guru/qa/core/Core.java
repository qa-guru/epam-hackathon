package guru.qa.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;

public class Core {

    public static void open(String url) {
        WebDriverContainer.INSTANCE.getOrInitWebDriver().navigate().to(url);
    }

    public static void close() {
        WebDriverContainer.INSTANCE.closeWebDriver();
    }

    @Nonnull
    public static WebElement locate(@Nonnull String cssSelector) {
        By locator = By.cssSelector(cssSelector);
        return new SimpleElement(locator);
    }

    @Nonnull
    public static WebElement locateX(@Nonnull String xpathSelector) {
        By locator = By.xpath(xpathSelector);
        return new SimpleElement(locator);
    }

    @Nonnull
    public static ElementList locateAll(@Nonnull String cssSelector) {
        By locator = By.cssSelector(cssSelector);
        return new ElementList(locator);
    }

    @Nonnull
    public static ElementList locateXAll(@Nonnull String xpathSelector) {
        By locator = By.xpath(xpathSelector);
        return new ElementList(locator);
    }
}
