package dev.list;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LinkedPositionalListTest {
    @Test
    void startsEmpty() {
        LinkedPositionalList<String> list = new LinkedPositionalList<>();

        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        assertNull(list.first());
        assertNull(list.last());
    }

    @Test
    void addFirstToEmptyListMakesReturnedPositionBothFirstAndLast() {
        LinkedPositionalList<String> list = new LinkedPositionalList<>();

        Position<String> inserted = list.addFirst("first");

        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertSame(inserted, list.first());
        assertSame(inserted, list.last());
        assertEquals("first", inserted.getElement());
        assertNull(list.before(inserted));
        assertNull(list.after(inserted));
    }

    @Test
    void addFirstRepeatedlyUpdatesFirstAndSizeWhilePreservingLast() {
        LinkedPositionalList<String> list = new LinkedPositionalList<>();
        Position<String> oldest = list.addFirst("oldest");

        Position<String> middle = list.addFirst("newFirst");

        assertEquals(2, list.size());
        assertFalse(list.isEmpty());
        assertSame(middle, list.first());
        assertSame(oldest, list.last());
        assertEquals("middle", middle.getElement());

        Position<String> newest = list.addFirst("newest");

        assertEquals(3, list.size());
        assertFalse(list.isEmpty());
        assertSame(newest, list.first());
        assertSame(oldest, list.last());
        assertEquals("newest", newest.getElement());
        assertEquals("oldest", oldest.getElement());
        assertNull(list.before(newest));
        assertSame(middle, list.after(newest));
        assertSame(newest, list.before(middle));
        assertSame(oldest, list.after(middle));
        assertSame(middle, list.before(oldest));
        assertNull(list.after(oldest));
    }

    @Test
    void addFirstAcceptsNullAndCountsItAsAnElement() {
        LinkedPositionalList<String> list = new LinkedPositionalList<>();

        Position<String> inserted = list.addFirst(null);

        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertSame(inserted, list.first());
        assertSame(inserted, list.last());
        assertNull(inserted.getElement());
    }

    @Test
    void addLastToEmptyListMakesReturnedPositionBothFirstAndLast() {
        LinkedPositionalList<String> list = new LinkedPositionalList<>();

        Position<String> inserted = list.addLast("last");

        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertSame(inserted, list.first());
        assertSame(inserted, list.last());
        assertEquals("last", inserted.getElement());
        assertNull(list.before(inserted));
        assertNull(list.after(inserted));
    }

    @Test
    void addLastRepeatedlyUpdatesLastAndSizeWhilePreservingFirst() {
        LinkedPositionalList<String> list = new LinkedPositionalList<>();
        Position<String> first = list.addLast("first");

        Position<String> middle = list.addLast("middle");

        assertEquals(2, list.size());
        assertFalse(list.isEmpty());
        assertSame(first, list.first());
        assertSame(middle, list.last());
        assertEquals("middle", middle.getElement());

        Position<String> last = list.addLast("last");

        assertEquals(3, list.size());
        assertFalse(list.isEmpty());
        assertSame(first, list.first());
        assertSame(last, list.last());
        assertEquals("first", first.getElement());
        assertEquals("last", last.getElement());
        assertNull(list.before(first));
        assertSame(middle, list.after(first));
        assertSame(first, list.before(middle));
        assertSame(last, list.after(middle));
        assertSame(middle, list.before(last));
        assertNull(list.after(last));
    }

    @Test
    void addLastAppendsAfterElementInsertedWithAddFirst() {
        LinkedPositionalList<String> list = new LinkedPositionalList<>();
        Position<String> first = list.addFirst("first");

        Position<String> last = list.addLast("last");

        assertEquals(2, list.size());
        assertFalse(list.isEmpty());
        assertSame(first, list.first());
        assertSame(last, list.last());
        assertEquals("first", first.getElement());
        assertEquals("last", last.getElement());
        assertNull(list.before(first));
        assertSame(last, list.after(first));
        assertSame(first, list.before(last));
        assertNull(list.after(last));
    }

    @Test
    void addLastAcceptsNullAndCountsItAsAnElement() {
        LinkedPositionalList<String> list = new LinkedPositionalList<>();

        Position<String> inserted = list.addLast(null);

        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertSame(inserted, list.first());
        assertSame(inserted, list.last());
        assertNull(inserted.getElement());
    }

    @ParameterizedTest(name = "insert X before index {0}: [A B C] becomes [{1}]")
    @CsvSource({
            "0, X A B C",
            "1, A X B C",
            "2, A B X C"
    })
    void addBeforeInsertsBeforeFirstMiddleOrLastPosition(int targetIndex, String expectedOrder) {
        LinkedPositionalList<String> list = new LinkedPositionalList<>();
        Position<String> a = list.addLast("A");
        Position<String> b = list.addLast("B");
        Position<String> c = list.addLast("C");
        List<Position<String>> originalPositions = List.of(a, b, c);
        Position<String> target = originalPositions.get(targetIndex);

        Position<String> x = list.addBefore(target, "X");

        assertEquals(4, list.size());
        assertFalse(list.isEmpty());
        assertEquals("X", x.getElement());
        assertEquals("A", a.getElement());
        assertEquals("B", b.getElement());
        assertEquals("C", c.getElement());
        assertSame(x, list.before(target));
        assertSame(target, list.after(x));

        List<String> actualOrder = new ArrayList<>();
        Position<String> current = list.first();
        Position<String> previous = null;
        for (int i = 0; i < list.size(); i++) {
            assertSame(previous, list.before(current));
            actualOrder.add(current.getElement());
            previous = current;
            current = list.after(current);
        }
        assertNull(current);
        assertSame(previous, list.last());
        assertEquals(expectedOrder, String.join(" ", actualOrder));
    }

    @Test
    void addBeforeOnlyPositionUpdatesFirstAndPreservesLast() {
        LinkedPositionalList<String> list = new LinkedPositionalList<>();
        Position<String> original = list.addFirst("original");

        Position<String> inserted = list.addBefore(original, "inserted");

        assertEquals(2, list.size());
        assertFalse(list.isEmpty());
        assertSame(inserted, list.first());
        assertSame(original, list.last());
        assertEquals("inserted", inserted.getElement());
        assertEquals("original", original.getElement());
        assertNull(list.before(inserted));
        assertSame(original, list.after(inserted));
        assertSame(inserted, list.before(original));
        assertNull(list.after(original));
    }

    @Test
    void addBeforeAcceptsNullAndCountsItAsAnElement() {
        LinkedPositionalList<String> list = new LinkedPositionalList<>();
        Position<String> original = list.addFirst("original");

        Position<String> inserted = list.addBefore(original, null);

        assertEquals(2, list.size());
        assertFalse(list.isEmpty());
        assertSame(inserted, list.first());
        assertSame(original, list.last());
        assertNull(inserted.getElement());
        assertSame(original, list.after(inserted));
        assertSame(inserted, list.before(original));
    }

    @Test
    void addBeforeRejectsNullPositionOnEmptyList() {
        LinkedPositionalList<String> list = new LinkedPositionalList<>();

        assertThrows(IllegalArgumentException.class, () -> list.addBefore(null, "inserted"));

        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        assertNull(list.first());
        assertNull(list.last());
    }

    @Test
    void addBeforeRejectsCustomPositionWithoutChangingList() {
        LinkedPositionalList<String> list = new LinkedPositionalList<>();
        Position<String> original = list.addFirst("original");
        Position<String> invalid = () -> "outside";

        assertThrows(IllegalArgumentException.class, () -> list.addBefore(invalid, "inserted"));

        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertSame(original, list.first());
        assertSame(original, list.last());
        assertEquals("original", original.getElement());
        assertNull(list.before(original));
        assertNull(list.after(original));
    }

    @Test
    void addBeforeRejectsPositionFromAnotherListWithoutChangingEitherList() {
        LinkedPositionalList<String> list = new LinkedPositionalList<>();
        Position<String> original = list.addFirst("original");
        LinkedPositionalList<String> other = new LinkedPositionalList<>();
        Position<String> foreign = other.addFirst("foreign");

        assertThrows(IllegalArgumentException.class, () -> list.addBefore(foreign, "inserted"));

        assertEquals(1, list.size());
        assertSame(original, list.first());
        assertSame(original, list.last());
        assertNull(list.before(original));
        assertNull(list.after(original));
        assertEquals(1, other.size());
        assertSame(foreign, other.first());
        assertSame(foreign, other.last());
        assertEquals("foreign", foreign.getElement());
        assertNull(other.before(foreign));
        assertNull(other.after(foreign));
    }

    @ParameterizedTest(name = "insert X after index {0}: [A B C] becomes [{1}]")
    @CsvSource({
            "0, A X B C",
            "1, A B X C",
            "2, A B C X"
    })
    void addAfterInsertsAfterFirstMiddleOrLastPosition(int targetIndex, String expectedOrder) {
        LinkedPositionalList<String> list = new LinkedPositionalList<>();
        Position<String> a = list.addLast("A");
        Position<String> b = list.addLast("B");
        Position<String> c = list.addLast("C");
        List<Position<String>> originalPositions = List.of(a, b, c);
        Position<String> target = originalPositions.get(targetIndex);

        Position<String> x = list.addAfter(target, "X");

        assertEquals(4, list.size());
        assertFalse(list.isEmpty());
        assertSame(a, list.first());
        assertEquals("X", x.getElement());
        assertEquals("A", a.getElement());
        assertEquals("B", b.getElement());
        assertEquals("C", c.getElement());
        assertSame(x, list.after(target));
        assertSame(target, list.before(x));

        List<String> actualOrder = new ArrayList<>();
        Position<String> current = list.first();
        Position<String> previous = null;
        for (int i = 0; i < list.size(); i++) {
            assertSame(previous, list.before(current));
            actualOrder.add(current.getElement());
            previous = current;
            current = list.after(current);
        }
        assertNull(current);
        assertSame(previous, list.last());
        assertEquals(expectedOrder, String.join(" ", actualOrder));
    }

    @Test
    void addAfterOnlyPositionUpdatesLastAndPreservesFirst() {
        LinkedPositionalList<String> list = new LinkedPositionalList<>();
        Position<String> a = list.addFirst("A");

        Position<String> x = list.addAfter(a, "X");

        assertEquals(2, list.size());
        assertFalse(list.isEmpty());
        assertSame(a, list.first());
        assertSame(x, list.last());
        assertEquals("A", a.getElement());
        assertEquals("X", x.getElement());
        assertNull(list.before(a));
        assertSame(x, list.after(a));
        assertSame(a, list.before(x));
        assertNull(list.after(x));
    }

    @Test
    void addAfterAcceptsNullAndCountsItAsAnElement() {
        LinkedPositionalList<String> list = new LinkedPositionalList<>();
        Position<String> a = list.addFirst("A");

        Position<String> nullPosition = list.addAfter(a, null);

        assertEquals(2, list.size());
        assertFalse(list.isEmpty());
        assertSame(a, list.first());
        assertSame(nullPosition, list.last());
        assertEquals("A", a.getElement());
        assertNull(nullPosition.getElement());
        assertNull(list.before(a));
        assertSame(nullPosition, list.after(a));
        assertSame(a, list.before(nullPosition));
        assertNull(list.after(nullPosition));
    }

    @Test
    void addAfterRejectsNullPositionOnEmptyList() {
        LinkedPositionalList<String> list = new LinkedPositionalList<>();

        assertThrows(IllegalArgumentException.class, () -> list.addAfter(null, "X"));

        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        assertNull(list.first());
        assertNull(list.last());
    }

    @Test
    void addAfterRejectsCustomPositionWithoutChangingList() {
        LinkedPositionalList<String> list = new LinkedPositionalList<>();
        Position<String> a = list.addFirst("A");
        Position<String> invalid = () -> "outside";

        assertThrows(IllegalArgumentException.class, () -> list.addAfter(invalid, "X"));

        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertSame(a, list.first());
        assertSame(a, list.last());
        assertEquals("A", a.getElement());
        assertNull(list.before(a));
        assertNull(list.after(a));
    }

    @Test
    void addAfterRejectsPositionFromAnotherListWithoutChangingEitherList() {
        LinkedPositionalList<String> list = new LinkedPositionalList<>();
        Position<String> a = list.addFirst("A");
        LinkedPositionalList<String> other = new LinkedPositionalList<>();
        Position<String> foreign = other.addFirst("foreign");

        assertThrows(IllegalArgumentException.class, () -> list.addAfter(foreign, "X"));

        assertEquals(1, list.size());
        assertSame(a, list.first());
        assertSame(a, list.last());
        assertEquals("A", a.getElement());
        assertNull(list.before(a));
        assertNull(list.after(a));
        assertEquals(1, other.size());
        assertSame(foreign, other.first());
        assertSame(foreign, other.last());
        assertEquals("foreign", foreign.getElement());
        assertNull(other.before(foreign));
        assertNull(other.after(foreign));
    }

    @ParameterizedTest(name = "replace index {0} with X: [A B C] becomes [{2}], returns {1}")
    @CsvSource({
            "0, A, X B C",
            "1, B, A X C",
            "2, C, A B X"
    })
    void replaceElReplacesFirstMiddleOrLastElementAndPreservesPositions(
            int targetIndex, String expectedOldElement, String expectedOrder) {
        LinkedPositionalList<String> list = new LinkedPositionalList<>();
        Position<String> a = list.addLast("A");
        Position<String> b = list.addLast("B");
        Position<String> c = list.addLast("C");
        Position<String> target = List.of(a, b, c).get(targetIndex);

        String oldElement = list.replaceEl(target, "X");

        assertEquals(expectedOldElement, oldElement);
        assertEquals("X", target.getElement());
        assertEquals(expectedOrder, String.join(" ", a.getElement(), b.getElement(), c.getElement()));
        assertEquals(3, list.size());
        assertFalse(list.isEmpty());
        assertSame(a, list.first());
        assertSame(c, list.last());
        assertNull(list.before(a));
        assertSame(b, list.after(a));
        assertSame(a, list.before(b));
        assertSame(c, list.after(b));
        assertSame(b, list.before(c));
        assertNull(list.after(c));
    }

    @Test
    void replaceElOnOnlyPositionPreservesFirstLastAndSize() {
        LinkedPositionalList<String> list = new LinkedPositionalList<>();
        Position<String> a = list.addFirst("A");

        String oldElement = list.replaceEl(a, "X");

        assertEquals("A", oldElement);
        assertEquals("X", a.getElement());
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertSame(a, list.first());
        assertSame(a, list.last());
        assertNull(list.before(a));
        assertNull(list.after(a));
    }

    @Test
    void replaceElAcceptsNullWithoutRemovingPosition() {
        LinkedPositionalList<String> list = new LinkedPositionalList<>();
        Position<String> a = list.addFirst("A");

        String oldElement = list.replaceEl(a, null);

        assertEquals("A", oldElement);
        assertNull(a.getElement());
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertSame(a, list.first());
        assertSame(a, list.last());
        assertNull(list.before(a));
        assertNull(list.after(a));
    }

    @Test
    void replaceElReturnsNullWhenPreviousElementWasNull() {
        LinkedPositionalList<String> list = new LinkedPositionalList<>();
        Position<String> position = list.addFirst(null);

        String oldElement = list.replaceEl(position, "X");

        assertNull(oldElement);
        assertEquals("X", position.getElement());
        assertEquals(1, list.size());
        assertSame(position, list.first());
        assertSame(position, list.last());
        assertNull(list.before(position));
        assertNull(list.after(position));
    }

    @Test
    void replaceElRepeatedlyReturnsMostRecentElementAtSamePosition() {
        LinkedPositionalList<String> list = new LinkedPositionalList<>();
        Position<String> position = list.addFirst("A");

        assertEquals("A", list.replaceEl(position, "X"));
        assertEquals("X", list.replaceEl(position, "Y"));

        assertEquals("Y", position.getElement());
        assertEquals(1, list.size());
        assertSame(position, list.first());
        assertSame(position, list.last());
        assertNull(list.before(position));
        assertNull(list.after(position));
    }

    @Test
    void replaceElRejectsNullPositionOnEmptyList() {
        LinkedPositionalList<String> list = new LinkedPositionalList<>();

        assertThrows(IllegalArgumentException.class, () -> list.replaceEl(null, "X"));

        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        assertNull(list.first());
        assertNull(list.last());
    }

    @Test
    void replaceElRejectsCustomPositionWithoutChangingList() {
        LinkedPositionalList<String> list = new LinkedPositionalList<>();
        Position<String> a = list.addFirst("A");
        Position<String> invalid = () -> "outside";

        assertThrows(IllegalArgumentException.class, () -> list.replaceEl(invalid, "X"));

        assertEquals(1, list.size());
        assertSame(a, list.first());
        assertSame(a, list.last());
        assertEquals("A", a.getElement());
        assertNull(list.before(a));
        assertNull(list.after(a));
    }

    @Test
    void replaceElRejectsPositionFromAnotherListWithoutChangingEitherList() {
        LinkedPositionalList<String> list = new LinkedPositionalList<>();
        Position<String> a = list.addFirst("A");
        LinkedPositionalList<String> other = new LinkedPositionalList<>();
        Position<String> foreign = other.addFirst("foreign");

        assertThrows(IllegalArgumentException.class, () -> list.replaceEl(foreign, "X"));

        assertEquals(1, list.size());
        assertSame(a, list.first());
        assertSame(a, list.last());
        assertEquals("A", a.getElement());
        assertNull(list.before(a));
        assertNull(list.after(a));
        assertEquals(1, other.size());
        assertSame(foreign, other.first());
        assertSame(foreign, other.last());
        assertEquals("foreign", foreign.getElement());
        assertNull(other.before(foreign));
        assertNull(other.after(foreign));
    }

    @ParameterizedTest(name = "remove index {0}: [A B C] becomes [{2}], returns {1}")
    @CsvSource({
            "0, A, B C",
            "1, B, A C",
            "2, C, A B"
    })
    void removeFirstMiddleOrLastPositionReturnsElementAndReconnectsNeighbors(
            int targetIndex, String expectedRemovedElement, String expectedOrder) {
        LinkedPositionalList<String> list = new LinkedPositionalList<>();
        Position<String> a = list.addLast("A");
        Position<String> b = list.addLast("B");
        Position<String> c = list.addLast("C");
        List<Position<String>> positions = new ArrayList<>(List.of(a, b, c));
        Position<String> target = positions.get(targetIndex);

        String removedElement = list.remove(target);

        assertEquals(expectedRemovedElement, removedElement);
        assertEquals(2, list.size());
        assertFalse(list.isEmpty());
        positions.remove(targetIndex);
        Position<String> firstRemaining = positions.get(0);
        Position<String> lastRemaining = positions.get(1);
        assertSame(firstRemaining, list.first());
        assertSame(lastRemaining, list.last());
        assertEquals(expectedOrder, String.join(" ", list.first().getElement(), list.last().getElement()));
        assertNull(list.before(firstRemaining));
        assertSame(lastRemaining, list.after(firstRemaining));
        assertSame(firstRemaining, list.before(lastRemaining));
        assertNull(list.after(lastRemaining));
        assertThrows(IllegalStateException.class, target::getElement);
    }

    @Test
    void removeOnlyPositionReturnsElementAndLeavesListEmpty() {
        LinkedPositionalList<String> list = new LinkedPositionalList<>();
        Position<String> a = list.addFirst("A");

        String removedElement = list.remove(a);

        assertEquals("A", removedElement);
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        assertNull(list.first());
        assertNull(list.last());
        assertThrows(IllegalStateException.class, a::getElement);
    }

    @Test
    void removeNullElementReturnsNullAndPreservesRemainingPosition() {
        LinkedPositionalList<String> list = new LinkedPositionalList<>();
        Position<String> nullPosition = list.addLast(null);
        Position<String> a = list.addLast("A");

        String removedElement = list.remove(nullPosition);

        assertNull(removedElement);
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertSame(a, list.first());
        assertSame(a, list.last());
        assertEquals("A", a.getElement());
        assertNull(list.before(a));
        assertNull(list.after(a));
        assertThrows(IllegalStateException.class, nullPosition::getElement);
    }

    @Test
    void removeInvalidatesPositionForAllSubsequentOperations() {
        LinkedPositionalList<String> list = new LinkedPositionalList<>();
        Position<String> a = list.addLast("A");
        Position<String> b = list.addLast("B");
        list.remove(a);

        assertThrows(IllegalStateException.class, a::getElement);
        assertThrows(IllegalArgumentException.class, () -> list.remove(a));
        assertThrows(IllegalArgumentException.class, () -> list.before(a));
        assertThrows(IllegalArgumentException.class, () -> list.after(a));
        assertThrows(IllegalArgumentException.class, () -> list.addBefore(a, "X"));
        assertThrows(IllegalArgumentException.class, () -> list.addAfter(a, "X"));
        assertThrows(IllegalArgumentException.class, () -> list.replaceEl(a, "X"));

        assertEquals(1, list.size());
        assertSame(b, list.first());
        assertSame(b, list.last());
        assertEquals("B", b.getElement());
        assertNull(list.before(b));
        assertNull(list.after(b));
    }

    @Test
    void removeRejectsNullPositionOnEmptyList() {
        LinkedPositionalList<String> list = new LinkedPositionalList<>();

        assertThrows(IllegalArgumentException.class, () -> list.remove(null));

        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        assertNull(list.first());
        assertNull(list.last());
    }

    @Test
    void removeRejectsCustomPositionWithoutChangingList() {
        LinkedPositionalList<String> list = new LinkedPositionalList<>();
        Position<String> a = list.addFirst("A");
        Position<String> invalid = () -> "outside";

        assertThrows(IllegalArgumentException.class, () -> list.remove(invalid));

        assertEquals(1, list.size());
        assertSame(a, list.first());
        assertSame(a, list.last());
        assertEquals("A", a.getElement());
        assertNull(list.before(a));
        assertNull(list.after(a));
    }

    @Test
    void removeRejectsPositionFromAnotherListWithoutChangingEitherList() {
        LinkedPositionalList<String> list = new LinkedPositionalList<>();
        Position<String> a = list.addFirst("A");
        LinkedPositionalList<String> other = new LinkedPositionalList<>();
        Position<String> foreign = other.addFirst("foreign");

        assertThrows(IllegalArgumentException.class, () -> list.remove(foreign));

        assertEquals(1, list.size());
        assertSame(a, list.first());
        assertSame(a, list.last());
        assertEquals("A", a.getElement());
        assertNull(list.before(a));
        assertNull(list.after(a));
        assertEquals(1, other.size());
        assertSame(foreign, other.first());
        assertSame(foreign, other.last());
        assertEquals("foreign", foreign.getElement());
        assertNull(other.before(foreign));
        assertNull(other.after(foreign));
    }
}
