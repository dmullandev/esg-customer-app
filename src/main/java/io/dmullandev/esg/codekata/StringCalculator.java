package io.dmullandev.esg.codekata;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringCalculator {
    private static final String DELIMITER_DEFAULT = "[,\n]";

    private static final String DELIMITER_CONFIGURATION_PREFIX = "//";
    private static final String DELIMITER_CONFIGURATION_DELIMITER_MATCHER = "(.*)";
    private static final String DELIMITER_CONFIGURATION_DELIMITER_NEWLINE_SUFFIX = "\\n";
    private static final String DELIMITER_CONFIGURATION_DELIMITER_REMAINING_SUFFIX = "(.*)";

    private static final String DELIMITER_CONFIGURATION_REGEX =
                    DELIMITER_CONFIGURATION_PREFIX + DELIMITER_CONFIGURATION_DELIMITER_MATCHER
                                    + DELIMITER_CONFIGURATION_DELIMITER_NEWLINE_SUFFIX + DELIMITER_CONFIGURATION_DELIMITER_REMAINING_SUFFIX;
    private static final Pattern pattern = Pattern.compile(DELIMITER_CONFIGURATION_REGEX, Pattern.DOTALL);

    private String delimiter = DELIMITER_DEFAULT;

    public int add(String numbers) {
        if (numbers == null || numbers.length() == 0) {
            return 0;
        }

        int total = 0;

        String cleanedNumbers = setDelimiterAndGetNumbers(numbers);
        log.info("Numbers: {}", cleanedNumbers);

        List<String> numbersList = Arrays.asList(cleanedNumbers.split(delimiter));
        log.info("Numbers List: {}", numbersList);

        for (String digitStr : numbersList) {
            int digit = Integer.parseInt(digitStr);
            total += digit;
        }

        return total;
    }

    public String setDelimiterAndGetNumbers(String numbers) {
        Matcher matcher = pattern.matcher(numbers);
        matcher.find();

        if (matcher.matches()) {
            delimiter = matcher.group(1);
            log.info("Delimiter: ", delimiter);

            return matcher.group(2);
        }

        return numbers;
    }
}
