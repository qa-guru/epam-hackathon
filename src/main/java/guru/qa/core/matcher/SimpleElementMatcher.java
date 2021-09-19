package guru.qa.core.matcher;

import guru.qa.core.WebDriverContainer;
import guru.qa.core.config.Config;
import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Consumer;

import static guru.qa.core.Utils.sleep;

public class SimpleElementMatcher {

    private final WebElement matchedElement;

    private SimpleElementMatcher(WebElement matchedElement) {
        this.matchedElement = matchedElement;
    }

    @Nonnull
    public static SimpleElementMatcher assertThat(@Nonnull WebElement element) {
        return new SimpleElementMatcher(element);
    }

    @Nonnull
    public SimpleElementMatcher isVisible() {
        flexCheck(webElement -> org.assertj.core.api.Assertions.assertThat(webElement.isDisplayed())
                .isTrue());
        return this;
    }

    @Nonnull
    public SimpleElementMatcher isDisabled() {
        flexCheck(webElement -> org.assertj.core.api.Assertions.assertThat(webElement.isEnabled())
                .isFalse());
        return this;
    }

    @Nonnull
    public SimpleElementMatcher isNotVisible() {
        flexCheck(webElement -> org.assertj.core.api.Assertions.assertThat(webElement.isDisplayed())
                .isFalse());
        return this;
    }

    @Nonnull
    public SimpleElementMatcher hasText(@Nullable String expectedText) {
        flexCheck(webElement -> org.assertj.core.api.Assertions.assertThat(webElement.getText())
                .isEqualTo(expectedText));
        return this;
    }

    @Nonnull
    public SimpleElementMatcher hasTexts(String... expectedTexts) {
        flexCheck(webElement -> org.assertj.core.api.Assertions.assertThat(webElement.getText())
                .contains(expectedTexts));
        return this;
    }

    @Nonnull
    public SimpleElementMatcher hasNoText(@Nullable String expectedText) {
        flexCheck(webElement -> org.assertj.core.api.Assertions.assertThat(webElement.getText())
                .isNotEqualTo(expectedText));
        return this;
    }

    @Nonnull
    public SimpleElementMatcher containsText(@Nullable String expectedText) {
        flexCheck(webElement -> org.assertj.core.api.Assertions.assertThat(webElement.getText())
                .contains(expectedText));
        return this;
    }

    @Nonnull
    public SimpleElementMatcher hasAttribute(@Nullable String attributeName, @Nullable String expectedValue) {
        flexCheck(webElement -> org.assertj.core.api.Assertions.assertThat(webElement.getAttribute(attributeName))
                .contains(expectedValue));
        return this;
    }

    private void flexCheck(@Nonnull Consumer<WebElement> action) {
        StopWatch stopWatch = StopWatch.createStarted();
        boolean scrolled = false;
        while (stopWatch.getTime() <= Config.INSTANCE.actionTimeout) {
            try {
                if (!scrolled) {
                    ((JavascriptExecutor) WebDriverContainer.INSTANCE.getRequiredWebDriver())
                            .executeScript("arguments[0].scrollIntoView(false)", matchedElement);
                    scrolled = true;
                }
                action.accept(matchedElement);
                return;
            } catch (Throwable e) {
                sleep(Config.INSTANCE.defaultIterationTimeout);
            }
        }
        action.accept(matchedElement);
    }
}
