package utils;

import org.openqa.selenium.Cookie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CookieUtils {

    public static List<Cookie> parseCookie(List<String> cookies) {
        List<Cookie> parsedCookie = new ArrayList<>();
        Set<String> sourceCookies = new HashSet<>();
        for (String header : cookies) {
            sourceCookies.addAll(Arrays.asList(header.split("; ")));
        }
        for (String sourceCookie : sourceCookies) {
            String[] split = sourceCookie.split("=", 2);
            if (split.length == 2) {
                parsedCookie.add(new Cookie(split[0], split[1]));
            }
        }
        return parsedCookie;
    }

    public static String parseCookieList(List<String> cookiesVeryNeeded) {
        StringBuilder parsedCookie = new StringBuilder();
        for (String cookie : cookiesVeryNeeded) {
            parsedCookie.append(cookie).append(";");
        }
        return parsedCookie.toString();
    }
}
