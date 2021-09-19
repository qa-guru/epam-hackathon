package guru.qa.bdd.utils;

public class TestFailure {

    private String testName;
    private String reason;

    public TestFailure(String testName, String reason) {
        this.testName = testName;
        this.reason = reason;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
