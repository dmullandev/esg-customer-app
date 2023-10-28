package io.dmullandev.esg.codekata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StringCalculatorTest {

    StringCalculator testSc = new StringCalculator();

    @BeforeEach
    void reset() {
        testSc = new StringCalculator();
    }

    @Test
    void testNullAndEmptyString() {
        Assertions.assertEquals(0, testSc.add(""));
        Assertions.assertEquals(0, testSc.add(null));
    }

    @Test
    void testOneAndTwoNumbers() {
        Assertions.assertEquals(1, testSc.add("1"));
        Assertions.assertEquals(3, testSc.add("1,2"));
    }

    @Test
    void testHandlingNewLines() {
        Assertions.assertEquals(2, testSc.add("//\n\n1\n2"));
        Assertions.assertEquals(1, testSc.add("//\n\n1"));
    }

    @Test
    void testDelimiters() {
        Assertions.assertEquals("1,2,3", testSc.setDelimiterAndGetNumbers("1,2,3"));
        Assertions.assertEquals("1;2;3", testSc.setDelimiterAndGetNumbers("//;\n1;2;3"));
        Assertions.assertEquals("1;;2;;3", testSc.setDelimiterAndGetNumbers("//;;\n1;;2;;3"));
        Assertions.assertEquals(6, testSc.add("//;\n1;2;3"));

    }

    @Test
    void testAnyLengthDelimiters() {
        Assertions.assertEquals(6, testSc.add("//xxxxxx\n1xxxxxx2xxxxxx3"));
        Assertions.assertEquals(6, testSc.add("//;;\n1;;2;;3"));
    }

    @Test
    void testNegativenumbersThrowsException() {
        IllegalArgumentException iae = Assertions.assertThrows(IllegalArgumentException.class, () -> testSc.add("//;;\n1;;2;;3;;-4;;-5"));
        Assertions.assertEquals("Negatives not allowed: [-4, -5]", iae.getMessage());
    }


}
