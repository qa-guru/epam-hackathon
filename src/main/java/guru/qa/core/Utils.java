package guru.qa.core;

import java.util.concurrent.TimeUnit;

public class Utils {

    public static void sleep(long sleepTime) {
        try {
            TimeUnit.MILLISECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
