package utils;

import com.github.javafaker.Faker;

public class RandomUtils {

    private static final Faker faker = new Faker();

    public static String getRandomEmail() {
        return faker.internet().emailAddress();
    }
}
