package dev.list;

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

    PositionalList<Item<E>> list = new LinkedPositionalList<>();

    public FavoritesList() {
    }

    protected E value(Position<Item<E>> p) {
        return p.getElement().getValue();
    }

    protected int count(Position<Item<E>> p) {
        return p.getElement().getCount();
    }
}
