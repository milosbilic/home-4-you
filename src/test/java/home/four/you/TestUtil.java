package home.four.you;

import java.util.SplittableRandom;

/**
 * Test utility class.
 */
public class TestUtil {

    public static Long generateId() {
        return new SplittableRandom().nextLong(0, 1_001);
    }
}
