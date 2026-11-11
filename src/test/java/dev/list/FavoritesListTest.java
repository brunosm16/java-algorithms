package dev.list;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @Test
    void movePositionByCountLeavesOnlyItemInPlace() {
        FavoritesList<String> favorites = new FavoritesList<>();
        Position<FavoritesList.Item<String>> only =
                favorites.list.addLast(new FavoritesList.Item<>("only"));

        favorites.movePositionByCount(only);

        assertSame(only, favorites.list.first());
        assertSame(only, favorites.list.last());
        assertEquals(1, favorites.list.size());
    }

    @Test
    void movePositionByCountLeavesFirstItemInPlace() {
        FavoritesList<String> favorites = new FavoritesList<>();
        FavoritesList.Item<String> firstItem = new FavoritesList.Item<>("first");
        firstItem.increment();
        Position<FavoritesList.Item<String>> first = favorites.list.addLast(firstItem);
        Position<FavoritesList.Item<String>> last =
                favorites.list.addLast(new FavoritesList.Item<>("last"));

        favorites.movePositionByCount(first);

        assertSame(first, favorites.list.first());
        assertSame(last, favorites.list.after(first));
        assertSame(last, favorites.list.last());
        assertEquals(2, favorites.list.size());
    }

    @Test
    void movePositionByCountLeavesItemBehindHigherCount() {
        FavoritesList<String> favorites = new FavoritesList<>();
        FavoritesList.Item<String> popular = new FavoritesList.Item<>("popular");
        popular.increment();
        Position<FavoritesList.Item<String>> first = favorites.list.addLast(popular);
        Position<FavoritesList.Item<String>> last =
                favorites.list.addLast(new FavoritesList.Item<>("unaccessed"));

        favorites.movePositionByCount(last);

        assertSame(first, favorites.list.first());
        assertSame(last, favorites.list.after(first));
        assertSame(last, favorites.list.last());
        assertEquals(2, favorites.list.size());
    }

    @Test
    void movePositionByCountLeavesItemBehindEqualCount() {
        FavoritesList<String> favorites = new FavoritesList<>();
        FavoritesList.Item<String> firstItem = new FavoritesList.Item<>("first");
        firstItem.increment();
        FavoritesList.Item<String> lastItem = new FavoritesList.Item<>("last");
        lastItem.increment();
        Position<FavoritesList.Item<String>> first = favorites.list.addLast(firstItem);
        Position<FavoritesList.Item<String>> last = favorites.list.addLast(lastItem);

        favorites.movePositionByCount(last);

        assertSame(first, favorites.list.first());
        assertSame(last, favorites.list.after(first));
        assertSame(last, favorites.list.last());
        assertEquals(2, favorites.list.size());
    }

    @Test
    void movePositionByCountMovesItemAheadOfOneLowerCount() {
        FavoritesList<String> favorites = new FavoritesList<>();
        FavoritesList.Item<String> unaccessed = new FavoritesList.Item<>("unaccessed");
        Position<FavoritesList.Item<String>> first = favorites.list.addLast(unaccessed);
        FavoritesList.Item<String> popular = new FavoritesList.Item<>("popular");
        popular.increment();
        Position<FavoritesList.Item<String>> last = favorites.list.addLast(popular);

        favorites.movePositionByCount(last);

        assertSame(popular, favorites.list.first().getElement());
        assertSame(first, favorites.list.after(favorites.list.first()));
        assertSame(first, favorites.list.last());
        assertSame(unaccessed, first.getElement());
        assertEquals(1, popular.getCount());
        assertEquals(0, unaccessed.getCount());
        assertEquals(2, favorites.list.size());
    }

    @Test
    void movePositionByCountMovesItemAheadOfSeveralLowerCounts() {
        FavoritesList<String> favorites = new FavoritesList<>();
        FavoritesList.Item<String> firstItem = new FavoritesList.Item<>("first");
        firstItem.increment();
        Position<FavoritesList.Item<String>> first = favorites.list.addLast(firstItem);
        Position<FavoritesList.Item<String>> middle =
                favorites.list.addLast(new FavoritesList.Item<>("middle"));
        FavoritesList.Item<String> popular = new FavoritesList.Item<>("popular");
        popular.increment();
        popular.increment();
        Position<FavoritesList.Item<String>> last = favorites.list.addLast(popular);

        favorites.movePositionByCount(last);

        assertSame(popular, favorites.list.first().getElement());
        assertSame(first, favorites.list.after(favorites.list.first()));
        assertSame(middle, favorites.list.after(first));
        assertSame(middle, favorites.list.last());
        assertEquals(2, popular.getCount());
        assertEquals(1, firstItem.getCount());
        assertEquals(0, favorites.count(middle));
        assertEquals(3, favorites.list.size());
    }

    @Test
    void movePositionByCountStopsAfterItemWithHigherCount() {
        FavoritesList<String> favorites = new FavoritesList<>();
        FavoritesList.Item<String> highest = new FavoritesList.Item<>("highest");
        highest.increment();
        highest.increment();
        Position<FavoritesList.Item<String>> first = favorites.list.addLast(highest);
        Position<FavoritesList.Item<String>> middle =
                favorites.list.addLast(new FavoritesList.Item<>("unaccessed"));
        FavoritesList.Item<String> moving = new FavoritesList.Item<>("moving");
        moving.increment();
        Position<FavoritesList.Item<String>> last = favorites.list.addLast(moving);

        favorites.movePositionByCount(last);

        assertSame(first, favorites.list.first());
        assertSame(moving, favorites.list.after(first).getElement());
        assertSame(middle, favorites.list.after(favorites.list.after(first)));
        assertSame(middle, favorites.list.last());
        assertEquals(3, favorites.list.size());
    }

    @Test
    void movePositionByCountStopsAfterItemWithEqualCount() {
        FavoritesList<String> favorites = new FavoritesList<>();
        FavoritesList.Item<String> equal = new FavoritesList.Item<>("equal");
        equal.increment();
        Position<FavoritesList.Item<String>> first = favorites.list.addLast(equal);
        Position<FavoritesList.Item<String>> middle =
                favorites.list.addLast(new FavoritesList.Item<>("unaccessed"));
        FavoritesList.Item<String> moving = new FavoritesList.Item<>("moving");
        moving.increment();
        Position<FavoritesList.Item<String>> last = favorites.list.addLast(moving);

        favorites.movePositionByCount(last);

        assertSame(first, favorites.list.first());
        assertSame(moving, favorites.list.after(first).getElement());
        assertSame(middle, favorites.list.after(favorites.list.after(first)));
        assertSame(middle, favorites.list.last());
        assertEquals(3, favorites.list.size());
    }

    @Test
    void sizeReturnsZeroForEmptyList() {
        FavoritesList<String> favorites = new FavoritesList<>();

        assertEquals(0, favorites.size());
    }

    @Test
    void sizeReturnsOneForSingleItem() {
        FavoritesList<String> favorites = new FavoritesList<>();
        favorites.list.addLast(new FavoritesList.Item<>("only"));

        assertEquals(1, favorites.size());
    }

    @Test
    void sizeReturnsNumberOfItems() {
        FavoritesList<String> favorites = new FavoritesList<>();
        favorites.list.addLast(new FavoritesList.Item<>("first"));
        favorites.list.addLast(new FavoritesList.Item<>("middle"));
        favorites.list.addLast(new FavoritesList.Item<>("last"));

        assertEquals(3, favorites.size());
    }

    @Test
    void sizeDecreasesAfterRemovingItem() {
        FavoritesList<String> favorites = new FavoritesList<>();
        Position<FavoritesList.Item<String>> first =
                favorites.list.addLast(new FavoritesList.Item<>("first"));
        favorites.list.addLast(new FavoritesList.Item<>("last"));

        favorites.list.remove(first);

        assertEquals(1, favorites.size());
    }

    @Test
    void sizeReturnsZeroAfterRemovingLastItem() {
        FavoritesList<String> favorites = new FavoritesList<>();
        Position<FavoritesList.Item<String>> only =
                favorites.list.addLast(new FavoritesList.Item<>("only"));

        favorites.list.remove(only);

        assertEquals(0, favorites.size());
    }

    @Test
    void isEmptyReturnsTrueForNewList() {
        FavoritesList<String> favorites = new FavoritesList<>();

        assertTrue(favorites.isEmpty());
    }

    @Test
    void isEmptyReturnsFalseForSingleItem() {
        FavoritesList<String> favorites = new FavoritesList<>();
        favorites.list.addLast(new FavoritesList.Item<>("only"));

        assertFalse(favorites.isEmpty());
    }

    @Test
    void isEmptyReturnsFalseWhenItemsRemainAfterRemoval() {
        FavoritesList<String> favorites = new FavoritesList<>();
        Position<FavoritesList.Item<String>> first =
                favorites.list.addLast(new FavoritesList.Item<>("first"));
        favorites.list.addLast(new FavoritesList.Item<>("last"));

        favorites.list.remove(first);

        assertFalse(favorites.isEmpty());
    }

    @Test
    void isEmptyReturnsTrueAfterRemovingLastItem() {
        FavoritesList<String> favorites = new FavoritesList<>();
        Position<FavoritesList.Item<String>> only =
                favorites.list.addLast(new FavoritesList.Item<>("only"));

        favorites.list.remove(only);

        assertTrue(favorites.isEmpty());
    }

    @Test
    void accessAddsFirstItemWithCountOne() {
        FavoritesList<String> favorites = new FavoritesList<>();

        favorites.access("first");

        assertEquals(1, favorites.size());
        assertFalse(favorites.isEmpty());
        assertEquals("first", favorites.value(favorites.list.first()));
        assertEquals(1, favorites.count(favorites.list.first()));
        assertSame(favorites.list.first(), favorites.list.last());
    }

    @Test
    void accessIncrementsExistingItemWithoutAddingDuplicate() {
        FavoritesList<String> favorites = new FavoritesList<>();
        favorites.access("same");
        FavoritesList.Item<String> item = favorites.list.first().getElement();

        favorites.access("same");

        assertEquals(1, favorites.size());
        assertSame(item, favorites.list.first().getElement());
        assertEquals(2, item.getCount());
    }

    @Test
    void accessAddsNewItemAfterExistingItemWithEqualCount() {
        FavoritesList<String> favorites = new FavoritesList<>();
        favorites.access("first");

        favorites.access("second");

        assertEquals(2, favorites.size());
        assertEquals("first", favorites.value(favorites.list.first()));
        assertEquals("second", favorites.value(favorites.list.last()));
        assertEquals(1, favorites.count(favorites.list.first()));
        assertEquals(1, favorites.count(favorites.list.last()));
    }

    @Test
    void accessMovesItemToFrontWhenItsCountBecomesHighest() {
        FavoritesList<String> favorites = new FavoritesList<>();
        favorites.access("first");
        favorites.access("middle");
        favorites.access("last");
        FavoritesList.Item<String> lastItem = favorites.list.last().getElement();

        favorites.access("last");

        assertEquals(3, favorites.size());
        assertSame(lastItem, favorites.list.first().getElement());
        assertEquals(2, favorites.count(favorites.list.first()));
        assertEquals("first", favorites.value(favorites.list.after(favorites.list.first())));
        assertEquals("middle", favorites.value(favorites.list.last()));
    }

    @Test
    void accessKeepsItemBehindHigherCount() {
        FavoritesList<String> favorites = new FavoritesList<>();
        favorites.access("popular");
        favorites.access("popular");
        favorites.access("popular");
        favorites.access("other");

        favorites.access("other");

        assertEquals(2, favorites.size());
        assertEquals("popular", favorites.value(favorites.list.first()));
        assertEquals(3, favorites.count(favorites.list.first()));
        assertEquals("other", favorites.value(favorites.list.last()));
        assertEquals(2, favorites.count(favorites.list.last()));
    }

    @Test
    void accessKeepsItemBehindEqualCountAfterIncrement() {
        FavoritesList<String> favorites = new FavoritesList<>();
        favorites.access("first");
        favorites.access("first");
        favorites.access("second");

        favorites.access("second");

        assertEquals(2, favorites.size());
        assertEquals("first", favorites.value(favorites.list.first()));
        assertEquals("second", favorites.value(favorites.list.last()));
        assertEquals(2, favorites.count(favorites.list.first()));
        assertEquals(2, favorites.count(favorites.list.last()));
    }

    @Test
    void accessMovesItemToMiddleBehindHigherCount() {
        FavoritesList<String> favorites = new FavoritesList<>();
        favorites.access("highest");
        favorites.access("highest");
        favorites.access("highest");
        favorites.access("lowest");
        favorites.access("moving");

        favorites.access("moving");

        Position<FavoritesList.Item<String>> middle = favorites.list.after(favorites.list.first());
        assertEquals(3, favorites.size());
        assertEquals("highest", favorites.value(favorites.list.first()));
        assertEquals(3, favorites.count(favorites.list.first()));
        assertEquals("moving", favorites.value(middle));
        assertEquals(2, favorites.count(middle));
        assertEquals("lowest", favorites.value(favorites.list.last()));
        assertEquals(1, favorites.count(favorites.list.last()));
    }

    @Test
    void getFavoritesRejectsNegativeCount() {
        FavoritesList<String> favorites = new FavoritesList<>();

        assertThrows(IllegalArgumentException.class, () -> favorites.getFavorites(-1));
    }

    @Test
    void getFavoritesRejectsCountGreaterThanSize() {
        FavoritesList<String> favorites = new FavoritesList<>();
        favorites.access("only");

        assertThrows(IllegalArgumentException.class, () -> favorites.getFavorites(2));
    }

    @Test
    void getFavoritesRejectsPositiveCountOnEmptyList() {
        FavoritesList<String> favorites = new FavoritesList<>();

        assertThrows(IllegalArgumentException.class, () -> favorites.getFavorites(1));
    }

    @Test
    void getFavoritesReturnsEmptyIterableForZeroOnEmptyList() {
        FavoritesList<String> favorites = new FavoritesList<>();

        Iterable<String> result = favorites.getFavorites(0);

        assertFalse(result.iterator().hasNext());
    }

    @Test
    void getFavoritesReturnsEmptyIterableForZeroOnPopulatedList() {
        FavoritesList<String> favorites = new FavoritesList<>();
        favorites.access("only");

        Iterable<String> result = favorites.getFavorites(0);

        assertFalse(result.iterator().hasNext());
    }

    @Test
    void getFavoritesReturnsOnlyItem() {
        FavoritesList<String> favorites = new FavoritesList<>();
        favorites.access("only");

        Iterator<String> result = favorites.getFavorites(1).iterator();

        assertTrue(result.hasNext());
        assertEquals("only", result.next());
        assertFalse(result.hasNext());
    }

    @Test
    void getFavoritesReturnsRequestedNumberOfMostAccessedItems() {
        FavoritesList<String> favorites = new FavoritesList<>();
        favorites.access("least");
        favorites.access("second");
        favorites.access("second");
        favorites.access("most");
        favorites.access("most");
        favorites.access("most");

        Iterator<String> result = favorites.getFavorites(2).iterator();

        assertEquals("most", result.next());
        assertEquals("second", result.next());
        assertFalse(result.hasNext());
    }

    @Test
    void getFavoritesReturnsAllItemsInDescendingCountOrder() {
        FavoritesList<String> favorites = new FavoritesList<>();
        favorites.access("least");
        favorites.access("second");
        favorites.access("second");
        favorites.access("most");
        favorites.access("most");
        favorites.access("most");

        Iterator<String> result = favorites.getFavorites(3).iterator();

        assertEquals("most", result.next());
        assertEquals("second", result.next());
        assertEquals("least", result.next());
        assertFalse(result.hasNext());
    }

    @Test
    void getFavoritesPreservesOrderForEqualCounts() {
        FavoritesList<String> favorites = new FavoritesList<>();
        favorites.access("first");
        favorites.access("second");
        favorites.access("third");

        Iterator<String> result = favorites.getFavorites(2).iterator();

        assertEquals("first", result.next());
        assertEquals("second", result.next());
        assertFalse(result.hasNext());
    }

    @Test
    void getFavoritesLeavesOriginalItemsAndCountsUnchanged() {
        FavoritesList<String> favorites = new FavoritesList<>();
        favorites.access("popular");
        favorites.access("popular");
        favorites.access("other");
        Position<FavoritesList.Item<String>> first = favorites.list.first();
        Position<FavoritesList.Item<String>> last = favorites.list.last();

        Iterator<String> result = favorites.getFavorites(1).iterator();

        assertEquals("popular", result.next());
        assertFalse(result.hasNext());
        assertEquals(2, favorites.size());
        assertSame(first, favorites.list.first());
        assertSame(last, favorites.list.last());
        assertEquals("popular", favorites.value(first));
        assertEquals(2, favorites.count(first));
        assertEquals("other", favorites.value(last));
        assertEquals(1, favorites.count(last));
    }

    @Test
    void getFavoritesResultCanBeIteratedMoreThanOnce() {
        FavoritesList<String> favorites = new FavoritesList<>();
        favorites.access("only");

        Iterable<String> result = favorites.getFavorites(1);

        Iterator<String> firstIterator = result.iterator();
        assertEquals("only", firstIterator.next());
        assertFalse(firstIterator.hasNext());

        Iterator<String> secondIterator = result.iterator();
        assertEquals("only", secondIterator.next());
        assertFalse(secondIterator.hasNext());
    }
}
