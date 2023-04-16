package home.four.you;

import java.time.Instant;
import java.util.SplittableRandom;

import static java.time.temporal.ChronoUnit.SECONDS;

/**
 * Test utility class.
 */
public class TestUtil {

    public static Long generateId() {
        return new SplittableRandom().nextLong(0, 1_00001);
    }

    public static String truncateInstant(Instant instant) {
        var truncatedTime = instant.truncatedTo(SECONDS).toString();

        return truncatedTime.substring(0, truncatedTime.length() - 1);
    }
}
