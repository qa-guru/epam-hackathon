package guru.qa.launcher;

import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.engine.discovery.UniqueIdSelector;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestIdentifier;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectDirectory;

public class RunCucumber {
    public static void main(String[] args) {
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(
                        selectDirectory("path/to/features")
                )
                .build();

        Launcher launcher = LauncherFactory.create();
        SummaryGeneratingListener listener = new SummaryGeneratingListener();
        launcher.registerTestExecutionListeners(listener);
        launcher.execute(request);

        TestExecutionSummary summary = listener.getSummary();
        // Do something with summary

        List<UniqueIdSelector> failures = summary.getFailures().stream()
                .map(TestExecutionSummary.Failure::getTestIdentifier)
                .filter(TestIdentifier::isTest)
                .map(TestIdentifier::getUniqueId)
                .map(DiscoverySelectors::selectUniqueId)
                .collect(Collectors.toList());

        LauncherDiscoveryRequest rerunRequest = LauncherDiscoveryRequestBuilder.request()
                .selectors(failures)
                .build();

        launcher.execute(rerunRequest);
        TestExecutionSummary rerunSummary = listener.getSummary();
    }
}
