package reporter;

import com.lowagie.text.DocumentException;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;
import utils.TestFailure;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class CucumberReporter {

    public static void report(TestExecutionSummary summary) throws IOException, DocumentException {
        HashMap<String, Object> values = new HashMap<>();
        values.put("testsStarted", String.format("%s tests were started", summary.getTestsStartedCount()));
        values.put("testsSuccess", String.format("%s, tests were successful", summary.getTestsSucceededCount()));
        values.put("testsFailed", String.format("%s, tests failed", summary.getTestsFailedCount()));

        if (summary.getFailures().size() > 0) {
            ArrayList<TestFailure> failures = new ArrayList<>();
            for (TestExecutionSummary.Failure failure : summary.getFailures()) {
                failures.add(new TestFailure(failure.getTestIdentifier().getDisplayName(), failure.getException().getMessage()));
            }
            values.put("failures", failures);
        }
        generateReport(parseTemplate("templates/report", values));
    }

    public static String parseTemplate(String template, HashMap<String, Object> values) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        Context context = new Context();
        context.setVariables(values);
        return templateEngine.process(template, context);
    }


    public static File generateReport(String html) throws IOException, DocumentException {
        Path currentRelativePath = Paths.get("");
        File file = new File(currentRelativePath.toAbsolutePath().toString() + "/report.pdf");
        file.setWritable(true);
        file.createNewFile();
        OutputStream outputStream = new FileOutputStream(file);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();
        return file;
    }
}
