package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReviewFieldError {
    EMPTY_TITLE("Please enter a title"),
    EMPTY_DESCRIPTION("PLEASE ENTER A DESCRIPTION"),
    NO_RATE("Please enter a rating");

    private final String text;
}
