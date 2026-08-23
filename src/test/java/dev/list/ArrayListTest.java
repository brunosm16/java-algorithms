package dev.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayListTest {
    @Test
    void startsEmpty() {
        ArrayList<Integer> list = new ArrayList<>();

        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    void constructorRejectsNonPositiveCapacity() {
        assertThrows(IllegalArgumentException.class, () -> new ArrayList<>(0));
        assertThrows(IllegalArgumentException.class, () -> new ArrayList<>(-1));
    }

    @Test
    void addInsertsIntoEmptyList() {
        ArrayList<String> list = new ArrayList<>();

        list.add(0, "first");

        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
        assertEquals("first", list.get(0));
    }

    @Test
    void addAppendsAtCurrentSize() {
        ArrayList<String> list = new ArrayList<>();

        list.add(0, "first");
        list.add(1, "second");

        assertEquals(2, list.size());
        assertEquals("first", list.get(0));
        assertEquals("second", list.get(1));
    }

    @Test
    void addInsertsInMiddleAndShiftsElementsRight() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0, 1);
        list.add(1, 3);

        list.add(1, 2);

        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    void addRejectsInvalidIndexes() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0, 1);

        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(2, 2));
        assertEquals(1, list.size());
        assertEquals(1, list.get(0));
    }

    @Test
    void addResizesBeyondConfiguredCapacity() {
        ArrayList<Integer> list = new ArrayList<>(2);
        list.add(0, 1);
        list.add(1, 2);

        list.add(2, 3);

        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    void addResizesAndStillSupportsMiddleInsertion() {
        ArrayList<Integer> list = new ArrayList<>(1);
        list.add(0, 1);
        list.add(1, 3);

        list.add(1, 2);

        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    void getReturnsElementWithoutRemovingIt() {
        ArrayList<String> list = new ArrayList<>();
        list.add(0, "first");
        list.add(1, "second");

        assertEquals("first", list.get(0));
        assertEquals("first", list.get(0));
        assertEquals(2, list.size());
    }

    @Test
    void getRejectsInvalidIndexes() {
        ArrayList<String> list = new ArrayList<>();
        list.add(0, "first");

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    }

    @Test
    void replaceUpdatesElementAndReturnsPreviousValue() {
        ArrayList<String> list = new ArrayList<>();
        list.add(0, "old");

        assertEquals("old", list.replace(0, "new"));

        assertEquals(1, list.size());
        assertEquals("new", list.get(0));
    }

    @Test
    void replaceRejectsInvalidIndexes() {
        ArrayList<String> list = new ArrayList<>();
        list.add(0, "first");

        assertThrows(IndexOutOfBoundsException.class, () -> list.replace(-1, "invalid"));
        assertThrows(IndexOutOfBoundsException.class, () -> list.replace(1, "invalid"));
        assertEquals("first", list.get(0));
    }

    @Test
    void removeReturnsElementAndShiftsElementsLeft() {
        ArrayList<String> list = new ArrayList<>();
        list.add(0, "first");
        list.add(1, "second");
        list.add(2, "third");

        assertEquals("second", list.remove(1));

        assertEquals(2, list.size());
        assertEquals("first", list.get(0));
        assertEquals("third", list.get(1));
    }

    @Test
    void removeWorksWhenListIsFull() {
        ArrayList<Integer> list = new ArrayList<>(2);
        list.add(0, 1);
        list.add(1, 2);

        assertEquals(1, list.remove(0));

        assertEquals(1, list.size());
        assertEquals(2, list.get(0));
    }

    @Test
    void removeRejectsInvalidIndexes() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0, 1);

        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1));
        assertEquals(1, list.size());
        assertEquals(1, list.get(0));
    }

    @Test
    void removeClearsTrailingSlot() {
        ArrayList<String> list = new ArrayList<>(2);
        list.add(0, "first");
        list.add(1, "second");

        assertEquals("second", list.remove(1));
        list.add(1, null);

        assertNull(list.get(1));
    }
}
