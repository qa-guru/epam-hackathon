package domain;

public enum AlertMessage {

    MANDATORY_REVIEW_FIELD("Please fill all mandatory review fields"),
    SUCCESSFUL_REVIEW("Thank you for your review."),
    NOT_FOUND("404 Page Not Found");

    private final String text;

    AlertMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
