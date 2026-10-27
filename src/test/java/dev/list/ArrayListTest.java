package dev.list;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    private static int capacityOf(ArrayList<?> list) throws ReflectiveOperationException {
        Field data = ArrayList.class.getDeclaredField("data");
        data.setAccessible(true);
        return ((Object[]) data.get(list)).length;
    }
}
