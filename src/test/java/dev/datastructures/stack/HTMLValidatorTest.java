package dev.datastructures.stack;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HTMLValidatorTest {
    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "plain text",
            "<p></p>",
            "<html><body><p>Hello</p></body></html>",
            "<div><span>Text</span><strong>Bold</strong></div>"
    })
    void returnsTrueForValidHtml(String html) {
        assertTrue(HTMLValidator.isHTMLValid(html));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "<p>",
            "</p>",
            "<p></div>",
            "<div><p></div></p>",
            "<html><body></html>",
            "<p"
    })
    void returnsFalseForInvalidHtml(String html) {
        assertFalse(HTMLValidator.isHTMLValid(html));
    }
}
