package dev.list;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FavoritesListTest {
    @Test
    void startsEmpty() {
        FavoritesList<String> favorites = new FavoritesList<>();

        assertTrue(favorites.list.isEmpty());
        assertEquals(0, favorites.list.size());
        assertNull(favorites.list.first());
        assertNull(favorites.list.last());
    }

    @Test
    void itemStoresValueAndStartsWithZeroCount() {
        Object value = new Object();
        FavoritesList.Item<Object> item = new FavoritesList.Item<>(value);

        assertSame(value, item.getValue());
        assertEquals(0, item.getCount());
    }

    @Test
    void itemCanStoreNull() {
        FavoritesList.Item<String> item = new FavoritesList.Item<>(null);

        assertNull(item.getValue());
        assertEquals(0, item.getCount());
    }

    @Test
    void incrementIncreasesCountByOne() {
        FavoritesList.Item<String> item = new FavoritesList.Item<>("value");

        item.increment();

        assertEquals(1, item.getCount());
    }

    @Test
    void incrementAccumulatesAcrossCalls() {
        FavoritesList.Item<String> item = new FavoritesList.Item<>("value");

        item.increment();
        item.increment();

        assertEquals(2, item.getCount());
    }

    @Test
    void incrementPreservesValue() {
        Object value = new Object();
        FavoritesList.Item<Object> item = new FavoritesList.Item<>(value);

        item.increment();

        assertSame(value, item.getValue());
    }

    @Test
    void incrementDoesNotAffectOtherItems() {
        FavoritesList.Item<String> first = new FavoritesList.Item<>("same");
        FavoritesList.Item<String> second = new FavoritesList.Item<>("same");

        first.increment();

        assertEquals(1, first.getCount());
        assertEquals(0, second.getCount());
    }

    @Test
    void valueReturnsValueStoredAtPosition() {
        FavoritesList<Object> favorites = new FavoritesList<>();
        Object value = new Object();
        Position<FavoritesList.Item<Object>> position =
                favorites.list.addLast(new FavoritesList.Item<>(value));

        assertSame(value, favorites.value(position));
    }

    @Test
    void valueReturnsNullForItemWithNullValue() {
        FavoritesList<String> favorites = new FavoritesList<>();
        Position<FavoritesList.Item<String>> position =
                favorites.list.addLast(new FavoritesList.Item<>(null));

        assertNull(favorites.value(position));
    }

    @Test
    void countReflectsItemIncrements() {
        FavoritesList<String> favorites = new FavoritesList<>();
        FavoritesList.Item<String> item = new FavoritesList.Item<>("value");
        Position<FavoritesList.Item<String>> position = favorites.list.addLast(item);

        assertEquals(0, favorites.count(position));

        item.increment();
        item.increment();

        assertEquals(2, favorites.count(position));
    }

    @Test
    void findPositionFindsFirstItem() {
        FavoritesList<String> favorites = new FavoritesList<>();
        Position<FavoritesList.Item<String>> first =
                favorites.list.addLast(new FavoritesList.Item<>("first"));
        favorites.list.addLast(new FavoritesList.Item<>("middle"));
        favorites.list.addLast(new FavoritesList.Item<>("last"));

        assertSame(first, favorites.findPosition("first"));
    }

    @Test
    void findPositionFindsMiddleItem() {
        FavoritesList<String> favorites = new FavoritesList<>();
        favorites.list.addLast(new FavoritesList.Item<>("first"));
        Position<FavoritesList.Item<String>> middle =
                favorites.list.addLast(new FavoritesList.Item<>("middle"));
        favorites.list.addLast(new FavoritesList.Item<>("last"));

        assertSame(middle, favorites.findPosition("middle"));
    }

    @Test
    void findPositionFindsLastItem() {
        FavoritesList<String> favorites = new FavoritesList<>();
        favorites.list.addLast(new FavoritesList.Item<>("first"));
        favorites.list.addLast(new FavoritesList.Item<>("middle"));
        Position<FavoritesList.Item<String>> last =
                favorites.list.addLast(new FavoritesList.Item<>("last"));

        assertSame(last, favorites.findPosition("last"));
    }

    @Test
    void findPositionPreservesListOrder() {
        FavoritesList<String> favorites = new FavoritesList<>();
        Position<FavoritesList.Item<String>> first =
                favorites.list.addLast(new FavoritesList.Item<>("first"));
        Position<FavoritesList.Item<String>> middle =
                favorites.list.addLast(new FavoritesList.Item<>("middle"));
        Position<FavoritesList.Item<String>> last =
                favorites.list.addLast(new FavoritesList.Item<>("last"));

        assertSame(middle, favorites.findPosition("middle"));

        assertEquals(3, favorites.list.size());
        assertSame(first, favorites.list.first());
        assertSame(middle, favorites.list.after(first));
        assertSame(last, favorites.list.after(middle));
        assertSame(last, favorites.list.last());
    }

    @Test
    void findPositionPreservesItemCount() {
        FavoritesList<String> favorites = new FavoritesList<>();
        FavoritesList.Item<String> item = new FavoritesList.Item<>("value");
        item.increment();
        favorites.list.addLast(item);

        favorites.findPosition("value");

        assertEquals(1, item.getCount());
    }

    @Test
    void findPositionFindsOnlyItem() {
        FavoritesList<String> favorites = new FavoritesList<>();
        Position<FavoritesList.Item<String>> position =
                favorites.list.addLast(new FavoritesList.Item<>("only"));

        assertSame(position, favorites.findPosition("only"));
    }

    @Test
    void findPositionComparesValuesUsingEquals() {
        FavoritesList<String> favorites = new FavoritesList<>();
        Position<FavoritesList.Item<String>> position =
                favorites.list.addLast(new FavoritesList.Item<>("value"));
        favorites.list.addLast(new FavoritesList.Item<>("other"));

        assertSame(position, favorites.findPosition("value"));
    }

    @Test
    void findPositionReturnsFirstMatchingDuplicate() {
        FavoritesList<String> favorites = new FavoritesList<>();
        favorites.list.addLast(new FavoritesList.Item<>("other"));
        Position<FavoritesList.Item<String>> firstMatch =
                favorites.list.addLast(new FavoritesList.Item<>("same"));
        favorites.list.addLast(new FavoritesList.Item<>("same"));

        assertSame(firstMatch, favorites.findPosition("same"));
    }

    @Test
    void findPositionReturnsNullOnEmptyList() {
        FavoritesList<String> favorites = new FavoritesList<>();

        assertNull(favorites.findPosition("missing"));
    }

    @Test
    void findPositionReturnsNullWhenOnlyItemDoesNotMatch() {
        FavoritesList<String> favorites = new FavoritesList<>();
        favorites.list.addLast(new FavoritesList.Item<>("only"));

        assertNull(favorites.findPosition("missing"));
    }

    @Test
    void findPositionReturnsNullWhenNoItemMatches() {
        FavoritesList<String> favorites = new FavoritesList<>();
        favorites.list.addLast(new FavoritesList.Item<>("first"));
        favorites.list.addLast(new FavoritesList.Item<>("middle"));
        favorites.list.addLast(new FavoritesList.Item<>("last"));

        assertNull(favorites.findPosition("missing"));
    }
}
