package api;

import guru.qa.core.Core;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.Cookie;
import utils.RandomUtils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static api.HttpClientFactory.BASE_URL;
import static utils.CookieUtils.parseCookie;
import static utils.CookieUtils.parseCookieList;

public class ApiCalls {

    private HttpClient httpClient = HttpClientFactory.getClient();
    private ThreadLocal<List<String>> cookiesVeryNeeded = new ThreadLocal<>();

    public void apiLogin(String email, String password) throws IOException, InterruptedException {
        HttpRequest.BodyPublisher requestBody =
                HttpRequest.BodyPublishers.ofString("j_username=" + decodeEmail(email) + "&j_password="
                        + password + "&CSRFToken=" + getCSRFToken());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "j_spring_security_check"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Accept", "*/*")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Cookie", parseCookieList(cookiesVeryNeeded.get()))
                .POST(requestBody)
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        List<String> cookies = response.headers().allValues("Set-Cookie");
        for (Cookie cookie : parseCookie(cookies)) {
            Core.deleteCookie(cookie);
            Core.addCookie(cookie);
        }
    }

    public void apiRegister(String email, String password) throws IOException, InterruptedException {
        HttpRequest.BodyPublisher requestBody =
                HttpRequest.BodyPublishers.ofString("titleCode=miss&firstName=" + RandomUtils.getRandomFirstName() + "&lastName=" + RandomUtils.getRandomLastName() + "&email=" + decodeEmail(email) + "&pwd="
                        + password + "&checkPwd=" + password + "&consentForm.consentTemplateId=MARKETING_NEWSLETTER&consentForm.consentTemplateVersion=0&termsCheck=true&CSRFToken=" + getCSRFToken());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "login/register"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Accept", "*/*")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Cookie", parseCookieList(cookiesVeryNeeded.get()))
                .POST(requestBody)
                .build();

        httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private String getCSRFToken() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .GET()
                .build();

        HttpResponse<String> res = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        cookiesVeryNeeded.set(res.headers().allValues("Set-Cookie"));
        return StringUtils.substringBetween(res.body(), "CSRFToken = '", "'");
    }

    private String decodeEmail(String email) {
        return email.replace("@", "%40");
    }
}
