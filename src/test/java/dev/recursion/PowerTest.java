package dev.recursion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PowerTest {
    @Test
    void returnsOneWhenPowerIsZero() {
        assertEquals(1, Power.powerOf(5, 0));
    }

    @Test
    void returnsBaseWhenPowerIsOne() {
        assertEquals(5, Power.powerOf(5, 1));
    }

    @Test
    void returnsBaseRaisedToPower() {
        assertEquals(32, Power.powerOf(2, 5));
    }

    @Test
    void returnsOneWhenBaseIsOne() {
        assertEquals(1, Power.powerOf(1, 10));
    }

    @Test
    void returnsZeroWhenBaseIsZeroAndPowerIsPositive() {
        assertEquals(0, Power.powerOf(0, 3));
    }

    @Test
    void squarePowerReturnsOneWhenPowerIsZero() {
        assertEquals(1, Power.powerOfSquare(5, 0));
    }

    @Test
    void squarePowerReturnsBaseWhenPowerIsOne() {
        assertEquals(5, Power.powerOfSquare(5, 1));
    }

    @Test
    void squarePowerReturnsBaseRaisedToEvenPower() {
        assertEquals(16, Power.powerOfSquare(2, 4));
    }

    @Test
    void squarePowerReturnsBaseRaisedToOddPower() {
        assertEquals(32, Power.powerOfSquare(2, 5));
    }

    @Test
    void squarePowerReturnsOneWhenBaseIsOne() {
        assertEquals(1, Power.powerOfSquare(1, 10));
    }

    @Test
    void squarePowerReturnsZeroWhenBaseIsZeroAndPowerIsPositive() {
        assertEquals(0, Power.powerOfSquare(0, 3));
    }
}
