package runner;

import guru.qa.core.config.Config;
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

public class CucumberRunner {

    public static void main(String[] args) {
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(selectDirectory("src/test/resources/features"))
                .build();

        Launcher launcher = LauncherFactory.create();
        SummaryGeneratingListener listener = new SummaryGeneratingListener();
        launcher.registerTestExecutionListeners(listener);
        launcher.execute(request);

        TestExecutionSummary summary = listener.getSummary();

        System.out.println("TEST RESULTS: ");

        System.out.println(String.format("%s tests were started", summary.getTestsStartedCount()));
        System.out.println(String.format("%s, tests were successful", summary.getTestsSucceededCount()));
        System.out.println(String.format("%s, tests failed", summary.getTestsFailedCount()));

        if (summary.getFailures().size() > 0) {
            for (TestExecutionSummary.Failure failure : summary.getFailures()) {
                System.out.println(String.format("Test \t %s \t is failed because of %s", failure.getTestIdentifier().getDisplayName(), failure.getException().getMessage()));
            }
        }

        if (Config.INSTANCE.rerunFailedTests && summary.getFailures().size() > 0) {
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
        }
    }
}
