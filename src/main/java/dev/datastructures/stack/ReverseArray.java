package dev.datastructures.stack;

public class ReverseArray {
    public static <E> void reverseArray(E[] arr) {
        LinkedStack<E> buffer = new LinkedStack<>();

        for (E e : arr) {
            buffer.push(e);
        }

        for(int i = 0; i < arr.length; i++) {
            arr[i] = buffer.pop();
        }
    }
}
