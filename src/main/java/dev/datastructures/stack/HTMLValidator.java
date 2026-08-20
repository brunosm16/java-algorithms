package dev.datastructures.stack;

public class HTMLValidator {
    public static boolean isHTMLValid(String html) {
        int opening = html.indexOf('<');

        LinkedStack<String> stack = new LinkedStack<>();

        while (opening != -1) {
            int enclosing = html.indexOf('>', opening + 1);

            // No enclosing delimiter for an opening delimiter
            if (enclosing == -1) {
                return false;
            }

            String tag = html.substring(opening + 1, enclosing);

            if (tag.startsWith("/")) {
                if (stack.isEmpty()) return false;

                // For example, transform '/p' into 'p'
                String enclosingTagValue = tag.substring(1);

                if (!stack.pop().equals(enclosingTagValue)) return false;
            } else {
                stack.push(tag);
            }

            opening = html.indexOf('<', enclosing + 1);
        }

        // All openings matched all enclosing delimiters
        return stack.isEmpty();
    }
}
