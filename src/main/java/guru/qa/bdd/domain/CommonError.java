package guru.qa.bdd.domain;

public enum CommonError {

    ACCOUNT_ALREADY_EXISTS("An account already exists for this email address."),
    EMPTY_FIRST_NAME("Please enter a first name"),
    EMPTY_LAST_NAME("Please enter a last name"),
    INVALID_EMAIL("Please enter a valid email"),
    PASSWORD_MIN_REQUIREMENTS_ERROR("Password does not meet minimum requirements."),
    PASSWORD_CONFIRMATION_DOES_NOT_MATCH("Password and password confirmation do not match"),
    CURRENT_PASSWORD_NOT_MATCH("Please enter your current password"),
    INVALID_LOGIN_DATA("Your username or password was incorrect.");

    private final String text;

    CommonError(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
