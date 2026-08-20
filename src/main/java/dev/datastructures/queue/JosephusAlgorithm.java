package dev.datastructures.queue;

public class JosephusAlgorithm {
    public static <E> E josephus(CircularLinkedQueue<E> queue, int eliminationCount) throws IllegalArgumentException {
        if (eliminationCount <= 0) {
            throw new IllegalArgumentException("Elimination count must be positive");
        }

        while (queue.size() > 1) {
            for (int i = 0; i < eliminationCount - 1; i++) {
                queue.rotate();
            }

            E eliminated = queue.dequeue();

            System.out.println("Eliminated: " + eliminated);
        }

        E winner = queue.dequeue();

        System.out.println("Winner: " + winner);

        return winner;
    }
}
