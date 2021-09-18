package guru.qa.core;

import guru.qa.core.config.Config;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class WebDriverFactory {

    /**
     * @throws IllegalStateException if configured webdriver not supported
     */
    @Nonnull
    public static WebDriver createWebDriver() {
        switch (Config.INSTANCE.browser) {
            case CHROME: {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", prefs());
                options.addArguments("--no-sandbox", "--ignore-ssl-errors=yes", "--ignore-certificate-errors");
                return new ChromeDriver(options);
            }
            case FIREFOX: {
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            }
            default: {
                throw new IllegalStateException("Browser not supported");
            }
        }
    }

    @Nonnull
    private static Map<String, Object> prefs() {
        Map<String, Object> chromePreferences = new HashMap<>();
        chromePreferences.put("safebrowsing.enabled", true);
        chromePreferences.put("credentials_enable_service", false);
        chromePreferences.put("plugins.always_open_pdf_externally", true);
        chromePreferences.put("profile.default_content_setting_values.automatic_downloads", 1);
        return chromePreferences;
    }
}
