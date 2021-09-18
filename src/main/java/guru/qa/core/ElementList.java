package guru.qa.core;

import guru.qa.core.config.Config;
import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static guru.qa.core.Utils.sleep;

public class ElementList implements List<WebElement> {

    private final By locator;
    private List<WebElement> delegate;

    public ElementList(By locator) {
        this.locator = locator;
    }

    @Override
    public int size() {
        return execute((Function<List<WebElement>, Integer>) List::size);
    }

    @Override
    public boolean isEmpty() {
        return execute((Function<List<WebElement>, Boolean>) List::isEmpty);
    }

    @Override
    public boolean contains(Object o) {
        return execute(webElements -> {
            return webElements.contains(o);
        });
    }

    @Override
    public Iterator<WebElement> iterator() {
        return execute((Function<List<WebElement>, Iterator<WebElement>>) List::iterator);
    }

    @Override
    public void forEach(Consumer<? super WebElement> action) {
        execute(webElements -> {
            webElements.forEach(action);
        });
    }

    @Override
    public Object[] toArray() {
        return execute((Function<List<WebElement>, Object[]>) List::toArray);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return execute(webElements -> {
            return webElements.toArray(a);
        });
    }

    @Override
    public <T> T[] toArray(IntFunction<T[]> generator) {
        return execute(webElements -> {
            return webElements.toArray(generator);
        });
    }

    @Override
    public boolean add(WebElement simpleElement) {
        return execute(webElements -> {
            return webElements.add(simpleElement);
        });
    }

    @Override
    public boolean remove(Object o) {
        return execute(webElements -> {
            return webElements.remove(o);
        });
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return execute(webElements -> {
            return webElements.containsAll(c);
        });
    }

    @Override
    public boolean addAll(Collection<? extends WebElement> c) {
        return execute(webElements -> {
            return webElements.addAll(c);
        });
    }

    @Override
    public boolean addAll(int index, Collection<? extends WebElement> c) {
        return execute(webElements -> {
            return webElements.addAll(index, c);
        });
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return execute(webElements -> {
            return webElements.removeAll(c);
        });
    }

    @Override
    public boolean removeIf(Predicate<? super WebElement> filter) {
        return execute(webElements -> {
            return webElements.removeIf(filter);
        });
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return execute(webElements -> {
            return webElements.retainAll(c);
        });
    }

    @Override
    public void replaceAll(UnaryOperator<WebElement> operator) {
        execute(webElements -> {
            webElements.replaceAll(operator);
        });
    }

    @Override
    public void sort(Comparator<? super WebElement> c) {
        execute(webElements -> {
            webElements.sort(c);
        });
    }

    @Override
    public void clear() {
        execute((Consumer<List<WebElement>>) List::clear);
    }

    @Override
    public WebElement get(int index) {
        return execute(webElements -> {
            return webElements.get(index);
        });
    }

    @Override
    public WebElement set(int index, WebElement element) {
        return execute(webElements -> {
            return webElements.set(index, element);
        });
    }

    @Override
    public void add(int index, WebElement element) {
        execute(webElements -> {
            webElements.add(element);
        });
    }

    @Override
    public WebElement remove(int index) {
        return execute(webElements -> {
            return webElements.remove(index);
        });
    }

    @Override
    public int indexOf(Object o) {
        return execute(webElements -> {
            return webElements.indexOf(o);
        });
    }

    @Override
    public int lastIndexOf(Object o) {
        return execute(webElements -> {
            return webElements.lastIndexOf(o);
        });
    }

    @Override
    public ListIterator<WebElement> listIterator() {
        return execute(webElements -> {
            return webElements.listIterator();
        });
    }

    @Override
    public ListIterator<WebElement> listIterator(int index) {
        return execute(webElements -> {
            return webElements.listIterator(index);
        });
    }

    @Override
    public List<WebElement> subList(int fromIndex, int toIndex) {
        return execute(webElements -> {
            return webElements.subList(fromIndex, toIndex);
        });
    }

    @Override
    public Spliterator<WebElement> spliterator() {
        return execute((Function<List<WebElement>, Spliterator<WebElement>>) List::spliterator);
    }

    @Override
    public Stream<WebElement> stream() {
        return execute((Function<List<WebElement>, Stream<WebElement>>) List::stream);
    }

    @Override
    public Stream<WebElement> parallelStream() {
        return execute((Function<List<WebElement>, Stream<WebElement>>) List::parallelStream);
    }

    private void execute(@Nonnull Consumer<List<WebElement>> action) {
        checkDelegate();
        StopWatch stopWatch = StopWatch.createStarted();
        while (stopWatch.getTime() <= Config.INSTANCE.actionTimeout) {
            try {
                action.accept(delegate);
                return;
            } catch (Exception e) {
                sleep(250);
            }
        }
        action.accept(delegate);
    }

    @Nullable
    private <T> T execute(@Nonnull Function<List<WebElement>, T> action) {
        checkDelegate();
        StopWatch stopWatch = StopWatch.createStarted();
        while (stopWatch.getTime() <= Config.INSTANCE.actionTimeout) {
            try {
                return action.apply(delegate);
            } catch (Exception e) {
                sleep(250);
            }
        }
        return action.apply(delegate);
    }

    private void checkDelegate() {
        if (delegate == null) {
            delegate = SimpleElementLocator.INSTANCE.findElements(WebDriverContainer.INSTANCE.getRequiredWebDriver(), locator);
        }
    }
}
