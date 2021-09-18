package guru.qa.core.matcher;

import guru.qa.core.SimpleElement;
import guru.qa.core.config.Config;
import org.apache.commons.lang3.time.StopWatch;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

import static guru.qa.core.Utils.sleep;

public class SimpleElementMatcher {

    private final SimpleElement matchedElement;

    private SimpleElementMatcher(SimpleElement matchedElement) {
        this.matchedElement = matchedElement;
    }

    @Nonnull
    public static SimpleElementMatcher assertThat(@Nonnull SimpleElement element) {
        return new SimpleElementMatcher(element);
    }

    @Nonnull
    public SimpleElementMatcher isVisible() {
        flexCheck(webElement -> org.assertj.core.api.Assertions.assertThat(webElement.isDisplayed())
                .isTrue());
        return this;
    }

    @Nonnull
    public SimpleElementMatcher hasText(String expectedText) {
        flexCheck(webElement -> org.assertj.core.api.Assertions.assertThat(webElement.getText())
                .isEqualTo(expectedText));
        return this;
    }

    private void flexCheck(Consumer<SimpleElement> action) {
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
