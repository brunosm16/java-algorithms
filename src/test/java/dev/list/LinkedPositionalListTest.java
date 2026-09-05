package dev.list;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LinkedPositionalListTest {
    @Test
    void getNodeFromPositionReturnsNodeWhenPositionIsValid() throws Exception {
        LinkedPositionalList<String> list = new LinkedPositionalList<>();
        Object trailer = newNode(null, null, null);
        Object node = newNode("first", null, trailer);

        Object result = invokeGetNodeFromPosition(list, node);

        assertSame(node, result);
    }

    @Test
    void getNodeFromPositionRejectsNullPosition() {
        LinkedPositionalList<String> list = new LinkedPositionalList<>();

        assertThrows(IllegalArgumentException.class, () -> invokeGetNodeFromPosition(list, null));
    }

    @Test
    void getNodeFromPositionRejectsPositionFromDifferentImplementation() {
        LinkedPositionalList<String> list = new LinkedPositionalList<>();
        Position<String> position = () -> "external";

        assertThrows(IllegalArgumentException.class, () -> invokeGetNodeFromPosition(list, position));
    }

    @Test
    void getNodeFromPositionRejectsPositionWithNullNextReference() throws Exception {
        LinkedPositionalList<String> list = new LinkedPositionalList<>();
        Object staleNode = newNode("removed", null, null);

        assertThrows(IllegalArgumentException.class, () -> invokeGetNodeFromPosition(list, staleNode));
    }

    private static Object invokeGetNodeFromPosition(LinkedPositionalList<String> list, Object position)
            throws ReflectiveOperationException {
        try {
            Method method = LinkedPositionalList.class.getDeclaredMethod("getNodeFromPosition", Position.class);
            method.setAccessible(true);
            return method.invoke(list, position);
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();

            if (cause instanceof RuntimeException runtimeException) {
                throw runtimeException;
            }

            throw e;
        }
    }

    private static Object newNode(String element, Object prev, Object next) throws ReflectiveOperationException {
        Class<?> nodeClass = getNodeClass();
        Constructor<?> constructor = nodeClass.getDeclaredConstructor(Object.class, nodeClass, nodeClass);
        constructor.setAccessible(true);

        return constructor.newInstance(element, prev, next);
    }

    private static Class<?> getNodeClass() {
        for (Class<?> declaredClass : LinkedPositionalList.class.getDeclaredClasses()) {
            if ("Node".equals(declaredClass.getSimpleName())) {
                return declaredClass;
            }
        }

        throw new IllegalStateException("LinkedPositionalList.Node class not found");
    }
}
