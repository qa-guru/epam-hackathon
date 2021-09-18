package domain;

public enum FieldError {

    ACCOUNT_ALREADY_EXISTS("An account already exists for this email address."),
    EMPTY_FIRST_NAME("Please enter a first name"),
    EMPTY_LAST_NAME("Please enter a last name"),
    INVALID_EMAIL("Please enter a valid email");

    private final String text;

    FieldError(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
