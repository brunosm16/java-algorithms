package dev.recursion;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RulerTest {
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        originalOut = System.out;
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void drawRulerWithZeroInchesPrintsOnlyTheInitialMajorTick() {
        Ruler ruler = new Ruler();

        ruler.drawRuler(0, 3);

        assertEquals(lines("--- 0"), output.toString());
    }

    @Test
    void drawRulerPrintsRecursiveIntervalsBetweenMajorTicks() {
        Ruler ruler = new Ruler();

        ruler.drawRuler(2, 3);

        assertEquals(lines(
                "--- 0",
                "-",
                "--",
                "-",
                "--- 1",
                "-",
                "--",
                "-",
                "--- 2"
        ), output.toString());
    }

    private static String lines(String... lines) {
        return String.join(System.lineSeparator(), lines) + System.lineSeparator();
    }
}
