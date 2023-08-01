package dgsw.pioneers.checkIn.global.lib.random;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomGenerator {

    private final int LEFT_LIMIT = 48; // numeral '0'
    private final int RIGHT_LIMIT = 122; // letter 'z'
    private final int TARGET_STR_LENGTH = 7;
    private final Random random;

    public RandomGenerator() {
        this.random = new Random();
    }

    public String generate() {
        return random.ints(LEFT_LIMIT, RIGHT_LIMIT + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(TARGET_STR_LENGTH)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
