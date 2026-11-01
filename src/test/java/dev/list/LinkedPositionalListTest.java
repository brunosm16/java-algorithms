package dev.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
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
}
