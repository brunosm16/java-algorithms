package dev.recursion;

public class Ruler {
    public void drawRuler(int inches, int majorLength) {
        drawLine(majorLength, 0);

        for (int j = 1; j <= inches; j++) {
            drawInterval(majorLength - 1);
            drawLine(majorLength, j);
        }
    }

    private void drawLine(int length, int label) {
        for (int i = 0; i < length; i++) {
            System.out.print('-');
        }

        if (label >= 0) {
            System.out.print(" " + label);
        }

        System.out.println();
    }

    private void drawLine(int length) {
        drawLine(length, -1);
    }

    private void drawInterval(int centralLength) {
        if (centralLength >= 1) {
            drawInterval(centralLength - 1);
            drawLine(centralLength);
            drawInterval(centralLength - 1);
        }
    }

    public static void main(String[] args) {
        Ruler ruler = new Ruler();

        // Draw first line of ruler
        ruler.drawRuler(3, 4);
    }
}
