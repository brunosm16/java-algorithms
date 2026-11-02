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
}
