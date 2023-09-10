package dgsw.pioneers.checkIn.global.lib.random;

import java.util.Random;

public class RandomGenerator {

    private static final int LEFT_LIMIT = 48; // numeral '0'
    private static final int RIGHT_LIMIT = 122; // letter 'z'
    private static final int TARGET_STR_LENGTH = 7;
    private static final Random random = new Random();

    public static String generate() {
        return random.ints(LEFT_LIMIT, RIGHT_LIMIT + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(TARGET_STR_LENGTH)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
