package guru.qa.core;

import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Thread.currentThread;

public enum WebDriverContainer {
    INSTANCE;

    private final Map<Long, WebDriver> webDriverHolder = new ConcurrentHashMap<>(4);
    private final Queue<Thread> allWebDriverThreads = new ConcurrentLinkedQueue<>();
    private final AtomicBoolean cleanupThreadStarted = new AtomicBoolean(false);

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
            markForAutoClose(currentThread());
        }
        return getRequiredWebDriver();
    }

    public void closeWebDriver() {
        final long threadId = currentThread().getId();
        WebDriver webDriver = getWebDriver(threadId);
        if (webDriver != null) {
            webDriver.quit();
        }
        webDriverHolder.remove(threadId);
    }

    @Nullable
    public WebDriver getWebDriver(long threadId) {
        return webDriverHolder.get(threadId);
    }

    private void markForAutoClose(Thread thread) {
        allWebDriverThreads.add(thread);
        if (!cleanupThreadStarted.get()) {
            synchronized (this) {
                if (!cleanupThreadStarted.get()) {
                    new CloseDriverThread(allWebDriverThreads).start();
                    cleanupThreadStarted.set(true);
                }
            }
        }
    }
}
