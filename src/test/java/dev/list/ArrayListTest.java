package dev.list;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayListTest {
    @Test
    void defaultConstructorCreatesEmptyListWithDefaultCapacity() throws ReflectiveOperationException {
        ArrayList<String> list = new ArrayList<>();

        assertEquals(0, list.size());
        assertEquals(ArrayList.MAX_CAPACITY, capacityOf(list));
    }

    @Test
    void constructorCreatesEmptyListWithRequestedCapacity() throws ReflectiveOperationException {
        ArrayList<Integer> list = new ArrayList<>(5);

        assertEquals(0, list.size());
        assertEquals(5, capacityOf(list));
    }

    @Test
    void constructorAcceptsZeroCapacity() throws ReflectiveOperationException {
        ArrayList<String> list = new ArrayList<>(0);

        assertEquals(0, list.size());
        assertEquals(0, capacityOf(list));
    }

    @Test
    void constructorRejectsNegativeCapacity() {
        IndexOutOfBoundsException exception = assertThrows(
                IndexOutOfBoundsException.class, () -> new ArrayList<>(-1));

        assertEquals("Capacity of array cannot be negative: -1", exception.getMessage());
    }

    @Test
    void sizeReturnsZeroForNewDefaultList() {
        ArrayList<String> list = new ArrayList<>();

        assertEquals(0, list.size());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 5, 16, 32})
    void sizeReturnsZeroRegardlessOfInitialCapacity(int capacity) {
        ArrayList<Integer> list = new ArrayList<>(capacity);

        assertEquals(0, list.size());
    }

    @Test
    void isEmptyReturnsTrueForNewDefaultList() {
        ArrayList<String> list = new ArrayList<>();

        assertTrue(list.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 5, 16, 32})
    void isEmptyReturnsTrueRegardlessOfInitialCapacity(int capacity) {
        ArrayList<Integer> list = new ArrayList<>(capacity);

        assertTrue(list.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, -1, 0, 1, 15, 16, Integer.MAX_VALUE})
    void getRejectsIndexesOnEmptyList(int idx) {
        ArrayList<String> list = new ArrayList<>();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> list.get(idx));

        assertEquals("Current index is out of boundsUpper Limit", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1})
    void getRejectsIndexesOnZeroCapacityList(int idx) {
        ArrayList<String> list = new ArrayList<>(0);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> list.get(idx));

        assertEquals("Current index is out of boundsUpper Limit", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void replaceReturnsPreviousElementAndUpdatesOnlyRequestedIndex(int idx)
            throws ReflectiveOperationException {
        String[] original = {"first", "second", "third"};
        ArrayList<String> list = listWithElements(original);

        String previous = list.replace(idx, "replacement");

        assertEquals(original[idx], previous);
        assertEquals(3, list.size());
        for (int i = 0; i < original.length; i++) {
            assertEquals(i == idx ? "replacement" : original[i], list.get(i));
        }
    }

    @Test
    void replaceAcceptsNullElement() throws ReflectiveOperationException {
        ArrayList<String> list = listWithElements("original");

        assertEquals("original", list.replace(0, null));

        assertNull(list.get(0));
        assertEquals(1, list.size());
    }

    @Test
    void replaceReturnsNullWhenPreviousElementIsNull() throws ReflectiveOperationException {
        ArrayList<String> list = listWithElements((String) null);

        assertNull(list.replace(0, "replacement"));

        assertEquals("replacement", list.get(0));
        assertEquals(1, list.size());
    }

    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, -1, 0, 1, 16, Integer.MAX_VALUE})
    void replaceRejectsIndexesOnEmptyList(int idx) {
        ArrayList<String> list = new ArrayList<>();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> list.replace(idx, "replacement"));

        assertEquals("Current index is out of boundsUpper Limit", exception.getMessage());
        assertEquals(0, list.size());
    }

    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, -1, 3, 4, 5, Integer.MAX_VALUE})
    void replaceRejectsInvalidIndexesWithoutChangingElements(int idx)
            throws ReflectiveOperationException {
        ArrayList<String> list = listWithElements("first", "second", "third");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> list.replace(idx, "replacement"));

        assertEquals("Current index is out of boundsUpper Limit", exception.getMessage());
        assertEquals(3, list.size());
        assertEquals("first", list.get(0));
        assertEquals("second", list.get(1));
        assertEquals("third", list.get(2));
    }

    @Test
    void addInsertsFirstElementIntoEmptyList() {
        ArrayList<String> list = new ArrayList<>(1);

        list.add(0, "first");

        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertEquals("first", list.get(0));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    void addInsertsAtRequestedIndexAndShiftsFollowingElements(int idx)
            throws ReflectiveOperationException {
        String[] original = {"first", "second", "third"};
        ArrayList<String> list = listWithElements(original);

        list.add(idx, "inserted");

        assertEquals(4, list.size());
        for (int i = 0; i < list.size(); i++) {
            String expected = i == idx ? "inserted" : original[i < idx ? i : i - 1];
            assertEquals(expected, list.get(i));
        }
    }

    @Test
    void addAcceptsNullAndCountsItAsAnElement() throws ReflectiveOperationException {
        ArrayList<String> list = listWithElements("original");

        list.add(0, null);

        assertEquals(2, list.size());
        assertFalse(list.isEmpty());
        assertNull(list.get(0));
        assertEquals("original", list.get(1));
    }

    @Test
    void addCanFillLastAvailableSlot() throws ReflectiveOperationException {
        ArrayList<String> list = listWithCapacityAndElements(2, "first");

        list.add(1, "second");

        assertEquals(2, list.size());
        assertEquals("first", list.get(0));
        assertEquals("second", list.get(1));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void addRejectsFullListWithoutChangingElements(int idx) throws ReflectiveOperationException {
        ArrayList<String> list = listWithCapacityAndElements(2, "first", "second");

        IllegalStateException exception = assertThrows(
                IllegalStateException.class, () -> list.add(idx, "extra"));

        assertEquals("Array capacity is full", exception.getMessage());
        assertEquals(2, list.size());
        assertEquals("first", list.get(0));
        assertEquals("second", list.get(1));
    }

    @Test
    void addRejectsZeroCapacityList() {
        ArrayList<String> list = new ArrayList<>(0);

        IllegalStateException exception = assertThrows(
                IllegalStateException.class, () -> list.add(0, "first"));

        assertEquals("Array capacity is full", exception.getMessage());
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, -1, 1, 16, Integer.MAX_VALUE})
    void addRejectsInvalidIndexesOnEmptyList(int idx) {
        ArrayList<String> list = new ArrayList<>();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> list.add(idx, "first"));

        assertEquals("Current index is out of boundsUpper Limit", exception.getMessage());
        assertEquals(0, list.size());
    }

    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, -1, 4, 5, Integer.MAX_VALUE})
    void addRejectsInvalidIndexesWithoutChangingElements(int idx)
            throws ReflectiveOperationException {
        ArrayList<String> list = listWithElements("first", "second", "third");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> list.add(idx, "extra"));

        assertEquals("Current index is out of boundsUpper Limit", exception.getMessage());
        assertEquals(3, list.size());
        assertEquals("first", list.get(0));
        assertEquals("second", list.get(1));
        assertEquals("third", list.get(2));
    }

    @Test
    void addSupportsRepeatedInsertionsUntilCapacityIsReached() {
        ArrayList<String> list = new ArrayList<>(4);

        list.add(0, "second");
        list.add(0, "first");
        list.add(2, "fourth");
        list.add(2, "third");

        assertEquals(4, list.size());
        assertEquals("first", list.get(0));
        assertEquals("second", list.get(1));
        assertEquals("third", list.get(2));
        assertEquals("fourth", list.get(3));
        assertThrows(IllegalStateException.class, () -> list.add(4, "extra"));
        assertEquals(4, list.size());
        assertEquals("fourth", list.get(3));
    }

    @Test
    void addAllowsDuplicateElements() throws ReflectiveOperationException {
        ArrayList<String> list = listWithElements("same", "last");

        list.add(1, "same");

        assertEquals(3, list.size());
        assertEquals("same", list.get(0));
        assertEquals("same", list.get(1));
        assertEquals("last", list.get(2));
    }

    @Test
    void addAtFrontShiftsElementsIntoLastAvailableSlot() throws ReflectiveOperationException {
        ArrayList<String> list = listWithCapacityAndElements(3, "second", "third");

        list.add(0, "first");

        assertEquals(3, list.size());
        assertEquals("first", list.get(0));
        assertEquals("second", list.get(1));
        assertEquals("third", list.get(2));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void removeReturnsElementAndPreservesRemainingOrder(int idx) throws ReflectiveOperationException {
        String[] original = {"first", "second", "third"};
        ArrayList<String> list = listWithElements(original);

        assertEquals(original[idx], list.remove(idx));

        assertEquals(2, list.size());
        assertFalse(list.isEmpty());
        for (int i = 0; i < list.size(); i++) {
            assertEquals(original[i < idx ? i : i + 1], list.get(i));
        }
        assertThrows(IllegalArgumentException.class, () -> list.get(2));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void removeFromFullListClearsVacatedSlot(int idx) throws ReflectiveOperationException {
        String[] original = {"first", "second", "third"};
        ArrayList<String> list = listWithCapacityAndElements(3, original);

        assertEquals(original[idx], list.remove(idx));

        assertEquals(2, list.size());
        for (int i = 0; i < list.size(); i++) {
            assertEquals(original[i < idx ? i : i + 1], list.get(i));
        }
        Field data = ArrayList.class.getDeclaredField("data");
        data.setAccessible(true);
        Object[] storage = (Object[]) data.get(list);
        assertEquals(3, storage.length);
        assertNull(storage[2]);
    }

    @Test
    void removeOnlyElementLeavesEmptyListAndClearsSlot() throws ReflectiveOperationException {
        ArrayList<String> list = listWithCapacityAndElements(1, "only");

        assertEquals("only", list.remove(0));

        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Field data = ArrayList.class.getDeclaredField("data");
        data.setAccessible(true);
        assertNull(((Object[]) data.get(list))[0]);
        assertThrows(IllegalArgumentException.class, () -> list.remove(0));
    }

    @Test
    void removeReturnsNullForNullElement() throws ReflectiveOperationException {
        ArrayList<String> list = listWithElements("first", null, "third");

        assertNull(list.remove(1));

        assertEquals(2, list.size());
        assertEquals("first", list.get(0));
        assertEquals("third", list.get(1));
    }

    @Test
    void removeShiftsNullElementsWithoutDroppingThem() throws ReflectiveOperationException {
        ArrayList<String> list = listWithElements("first", null, "third");

        assertEquals("first", list.remove(0));

        assertEquals(2, list.size());
        assertNull(list.get(0));
        assertEquals("third", list.get(1));
    }

    @Test
    void removeDeletesOnlyRequestedDuplicate() throws ReflectiveOperationException {
        ArrayList<String> list = listWithElements("same", "middle", "same");

        assertEquals("same", list.remove(2));

        assertEquals(2, list.size());
        assertEquals("same", list.get(0));
        assertEquals("middle", list.get(1));
    }

    @Test
    void removeRepeatedlyEmptiesListAndAllowsReuse() throws ReflectiveOperationException {
        ArrayList<String> list = listWithCapacityAndElements(3, "first", "second", "third");

        assertEquals("second", list.remove(1));
        assertEquals("third", list.remove(1));
        assertEquals("first", list.remove(0));
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());

        list.add(0, "new first");
        list.add(1, "new second");
        list.add(2, "new third");

        assertEquals(3, list.size());
        assertEquals("new first", list.get(0));
        assertEquals("new second", list.get(1));
        assertEquals("new third", list.get(2));
    }

    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, -1, 0, 1, 16, Integer.MAX_VALUE})
    void removeRejectsIndexesOnEmptyList(int idx) {
        ArrayList<String> list = new ArrayList<>();

        assertThrows(IllegalArgumentException.class, () -> list.remove(idx));

        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    void removeRejectsZeroCapacityList() {
        ArrayList<String> list = new ArrayList<>(0);

        assertThrows(IllegalArgumentException.class, () -> list.remove(0));

        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, -1, 3, 4, Integer.MAX_VALUE})
    void removeRejectsInvalidIndexesWithoutChangingElements(int idx)
            throws ReflectiveOperationException {
        ArrayList<String> list = listWithElements("first", "second", "third");

        assertThrows(IllegalArgumentException.class, () -> list.remove(idx));

        assertEquals(3, list.size());
        assertEquals("first", list.get(0));
        assertEquals("second", list.get(1));
        assertEquals("third", list.get(2));
    }

    @Test
    void removeReturnsExactStoredInstance() {
        ArrayList<Object> list = new ArrayList<>(2);
        Object first = new Object();
        Object second = new Object();
        list.add(0, first);
        list.add(1, second);

        assertSame(first, list.remove(0));

        assertEquals(1, list.size());
        assertSame(second, list.get(0));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void removeFromFullListAllowsAnotherInsertion(int idx) throws ReflectiveOperationException {
        String[] original = {"first", "second", "third"};
        ArrayList<String> list = listWithCapacityAndElements(3, original);

        assertEquals(original[idx], list.remove(idx));
        list.add(idx, "replacement");

        assertEquals(3, list.size());
        for (int i = 0; i < list.size(); i++) {
            assertEquals(i == idx ? "replacement" : original[i], list.get(i));
        }
        assertThrows(IllegalStateException.class, () -> list.add(3, "extra"));
    }

    @Test
    void removeRejectsFormerLastIndexAfterListShrinks() throws ReflectiveOperationException {
        ArrayList<String> list = listWithElements("first", "second", "third");

        assertEquals("first", list.remove(0));

        assertThrows(IllegalArgumentException.class, () -> list.remove(2));
        assertEquals(2, list.size());
        assertEquals("second", list.get(0));
        assertEquals("third", list.get(1));
    }

    // Populate directly to isolate each test from add() defects.
    private static ArrayList<String> listWithElements(String... elements)
            throws ReflectiveOperationException {
        return listWithCapacityAndElements(elements.length + 2, elements);
    }

    private static ArrayList<String> listWithCapacityAndElements(int capacity, String... elements)
            throws ReflectiveOperationException {
        ArrayList<String> list = new ArrayList<>(capacity);
        Field data = ArrayList.class.getDeclaredField("data");
        data.setAccessible(true);
        System.arraycopy(elements, 0, data.get(list), 0, elements.length);

        Field size = ArrayList.class.getDeclaredField("size");
        size.setAccessible(true);
        size.setInt(list, elements.length);
        return list;
    }

    private static int capacityOf(ArrayList<?> list) throws ReflectiveOperationException {
        Field data = ArrayList.class.getDeclaredField("data");
        data.setAccessible(true);
        return ((Object[]) data.get(list)).length;
    }
}
