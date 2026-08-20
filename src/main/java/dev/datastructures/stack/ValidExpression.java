package dev.datastructures.stack;

public class ValidExpression {
    public static boolean isValidExpression(String expression) {
        String opening = "([{";
        String closing = ")]}";

        LinkedStack<Character> stackValidation = new LinkedStack<>();

        for (char c : expression.toCharArray()) {
            if (opening.indexOf(c) != -1) {
                stackValidation.push(c);
            } else if (closing.indexOf(c) != -1) {
                if (stackValidation.isEmpty()) {
                    return false;
                }

                if (!isValidEnclosing(stackValidation.pop(), c)) {
                    return false;
                }
            }
        }

        return stackValidation.isEmpty();
    }

    private static boolean isValidEnclosing(char leftDelimiter, char rightDelimiter) {
        return (leftDelimiter == '(' && rightDelimiter == ')') || (leftDelimiter == '{' && rightDelimiter == '}') || (leftDelimiter == '[' && rightDelimiter == ']');
    }
}
