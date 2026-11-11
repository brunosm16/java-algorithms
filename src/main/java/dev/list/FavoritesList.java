package dev.list;

import java.util.Iterator;

public class FavoritesList<E> {
    protected static class Item<E> {
        private int count = 0;

        private E value;

        public Item(E valueItem) {
            value = valueItem;
        }

        public int getCount() {
            return count;
        }

        public E getValue() {
            return value;
        }

        public void increment() {
            count++;
        }
    }

    LinkedPositionalList<Item<E>> list = new LinkedPositionalList<>();

    public FavoritesList() {
    }

    protected E value(Position<Item<E>> p) {
        return p.getElement().getValue();
    }

    protected int count(Position<Item<E>> p) {
        return p.getElement().getCount();
    }

    protected Position<Item<E>> findPosition(E el) {
        Position<Item<E>> walk = list.first();

        while (walk != null && !el.equals(value(walk))) {
            walk = list.after(walk);
        }

        return walk;
    }

    protected void movePositionByCount(Position<Item<E>> p) {
        Position<Item<E>> walk = p;

        int pCount = count(p);

        while(walk != list.first() && count(list.before(walk)) < pCount) {
            walk = list.before(walk);
        }

        if(walk != p) {
            list.addBefore(walk, list.remove(p));
        }
    }

    public int size() {
        return this.list.size();
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public void access(E el) {
        Position<Item<E>> p = findPosition(el);

        if(p == null) {
            p = this.list.addLast(new Item<>(el));
        }

        p.getElement().increment();
        movePositionByCount(p);
    }

    public Iterable<E> getFavorites(int k) {
        if(k < 0 || k > size()) {
            throw new IllegalArgumentException("Invalid k range");
        }

        PositionalList<E> result = new LinkedPositionalList<>();

        Iterator<Item<E>> iterator = list.iterator();

        for(int i = 0; i < k; i++) {
            result.addLast(iterator.next().getValue());
        }

        return result;
    }
}
