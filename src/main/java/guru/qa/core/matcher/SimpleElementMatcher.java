package guru.qa.core.matcher;

import guru.qa.core.config.Config;
import org.apache.commons.lang3.time.StopWatch;
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
    public SimpleElementMatcher hasText(@Nullable String expectedText) {
        flexCheck(webElement -> org.assertj.core.api.Assertions.assertThat(webElement.getText())
                .isEqualTo(expectedText));
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

    private void flexCheck(@Nonnull Consumer<WebElement> action) {
        StopWatch stopWatch = StopWatch.createStarted();
        while (stopWatch.getTime() <= Config.INSTANCE.actionTimeout) {
            try {
                action.accept(matchedElement);
                return;
            } catch (Throwable e) {
                sleep(250);
            }
        }
        action.accept(matchedElement);
    }
}
