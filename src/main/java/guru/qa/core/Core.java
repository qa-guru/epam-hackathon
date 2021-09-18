package guru.qa.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Core {

    public static void open(String url) {
        WebDriverContainer.INSTANCE.getOrInitWebDriver().navigate().to(url);
    }

    public static void close() {
        WebDriverContainer.INSTANCE.closeWebDriver();
    }

    public static WebElement locate(String cssSelector) {
        By locator = By.cssSelector(cssSelector);
        return new SimpleElement(locator);
    }

    public static WebElement locateX(String xpathSelector) {
        By locator = By.xpath(xpathSelector);
        return new SimpleElement(locator);
    }

    public static ElementList locateAll(String cssSelector) {
        By locator = By.cssSelector(cssSelector);
        return new ElementList(locator);
    }

    public static ElementList locateXAll(String xpathSelector) {
        By locator = By.xpath(xpathSelector);
        return new ElementList(locator);
    }
}
