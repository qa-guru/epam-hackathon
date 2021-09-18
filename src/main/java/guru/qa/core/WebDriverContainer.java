package guru.qa.core;

import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.Thread.currentThread;

public enum WebDriverContainer {
    INSTANCE;

    private final Map<Long, WebDriver> webDriverHolder = new ConcurrentHashMap<>(4);

    /**
     * @throws IllegalStateException if webdriver not bound to current thread
     */
    @Nonnull
    public WebDriver getRequiredWebDriver() {
        final long theadId = currentThread().getId();
        return Optional.ofNullable(webDriverHolder.get(theadId))
                .orElseThrow(() -> new IllegalStateException("No webdriver found for thread " + theadId));
    }

    @Nonnull
    public WebDriver getOrInitWebDriver() {
        final long theadId = currentThread().getId();
        if (!webDriverHolder.containsKey(theadId)) {
            WebDriver driver = WebDriverFactory.createWebDriver();
            webDriverHolder.put(theadId, driver);
        }
        return getRequiredWebDriver();
    }

    public void closeWebDriver() {
        final long threadId = currentThread().getId();
        getRequiredWebDriver().close();
        webDriverHolder.remove(threadId);
    }
}
