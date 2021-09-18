package guru.qa.core.matcher;

import guru.qa.core.ElementList;
import guru.qa.core.config.Config;
import org.apache.commons.lang3.time.StopWatch;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;
import java.util.function.Consumer;
import java.util.function.Function;

import static guru.qa.core.Utils.sleep;

public class ElementListMatcher {

    private final ElementList matchedList;

    private ElementListMatcher(ElementList matchedList) {
        this.matchedList = matchedList;
    }

    @Nonnull
    public static ElementListMatcher assertThat(@Nonnull ElementList list) {
        return new ElementListMatcher(list);
    }

    @Nonnull
    public ElementListMatcher hasSizeGreaterThan(int expectedSize) {
        flexCheck(webElements -> org.assertj.core.api.Assertions.assertThat(webElements)
                .hasSizeGreaterThan(expectedSize));
        return this;
    }

    @Nonnull
    public ElementListMatcher containsTextsInAnyOrder(String... expectedTexts) {
        flexCheck(webElements -> Assertions.assertThat(webElements)
                .hasSizeGreaterThan(0)
                .extracting((Function<WebElement, String>) WebElement::getText)
                .containsExactlyInAnyOrder(expectedTexts));
        return this;
    }

    private void flexCheck(Consumer<ElementList> action) {
        StopWatch stopWatch = StopWatch.createStarted();
        while (stopWatch.getTime() <= Config.INSTANCE.actionTimeout) {
            try {
                action.accept(matchedList);
                return;
            } catch (Throwable e) {
                sleep(250);
            }
        }
        action.accept(matchedList);
    }
}