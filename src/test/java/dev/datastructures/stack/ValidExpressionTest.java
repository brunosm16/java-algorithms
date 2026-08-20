package dev.datastructures.stack;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidExpressionTest {
    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "()",
            "[]",
            "{}",
            "()[]{}",
            "{[()]}",
            "(([]){})",
            "(a + b) * [c - {d / e}]",
            "text without delimiters"
    })
    void returnsTrueForValidExpressions(String expression) {
        assertTrue(ValidExpression.isValidExpression(expression));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "(]",
            "{)",
            "[}",
            ")(",
            "({[]",
            "([)]",
            "(()",
            "())",
            "(a + b] * c"
    })
    void returnsFalseForInvalidExpressions(String expression) {
        assertFalse(ValidExpression.isValidExpression(expression));
    }
}
