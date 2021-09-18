package guru.qa.core;

import guru.qa.core.config.Config;
import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;
import java.util.List;

import static guru.qa.core.Utils.sleep;

public enum SimpleElementLocator {
    INSTANCE;

    @Nonnull
    public WebElement findElement(SearchContext ctx, By selector) {
        StopWatch stopWatch = StopWatch.createStarted();
        while (stopWatch.getTime() <= Config.INSTANCE.actionTimeout) {
            try {
                return ctx.findElement(selector);
            } catch (Exception e) {
                sleep(250);
            }
        }
        return ctx.findElement(selector);
    }

    @Nonnull
    public List<WebElement> findElements(SearchContext ctx, By selector) {
        StopWatch stopWatch = StopWatch.createStarted();
        while (stopWatch.getTime() <= Config.INSTANCE.actionTimeout) {
            try {
                List<WebElement> elements = ctx.findElements(selector);
                if (elements == null || elements.isEmpty()) {
                    sleep(250);
                    continue;
                } else return elements;
            } catch (Exception e) {
                sleep(250);
            }
        }
        return ctx.findElements(selector);
    }
}
